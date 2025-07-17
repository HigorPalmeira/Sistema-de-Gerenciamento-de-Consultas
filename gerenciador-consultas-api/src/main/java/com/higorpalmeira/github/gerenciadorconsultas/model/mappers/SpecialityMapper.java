package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SimpleOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Mapper(componentModel = "spring")
public interface SpecialityMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "doctors", ignore = true)
	Speciality createToSpeciality(CreateSpecialityDto createSpecialityDto);
	
	@Mapping(target = "associateDoctors", expression = "java(speciality.getDoctors() != null ? speciality.getDoctors().size() : 0)")
	SimpleOutputSpecialityDto specialityToSimpleOutputSpecialityDto(Speciality speciality);

}
