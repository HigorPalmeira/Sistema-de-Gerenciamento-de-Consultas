package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.DoctorMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.DoctorRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.SpecialityRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class DoctorService {
	
	private DoctorRepository doctorRepository;
	
	private SpecialityRepository specialityRepository;
	
	private DoctorMapper doctorMapper;
	
	public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, DoctorMapper doctorMapper) {
		this.doctorRepository = doctorRepository;
		this.specialityRepository = specialityRepository;
		this.doctorMapper = doctorMapper;
	}
	
	@Transactional
	public UUID createDoctor(CreateDoctorDto createDoctorDto) {
		
		if (!Validator.CRMValidation(createDoctorDto.crm())) {
			throw new InvalidDataException("Invalid CRM.");
		}
		
		if (!Validator.EmailValidation(createDoctorDto.email())) {
			throw new InvalidDataException("Invalid e-mail format.");
		}
		
		if (doctorRepository.existsByCrm(createDoctorDto.crm())) {
			throw new DataConflictException("CRM already registered in the system.");
		}
		
		if (doctorRepository.existsByEmail(createDoctorDto.email())) {
			throw new DataConflictException("E-mail already registered in the system.");
		}
		
		var specialityId = createDoctorDto.specialityId();
		var specialityEntity = specialityRepository
				.findById(UUID.fromString(specialityId))
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + specialityId));
		
		var doctor = doctorMapper.toEntity(createDoctorDto, specialityEntity);
		
		var doctorSaved = doctorRepository.save(doctor);
		
		return doctorSaved.getDoctorId();
	}
	
	@Transactional(readOnly = true)
	public Optional<Doctor> findDoctorById(String doctorId) {
		
		return doctorRepository.findById(UUID.fromString(doctorId));
		
	}

}
