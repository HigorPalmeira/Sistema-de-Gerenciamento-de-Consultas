package com.higorpalmeira.github.gerenciadorconsultas.model.dto.update;

import java.time.LocalDate;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;

public class UpdatePatientDto {

	private String firstName;
	
	private String lastName;
	
	private String cpf;
	
	private LocalDate birthdate;
	
	private GenderType gender;
	
	private StatusAccountType status;
	
	private String telephone;
	
	private String email;
	
	private UpdateAddressDto address;

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

	public UpdateAddressDto getAddress() {
		return address;
	}

	public void setAddress(UpdateAddressDto address) {
		this.address = address;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
