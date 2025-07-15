package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
	private LocalDate birthdate;
	
	@Column(name = "gender")
	private GenderType gender;
	
	@Column(name = "status")
	private StatusAccountType status;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn (name = "address_id", referencedColumnName = "id")
	private Address address;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Patient() {
		
	}

	public Patient(String firstName, String lastName, String cpf, LocalDate birthdate, GenderType gender,
			StatusAccountType status, String telephone, String email, Address address, Instant creationTimestamp, Instant updateTimestamp) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cpf = cpf;
		this.birthdate = birthdate;
		this.gender = gender;
		this.status = status;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}



	public Patient(UUID patientId, String firstName, String lastName, String cpf, LocalDate birthdate,
			GenderType gender, StatusAccountType status, String telephone, String email, Address address, Instant creationTimestamp,
			Instant updateTimestamp) {
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
		this.address = address;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
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

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public StatusAccountType getStatus() {
		return status;
	}

	public void setStatus(StatusAccountType status) {
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
}
