package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;

public record OldOutputDetailedDoctorDto(
		UUID id,
		String firstName,
		String lastName,
		String crm,
		StatusAccountType status,
		String telephone,
		String email,
		String speciality
) {

}
