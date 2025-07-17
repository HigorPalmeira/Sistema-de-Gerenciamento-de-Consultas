package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;

@Component
public class OldAddressMapper {

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
		
		if (updateAddressDto.cep() != null) {
			address.setCep(updateAddressDto.cep());
		}
		
		if (updateAddressDto.street() != null) {
			address.setStreet(updateAddressDto.street());
		}
		
		if (updateAddressDto.complement() != null) {
			address.setComplement(updateAddressDto.complement());
		}
		
		if (updateAddressDto.neighborhood() != null) {
			address.setNeighborhood(updateAddressDto.neighborhood());
		}
		
		if (updateAddressDto.locality() != null) {
			address.setLocality(updateAddressDto.locality());
		}
		
		if (updateAddressDto.uf() != null) {
			address.setUf(updateAddressDto.uf());
		}
		
	}
	
	public OutputAddressDto toOutputAddressDto(Address address) {
		
		return new OutputAddressDto(
				address.getId().toString(),
				address.getCep(),
				address.getStreet(),
				address.getComplement(),
				address.getNeighborhood(),
				address.getLocality(),
				address.getUf()
				);
		
	}
	
}
