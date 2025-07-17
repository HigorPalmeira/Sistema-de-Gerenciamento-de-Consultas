package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldOutputPatientConsultationDto(
		String id,
		String dateTime,
		String status,
		String observations,
		String value,
		OldOutputSimpleDoctorDto doctor
) {

}
