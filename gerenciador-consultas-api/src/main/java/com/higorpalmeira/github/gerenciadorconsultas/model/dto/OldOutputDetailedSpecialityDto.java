package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.List;
import java.util.UUID;

public record OldOutputDetailedSpecialityDto(
		UUID id,
		String description,
		List<OldOutputSimpleDoctorDto> doctors
) {

}
