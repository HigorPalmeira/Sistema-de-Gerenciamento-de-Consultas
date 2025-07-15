package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.List;
import java.util.UUID;

public record OutputDetailedSpecialityDto(
		UUID id,
		String description,
		List<OutputSimpleDoctorDto> doctors
) {

}
