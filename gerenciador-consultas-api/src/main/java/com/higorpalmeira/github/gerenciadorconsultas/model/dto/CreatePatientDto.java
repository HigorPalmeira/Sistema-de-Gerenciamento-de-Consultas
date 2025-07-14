package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record CreatePatientDto(
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String telephone,
		String email,
		CreateAddressDto address
) {

}
