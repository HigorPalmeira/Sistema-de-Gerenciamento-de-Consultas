package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

}
