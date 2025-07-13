package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdatePatientDto;
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
	
	public Optional<Patient> findPatientById(String patientId) {
		
		return patientRepository.findById(UUID.fromString(patientId));
		
	}
	
	public List<Patient> listPatients() {
		
		return patientRepository.findAll();
		
	}
	
	public void updatePatientById(String patientId, UpdatePatientDto updatePatientDto) {
		
		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository.findById(id);
		
		if (patientEntity.isPresent()) {
			
			var patient = patientEntity.get();
			
			if (updatePatientDto.firstName() != null) {
				patient.setFirstName(updatePatientDto.firstName());
			}
			
			if (updatePatientDto.lastName() != null) {
				patient.setLastName(updatePatientDto.lastName());
			}
			
			if (updatePatientDto.cpf() != null && Validator.CPFValidation(updatePatientDto.cpf())) {
				patient.setCpf(updatePatientDto.cpf());
			}
			
			if (updatePatientDto.birthdate() != null) {
				patient.setBirthdate(LocalDate.parse(updatePatientDto.birthdate(), DateTimeFormatter.ISO_LOCAL_DATE));
			}
			
			if (updatePatientDto.gender() != null) {
				patient.setGender(GenderType.fromType(updatePatientDto.gender()));
			}
			
			if (updatePatientDto.status() != null) {
				patient.setStatus(StatusType.fromType(updatePatientDto.status()));
			}
			
			if (updatePatientDto.telephone() != null) {
				patient.setTelephone(updatePatientDto.telephone());
			}
			
			if (updatePatientDto.email() != null && Validator.EmailValidation(updatePatientDto.email())) {
				patient.setEmail(updatePatientDto.email());
			}
			
			patientRepository.save(patient);
			
		}
		
	}
	
	public void deletePatientById(String patientId) {
		
		var id = UUID.fromString(patientId);
		var patientExists = patientRepository.existsById(id);
		
		if (patientExists) {
			patientRepository.deleteById(id);
		}
		 
	}

}
