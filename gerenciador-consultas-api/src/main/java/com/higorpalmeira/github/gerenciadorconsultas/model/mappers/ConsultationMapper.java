package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consultation;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, PatientMapper.class})
public interface ConsultationMapper {
	
	@Mapping(target = "consultationId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "status", expression = "java(StatusConsultationType.SCHEDULED)")
	@Mapping(target = "doctor", expression = "java(doctor != null ? doctor : null)")
	@Mapping(target = "patient", expression = "java(patient != null ? patient : null)")
	Consultation createToConsultation(CreateConsultationDto createConsultationDto, Doctor doctor, Patient patient);
	
	SimpleOutputConsultationDto consultationToSimpleOutputConsultationDto(Consultation consultation);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "consultationId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "doctor", expression = "java(doctor != null ? doctor : null)")
	@Mapping(target = "patient", expression = "java(patient != null ? patient : null)")
	@Mapping(target = "status", expression = "java(consultation.setStatus(updateConsultationDto.getStatus()))")
	void updateConsultationFromUpdateConsultationDto(UpdateConsultationDto updateConsultationDto, Doctor doctor, Patient patient, @MappingTarget Consultation consultation);
	
}
