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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimpleDoctorDto;
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
	public ResponseEntity<Doctor> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {
		
		var doctorId = doctorService.createDoctor(createDoctorDto);
		
		return ResponseEntity.created(URI.create("/v1/doctor/" + doctorId.toString())).build();
		
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<OutputSimpleDoctorDto> findSimpleDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findSimpleDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping("/details/{doctorId}")
	public ResponseEntity<OutputDetailedDoctorDto> findDetailedDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findDetailedDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping
	public ResponseEntity<List<OutputSimpleDoctorDto>> listDoctors() {
		
		var doctors = doctorService.listDoctors();
		
		return ResponseEntity.ok(doctors);
		
	}

}
