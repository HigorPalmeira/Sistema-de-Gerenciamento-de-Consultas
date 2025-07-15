package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputDetailedSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimpleDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimpleSpeciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.SpecialityMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.SpecialityRepository;

@Service
public class SpecialityService {
	
	private SpecialityRepository specialityRepository;
	
	private SpecialityMapper specialityMapper;
	
	public SpecialityService(SpecialityRepository specialityRepository, SpecialityMapper specialityMapper) {
		this.specialityRepository = specialityRepository;
		this.specialityMapper = specialityMapper;
	}
	
	@Transactional
	public UUID createSpeciality(CreateSpecialityDto createSpecialityDto) {
		
		var speciality = specialityMapper.toEntity(createSpecialityDto);
		
		var specialitySaved = specialityRepository.save(speciality);
		
		return specialitySaved.getId();
		
	}
	
	@Transactional(readOnly = true)
	public OutputSimpleSpeciality findSimpleSpecialityById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id)
				.map(speciality -> new OutputSimpleSpeciality(
						speciality.getId(),
						speciality.getDescription(),
						speciality.getDoctors().size()
						)).orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		return specialityEntity;
		
	}
	
	@Transactional(readOnly = true)
	public OutputDetailedSpecialityDto findSpecialityDetailedById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id)
				.map(speciality -> new OutputDetailedSpecialityDto(
						speciality.getId(),
						speciality.getDescription(),
						speciality.getDoctors().stream()
							.map(doctor -> new OutputSimpleDoctorDto(
									doctor.getDoctorId(),
									doctor.getFirstName(),
									doctor.getCrm(),
									doctor.getTelephone(),
									doctor.getEmail()
									)).toList()
						)).orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		return specialityEntity;
	}
	
	@Transactional(readOnly = true)
	public List<Speciality> listSpecialities() {
		
		return specialityRepository.findAll();
		
	}
	
	@Transactional
	public void updateSpecialityById(String specialityId, UpdateSpecialityDto updateSpecialityDto) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		specialityMapper.updateEntityFromDto(specialityEntity, updateSpecialityDto);
		
	}
	
	public void deleteSpecialityById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityExists = specialityRepository.existsById(id);
		
		if (specialityExists) {
			specialityRepository.deleteById(id);
		}
		
	}

}
