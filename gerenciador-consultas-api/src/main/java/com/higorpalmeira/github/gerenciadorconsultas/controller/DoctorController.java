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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.MedicoService;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
	
	private MedicoService doctorService;
	
	public DoctorController(MedicoService doctorService) {
		this.doctorService = doctorService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> createDoctor(@RequestBody CriarMedicoDto createDoctorDto) {
		
		var doctorId = doctorService.criarMedico(createDoctorDto);
		
		return ResponseEntity.created(URI.create("/v1/doctor/" + doctorId.toString())).build();
		
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<SaidaSimplesMedicoDto> findSimpleDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.buscarSaidaSimplesMedicoPorId(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping("/details/{doctorId}")
	public ResponseEntity<OldOutputDetailedDoctorDto> findDetailedDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findDetailedDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listDoctors() {
		
		var doctors = doctorService.listarTodosSaidaSimplesMedico();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listDoctorsActive() {
		
		var doctors = doctorService.listarTodosSaidaSimplesMedicoAtivos();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping("/inactive")
	public ResponseEntity<List<SaidaSimplesMedicoDto>> listDoctorInactive() {
		
		var doctors = doctorService.listarTodosSaidaSimplesMedicoInativos();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@PutMapping("/{doctorId}")
	public ResponseEntity<Void> updateDoctorById(@PathVariable("doctorId") String doctorId, @RequestBody AtualizarMedicoDto updateDoctorDto) {
		
		doctorService.atualizarMedicoPorId(doctorId, updateDoctorDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<Void> deleteDoctorById(@PathVariable("doctorId") String doctorId) {
		
		doctorService.deletarMedicoPorId(doctorId);
		
		return ResponseEntity.noContent().build();
		
	}

}
