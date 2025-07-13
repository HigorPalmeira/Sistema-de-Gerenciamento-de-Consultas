package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record UpdatePatientDto(
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String status,
		String telephone,
		String email
) {

}
