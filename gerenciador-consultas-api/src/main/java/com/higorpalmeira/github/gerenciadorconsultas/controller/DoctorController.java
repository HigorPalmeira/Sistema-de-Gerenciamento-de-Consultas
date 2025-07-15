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
	
	@GetMapping("/test/{doctorId}")
	public ResponseEntity<Doctor> findDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findDoctorById(doctorId);
		
		if (doctor.isPresent()) {
			
			return ResponseEntity.ok(doctor.get());
			
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	@GetMapping("/{doctorId}")
	public ResponseEntity<OutputSimpleDoctorDto> findSimpleDoctorById(@PathVariable("doctorId") String doctorId) {
		
		var doctor = doctorService.findSimpleDoctorById(doctorId);
		
		return ResponseEntity.ok(doctor);
		
	}
	
	@GetMapping
	public ResponseEntity<List<Doctor>> listDoctors() {
		
		var doctors = doctorService.listDoctors();
		
		return ResponseEntity.ok(doctors);
		
	}

}
