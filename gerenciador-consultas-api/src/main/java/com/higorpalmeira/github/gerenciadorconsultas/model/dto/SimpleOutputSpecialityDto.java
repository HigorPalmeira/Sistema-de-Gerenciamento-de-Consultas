package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

public class SimpleOutputSpecialityDto {
	
	private UUID id;
	private String description;
	private int associateDoctors;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAssociateDoctors() {
		return associateDoctors;
	}
	public void setAssociateDoctors(int associateDoctors) {
		this.associateDoctors = associateDoctors;
	}

}
