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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SimpleOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
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
	public ResponseEntity<UUID> createSpeciality(@RequestBody CreateSpecialityDto createSpecialityDto) {
		
		var specialityId = specialityService.createSpeciality(createSpecialityDto);
		
		return ResponseEntity.created(URI.create("/v1/speciality/" + specialityId.toString())).build();
		
	}
	
	@GetMapping("/{specialityId}")
	public ResponseEntity<SimpleOutputSpecialityDto> findSimpleOutputSpecialityById(@PathVariable("specialityId") String specialityId) {
		
		var speciality = specialityService.findSimpleOutputSpecialityById(specialityId);
		
		return ResponseEntity.ok(speciality);
		
	}
	
	@PutMapping("/{specialityId}")
	public ResponseEntity<Void> updateSpecialityById(@PathVariable("specialityId") String specialityId, @RequestBody UpdateSpecialityDto updateSpecialityDto) {
		
		specialityService.updateSpecialityById(specialityId, updateSpecialityDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/details/{specialityId}")
	public ResponseEntity<OldOutputDetailedSpecialityDto> findSpecialityDetailedById(@PathVariable("specialityId") String specialityId) {
		
		var output = specialityService.findSpecialityDetailedById(specialityId);
		
		return ResponseEntity.ok(output);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Speciality>> listSpecialities() {
		
		var specialities = specialityService.listSpecialities();
		
		return ResponseEntity.ok(specialities);
		
	}
	
	@DeleteMapping("/{specialityId}")
	public ResponseEntity<Void> deleteById(@PathVariable("specialityId") String specialityId) {

		specialityService.deleteSpecialityById(specialityId);
		
		return ResponseEntity.noContent().build();

	}

}
