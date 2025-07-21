package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class})
public interface EspecialidadeMapper {
	
	/*
	 * Cria uma entidade 'Especialidade' com os dados do DTO.
	 * 
	 * @param criarEspecialidadeDto Objeto com os dados para criação da entidade.
	 * @return Especialidade Entidade criada a partir dos dados do DTO.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "medicos", ignore = true)
	Especialidade criarEspecialidadeDtoParaEspecialidade(CriarEspecialidadeDto criarEspecialidadeDto);
	
	/*
	 * Cria um DTO de saída simples a partir da entidade 'Especialidade'.
	 * 
	 * @param especialidade Entidade a ser transformada.
	 * @return SaidaSimplesEspecialidadeDto DTO de saída simples criado.
	 * */
	@Mapping(target = "medicosAssociados", expression = "java(especialidade.getMedicos() != null ? especialidade.getMedicos().size() : 0)")
	SaidaSimplesEspecialidadeDto especialidadeParaSaidaSimplesEspecialidadeDto(Especialidade especialidade);
	
	/*
	 * Criar um DTO de saída detalhada a partir da entidade 'Especialidade'.
	 * 
	 * @param especialidade Entidade a ser transforamada.
	 * @return SaidaDetalhadaEspecialidadeDto DTO de saída detalhada criada.
	 * */
	SaidaDetalhadaEspecialidadeDto especialidadeParaSaidaDetalhadaEspecialidadeDto(Especialidade especialidade);
	
	/**
     * Atualiza a entidade 'Especialidade' com os dados não nulos do DTO.
     * 
     * @param updateSpecialityDto O objeto com os dados para atualização.
     * @param especialidade A entidade que será atualizada (carregada do banco).
     */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "doctors", ignore = true)
	void updateSpecialityFromUpdateSpecialityDto(UpdateSpecialityDto updateSpecialityDto, @MappingTarget Especialidade speciality);

}
