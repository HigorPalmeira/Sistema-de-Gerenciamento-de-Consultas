package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldUpdateConsultationDto(
		String dateTime,
		String status,
		String observations,
		float value,
		String doctorId,
		String patientId
) {

}
