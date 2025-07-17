package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldOutputAddressDto(
		String id,
		String cep,
		String street,
		String complement,
		String neighborhood,
		String locality,
		String uf
) {

}
