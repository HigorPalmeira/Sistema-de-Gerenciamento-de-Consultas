package com.higorpalmeira.github.gerenciadorconsultas.model.dto.create;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateConsultationDto {
	
	private LocalDateTime dateTime;
	
	private String observations;
	
	private float value;
	
	private UUID doctorId;
	
	private UUID patientId;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public String getObservations() {
		return observations;
	}

	public void setObservations(String observations) {
		this.observations = observations;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public UUID getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(UUID doctorId) {
		this.doctorId = doctorId;
	}

	public UUID getPatientId() {
		return patientId;
	}

	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}

}
