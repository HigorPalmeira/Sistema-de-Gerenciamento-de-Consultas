package com.higorpalmeira.github.gerenciadorconsultas.model.dto.update;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

public class UpdateDoctorDto {
	
	private String firstName;
	
	private String lastName;
	
	private String crm;
	
	private TipoStatusConta status;
	
	private String telephone;
	
	private String email;

	private UUID specialityId;

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

	public UUID getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(UUID specialityId) {
		this.specialityId = specialityId;
	}

}
