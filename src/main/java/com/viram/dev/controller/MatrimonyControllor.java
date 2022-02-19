package com.viram.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viram.dev.repository.mat.MatrimonyRegistration;
import com.viram.dev.service.MatrimonyRegistrationService;

@RestController
@CrossOrigin(origins = "*")
public class MatrimonyControllor {

	@Autowired
	private MatrimonyRegistrationService service;
	
	@PostMapping("/matrimony-registration")
	public boolean saveDetails(@Validated @RequestBody MatrimonyRegistration matrimonyRegistration) {
		return service.matrimonyUserRegistration(matrimonyRegistration);
	}
}
