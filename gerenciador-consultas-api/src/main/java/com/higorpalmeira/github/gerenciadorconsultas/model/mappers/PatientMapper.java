package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;

@Mapper(componentModel = "spring", uses = {EnderecoMapper.class})
public interface PatientMapper {
	
	/*
	 * Cria uma entidade 'Patient' com os dados do DTO.
	 * 
	 * @param createPatientDto Objeto com os dados para criação da entidade.
	 * @return Patient Entidade criada a partir dos dados do DTO.
	 * */
	@Mapping(target = "patientId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ACTIVE)")
	Paciente createToPatient(CriarPacienteDto createPatientMapper);
	
	/*
	 * Cria um DTO de saída simples a partir da entidade 'Patient'.
	 * 
	 * @param patient Entidade a ser transformada.
	 * @return SimpleOutputPatientDto DTO de saída simples criado.
	 * */
	SimpleOutputPatientDto patientToSimpleOutputPatientDto(Paciente patient);

	/**
     * Atualiza a entidade 'Patient' com os dados não nulos do DTO.
     * 
     * @param updatePatientDto O objeto com os dados para atualização.
     * @param patient A entidade que será atualizada (carregada do banco).
     */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "patientId", ignore = true)
	@Mapping(target = "cpf", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	void updatePatientFromUpdatePatientDto(UpdatePatientDto updatePatientDto, @MappingTarget Paciente patient);
	
}
