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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplePacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.PacienteService;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
	
	private PacienteService patientService;
	
	public PatientController(PacienteService patientService) {
		this.patientService = patientService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createPatient(@RequestBody CriarPacienteDto createPatientDto) {
		
		var patientId = patientService.criarPaciente(createPatientDto);
		
		return ResponseEntity.created(URI.create("/v1/patient/" + patientId.toString())).build();
		
	}
	
	@GetMapping ("/{patientId}")
	public ResponseEntity<SaidaSimplePacienteDto> findSimplePatientById(@PathVariable("patientId") String patientId) {
		
		var patient = patientService.buscarSaidaSimplesPacientePorId(patientId);
		
		return ResponseEntity.ok(patient);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplePacienteDto>> listSimplePatients() {
		
		var patients = patientService.listarTodosSaidaSimplesPaciente();
		
		return ResponseEntity.ok(patients);
		
	}
	
	@PutMapping("/{patientId}")
	public ResponseEntity<Void> updatePatientById(@PathVariable("patientId") String patientId, @RequestBody AtualizarPacienteDto updatePatientDto) {
		
		patientService.atualizarPacientePorId(patientId, updatePatientDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<Void> deleteById(@PathVariable("patientId") String patientId) {
		
		patientService.deletarPacientePorId(patientId);
		
		return ResponseEntity.noContent().build();
		
	}

}
