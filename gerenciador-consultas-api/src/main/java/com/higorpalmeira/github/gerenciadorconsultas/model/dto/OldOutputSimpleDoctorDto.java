package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

public record OldOutputSimpleDoctorDto(
		UUID id,
		String firstName,
		String crm,
		String telephone,
		String email
) {

}
