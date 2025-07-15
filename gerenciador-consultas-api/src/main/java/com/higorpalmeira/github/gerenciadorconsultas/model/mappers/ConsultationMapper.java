package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consultation;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusConsultationType;

public class ConsultationMapper {
	
	public Consultation toEntity(CreateConsultationDto createConsultationDto, Doctor doctor, Patient patient) {
		
		return new Consultation(
				LocalDateTime.parse(createConsultationDto.dateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
				StatusConsultationType.fromType(createConsultationDto.status()),
				createConsultationDto.observations(),
				createConsultationDto.value(),
				doctor,
				patient,
				Instant.now(),
				null
				);
		
	}

}
