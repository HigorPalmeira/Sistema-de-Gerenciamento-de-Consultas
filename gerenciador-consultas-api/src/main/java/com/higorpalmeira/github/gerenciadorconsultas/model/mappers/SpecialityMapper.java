package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

public class SpecialityMapper {

	public Speciality toEntity(CreateSpecialityDto specialityDto) {
		
		return new Speciality(
				specialityDto.description(),
				Instant.now(),
				null
				);
		
	}
	
	public void updateEntityFromDto(Speciality speciality, UpdateSpecialityDto updateSpecialityDto) {
		
		if (updateSpecialityDto.description() != null) {
			speciality.setDescription(updateSpecialityDto.description());
		}
		
	}
	
}
