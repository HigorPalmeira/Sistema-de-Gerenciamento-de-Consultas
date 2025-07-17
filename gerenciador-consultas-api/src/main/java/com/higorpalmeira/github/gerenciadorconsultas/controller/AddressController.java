package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.OutputAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.AddressService;

@RestController
@RequestMapping("/v1/address")
public class AddressController {
	
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createAddress(@RequestBody CreateAddressDto createAddressDto) {
		
		var addressId = addressService.createAddress(createAddressDto);
		
		return ResponseEntity.created(URI.create("/v1/address/" + addressId.toString())).build();
		
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<OutputAddressDto> findAddressById(@PathVariable("addressId") String addressId) {
		
		var address = addressService.findAddressById(addressId);
		
		return ResponseEntity.ok(address);
	}
	
	@GetMapping
	public ResponseEntity<List<OutputAddressDto>> listAddresses() {
		
		var addresses = addressService.listAddresses();
		
		return ResponseEntity.ok(addresses);
		
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Void> updateAddressById(@PathVariable("addressId") String addressId, @RequestBody UpdateAddressDto updateAddressDto) {
		
		addressService.updateAddressById(addressId, updateAddressDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteById(@PathVariable("addressId") String addressId) {
		
		addressService.deleteAddressById(addressId);
		
		return ResponseEntity.noContent().build();
		
	}

}
