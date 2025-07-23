package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class, PacienteMapper.class}, imports = {TipoStatusConsulta.class})
public abstract class ConsultaMapper {
	
	@Autowired
	protected MedicoMapper medicoMapper;
	
	@Autowired
	protected PacienteMapper pacienteMapper;
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "status", expression = "java(TipoStatusConsulta.AGENDADA)")
	public abstract Consulta criarConsultaDtoParaConsulta(CriarConsultaDto criarConsultaDto, Medico medico, Paciente paciente);
	
	public abstract SaidaSimplesConsultaDto consultaParaSaidaSimplesConsultaDto(Consulta consulta);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "status", expression = "java(atualizarConsultaDto.getStatus())")
	public abstract void atualizarConsultaDeAtualizarConsultaDto(AtualizarConsultaDto atualizarConsultaDto, Medico medico, Paciente paciente, @MappingTarget Consulta consulta);
	
}
