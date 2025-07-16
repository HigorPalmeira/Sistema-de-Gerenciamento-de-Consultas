package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record UpdateDoctorDto(
		String firstName,
		String lastName,
		String crm,
		String status,
		String telephone,
		String email,
		String specialityId
) {

}
