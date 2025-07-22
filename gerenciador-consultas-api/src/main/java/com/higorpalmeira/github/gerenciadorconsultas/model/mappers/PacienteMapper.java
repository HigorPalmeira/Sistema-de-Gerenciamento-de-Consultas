package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class, ConsultaMapper.class})
public interface PacienteMapper {
	
	/*
	 * Cria uma entidade 'Paciente' com os dados do DTO.
	 * 
	 * @param criarPacienteDto Objeto com os dados para criação da entidade.
	 * @return Paciente Entidade criada a partir dos dados do DTO.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ATIVO)")
	Paciente criarPacienteDtoParePaciente(CriarPacienteDto criarPacienteDto);
	
	/*
	 * Cria um DTO de saída simples a partir da entidade 'Paciente'.
	 * 
	 * @param paciente Entidade a ser transformada.
	 * @return SaidaSimplePacienteDto DTO de saída simples criado.
	 * */
	@Mapping(target = "consultas", expression = "java(paciente.getConsultas() != null ? paciente.getConsultas().size() : 0)")
	SaidaSimplesPacienteDto pacienteParaSaidaSimplesPacienteDto(Paciente paciente);
	
	/*
	 * Cria um DTO de saída detalhada a paritr da entidade 'Paciente'.
	 * 
	 * @param paciente Entidade a ser transformada.
	 * @return SaidaDetalhadaPacienteDto DTO de saída detalhada criado.
	 * */
	SaidaDetalhadaPacienteDto pacienteParaSaidaDetalhadaPacienteDto(Paciente paciente);

	/**
     * Atualiza a entidade 'Paciente' com os dados não nulos do DTO.
     * 
     * @param atualizarPacienteDto O objeto com os dados para atualização.
     * @param paciente A entidade que será atualizada (carregada do banco).
     */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "cpf", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	void atualizarPacienteDeAtualizarPacienteDto(AtualizarPacienteDto atualizarPacienteDto, @MappingTarget Paciente paciente);
	
}
