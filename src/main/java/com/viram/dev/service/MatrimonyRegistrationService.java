package com.viram.dev.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viram.dev.dto.DAOUser;
import com.viram.dev.repository.mat.AboutDetailsRepository;
import com.viram.dev.repository.mat.BasicDetailsRepository;
import com.viram.dev.repository.mat.MatrimonyRegistration;
import com.viram.dev.repository.mat.PersonalDetailsRepository;
import com.viram.dev.repository.mat.ProfessionalDetailsRepository;
import com.viram.dev.repository.mat.ReligionDetailsRepository;
import com.viram.dev.repository.mat.StatusDetailsRepository;

@Component
public class MatrimonyRegistrationService {

	@Autowired
	private JwtUserDetailsService userService;
	
	@Autowired
	private BasicDetailsRepository basicDetailsRepository;
	
	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;
	
	@Autowired
	private ReligionDetailsRepository religionDetailsRepository;
	
	@Autowired
	private StatusDetailsRepository statusDetailsRepository;
	
	@Autowired
	private ProfessionalDetailsRepository professionalDetailsRepository;
	
	@Autowired
	private AboutDetailsRepository aboutDetailsRepository;
	
	@Transactional
	public boolean matrimonyUserRegistration(MatrimonyRegistration matrimonyRegistration) {
		DAOUser user = registerUser(matrimonyRegistration);
		
		matrimonyRegistration.getBasicDetails().setUser(user);
		basicDetailsRepository.save(matrimonyRegistration.getBasicDetails());
		
		matrimonyRegistration.getPersonalDetails().setUser(user);
		personalDetailsRepository.save(matrimonyRegistration.getPersonalDetails());
		
		matrimonyRegistration.getReligionDetails().setUser(user);
		religionDetailsRepository.save(matrimonyRegistration.getReligionDetails());
		
		matrimonyRegistration.getStatusDetails().setUser(user);
		statusDetailsRepository.save(matrimonyRegistration.getStatusDetails());
		
		matrimonyRegistration.getProfessionalDetails().setUser(user);
		professionalDetailsRepository.save(matrimonyRegistration.getProfessionalDetails());
		
		matrimonyRegistration.getAboutDetails().setUser(user);
		aboutDetailsRepository.save(matrimonyRegistration.getAboutDetails());
		
		return true;
	}

	private DAOUser registerUser(MatrimonyRegistration matrimonyRegistration) {
		DAOUser user = new DAOUser();
		user.setFirstName(matrimonyRegistration.getBasicDetails().getName());
		user.setPhone(matrimonyRegistration.getBasicDetails().getPhone());
		user.setEmail(matrimonyRegistration.getPersonalDetails().getEmail());
		user.setUsername(matrimonyRegistration.getPersonalDetails().getEmail());
		user.setPassword(matrimonyRegistration.getPersonalDetails().getPassword());
		return userService.save(user);
	}

}
