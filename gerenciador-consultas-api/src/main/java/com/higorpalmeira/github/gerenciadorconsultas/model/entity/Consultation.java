package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusConsultationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_consultation")
public class Consultation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID consultationId;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "status")
	private StatusConsultationType status;
	
	@Column(name = "observations")
	private String observations;
	
	@Column(name = "value")
	private float value;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

}
