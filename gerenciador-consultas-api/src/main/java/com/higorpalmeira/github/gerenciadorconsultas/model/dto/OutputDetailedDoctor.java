package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;

public record OutputDetailedDoctor(
		UUID id,
		String firstName,
		String lastName,
		String crm,
		StatusType status,
		String telephone,
		String email,
		String speciality
) {

}
