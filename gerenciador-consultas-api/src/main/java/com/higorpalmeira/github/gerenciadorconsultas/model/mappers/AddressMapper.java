package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;

@Component
public class AddressMapper {

	public Address toEntity(CreateAddressDto addressDto) { 
		
		return new Address(
				addressDto.cep(),
				addressDto.street(),
				addressDto.complement(),
				addressDto.neighborhood(),
				addressDto.locality(),
				addressDto.uf(),
				Instant.now(),
				null
				);
		
	}
	
	public void updateEntityFromDto(Address address, UpdateAddressDto updateAddressDto) {
		
		
		
	}
	
}
