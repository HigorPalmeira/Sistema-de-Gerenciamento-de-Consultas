package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldOutputSimpleConsultationDto(
		String id,
		String dateTime,
		String status,
		float value,
		String doctorName,
		String doctorCrm,
		String patientName,
		String patientCpf
) {

}
