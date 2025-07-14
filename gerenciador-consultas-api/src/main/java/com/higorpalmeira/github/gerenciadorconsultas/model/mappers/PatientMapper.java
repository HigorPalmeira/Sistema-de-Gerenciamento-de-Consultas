package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

@Component
public class PatientMapper {
	
	public Patient toEntity(CreatePatientDto patientDto) {
		
		Address address = new Address(
				patientDto.address().cep(),
				patientDto.address().street(),
				patientDto.address().complement(),
				patientDto.address().neighborhood(),
				patientDto.address().locality(),
				patientDto.address().uf(),
				Instant.now(),
				null
				);
		
		return new Patient(
				patientDto.firstName(),
				patientDto.lastName(),
				patientDto.cpf(),
				LocalDate.parse(
						patientDto.birthdate(), 
						DateTimeFormatter.ISO_LOCAL_DATE),
				GenderType.fromType(patientDto.gender()),
				StatusType.ACTIVE,
				patientDto.telephone(),
				patientDto.email(),
				address,
				Instant.now(),
				null
				);
		
	}

}
