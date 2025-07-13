package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.AddressService;

@RestController
@RequestMapping("/v1/address")
public class AddressController {
	
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody CreateAddressDto createAddressDto) {
		
		var addressId = addressService.createAddress(createAddressDto);
		
		return ResponseEntity.created(URI.create("/v1/address/" + addressId.toString())).build();
		
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<Address> findAddressById(@PathVariable("addressId") String addressId) {
		
		var address = addressService.findAddressById(addressId);
		
		if (address.isPresent()) {
			
			return ResponseEntity.ok(address.get());
			
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}

}
