package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
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

	@Transactional(readOnly = false)
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

			if (updatePatientDto.address() != null) {

				var addressEntity = updatePatientDto.address();
				var address = patient.getAddress();

				if (addressEntity.cep() != null) {
					address.setCep(addressEntity.cep());
				}

				if (addressEntity.street() != null) {
					address.setStreet(addressEntity.street());
				}

				if (addressEntity.complement() != null) {
					address.setComplement(addressEntity.complement());
				}

				if (addressEntity.neighborhood() != null) {
					address.setNeighborhood(addressEntity.neighborhood());
				}

				if (addressEntity.locality() != null) {
					address.setLocality(addressEntity.locality());
				}

				if (addressEntity.uf() != null) {
					address.setUf(addressEntity.uf());
				}

				patient.setAddress(address);

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
