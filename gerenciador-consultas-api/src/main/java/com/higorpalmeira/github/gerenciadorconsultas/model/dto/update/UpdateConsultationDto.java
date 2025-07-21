package com.higorpalmeira.github.gerenciadorconsultas.model.dto.update;

import java.time.LocalDateTime;
import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

public class UpdateConsultationDto {

	private LocalDateTime dateTime;
	
	private TipoStatusConsulta status;
	
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

	public UUID getDoctorId() {
		return doctorId;
	}

	public void setDoctor(UUID doctorId) {
		this.doctorId = doctorId;
	}

	public UUID getPatientId() {
		return patientId;
	}

	public void setPatient(UUID patientId) {
		this.patientId = patientId;
	}
	
}
