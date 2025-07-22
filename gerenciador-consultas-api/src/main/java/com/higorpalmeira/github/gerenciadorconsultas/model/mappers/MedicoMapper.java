package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;

@Mapper(componentModel = "spring", uses = {EspecialidadeMapper.class, ConsultaMapper.class})
public interface MedicoMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ATIVO)")
	Medico criarMedicoDtoParaMedico(CriarMedicoDto criarMedicoDto, Especialidade especialidade);

	@Mapping(target = "consultas", expression = "java(doctor.getConsultas() != null ? doctor.getConsultas().size() : 0)")
	SaidaSimplesMedicoDto medicoParaSaidaSimplesMedicoDto(Medico medico);
	
	SaidaDetalhadaMedicoDto medicoParaSaidaDetalhadaMedicoDto(Medico medico);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "crm", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	void atualizarMedicoDeAtualizarMedicoDto(AtualizarMedicoDto atualizarMedicoDto, Especialidade especialidade, @MappingTarget Medico medico);
	
}
