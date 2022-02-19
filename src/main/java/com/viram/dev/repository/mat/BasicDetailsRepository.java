package com.viram.dev.repository.mat;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.viram.dev.dto.mat.BasicDetails;

@Repository
public interface BasicDetailsRepository extends CrudRepository<BasicDetails, Integer> {
		
}