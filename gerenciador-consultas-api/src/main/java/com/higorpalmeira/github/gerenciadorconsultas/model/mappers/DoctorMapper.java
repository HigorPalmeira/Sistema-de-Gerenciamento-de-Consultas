package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Mapper(componentModel = "spring", uses = {SpecialityMapper.class})
public interface DoctorMapper {
	
	@Mapping(target = "doctorId", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	@Mapping(target = "status", expression = "java(StatusAccountType.ACTIVE)")
	Doctor createToDoctor(CreateDoctorDto createDoctorDto, Speciality speciality);

	@Mapping(target = "consultations", expression = "java(doctor.getConsultations() != null ? doctor.getConsultations().size() : 0)")
	SimpleOutputDoctorDto doctorToSimpleOutputDoctorDto(Doctor doctor);
	
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "doctorId", ignore = true)
	@Mapping(target = "crm", ignore = true)
	@Mapping(target = "email", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "consultations", ignore = true)
	void updateDoctorFromUpdateDoctorDto(UpdateDoctorDto updateDoctorDto, Speciality speciality, @MappingTarget Doctor doctor);
	
}
