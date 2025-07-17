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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.PatientService;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
	
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createPatient(@RequestBody CreatePatientDto createPatientDto) {
		
		var patientId = patientService.createPatient(createPatientDto);
		
		return ResponseEntity.created(URI.create("/v1/patient/" + patientId.toString())).build();
		
	}
	
	@GetMapping ("/{patientId}")
	public ResponseEntity<SimpleOutputPatientDto> findSimplePatientById(@PathVariable("patientId") String patientId) {
		
		var patient = patientService.findSimplePatientById(patientId);
		
		return ResponseEntity.ok(patient);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SimpleOutputPatientDto>> listSimplePatients() {
		
		var patients = patientService.listSimplePatients();
		
		return ResponseEntity.ok(patients);
		
	}
	
	@PutMapping("/{patientId}")
	public ResponseEntity<Void> updatePatientById(@PathVariable("patientId") String patientId, @RequestBody UpdatePatientDto updatePatientDto) {
		
		patientService.updatePatientById(patientId, updatePatientDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<Void> deleteById(@PathVariable("patientId") String patientId) {
		
		patientService.deletePatientById(patientId);
		
		return ResponseEntity.noContent().build();
		
	}

}
