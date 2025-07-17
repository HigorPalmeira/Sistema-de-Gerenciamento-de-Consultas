package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldUpdatePatientDto(
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String status,
		String telephone,
		String email,
		OldUpdateAddressDto address
) {

}
