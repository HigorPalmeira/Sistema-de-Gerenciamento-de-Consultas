package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.time.LocalDateTime;

public record CreatePatientDto(
		String firstName,
		String lastName,
		String cpf,
		LocalDateTime birthdate,
		String gender,
		String status,
		String telephone,
		String email
) {

}
