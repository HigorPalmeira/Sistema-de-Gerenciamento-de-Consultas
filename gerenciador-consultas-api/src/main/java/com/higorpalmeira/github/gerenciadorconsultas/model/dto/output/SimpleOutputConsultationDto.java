package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.time.LocalDateTime;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusConsultationType;

public class SimpleOutputConsultationDto {
	
	private LocalDateTime dateTime;
	
	private StatusConsultationType status;
	
	private String observations;
	
	private float value;
	
	private SimpleOutputDoctorDto doctor;
	
	private SimpleOutputPatientDto patient;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public StatusConsultationType getStatus() {
		return status;
	}

	public void setStatus(StatusConsultationType status) {
		this.status = status;
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

	public SimpleOutputDoctorDto getDoctor() {
		return doctor;
	}

	public void setDoctor(SimpleOutputDoctorDto doctor) {
		this.doctor = doctor;
	}

	public SimpleOutputPatientDto getPatient() {
		return patient;
	}

	public void setPatient(SimpleOutputPatientDto patient) {
		this.patient = patient;
	}
	
}
