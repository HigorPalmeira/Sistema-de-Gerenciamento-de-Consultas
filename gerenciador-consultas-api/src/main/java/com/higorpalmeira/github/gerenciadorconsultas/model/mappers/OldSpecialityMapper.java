package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldCreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Component
public class OldSpecialityMapper {

	public Speciality toEntity(OldCreateSpecialityDto specialityDto) {
		
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
