package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldCreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputSimpleDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldUpdateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.DoctorService;

@RestController
@RequestMapping("/v1/doctor")
public class DoctorController {
	
	private DoctorService doctorService;
	
	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@PostMapping
	public ResponseEntity<Doctor> createDoctor(@RequestBody OldCreateDoctorDto createDoctorDto) {
		
		var doctorId = doctorService.createDoctor(createDoctorDto);
		
		return ResponseEntity.created(URI.create("/v1/doctor/" + doctorId.toString())).build();
		
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<OldOutputSimpleDoctorDto> findSimpleDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findSimpleDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping("/details/{doctorId}")
	public ResponseEntity<OldOutputDetailedDoctorDto> findDetailedDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findDetailedDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<OldOutputSimpleDoctorDto>> listDoctors() {
		
		var doctors = doctorService.listDoctors();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OldOutputSimpleDoctorDto>> listDoctorsActive() {
		
		var doctors = doctorService.listDoctorsActive();
		
		return ResponseEntity.ok(doctors);
		
	}
	
	@PutMapping("/{doctorId}")
	public ResponseEntity<Void> updateDoctorById(@PathVariable("doctorId") String doctorId, @RequestBody OldUpdateDoctorDto updateDoctorDto) {
		
		doctorService.updateDoctorById(doctorId, updateDoctorDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{doctorId}")
	public ResponseEntity<Void> deleteDoctorById(@PathVariable("doctorId") String doctorId) {
		
		doctorService.deleteDoctorById(doctorId);
		
		return ResponseEntity.noContent().build();
		
	}

}
