package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "sgc_doctor")
public class Doctor {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID doctorId;
	
	private String firstName;
	
	private String lastName;
	
	private String crm;
	
	private StatusType status;
	
	private String telephone;
	
	private String email;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "speciality_id")
	private Speciality speciality;

}
