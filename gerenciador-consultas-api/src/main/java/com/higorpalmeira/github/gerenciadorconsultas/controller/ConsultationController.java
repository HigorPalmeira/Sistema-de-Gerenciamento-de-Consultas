package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimpleConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consultation;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.ConsultationService;

@RestController
@RequestMapping("/v1/consultation")
public class ConsultationController {
	
	private ConsultationService consultationService;
	
	public ConsultationController(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}
	
	@PostMapping
	public ResponseEntity<Consultation> createConsultation(@RequestBody CreateConsultationDto createConsultationDto) {
		
		var consultationId = consultationService.createConsultation(createConsultationDto);
		
		return ResponseEntity.created(URI.create("/v1/consultation/" + consultationId.toString())).build();
		
	}
	
	@GetMapping("/{consultationId}")
	public ResponseEntity<OutputSimpleConsultationDto> findSimpleConsultationById(@PathVariable("consultationId") String consultationId) {
		
		var consultation = consultationService.findSimpleConsultationById(consultationId);
		
		return ResponseEntity.ok(consultation);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OutputSimpleConsultationDto>> listSimpleConsultations() {
		
		var consultations = consultationService.listSimpleConsultations();
		
		return ResponseEntity.ok(consultations);
		
	}

}
