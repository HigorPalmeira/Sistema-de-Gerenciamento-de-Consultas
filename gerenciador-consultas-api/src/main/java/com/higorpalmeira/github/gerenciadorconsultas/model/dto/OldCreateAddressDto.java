package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

public record OldCreateAddressDto(
		String cep,
		String street,
		String complement,
		String neighborhood,
		String locality,
		String uf
) {

}
