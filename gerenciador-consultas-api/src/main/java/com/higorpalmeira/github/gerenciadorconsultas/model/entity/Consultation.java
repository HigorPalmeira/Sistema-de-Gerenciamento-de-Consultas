package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_consultation")
public class Consultation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID consultationId;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TipoStatusConsulta status;
	
	@Column(name = "observations")
	private String observations;
	
	@Column(name = "value")
	private float value;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Paciente patient;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Consultation() {
		
	}

	public Consultation(UUID consultationId, LocalDateTime dateTime, TipoStatusConsulta status, String observations,
			float value, Doctor doctor, Paciente patient, Instant creationTimestamp, Instant updateTimestamp) {
		this.consultationId = consultationId;
		this.dateTime = dateTime;
		this.status = status;
		this.observations = observations;
		this.value = value;
		this.doctor = doctor;
		this.patient = patient;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Consultation(LocalDateTime dateTime, TipoStatusConsulta status, String observations, float value,
			Doctor doctor, Paciente patient, Instant creationTimestamp, Instant updateTimestamp) {
		this.dateTime = dateTime;
		this.status = status;
		this.observations = observations;
		this.value = value;
		this.doctor = doctor;
		this.patient = patient;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(UUID consultationId) {
		this.consultationId = consultationId;
	}

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

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Paciente getPatient() {
		return patient;
	}

	public void setPatient(Paciente patient) {
		this.patient = patient;
	}

	public Instant getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Instant creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Instant getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Instant updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

}
