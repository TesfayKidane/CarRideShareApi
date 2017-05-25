package edu.mum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.mum.domain.UserCredentials;
import edu.mum.service.UserCredentialsService;

@RestController
@RequestMapping({"/userCredentials"})
 public class UserCredentialsController {

	@Autowired
	UserCredentialsService credentialsService;
	
 	
 	@RequestMapping("/add")
	public void addOne(@RequestBody UserCredentials userCredentials ) {

		credentialsService.save(userCredentials);
 
 		return  ;
	}
 
 	@RequestMapping("/{id}")
	public UserCredentials findOne(@PathVariable("id") String userName ) {

		UserCredentials validCredentials = credentialsService.findByUserName(userName);
 
 		return  validCredentials;
	}
 
 
}
