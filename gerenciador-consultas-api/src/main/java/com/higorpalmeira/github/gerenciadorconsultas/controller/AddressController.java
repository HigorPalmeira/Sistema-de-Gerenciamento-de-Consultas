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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.EnderecoService;

@RestController
@RequestMapping("/v1/address")
public class AddressController {
	
	private EnderecoService addressService;
	
	public AddressController(EnderecoService addressService) {
		this.addressService = addressService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createAddress(@RequestBody CriarEnderecoDto createAddressDto) {
		
		var addressId = addressService.criarEndereco(createAddressDto);
		
		return ResponseEntity.created(URI.create("/v1/address/" + addressId.toString())).build();
		
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity<SaidaEnderecoDto> findAddressById(@PathVariable("addressId") String addressId) {
		
		var address = addressService.buscarEnderecoPorId(addressId);
		
		return ResponseEntity.ok(address);
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaEnderecoDto>> listAddresses() {
		
		var addresses = addressService.listarTodosEnderecos();
		
		return ResponseEntity.ok(addresses);
		
	}
	
	@PutMapping("/{addressId}")
	public ResponseEntity<Void> updateAddressById(@PathVariable("addressId") String addressId, @RequestBody AtualizarEnderecoDto updateAddressDto) {
		
		addressService.atualizarEnderecoPorId(addressId, updateAddressDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteById(@PathVariable("addressId") String addressId) {
		
		addressService.deletarEnderecoPorId(addressId);
		
		return ResponseEntity.noContent().build();
		
	}

}
