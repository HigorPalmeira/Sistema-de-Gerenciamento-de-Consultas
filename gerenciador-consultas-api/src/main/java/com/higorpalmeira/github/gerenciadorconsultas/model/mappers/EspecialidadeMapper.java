package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.DetailedOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface EspecialidadeMapper {
	
	/*
	 * Cria uma entidade 'Especialidade' com os dados do DTO.
	 * 
	 * @param createSpecialityDto Objeto com os dados para criação da entidade.
	 * @return Speciality Entidade criada a partir dos dados do DTO.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "medicos", ignore = true)
	Especialidade createToSpeciality(CreateSpecialityDto createSpecialityDto);
	
	/*
	 * Cria um DTO de saída simples a partir da entidade 'Speciality'.
	 * 
	 * @param speciality Entidade a ser transformada.
	 * @return SimpleOutputSpecialityDto DTO de saída simples criado.
	 * */
	@Mapping(target = "associateDoctors", expression = "java(speciality.getDoctors() != null ? speciality.getDoctors().size() : 0)")
	SimpleOutputSpecialityDto specialityToSimpleOutputSpecialityDto(Especialidade speciality);
	
	/*
	 * Criar um DTO de saída detalhada a partir da entidade 'Speciality'.
	 * 
	 * @param speciality Entidade a ser transforamada.
	 * @return DetailedOutputSpecialityDto DTO de saída detalhada criada.
	 * */
	DetailedOutputSpecialityDto specialityToDetailedOutputSpecialityDto(Especialidade speciality);
	
	/**
     * Atualiza a entidade 'Speciality' com os dados não nulos do DTO.
     * 
     * @param updateSpecialityDto O objeto com os dados para atualização.
     * @param speciality A entidade que será atualizada (carregada do banco).
     */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "doctors", ignore = true)
	void updateSpecialityFromUpdateSpecialityDto(UpdateSpecialityDto updateSpecialityDto, @MappingTarget Especialidade speciality);

}
