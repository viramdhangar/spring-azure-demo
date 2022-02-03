package com.docker.springdockerd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Welcome {

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome boot! application deployed to azure now!!";
	}
}
