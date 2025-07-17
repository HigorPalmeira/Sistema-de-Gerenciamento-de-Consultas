package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;

public class SimpleOutputDoctorDto {
	
	private UUID doctorId;

	private String firstName;
	
	private String lastName;
	
	private String crm;
	
	private StatusAccountType status;
	
	private String telephone;
	
	private String email;

	private SimpleOutputSpecialityDto speciality;
	
	private int consultations;

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

	public SimpleOutputSpecialityDto getSpeciality() {
		return speciality;
	}

	public void setSpeciality(SimpleOutputSpecialityDto speciality) {
		this.speciality = speciality;
	}

	public int getConsultations() {
		return consultations;
	}

	public void setConsultations(int consultations) {
		this.consultations = consultations;
	}
	
}
