package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;
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
		
		if (!Validator.CRMValidation(createDoctorDto.getCrm())) {
			throw new InvalidDataException("Invalid CRM.");
		}
		
		if (!Validator.EmailValidation(createDoctorDto.getEmail())) {
			throw new InvalidDataException("Invalid e-mail format.");
		}
		
		if (doctorRepository.existsByCrm(createDoctorDto.getCrm())) {
			throw new DataConflictException("CRM already registered in the system.");
		}
		
		if (doctorRepository.existsByEmail(createDoctorDto.getEmail())) {
			throw new DataConflictException("E-mail already registered in the system.");
		}
		
		var specialityId = createDoctorDto.getSpecialityId();
		var specialityEntity = specialityRepository
				.findById(specialityId)
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + specialityId));
		
		var doctor = doctorMapper.createToDoctor(createDoctorDto, specialityEntity);
		
		var doctorSaved = doctorRepository.save(doctor);
		
		return doctorSaved.getDoctorId();
	}
	
	@Transactional(readOnly = true)
	public SimpleOutputDoctorDto findSimpleDoctorById(String doctorId) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		return doctorMapper.doctorToSimpleOutputDoctorDto(doctorEntity);
		
	}
	
	@Transactional(readOnly = true)
	public OldOutputDetailedDoctorDto findDetailedDoctorById(String doctorId) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id)
				.map(doctor -> new OldOutputDetailedDoctorDto(
						doctor.getDoctorId(),
						doctor.getFirstName(),
						doctor.getLastName(),
						doctor.getCrm(),
						doctor.getStatus(),
						doctor.getTelephone(),
						doctor.getEmail(),
						doctor.getSpeciality().getDescription()
						)).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		return doctorEntity;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputDoctorDto> listDoctors() {
		
		var doctors = doctorRepository
				.findAll().stream()
				.map(doctor -> doctorMapper.doctorToSimpleOutputDoctorDto(doctor)
						).toList();
		
		return doctors;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputDoctorDto> listDoctorsActive() {
		
		var doctors = doctorRepository
				.findAllByStatus(StatusAccountType.ACTIVE).stream()
				.map(doctor -> doctorMapper.doctorToSimpleOutputDoctorDto(doctor)
						).toList();
		
		return doctors;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputDoctorDto> listDoctorsInactive() {
		
		var doctors = doctorRepository
				.findAllByStatus(StatusAccountType.INACTIVE).stream()
				.map(doctor -> doctorMapper.doctorToSimpleOutputDoctorDto(doctor)
						).toList();
		
		return doctors;
		
	}
	
	@Transactional
	public void updateDoctorById(String doctorId, UpdateDoctorDto updateDoctorDto) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		if (updateDoctorDto.getCrm() != null) {
			doctorRepository.findByCrm(updateDoctorDto.getCrm()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getDoctorId().equals(doctorEntity.getDoctorId())) {
					throw new DataConflictException("CRM is already in use by another doctor.");
				}
			});
			
			doctorEntity.setCrm(updateDoctorDto.getCrm());
		}
		
		if (updateDoctorDto.getEmail() != null) {
			doctorRepository.findByEmail(updateDoctorDto.getEmail()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getDoctorId().equals(doctorEntity.getDoctorId())) {
					throw new DataConflictException("Email is already in use by another doctor.");
				}
				
				doctorEntity.setEmail(updateDoctorDto.getEmail());
			});
		}
		
		var specialityId = updateDoctorDto.getSpecialityId();
		var specialityEntity = specialityRepository
				.findById(specialityId)
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + specialityId));
		
		doctorMapper.updateDoctorFromUpdateDoctorDto(updateDoctorDto, specialityEntity, doctorEntity);
		
	}
	
	@Transactional
	public void deleteDoctorById(String doctorId) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id);
		
		doctorEntity.ifPresent(doctor -> {
			doctor.setStatus(StatusAccountType.INACTIVE);
		});
		
	}

}
