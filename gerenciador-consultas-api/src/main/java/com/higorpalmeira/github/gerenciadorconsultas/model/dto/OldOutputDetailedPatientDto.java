package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.List;

public record OldOutputDetailedPatientDto(
		String patientId,
		String firstName,
		String lastName,
		String cpf,
		String birthdate,
		String gender,
		String status,
		String telephone,
		String email,
		OldOutputAddressDto address,
		List<OldOutputPatientConsultationDto> consultation
) {

}
