package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_patient")
public class Patient {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID patientId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "birthdate")
	private LocalDateTime birthdate;
	
	@Column(name = "gender")
	private GenderType gender;
	
	@Column(name = "status")
	private StatusType status;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;

	public Patient() {
		
	}

	public Patient(String firstName, String lastName, String cpf, LocalDateTime birthdate, GenderType gender,
			StatusType status, String telephone, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.gender = gender;
		this.status = status;
		this.telephone = telephone;
		this.email = email;
	}

	public Patient(UUID patientId, String firstName, String lastName, String cpf, LocalDateTime birthdate,
			GenderType gender, StatusType status, String telephone, String email) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.gender = gender;
		this.status = status;
		this.telephone = telephone;
		this.email = email;
	}

	public UUID getPatientId() {
		return patientId;
	}

	public void setPatientId(UUID patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDateTime getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDateTime birthdate) {
		this.birthdate = birthdate;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
