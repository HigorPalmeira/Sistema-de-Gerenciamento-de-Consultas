package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.springframework.stereotype.Component;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Component
public class OldSpecialityMapper {

	public void updateEntityFromDto(Speciality speciality, UpdateSpecialityDto updateSpecialityDto) {
		
		if (updateSpecialityDto.description() != null) {
			speciality.setDescription(updateSpecialityDto.description());
		}
		
	}
	
}
