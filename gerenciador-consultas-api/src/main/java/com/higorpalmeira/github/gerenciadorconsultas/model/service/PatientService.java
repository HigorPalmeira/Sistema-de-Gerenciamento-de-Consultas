package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	
	public PatientService(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	public UUID createPatient(CreatePatientDto createPatientDto) {
		
		if (Validator.CPFValidation(createPatientDto.cpf()) 
				&& Validator.EmailValidation(createPatientDto.email())) {
		
			var patient = new Patient(
					createPatientDto.firstName(),
					createPatientDto.lastName(),
					createPatientDto.cpf(),
					LocalDate.parse(
							createPatientDto.birthdate(), 
							DateTimeFormatter.ISO_LOCAL_DATE),
					GenderType.fromType(createPatientDto.gender()),
					StatusType.ACTIVE,
					createPatientDto.telephone(),
					createPatientDto.email(),
					Instant.now(),
					null
					);
			
			var patientSaved = patientRepository.save(patient);
			
			return patientSaved.getPatientId();
		}
		
		return null;
		
	}

}
