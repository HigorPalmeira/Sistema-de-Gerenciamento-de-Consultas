package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldOutputSimplePatientDto(
		String id,
		String firstName,
		String cpf,
		String birthdate,
		String status,
		String telephone,
		String email
) {

}
