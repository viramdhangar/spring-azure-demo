package com.viram.dev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viram.dev.dto.Address;
import com.viram.dev.dto.DAOUser;
import com.viram.dev.dto.mat.BasicDetails;
import com.viram.dev.repository.AddressRepository;
import com.viram.dev.repository.UserRepository;
import com.viram.dev.repository.mat.AboutDetailsRepository;
import com.viram.dev.repository.mat.BasicDetailsRepository;
import com.viram.dev.repository.mat.MatImageRepository;
import com.viram.dev.repository.mat.MatrimonyRegistration;
import com.viram.dev.repository.mat.PersonalDetailsRepository;
import com.viram.dev.repository.mat.ProfessionalDetailsRepository;
import com.viram.dev.repository.mat.ReligionDetailsRepository;

@Component
public class MatrimonyRegistrationService {

	@Autowired
	private JwtUserDetailsService userService;
	
	@Autowired
	private MatImageRepository matImageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private BasicDetailsRepository basicDetailsRepository;
	
	@Autowired
	private PersonalDetailsRepository personalDetailsRepository;
	
	@Autowired
	private ReligionDetailsRepository religionDetailsRepository;
	
	@Autowired
	private ProfessionalDetailsRepository professionalDetailsRepository;
	
	@Autowired
	private AboutDetailsRepository aboutDetailsRepository;
	
	public List<MatrimonyRegistration> myMatProfile(Long userId) {
		List<MatrimonyRegistration> mr = new ArrayList<>();
		List<BasicDetails> profile = basicDetailsRepository.findAllByUserId(userId);
		mr = profile.stream()
				.map(m -> new MatrimonyRegistration(m, personalDetailsRepository.findByBasicDetail(m)
						, religionDetailsRepository.findByBasicDetail(m)
						, professionalDetailsRepository.findByBasicDetail(m)
						, aboutDetailsRepository.findByBasicDetail(m)
						, matImageRepository.findAllByBasicDetail(m)))
				.collect(Collectors.toList());
		return mr;
	}
	
	
	public List<BasicDetails> allProfiles(int offset, int limit){
		return basicDetailsRepository.findAllProfileByLimit(offset, limit);
	}

}
