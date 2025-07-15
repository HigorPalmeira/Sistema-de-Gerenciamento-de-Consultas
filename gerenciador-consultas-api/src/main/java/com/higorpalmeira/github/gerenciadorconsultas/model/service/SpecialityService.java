package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
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
	
	public Optional<Speciality> findSpecialityById(String specialityId) {
		
		return specialityRepository.findById(UUID.fromString(specialityId));
		
	}
	
	public List<Speciality> listSpecialities() {
		
		return specialityRepository.findAll();
		
	}
	
	public void updateSpecialityById(String specialityId, UpdateSpecialityDto updateSpecialityDto) {
		
		var id = UUID.fromString(specialityId);
		var specialityEntity = specialityRepository.findById(id);
		
		if (specialityEntity.isPresent()) {
			
			var speciality = specialityEntity.get();
			
			if (updateSpecialityDto.description() != null) {
				speciality.setDescription( updateSpecialityDto.description() );
			}
			
			specialityRepository.save(speciality);
			
		}
		
	}
	
	public void deleteSpecialityById(String specialityId) {
		
		var id = UUID.fromString(specialityId);
		var specialityExists = specialityRepository.existsById(id);
		
		if (specialityExists) {
			specialityRepository.deleteById(id);
		}
		
	}

}
