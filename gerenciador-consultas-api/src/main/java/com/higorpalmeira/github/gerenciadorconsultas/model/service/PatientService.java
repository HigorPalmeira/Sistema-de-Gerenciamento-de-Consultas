package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.PatientMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PatientService {

	private PatientRepository patientRepository;

	private PatientMapper patientMapper;

	public PatientService(PatientRepository patientRepository, PatientMapper patientMapper) {
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
	}

	@Transactional
	public UUID createPatient(CreatePatientDto createPatientDto) {

		if (!Validator.CPFValidation(createPatientDto.getCpf())) {
			throw new InvalidDataException("Invalid CPF.");
		}

		if (!Validator.EmailValidation(createPatientDto.getEmail())) {
			throw new InvalidDataException("Invalid e-mail format.");
		}
		
		if (patientRepository.existsByCpf(createPatientDto.getCpf())) {
			throw new DataConflictException("CPF already registered in the system.");
		}
		
		if (patientRepository.existsByEmail(createPatientDto.getEmail())) {
			throw new DataConflictException("E-mail already registered in the system.");
		}

		var patient = patientMapper.createToPatient(createPatientDto);

		var patientSaved = patientRepository.save(patient);

		return patientSaved.getPatientId();

	}

	@Transactional(readOnly = true)
	public SimpleOutputPatientDto findSimplePatientById(String patientId) {
		
		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));

		return patientMapper.patientToSimpleOutputPatientDto(patientEntity);

	}
	
	@Transactional(readOnly = true)
	public OldOutputDetailedPatientDto findDetailedPatientById(String patientId) {
		
		return null;
		
	}

	@Transactional(readOnly = true)
	public List<SimpleOutputPatientDto> listSimplePatients() {
		
		var patients = patientRepository
				.findAll().stream()
				.map(patient -> patientMapper.patientToSimpleOutputPatientDto(patient)
						).toList();

		return patients;

	}

	@Transactional
	public void updatePatientById(String patientId, UpdatePatientDto updatePatientDto) {

		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
		
		if (updatePatientDto.getCpf() != null) {
			patientRepository.findByCpf(updatePatientDto.getCpf()).ifPresent(existingPatient -> {
				if (!existingPatient.getPatientId().equals(patientEntity.getPatientId())) {
					throw new DataConflictException("CPF is already in use by another patient.");
				}
			});
			
			patientEntity.setCpf(updatePatientDto.getCpf());
		}
		
		if (updatePatientDto.getEmail() != null) {
			patientRepository.findByEmail(updatePatientDto.getEmail()).ifPresent(existingPatient -> {
				if (!existingPatient.getPatientId().equals(patientEntity.getPatientId())) {
					throw new DataConflictException("Email is already in use by another patient.");
				}
			});
			
			patientEntity.setEmail(updatePatientDto.getEmail());
		}
		
		patientMapper.updatePatientFromUpdatePatientDto(updatePatientDto, patientEntity);

	}

	@Transactional
	public void deletePatientById(String patientId) {

		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id);

		patientEntity.ifPresent(patient -> {
			patient.setStatus(StatusAccountType.INACTIVE);
		});

	}

}
