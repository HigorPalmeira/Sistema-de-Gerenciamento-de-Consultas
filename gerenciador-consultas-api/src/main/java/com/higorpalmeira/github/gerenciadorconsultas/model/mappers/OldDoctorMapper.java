package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldCreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputSimpleDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldUpdateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;

@Component
public class OldDoctorMapper {
	
	public Doctor toEntity(OldCreateDoctorDto createDoctorDto, Speciality speciality) {
		
		return new Doctor(
				createDoctorDto.firstName(),
				createDoctorDto.lastName(),
				createDoctorDto.crm(),
				StatusAccountType.ACTIVE,
				createDoctorDto.telephone(),
				createDoctorDto.email(),
				speciality,
				Instant.now(),
				null
				);
		
	}
	
	public void updateEntityFromDto(Doctor doctor, OldUpdateDoctorDto updateDoctorDto, Speciality speciality) {
		
		if (updateDoctorDto.firstName() != null) {
			doctor.setFirstName(updateDoctorDto.firstName());
		}
		
		if (updateDoctorDto.lastName() != null) {
			doctor.setLastName(updateDoctorDto.lastName());
		}
		
		if (updateDoctorDto.status() != null) {
			doctor.setStatus(StatusAccountType.fromType(updateDoctorDto.status()));
		}
		
		if (updateDoctorDto.telephone() != null) {
			doctor.setTelephone(updateDoctorDto.telephone());
		}
		
		doctor.setSpeciality(speciality);
		
	}
	
	public void deleteEntityFromStatus(Doctor doctor) {
		
		doctor.setStatus(StatusAccountType.INACTIVE);
		
	}
	
	public OldOutputSimpleDoctorDto toOutputSimpleDoctorDto(Doctor doctor) {
		
		return new OldOutputSimpleDoctorDto(
				doctor.getDoctorId(),
				doctor.getFirstName(),
				doctor.getCrm(),
				doctor.getTelephone(),
				doctor.getEmail()
				);
		
	}

}
