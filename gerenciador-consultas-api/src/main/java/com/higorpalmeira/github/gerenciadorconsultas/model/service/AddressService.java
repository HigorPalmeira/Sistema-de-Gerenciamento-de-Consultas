package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateAddressDto;
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
	
	public List<Address> listAddresses() {
		
		return addressRepository.findAll();
		
	}
	
	public void updateAddressById(String addressId, UpdateAddressDto updateAddressDto) {
		
		var id = UUID.fromString(addressId);
		var addressEntity = addressRepository.findById(id);
		
		if (addressEntity.isPresent()) {
			
			var address = addressEntity.get();
			
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
			
			addressRepository.save(address);
			
		}
		
	}

}
