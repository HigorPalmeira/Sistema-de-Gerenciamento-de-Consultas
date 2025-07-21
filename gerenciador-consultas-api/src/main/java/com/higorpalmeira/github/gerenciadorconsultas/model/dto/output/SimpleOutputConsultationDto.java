package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.time.LocalDateTime;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

public class SimpleOutputConsultationDto {
	
	private LocalDateTime dateTime;
	
	private TipoStatusConsulta status;
	
	private String observations;
	
	private float value;
	
	private SaidaSimplesMedicoDto doctor;
	
	private SaidaSimplesPacienteDto patient;

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public TipoStatusConsulta getStatus() {
		return status;
	}

	public void setStatus(TipoStatusConsulta status) {
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

	public SaidaSimplesMedicoDto getDoctor() {
		return doctor;
	}

	public void setDoctor(SaidaSimplesMedicoDto doctor) {
		this.doctor = doctor;
	}

	public SaidaSimplesPacienteDto getPatient() {
		return patient;
	}

	public void setPatient(SaidaSimplesPacienteDto patient) {
		this.patient = patient;
	}
	
}
