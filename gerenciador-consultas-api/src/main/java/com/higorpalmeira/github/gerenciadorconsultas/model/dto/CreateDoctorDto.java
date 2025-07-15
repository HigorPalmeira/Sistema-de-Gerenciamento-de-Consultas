package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record CreateDoctorDto(
		String firstName,
		String lastName,
		String crm,
		String status,
		String telephone,
		String email,
		CreateSpecialityDto speciality
) {

}
