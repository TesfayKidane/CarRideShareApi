package edu.mum.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {
	
	@RequestMapping("")
	public String welcomeHome(){
		return "This is the ride share api";
	}
}
