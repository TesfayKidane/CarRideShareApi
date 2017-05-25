package edu.mum.controller;


import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.mum.domain.Address;
import edu.mum.domain.Ride;
import edu.mum.domain.User;
import edu.mum.emailservice.EmailService;
import edu.mum.service.RideService;
import edu.mum.service.UserService;

@RestController
@RequestMapping("/rides")
public class RideController {
	
	@Autowired
	private RideService rideService;
    
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailService emailService; 
	
 	@RequestMapping({"","/all"})
	public List<Ride> list(Model model) {
		return rideService.findAll();

	}
	
 	@RequestMapping("/{id}")
	public Ride getrideById(@PathVariable("id") Long id) {

		return rideService.findOne(id);
	}
 	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Ride processAddNewrideForm(@RequestBody Ride rideToBeAdded) {
 
 		try {
 			User user = userService.findOne(1l);//Long.parseLong(rideToBeAdded.getRideOwnerId())); 		
 			rideToBeAdded.setRideOwner(user);
 			user.addCreatedRide(rideToBeAdded);
			rideService.save(rideToBeAdded);
		} catch (Exception up) {
	      System.out.println("Transaction Failed!!!");
	      up.printStackTrace();
		}		
	   	return null;
	}   
	
	@RequestMapping(value = "/requestJoin/{rideId}/{userId}",  method = RequestMethod.GET)
	public void acceptJoin(@PathVariable("rideId") Long rideId, @PathVariable("userId") Long userId){
		Ride ride = rideService.findOne(rideId);
		User user = userService.findOne(userId);
		User owner = ride.getRideOwner();
		String confirmationUrl = "http://localhost:8080/CarRideShareApi/rides/acceptJoin/" + ride.getId() + "/" + user.getId();
		try {
			emailService.sendEMail(owner, user, confirmationUrl, "Request to join Ride");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/acceptJoin/{rideId}/{userId}",  method = RequestMethod.GET)
	public String requestJoin(@PathVariable("rideId") Long rideId, @PathVariable("userId") Long userId){
		Ride ride = rideService.findOne(rideId);
		User user = userService.findOne(userId);
		int seats = ride.getRemainingSeats();
		ride.setRemainingSeats(--seats);
		ride.addUsersThatReservedRide(user);
		rideService.update(ride);
		
		return "Join request processed Successfully. Thanks for confirming.";
	}
	
	@RequestMapping(value = "/search/{source}/{destination}",  method = RequestMethod.GET)
	public List<Ride> search(@PathVariable("source") String source, @PathVariable("destination") String destination){
		return rideService.search(source, destination);
	}

	@RequestMapping(value = "/createdRides/{userId}",  method = RequestMethod.GET)
	public List<Ride> ridesCreatedByUser(@PathVariable("userId") Long userId){
		return rideService.ridesCreatedByUser(userId);
	}	

	@RequestMapping(value = "/joinedRides/{userId}",  method = RequestMethod.GET)
	public List<Ride> ridesJoinedByUser(@PathVariable("userId") Long userId){
		return rideService.ridesJoinedByUser(userId);
	}
}
