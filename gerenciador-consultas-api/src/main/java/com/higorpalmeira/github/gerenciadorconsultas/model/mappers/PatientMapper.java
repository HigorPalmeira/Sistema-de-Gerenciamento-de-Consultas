package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface PatientMapper {
	
	@Mapping(target = "patientId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ACTIVE)")
	Patient createToPatient(CreatePatientDto createPatientMapper);
	
	SimpleOutputPatientDto patientToSimpleOutputPatientDto(Patient patient);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "patientId", ignore = true)
	@Mapping(target = "cpf", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	void updatePatientFromUpdatePatientDto(UpdatePatientDto updatePatientDto, @MappingTarget Patient patient);
	
}
