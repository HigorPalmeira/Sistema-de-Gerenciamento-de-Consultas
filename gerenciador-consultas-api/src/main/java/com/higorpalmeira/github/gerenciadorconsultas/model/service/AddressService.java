package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.AddressRepository;

@Service
public class AddressService {
	
	private AddressRepository addressRepository;
	
	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	public UUID createAddress(CreateAddressDto createAddressDto) {
		
		var address = new Address(
				createAddressDto.cep(),
				createAddressDto.street(),
				createAddressDto.complement(),
				createAddressDto.neighborhood(),
				createAddressDto.locality(),
				createAddressDto.uf(),
				Instant.now(),
				null
				);
		
		var addressSaved = addressRepository.save(address);
		
		return addressSaved.getId();
		
	}
	
	public Optional<Address> findAddressById(String addressId) {
		
		return addressRepository.findById(UUID.fromString(addressId));
		
	}

}
