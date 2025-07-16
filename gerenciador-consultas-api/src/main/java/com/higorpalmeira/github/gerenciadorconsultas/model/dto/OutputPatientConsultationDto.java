package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OutputPatientConsultationDto(
		String id,
		String dateTime,
		String status,
		String observations,
		String value,
		OutputSimpleDoctorDto doctor
) {

	public OutputPatientConsultationDto(String string, String string2, String type, String observations2, float value2,
			OutputSimpleDoctorDto outputSimpleDoctorDto) {
		// TODO Auto-generated constructor stub
	}

}
