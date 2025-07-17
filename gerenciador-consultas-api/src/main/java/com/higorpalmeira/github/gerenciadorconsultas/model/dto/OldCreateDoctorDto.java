package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldCreateDoctorDto(
		String firstName,
		String lastName,
		String crm,
		String telephone,
		String email,
		String specialityId
) {

}
