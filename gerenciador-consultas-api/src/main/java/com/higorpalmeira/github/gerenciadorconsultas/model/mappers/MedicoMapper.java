package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.util.Formatter;

@Mapper(componentModel = "spring", imports = {TipoStatusConta.class})
public abstract class MedicoMapper {
	
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
	@Mapping(target = "especialidade", ignore = true)
	@Mapping(target = "status", expression = "java(TipoStatusConta.ATIVO)")
	@Mapping(source = "crm", target = "crm", qualifiedByName = "mapCrmFormatadoToCrmLimpo")
	@Mapping(source = "telefone", target = "telefone", qualifiedByName = "mapTelefoneFormatadoToTelefoneLimpo")
	public abstract Medico criarMedicoDtoParaMedico(CriarMedicoDto criarMedicoDto);

	/*
	 * Cria um DTO de saída simples a partir da entidade 'Medico'.
	 * 
	 * @param medico Entidade a ser transformada.
	 * @return SaidaSimplesMedicoDto DTO de saída simples criado.
	 * */
	@Mapping(source = "consultas", target = "consultas", qualifiedByName = "mapConsultasToCount")
	@Mapping(source = "crm", target = "crm", qualifiedByName = "mapCrmLimpoToCrmFormatado")
	@Mapping(source = "telefone", target = "telefone", qualifiedByName = "mapTelefoneLimpoToTelefoneFormatado")
	@Mapping(target = "especialidade", ignore = true)
	public abstract SaidaSimplesMedicoDto medicoParaSaidaSimplesMedicoDto(Medico medico);
	
	/*
	 * Cria um DTO de saída detalhada a paritr da entidade 'Medico'.
	 * 
	 * @param medico Entidade a ser transformada.
	 * @return SaidaDetalhadaMedicoDto DTO de saída detalhada criado.
	 * */
	@Mapping(target = "consultas", ignore = true)
	@Mapping(target = "especialidade", ignore = true)
	@Mapping(source = "crm", target = "crm", qualifiedByName = "mapCrmLimpoToCrmFormatado")
	@Mapping(source = "telefone", target = "telefone", qualifiedByName = "mapTelefoneLimpoToTelefoneFormatado")
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
	@Mapping(target = "telefone", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultas", ignore = true)
	@Mapping(target = "especialidade", ignore = true)
	public abstract void atualizarMedicoDeAtualizarMedicoDto(AtualizarMedicoDto atualizarMedicoDto, @MappingTarget Medico medico);
	
	@Named("mapConsultasToCount")
	public int mapConsultasToCount(List<Consulta> consultas) {
		
		return consultas != null ? consultas.size() : 0;
		
	}
	
	@Named("mapCrmLimpoToCrmFormatado")
	public String mapCrmLimpoToCrmFormatado(String crm) {
		
		return Formatter.ofCrm(crm);
		
	}
	
	@Named("mapCrmFormatadoToCrmLimpo")
	public String mapCrmFormatadoToCrmLimpo(String crm) {
		
		return Formatter.clearCpfCepCrmTelefone(crm);
		
	}
	
	@Named("mapTelefoneLimpoToTelefoneFormatado")
	public String mapTelefoneLimpoToTelefoneFormatado(String telefone) {
		
		return Formatter.ofTelefone(telefone);
		
		
	}
	
	@Named("mapTelefoneFormatadoToTelefoneLimpo")
	public String mapTelefoneFormatadoToTelefoneLimpo(String telefone) {
		
		return Formatter.clearCpfCepCrmTelefone(telefone);
		
	}
}
