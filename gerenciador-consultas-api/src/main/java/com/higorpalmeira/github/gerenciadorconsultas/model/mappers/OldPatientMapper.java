package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Component
public class OldPatientMapper {

	public Patient toEntity(CreatePatientDto patientDto) {

		Address address = new Address(patientDto.address().cep(), patientDto.address().street(),
				patientDto.address().complement(), patientDto.address().neighborhood(), patientDto.address().locality(),
				patientDto.address().uf(), Instant.now(), null);

		return new Patient(patientDto.firstName(), patientDto.lastName(), patientDto.cpf(),
				LocalDate.parse(patientDto.birthdate(), DateTimeFormatter.ISO_LOCAL_DATE),
				GenderType.fromType(patientDto.gender()), StatusAccountType.ACTIVE, patientDto.telephone(), patientDto.email(),
				address, Instant.now(), null);

	}

	public void updateEntityFromDto(Patient patient, UpdatePatientDto updatePatientDto) {

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
			patient.setStatus(StatusAccountType.fromType(updatePatientDto.status()));
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

	}
	
	public void deleteEntityFromStatus(Patient patient) {
		
		patient.setStatus(StatusAccountType.INACTIVE);
		
	}

}
