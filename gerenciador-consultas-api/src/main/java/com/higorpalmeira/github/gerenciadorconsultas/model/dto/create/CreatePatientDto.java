package com.higorpalmeira.github.gerenciadorconsultas.model.dto.create;

import java.time.LocalDate;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;

public class CreatePatientDto {
	
	private String firstName;
	
	private String lastName;
	
	private String cpf;
	
	private LocalDate birthdate;
	
	private GenderType gender;
	
	private String telephone;
	
	private String email;
	
	private CreateAddressDto address;
	
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
	public CreateAddressDto getAddress() {
		return address;
	}
	public void setAddress(CreateAddressDto address) {
		this.address = address;
	}
	public GenderType getGender() {
		return gender;
	}
	public void setGender(GenderType gender) {
		this.gender = gender;
	}
	
}
