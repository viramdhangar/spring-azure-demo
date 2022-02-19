package com.viram.dev.repository.mat;

import com.viram.dev.dto.mat.AboutDetails;
import com.viram.dev.dto.mat.BasicDetails;
import com.viram.dev.dto.mat.PersonalDetails;
import com.viram.dev.dto.mat.ProfessionalDetails;
import com.viram.dev.dto.mat.ReligionDetails;
import com.viram.dev.dto.mat.StatusDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatrimonyRegistration {

	private BasicDetails basicDetails;
	private PersonalDetails personalDetails;
	private ReligionDetails religionDetails;
	private StatusDetails statusDetails;
	private ProfessionalDetails professionalDetails;
	private AboutDetails aboutDetails;
}
