package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputDetailedPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputPatientConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimplePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldAddressMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldDoctorMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldPatientMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PatientService {

	private PatientRepository patientRepository;

	private OldPatientMapper patientMapper;
	
	private OldDoctorMapper doctorMapper;
	
	private OldAddressMapper addressMapper;

	public PatientService(PatientRepository patientRepository, OldPatientMapper patientMapper, OldDoctorMapper doctorMapper, OldAddressMapper addressMapper) {
		this.patientRepository = patientRepository;
		this.patientMapper = patientMapper;
		this.doctorMapper = doctorMapper;
		this.addressMapper = addressMapper;
	}

	@Transactional
	public UUID createPatient(CreatePatientDto createPatientDto) {

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
	public OutputSimplePatientDto findSimplePatientById(String patientId) {
		
		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.map(patient -> new OutputSimplePatientDto(
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
	public OutputDetailedPatientDto findDetailedPatientById(String patientId) {
		
		var id = UUID.fromString(patientId);
		var patientEntity = patientRepository
				.findById(id)
				.map(patient -> new OutputDetailedPatientDto(
						patient.getPatientId().toString(),
						patient.getFirstName(),
						patient.getLastName(),
						patient.getCpf(),
						patient.getBirthdate().toString(),
						patient.getGender().getType(),
						patient.getStatus().getType(),
						patient.getTelephone(),
						patient.getEmail(),
						addressMapper.toOutputAddressDto(patient.getAddress()),
						patient.getConsultations().stream()
							.map(consultation -> new OutputPatientConsultationDto(
									consultation.getConsultationId().toString(),
									consultation.getDateTime().toString(),
									consultation.getStatus().getType(),
									consultation.getObservations(),
									consultation.getValue(),
									doctorMapper.toOutputSimpleDoctorDto(consultation.getDoctor())
									)).toList()
						)).orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + id));
		
		return patientEntity;
		
	}

	@Transactional(readOnly = true)
	public List<OutputSimplePatientDto> listSimplePatients() {
		
		var patients = patientRepository
				.findAll().stream()
				.map(patient -> new OutputSimplePatientDto(
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
	public void updatePatientById(String patientId, UpdatePatientDto updatePatientDto) {

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
