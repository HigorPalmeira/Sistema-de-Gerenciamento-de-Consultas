package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldUpdateDoctorDto(
		String firstName,
		String lastName,
		String crm,
		String status,
		String telephone,
		String email,
		String specialityId
) {

}
