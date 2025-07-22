package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class, PacienteMapper.class})
public interface ConsultationMapper {
	
	@Mapping(target = "consultationId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "status", expression = "java(StatusConsultationType.SCHEDULED)")
	@Mapping(target = "doctor", expression = "java(doctor != null ? doctor : null)")
	@Mapping(target = "patient", expression = "java(patient != null ? patient : null)")
	Consulta createToConsultation(CriarConsultaDto createConsultationDto, Medico doctor, Paciente patient);
	
	SaidaSimplesConsultaDto consultationToSimpleOutputConsultationDto(Consulta consultation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "consultationId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "doctor", expression = "java(doctor != null ? doctor : null)")
	@Mapping(target = "patient", expression = "java(patient != null ? patient : null)")
	@Mapping(target = "status", expression = "java(consultation.setStatus(updateConsultationDto.getStatus()))")
	void updateConsultationFromUpdateConsultationDto(AtualizarConsultaDto updateConsultationDto, Medico doctor, Paciente patient, @MappingTarget Consulta consultation);
	
}
