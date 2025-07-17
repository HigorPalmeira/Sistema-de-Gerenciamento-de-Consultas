package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.DetailedOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateSpecialityDto;
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
		
		var speciality = specialityMapper.createToSpeciality(createSpecialityDto);
		
		var specialitySaved = specialityRepository.save(speciality);
		
		return specialitySaved.getId();
		
	}
	
	@Transactional(readOnly = true)
	public SimpleOutputSpecialityDto findSimpleOutputSpecialityById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id).orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		return specialityMapper.specialityToSimpleOutputSpecialityDto(specialityEntity);
		
	}
	
	@Transactional(readOnly = true)
	public DetailedOutputSpecialityDto findSpecialityDetailedById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id)
				.map(speciality -> specialityMapper.specialityToDetailedOutputSpecialityDto(speciality))
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		return specialityEntity;
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputSpecialityDto> listSimpleAllSpecialities() {
		
		var specialities = specialityRepository
				.findAll().stream()
				.map(speciality -> specialityMapper.specialityToSimpleOutputSpecialityDto(speciality))
				.toList();
		
		return specialities;
		
	}
	
	@Transactional(readOnly = true)
	public List<DetailedOutputSpecialityDto> listDetailedAllSpecialities() {
		
		var specialities = specialityRepository
				.findAll().stream()
				.map(speciality -> specialityMapper.specialityToDetailedOutputSpecialityDto(speciality))
				.toList();
		
		return specialities;
		
	}
	
	@Transactional
	public void updateSpecialityById(String specialityId, UpdateSpecialityDto updateSpecialityDto) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Speciality not found with ID: " + id));
		
		specialityMapper.updateSpecialityFromUpdateSpecialityDto(updateSpecialityDto, specialityEntity);
		
	}
	
	public void deleteSpecialityById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityExists = specialityRepository.existsById(id);
		
		if (specialityExists) {
			specialityRepository.deleteById(id);
		}
		
	}

}
