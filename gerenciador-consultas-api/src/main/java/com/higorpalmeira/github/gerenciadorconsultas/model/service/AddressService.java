package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.OutputAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.AddressMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.AddressRepository;

@Service
public class AddressService {
	
	private AddressRepository addressRepository;
	
	private AddressMapper addressMapper;
	
	public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
		this.addressRepository = addressRepository;
		this.addressMapper = addressMapper;
	}
	
	@Transactional
	public UUID createAddress(CreateAddressDto createAddressDto) {
		
		var address = addressMapper.createToAddress(createAddressDto);
		
		var addressSaved = addressRepository.save(address);
		
		return addressSaved.getId();
		
	}
	
	@Transactional
	public OutputAddressDto findAddressById(String addressId) {
		
		var id = UUID.fromString(addressId);
		var addressEntity = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
		
		return addressMapper.addressToOutputAddressDto(addressEntity);
		
	}
	
	@Transactional
	public List<OutputAddressDto> listAddresses() {
		
		var addresses = addressRepository
				.findAll().stream()
				.map(address -> addressMapper.addressToOutputAddressDto(address))
				.toList();
		
		return addresses;
		
	}
	
	@Transactional
	public void updateAddressById(String addressId, UpdateAddressDto updateAddressDto) {
		
		var id = UUID.fromString(addressId);
		var addressEntity = addressRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Address not found with ID: " + id));
		
		addressMapper.updateAddressFromUpdateAddressDto(updateAddressDto, addressEntity);
		
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
