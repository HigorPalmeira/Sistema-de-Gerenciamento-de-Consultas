package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

@Component
public class DoctorMapper {
	
	public Doctor toEntity(CreateDoctorDto createDoctorDto, Speciality speciality) {
		
		return new Doctor(
				createDoctorDto.firstName(),
				createDoctorDto.lastName(),
				createDoctorDto.crm(),
				StatusType.ACTIVE,
				createDoctorDto.telephone(),
				createDoctorDto.email(),
				speciality,
				Instant.now(),
				null
				);
		
	}

}
