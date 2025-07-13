package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.PatientService;

@RestController
@RequestMapping("/v1/patient")
public class PatientController {
	
	private PatientService patientService;
	
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}
	
	@PostMapping
	public ResponseEntity<Patient> createPatient(@RequestBody CreatePatientDto createPatientDto) {
		
		var patientId = patientService.createPatient(createPatientDto);
		
		return ResponseEntity.created(URI.create("/v1/patient/" + patientId.toString())).build();
		
	}
	
	@GetMapping ("/{patientId}")
	public ResponseEntity<Patient> findPatientById(@PathVariable("patientId") String patientId) {
		
		var patient = patientService.findPatientById(patientId);
		
		if (patient.isPresent()) {
			
			return ResponseEntity.ok(patient.get());
			
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	@GetMapping
	public ResponseEntity<List<Patient>> listPatients() {
		
		var patients = patientService.listPatients();
		
		return ResponseEntity.ok(patients);
		
	}
	
	@DeleteMapping("/{patientId}")
	public ResponseEntity<Void> deleteById(@PathVariable("patientId") String patientId) {
		
		patientService.deletePatientById(patientId);
		
		return ResponseEntity.noContent().build();
		
	}

}
