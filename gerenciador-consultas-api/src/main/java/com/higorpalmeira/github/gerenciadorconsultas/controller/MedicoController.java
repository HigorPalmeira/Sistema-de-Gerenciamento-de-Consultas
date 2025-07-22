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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.MedicoService;

@RestController
@RequestMapping("/v1/medico")
public class MedicoController {
	
	private MedicoService medicoService;
	
	public MedicoController(MedicoService medicoService) {
		this.medicoService = medicoService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> criarMedico(@RequestBody CriarMedicoDto criarMedicoDto) {
		
		var doctorId = medicoService.criarMedico(criarMedicoDto);
		
		return ResponseEntity.created(URI.create("/v1/medico/" + doctorId.toString())).build();
		
	}
	
	@GetMapping("/{medicoId}")
	public ResponseEntity<SaidaSimplesMedicoDto> buscarSaidaSimplesMedicoPorId(@PathVariable("medicoId") String medicoId) {
		
		var medico = medicoService.buscarSaidaSimplesMedicoPorId(medicoId);
		
		return ResponseEntity.ok(medico);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listarTodosSaidaSimplesMedico() {
		
		var doctors = medicoService.listarTodosSaidaSimplesMedico();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping("/status/ativos")
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listarTodosSaidaSimplesMedicoAtivos() {
		
		var medicos = medicoService.listarTodosSaidaSimplesMedicoAtivos();
		
		return ResponseEntity.ok(medicos);
		
	}
	
	@GetMapping("/status/inativos")
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listarTodosSaidaSimplesMedicoInativos() {
		
		var medicos = medicoService.listarTodosSaidaSimplesMedicoInativos();
		
		return ResponseEntity.ok(medicos);
		
	}
	
	@GetMapping("/detalhes/{medicoId}")
	public ResponseEntity<SaidaDetalhadaMedicoDto> buscarSaidaDetalhadaMedicoPorId(@PathVariable("medicoId") String medicoId) {
		
		var medico = medicoService.buscarSaidaDetalhadaMedicoPorId(medicoId);
		
		return ResponseEntity.ok(medico);
		
	}
	
	@GetMapping("/detalhes")
	public ResponseEntity<List<SaidaDetalhadaMedicoDto>> listarTodosSaidaDetalhadaMedico() {
	
		var medicos = medicoService.listarTodosSaidaDetalhadaMedico();
		
		return ResponseEntity.ok(medicos);
		
	}
	
	@GetMapping("/detalhes/status/ativos")
	public ResponseEntity<List<SaidaDetalhadaMedicoDto>> listarTodosSaidaDetalhadaMedicoAtivos() {
		
		var medicos = medicoService.listarTodosSaidaDetalhadaMedicoAtivos();
		
		return ResponseEntity.ok(medicos);
		
	}
	
	@GetMapping("/detalhes/status/inativos")
	public ResponseEntity<List<SaidaDetalhadaMedicoDto>> listarTodosSaidaDetalhadaMedicoInativos() {
		
		var medicos = medicoService.listarTodosSaidaDetalhadaMedicoInativos();
		
		return ResponseEntity.ok(medicos);
		
	}
	
	@PutMapping("/{medicoId}")
	public ResponseEntity<Void> atualizarMedicoPorId(@PathVariable("medicoId") String medicoId, 
			@RequestBody AtualizarMedicoDto atualizarMedicoDto) {
		
		medicoService.atualizarMedicoPorId(medicoId, atualizarMedicoDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{medicoId}")
	public ResponseEntity<Void> deletarMedicoPorId(@PathVariable("medicoId") String medicoId) {
		
		medicoService.deletarMedicoPorId(medicoId);
		
		return ResponseEntity.noContent().build();
		
	}

}
