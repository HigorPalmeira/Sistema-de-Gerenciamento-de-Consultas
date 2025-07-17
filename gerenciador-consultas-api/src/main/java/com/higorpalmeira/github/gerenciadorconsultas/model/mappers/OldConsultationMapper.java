package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consultation;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusConsultationType;

@Component
public class OldConsultationMapper {
	
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
	
	public void updateEntityFromDto(Consultation consultation, UpdateConsultationDto updateConsultationDto, Patient patient, Doctor doctor) {
		
		if (updateConsultationDto.dateTime() != null) {
			consultation.setDateTime(LocalDateTime.parse(updateConsultationDto.dateTime(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
		}
		
		if (updateConsultationDto.status() != null) {
			consultation.setStatus(StatusConsultationType.fromType(updateConsultationDto.status()));
		}
		
		if (updateConsultationDto.observations() != null) {
			consultation.setObservations(updateConsultationDto.observations());
		}
		
		if (!Float.isNaN(updateConsultationDto.value())) {
			consultation.setValue(updateConsultationDto.value());
		}
		
		if (doctor != null) {
			
			consultation.setDoctor(doctor);
			
		}
		
		if (patient != null) {
			
			consultation.setPatient(patient);
			
		}
		
	}

}
