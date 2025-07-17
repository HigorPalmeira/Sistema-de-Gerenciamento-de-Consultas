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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.ConsultationService;

@RestController
@RequestMapping("/v1/consultation")
public class ConsultationController {
	
	private ConsultationService consultationService;
	
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createConsultation(@RequestBody CreateConsultationDto createConsultationDto) {
		
		var consultationId = consultationService.createConsultation(createConsultationDto);
		
		return ResponseEntity.created(URI.create("/v1/consultation/" + consultationId.toString())).build();
		
	}
	
	@GetMapping("/{consultationId}")
	public ResponseEntity<SimpleOutputConsultationDto> findSimpleConsultationById(@PathVariable("consultationId") String consultationId) {
		
		var consultation = consultationService.findSimpleConsultationById(consultationId);
		
		return ResponseEntity.ok(consultation);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SimpleOutputConsultationDto>> listSimpleConsultationsActive() {
		
		var consultations = consultationService.listSimpleConsultationsActive();
		
		return ResponseEntity.ok(consultations);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SimpleOutputConsultationDto>> listSimpleConsultations() {
		
		var consultations = consultationService.listSimpleConsultations();
		
		return ResponseEntity.ok(consultations);
		
	}
	
	@GetMapping("/scheduled")
	public ResponseEntity<List<SimpleOutputConsultationDto>> listSimpleConsultationsScheduled() {
		
		var consultations = consultationService.listSimpleConsultationsScheduled();
		
		return ResponseEntity.ok(consultations);
		
	}
	
	@PutMapping("/{consultationId}")
	public ResponseEntity<Void> updateConsultationById(@PathVariable("consultationId") String consultationId, @RequestBody UpdateConsultationDto updateConsultationDto) {
		
		consultationService.updateConsultation(consultationId, updateConsultationDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{consultationId}")
	public ResponseEntity<Void> deleteConsultationById(@PathVariable("consultationId") String consultationId) {
		
		consultationService.deleteConsultationById(consultationId);
		
		return ResponseEntity.noContent().build();
		
	}

}
