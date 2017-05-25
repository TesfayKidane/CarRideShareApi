package edu.mum.service;

import java.util.List;

import edu.mum.domain.Ride;
import edu.mum.domain.User;
 
public interface RideService {

	public void save(Ride ride);
	public Ride update(Ride ride);
	public List<Ride> findAll();
	public Ride findOne(Long id);
	public List<Ride> search(String source, String destination);
	public List<Ride> ridesCreatedByUser(Long userId);
	public List<Ride> ridesJoinedByUser(Long userId);

}
