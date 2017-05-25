package edu.mum.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import edu.mum.dao.RideDao;
import edu.mum.domain.Ride;
import edu.mum.domain.User;

@SuppressWarnings("unchecked")
@Repository
public class RideDaoImpl extends GenericDaoImpl<Ride> implements RideDao {

	public RideDaoImpl() {
		super.setDaoType(Ride.class);
	}

	@Override
	public List<Ride> search(String source, String destination) {
		Query query = entityManager
				.createQuery("select r from Ride r where r.rideOriginCity =:origin and r.rideDestinationCity =:destination");
		query.setParameter("origin", source);
		query.setParameter("destination", destination);

		return (List<Ride>) query.getResultList();
	}

	@Override
	public List<Ride> ridesCreatedByUser(Long userId) {
		Query query = entityManager
				.createQuery("select r from Ride r where r.rideOwnerId =:userId");
		query.setParameter("userId", userId.toString());

		return (List<Ride>) query.getResultList();
	}

	@Override
	public List<Ride> ridesJoinedByUser(Long userId) {
		Query query = entityManager
				.createQuery("select r from Ride r, User u where u.id = :userId and u member of r.usersThatReservedRide");
		query.setParameter("userId", userId);
		
		return (List<Ride>) query.getResultList();
	}

}