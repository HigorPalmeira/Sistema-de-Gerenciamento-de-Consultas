package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldCreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputSimpleDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldUpdateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusAccountType;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.OldDoctorMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.DoctorRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.SpecialityRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class DoctorService {
	
	private DoctorRepository doctorRepository;
	
	private SpecialityRepository specialityRepository;
	
	private OldDoctorMapper doctorMapper;
	
	public DoctorService(DoctorRepository doctorRepository, SpecialityRepository specialityRepository, OldDoctorMapper doctorMapper) {
		this.doctorRepository = doctorRepository;
		this.specialityRepository = specialityRepository;
		this.doctorMapper = doctorMapper;
	}
	
	@Transactional
	public UUID createDoctor(OldCreateDoctorDto createDoctorDto) {
		
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
	public OldOutputSimpleDoctorDto findSimpleDoctorById(String doctorId) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id)
				.map(doctor -> new OldOutputSimpleDoctorDto(
						doctor.getDoctorId(),
						doctor.getFirstName(),
						doctor.getCrm(),
						doctor.getTelephone(),
						doctor.getEmail()
						)).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		return doctorEntity;
		
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
	public List<OldOutputSimpleDoctorDto> listDoctors() {
		
		var doctors = doctorRepository
				.findAll().stream()
				.map(doctor -> new OldOutputSimpleDoctorDto(
						doctor.getDoctorId(),
						doctor.getFirstName(),
						doctor.getCrm(),
						doctor.getTelephone(),
						doctor.getEmail()
						)).toList();
		
		return doctors;
		
	}
	
	@Transactional(readOnly = true)
	public List<OldOutputSimpleDoctorDto> listDoctorsActive() {
		
		var doctors = doctorRepository
				.findAllByStatus(StatusAccountType.ACTIVE).stream()
				.map(doctor -> new OldOutputSimpleDoctorDto(
						doctor.getDoctorId(),
						doctor.getFirstName(),
						doctor.getCrm(),
						doctor.getTelephone(),
						doctor.getEmail()
						)).toList();
		
		return doctors;
		
	}
	
	@Transactional(readOnly = true)
	public List<Doctor> listDoctorsF() {
		
		return doctorRepository.findAll();
		
	}
	
	@Transactional
	public void updateDoctorById(String doctorId, OldUpdateDoctorDto updateDoctorDto) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
		
		if (updateDoctorDto.crm() != null) {
			doctorRepository.findByCrm(updateDoctorDto.crm()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getDoctorId().equals(doctorEntity.getDoctorId())) {
					throw new DataConflictException("CRM is already in use by another doctor.");
				}
			});
			
			doctorEntity.setCrm(updateDoctorDto.crm());
		}
		
		if (updateDoctorDto.email() != null) {
			doctorRepository.findByEmail(updateDoctorDto.email()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getDoctorId().equals(doctorEntity.getDoctorId())) {
					throw new DataConflictException("Email is already in use by another doctor.");
				}
				
				doctorEntity.setEmail(updateDoctorDto.email());
			});
		}
		
		var specialityId = updateDoctorDto.specialityId();
		var specialityEntity = specialityRepository
				.findById(UUID.fromString(specialityId))
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + specialityId));
		
		doctorMapper.updateEntityFromDto(doctorEntity, updateDoctorDto, specialityEntity);
		
	}
	
	@Transactional
	public void deleteDoctorById(String doctorId) {
		
		var id = UUID.fromString(doctorId);
		var doctorEntity = doctorRepository
				.findById(id);
		
		if (doctorEntity.isPresent()) {
			
			doctorMapper.deleteEntityFromStatus(doctorEntity.get());
			
		}
		
	}

}
