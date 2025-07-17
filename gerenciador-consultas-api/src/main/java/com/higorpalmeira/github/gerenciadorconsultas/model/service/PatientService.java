package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldCreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputSimplePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldUpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldPatientMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PatientService {

	private PatientRepository patientRepository;

	private OldPatientMapper patientMapper;

	public PatientService(PatientRepository patientRepository, OldPatientMapper patientMapper) {
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}

	@Transactional
	public UUID createPatient(OldCreatePatientDto createPatientDto) {

		if (!Validator.CPFValidation(createPatientDto.cpf())) {
			throw new InvalidDataException("Invalid CPF.");
		}

		if (!Validator.EmailValidation(createPatientDto.email())) {
			throw new InvalidDataException("Invalid e-mail format.");
		}
		
		if (patientRepository.existsByCpf(createPatientDto.cpf())) {
			throw new DataConflictException("CPF already registered in the system.");
		}
		
		if (patientRepository.existsByEmail(createPatientDto.email())) {
			throw new DataConflictException("E-mail already registered in the system.");
		}

		var patient = patientMapper.toEntity(createPatientDto);

		var patientSaved = patientRepository.save(patient);

		return patientSaved.getPatientId();

	}

	@Transactional(readOnly = true)
	public OldOutputSimplePatientDto findSimplePatientById(String patientId) {
		
		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.map(patient -> new OldOutputSimplePatientDto(
						patient.getPatientId().toString(),
						patient.getFirstName(),
						patient.getCpf(),
						patient.getBirthdate().toString(),
						patient.getStatus().getType(),
						patient.getTelephone(),
						patient.getEmail()
				)).orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));

		return patientEntity;

	}
	
	@Transactional(readOnly = true)
	public OldOutputDetailedPatientDto findDetailedPatientById(String patientId) {
		
		return null;
		
	}

	@Transactional(readOnly = true)
	public List<OldOutputSimplePatientDto> listSimplePatients() {
		
		var patients = patientRepository
				.findAll().stream()
				.map(patient -> new OldOutputSimplePatientDto(
						patient.getPatientId().toString(),
						patient.getFirstName(),
						patient.getCpf(),
						patient.getBirthdate().toString(),
						patient.getStatus().getType(),
						patient.getTelephone(),
						patient.getEmail()
						)).toList();

		return patients;

	}

	@Transactional
	public void updatePatientById(String patientId, OldUpdatePatientDto updatePatientDto) {

		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
		
		if (updatePatientDto.cpf() != null) {
			patientRepository.findByCpf(updatePatientDto.cpf()).ifPresent(existingPatient -> {
				if (!existingPatient.getPatientId().equals(patientEntity.getPatientId())) {
					throw new DataConflictException("CPF is already in use by another patient.");
				}
			});
			
			patientEntity.setCpf(updatePatientDto.cpf());
		}
		
		if (updatePatientDto.email() != null) {
			patientRepository.findByEmail(updatePatientDto.email()).ifPresent(existingPatient -> {
				if (!existingPatient.getPatientId().equals(patientEntity.getPatientId())) {
					throw new DataConflictException("Email is already in use by another patient.");
				}
			});
			
			patientEntity.setEmail(updatePatientDto.email());
		}
		
		patientMapper.updateEntityFromDto(patientEntity, updatePatientDto);

	}

	@Transactional
	public void deletePatientById(String patientId) {

		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id);

		if (patientEntity.isPresent()) {
			
			patientMapper.deleteEntityFromStatus(patientEntity.get());
			
		}

	}

}
