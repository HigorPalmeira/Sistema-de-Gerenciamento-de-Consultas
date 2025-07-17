package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldAddressMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.AddressRepository;

@Service
public class AddressService {
	
	private AddressRepository addressRepository;
	
	private OldAddressMapper addressMapper;
	
	public AddressService(AddressRepository addressRepository, OldAddressMapper addressMapper) {
		this.addressRepository = addressRepository;
		this.addressMapper = addressMapper;
	}
	
	@Transactional
	public UUID createAddress(CreateAddressDto createAddressDto) {
		
		var address = addressMapper.toEntity(createAddressDto);
		
		var addressSaved = addressRepository.save(address);
		
		return addressSaved.getId();
		
	}
	
	@Transactional
	public Optional<Address> findAddressById(String addressId) {
		
		return addressRepository.findById(UUID.fromString(addressId));
		
	}
	
	@Transactional
	public List<Address> listAddresses() {
		
		return addressRepository.findAll();
		
	}
	
	@Transactional
	public void updateAddressById(String addressId, UpdateAddressDto updateAddressDto) {
		
		var id = UUID.fromString(addressId);
		var addressEntity = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
		
		addressMapper.updateEntityFromDto(addressEntity, updateAddressDto);
		
	}
	
	@Transactional
	public void deleteAddressById(String addressId) {
		
		var id = UUID.fromString(addressId);
		var addressExists = addressRepository.existsById(id);
		
		if (addressExists) {
			addressRepository.deleteById(id);
		}
		
	}

}
