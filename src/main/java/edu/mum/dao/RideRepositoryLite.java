package edu.mum.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.mum.domain.Ride;

 

public interface RideRepositoryLite {

	List <Ride> getAllItems();
	
	Ride getItemById(String key);
	
	List<Ride> getItemsByCategory(String category);

	Set<Ride> getItemsByFilter(Map<String, List<String>> filterParams);
	
	void addItem(Ride product);		
}
