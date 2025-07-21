package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;

@Mapper(componentModel = "spring", uses = {EspecialidadeMapper.class})
public interface DoctorMapper {
	
	@Mapping(target = "doctorId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ACTIVE)")
	Medico createToDoctor(CriarMedicoDto createDoctorDto, Especialidade speciality);

	@Mapping(target = "consultations", expression = "java(doctor.getConsultations() != null ? doctor.getConsultations().size() : 0)")
	SaidaSimplesMedicoDto doctorToSimpleOutputDoctorDto(Medico doctor);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "doctorId", ignore = true)
	@Mapping(target = "crm", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	void updateDoctorFromUpdateDoctorDto(AtualizarMedicoDto updateDoctorDto, Especialidade speciality, @MappingTarget Medico doctor);
	
}
