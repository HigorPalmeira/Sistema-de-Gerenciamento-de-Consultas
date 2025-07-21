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
@RequestMapping("/v1/endereco")
public class EnderecoController {
	
	private EnderecoService enderecoService;
	
	public EnderecoController(EnderecoService enderecoService) {
		this.enderecoService = enderecoService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> criarEndereco(@RequestBody CriarEnderecoDto criarEnderecoDto) {
		
		var enderecoId = enderecoService.criarEndereco(criarEnderecoDto);
		
		return ResponseEntity.created(URI.create("/v1/endereco/" + enderecoId.toString())).build();
		
	}
	
	@GetMapping("/{enderecoId}")
	public ResponseEntity<SaidaEnderecoDto> buscarEnderecoPorId(@PathVariable("enderecoId") String enderecoId) {
		
		var endereco = enderecoService.buscarEnderecoPorId(enderecoId);
		
		return ResponseEntity.ok(endereco);
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaEnderecoDto>> listarTodosEnderecos() {
		
		var enderecos = enderecoService.listarTodosEnderecos();
		
		return ResponseEntity.ok(enderecos);
		
	}
	
	@PutMapping("/{enderecoId}")
	public ResponseEntity<Void> atualizarEnderecoPorId(@PathVariable("enderecoId") String enderecoId, 
			@RequestBody AtualizarEnderecoDto atualizarEnderecoDto) {
		
		enderecoService.atualizarEnderecoPorId(enderecoId, atualizarEnderecoDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{enderecoId}")
	public ResponseEntity<Void> deletarEnderecoPorId(@PathVariable("enderecoId") String enderecoId) {
		
		enderecoService.deletarEnderecoPorId(enderecoId);
		
		return ResponseEntity.noContent().build();
		
	}

}
