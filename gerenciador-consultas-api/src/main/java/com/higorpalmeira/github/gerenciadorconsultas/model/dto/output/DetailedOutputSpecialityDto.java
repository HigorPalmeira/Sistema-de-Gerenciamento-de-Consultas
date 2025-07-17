package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.util.List;
import java.util.UUID;

public class DetailedOutputSpecialityDto {

	private UUID id;
	
	private String description;
	
	private List<SimpleOutputDoctorDto> doctors;

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

	public List<SimpleOutputDoctorDto> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<SimpleOutputDoctorDto> doctors) {
		this.doctors = doctors;
	}
	
}
