package edu.mum.dao;

import java.util.List;

import edu.mum.domain.Ride;
import edu.mum.domain.User;

public interface RideDao extends GenericDao<Ride> {

	List<Ride> search(String source, String destination);

	List<Ride> ridesCreatedByUser(Long userId);

	List<Ride> ridesJoinedByUser(Long userId);
      
}
