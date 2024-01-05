package com.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	
	
	@GetMapping({"/", "/home", "/welcome"})
	public String welcome() {
		System.out.println("WelcomeRestController - welcome");
		return "Welcome";
	}

}
