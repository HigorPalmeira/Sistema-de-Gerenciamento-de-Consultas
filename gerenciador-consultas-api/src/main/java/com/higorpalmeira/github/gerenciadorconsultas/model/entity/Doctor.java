package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "sgc_doctor")
public class Doctor {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID doctorId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "crm")
	private String crm;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TipoStatusConta status;
	
	@Column(name = "telephone")
	private String telephone;
	
	@Column(name = "email")
	private String email;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "speciality_id")
	private Especialidade speciality;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Consultation> consultations = new ArrayList<>();
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Doctor() {
	
	}

	public Doctor(UUID doctorId, String firstName, String lastName, String crm, TipoStatusConta status, String telephone,
			String email, Especialidade speciality, Instant creationTimestamp, Instant updateTimestamp) {
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.crm = crm;
		this.status = status;
		this.telephone = telephone;
		this.email = email;
		this.speciality = speciality;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Doctor(String firstName, String lastName, String crm, TipoStatusConta status, String telephone, String email,
			Especialidade speciality, Instant creationTimestamp, Instant updateTimestamp) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.crm = crm;
		this.status = status;
		this.telephone = telephone;
		this.email = email;
		this.speciality = speciality;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(UUID doctorId) {
		this.doctorId = doctorId;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public TipoStatusConta getStatus() {
		return status;
	}

	public void setStatus(TipoStatusConta status) {
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

	public Especialidade getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Especialidade speciality) {
		this.speciality = speciality;
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

	public List<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}

}
