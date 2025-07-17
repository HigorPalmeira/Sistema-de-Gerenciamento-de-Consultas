package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

public record OldOutputSimpleSpeciality(
		UUID id,
		String description,
		int associateDoctors
) {

}
