package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;

@Mapper(componentModel = "spring", uses = {ConsultaMapper.class})
public abstract class MedicoMapper {
	
	@Autowired
	protected EspecialidadeMapper especialidadeMapper;
	
	/*
	 * Cria uma entidade 'Medico' com os dados do DTO.
	 * 
	 * @param criarMedicoDto Objeto com os dados para criação da entidade.
	 * @return Medico Entidade criada a partir dos dados do DTO.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	@Mapping(target = "status", expression = "java(TipoStatusConta.ATIVO)")
	public abstract Medico criarMedicoDtoParaMedico(CriarMedicoDto criarMedicoDto, Especialidade especialidade);

	/*
	 * Cria um DTO de saída simples a partir da entidade 'Medico'.
	 * 
	 * @param medico Entidade a ser transformada.
	 * @return SaidaSimplesMedicoDto DTO de saída simples criado.
	 * */
	@Mapping(source = "consultas", target = "consultas", qualifiedByName = "mapConsultasToCount")
	public abstract SaidaSimplesMedicoDto medicoParaSaidaSimplesMedicoDto(Medico medico);
	
	/*
	 * Cria um DTO de saída detalhada a paritr da entidade 'Medico'.
	 * 
	 * @param medico Entidade a ser transformada.
	 * @return SaidaDetalhadaMedicoDto DTO de saída detalhada criado.
	 * */
	public abstract SaidaDetalhadaMedicoDto medicoParaSaidaDetalhadaMedicoDto(Medico medico);
	
	/**
     * Atualiza a entidade 'Medico' com os dados não nulos do DTO.
     * 
     * @param atualizarMedicoDto O objeto com os dados para atualização.
     * @param medico A entidade que será atualizada (carregada do banco).
     */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "crm", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	public abstract void atualizarMedicoDeAtualizarMedicoDto(AtualizarMedicoDto atualizarMedicoDto, Especialidade especialidade, @MappingTarget Medico medico);
	
	@Named("mapConsultasToCount")
	public int mapConsultasToCount(List<Consulta> consultas) {
		
		return consultas != null ? consultas.size() : 0;
		
	}
}
