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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.DetailedOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.EspecialidadeService;

@RestController
@RequestMapping ("/v1/speciality")
public class SpecialityController {
	
	private EspecialidadeService specialityService;
	
	public SpecialityController(EspecialidadeService specialityService) {
		this.specialityService = specialityService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createSpeciality(@RequestBody CriarEspecialidadeDto createSpecialityDto) {
		
		var specialityId = specialityService.criarEspecialidade(createSpecialityDto);
		
		return ResponseEntity.created(URI.create("/v1/speciality/" + specialityId.toString())).build();
		
	}
	
	@GetMapping("/{specialityId}")
	public ResponseEntity<SimpleOutputSpecialityDto> findSimpleOutputSpecialityById(@PathVariable("specialityId") String specialityId) {
		
		var speciality = specialityService.buscarSaidaSimplesEspecialidadePorId(specialityId);
		
		return ResponseEntity.ok(speciality);
		
	}
	
	
	@GetMapping("/details/{specialityId}")
	public ResponseEntity<DetailedOutputSpecialityDto> findSpecialityDetailedById(@PathVariable("specialityId") String specialityId) {
		
		var output = specialityService.buscarSaidaDetalhadaEspecialidadePorId(specialityId);
		
		return ResponseEntity.ok(output);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SimpleOutputSpecialityDto>> listSimpleAllSpecialities() {
		
		var specialities = specialityService.listarTodasSaidaSimplesEspecialidade();
		
		return ResponseEntity.ok(specialities);
		
	}
	
	@GetMapping("/details")
	public ResponseEntity<List<DetailedOutputSpecialityDto>> listDetailedAllSpecialities() {
		
		var specialities = specialityService.listarTodasSaidaDetalhadaEspecialidade();
		
		return ResponseEntity.ok(specialities);
		
	}
	
	@PutMapping("/{specialityId}")
	public ResponseEntity<Void> updateSpecialityById(@PathVariable("specialityId") String specialityId, @RequestBody UpdateSpecialityDto updateSpecialityDto) {
		
		specialityService.atualizarEspecialidadePorId(specialityId, updateSpecialityDto);
		
		return ResponseEntity.noContent().build();
		
	}

	@DeleteMapping("/{specialityId}")
	public ResponseEntity<Void> deleteById(@PathVariable("specialityId") String specialityId) {

		specialityService.deletarEspecialidadePorId(specialityId);
		
		return ResponseEntity.noContent().build();

	}

}
