package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

@Component
public class DoctorMapper {
	
	public Doctor toEntity(CreateDoctorDto createDoctorDto) {
		
		var speciality = new Speciality(
				createDoctorDto.speciality().description(),
				Instant.now(),
				null
				);
		
		return new Doctor(
				createDoctorDto.firstName(),
				createDoctorDto.lastName(),
				createDoctorDto.crm(),
				StatusType.fromType(createDoctorDto.status()),
				createDoctorDto.telephone(),
				createDoctorDto.email(),
				speciality,
				Instant.now(),
				null
				);
		
	}

}
