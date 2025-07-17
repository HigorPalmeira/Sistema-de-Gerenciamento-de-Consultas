package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldCreatePatientDto(
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String telephone,
		String email,
		OldCreateAddressDto address
) {

}
