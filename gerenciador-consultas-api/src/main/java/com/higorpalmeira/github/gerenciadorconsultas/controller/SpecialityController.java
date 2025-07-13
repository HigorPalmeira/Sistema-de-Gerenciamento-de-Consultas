package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.SpecialityService;

@RestController
@RequestMapping ("/v1/speciality")
public class SpecialityController {
	
	private SpecialityService specialityService;
	
	public SpecialityController(SpecialityService specialityService) {
		this.specialityService = specialityService;
	}
	
	@PostMapping
	public ResponseEntity<Speciality> createSpeciality(@RequestBody CreateSpecialityDto createSpecialityDto) {
		
		var specialityId = specialityService.createSpeciality(createSpecialityDto);
		
		return ResponseEntity.created(URI.create("/v1/users/" + specialityId.toString())).build();
		
	}

}
