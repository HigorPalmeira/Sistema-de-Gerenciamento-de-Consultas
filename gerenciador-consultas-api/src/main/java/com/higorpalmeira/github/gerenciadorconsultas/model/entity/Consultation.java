package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_consultation")
public class Consultation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID consultationId;
	
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	
	@Column(name = "observations")
	private String observations;
	
	@Column(name = "value")
	private float value;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

}
