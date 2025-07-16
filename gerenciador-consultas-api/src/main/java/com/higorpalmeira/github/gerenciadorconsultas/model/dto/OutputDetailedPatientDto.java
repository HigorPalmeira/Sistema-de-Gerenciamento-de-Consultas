package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.List;

public record OutputDetailedPatientDto(
		String patientId,
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String status,
		String telephone,
		String email,
		OutputAddressDto address,
		List<OutputPatientConsultationDto> consultation
) {

}
