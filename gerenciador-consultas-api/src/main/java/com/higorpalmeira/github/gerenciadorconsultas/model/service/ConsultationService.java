package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OutputSimpleConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultationMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.ConsultationRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.DoctorRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;

@Service
public class ConsultationService {

	private ConsultationRepository consultationRepository;
	
	private DoctorRepository doctorRepository;
	
	private PatientRepository patientRepository;
	
	private ConsultationMapper consultationMapper;
	
	public ConsultationService(ConsultationRepository consultationRepository, DoctorRepository doctorRepository, PatientRepository patientRepository, ConsultationMapper consultationMapper) {
		this.consultationRepository = consultationRepository;
		this.doctorRepository = doctorRepository;
		this.patientRepository = patientRepository;
		this.consultationMapper = consultationMapper;
	}
	
	@Transactional
	public UUID createConsultation(CreateConsultationDto createConsultationDto) {
		
		// criar validação para o datetime
		if (createConsultationDto.value() < 0.0f) {
			throw new InvalidDataException("Invalid value.");
		}
		
		var doctorId = createConsultationDto.doctorId();
		var doctorEntity = doctorRepository
				.findById(UUID.fromString(doctorId))
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		
		var patientId = createConsultationDto.patientId();
		var patientEntity = patientRepository
				.findById(UUID.fromString(patientId))
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));
		
		var consultation = consultationMapper.toEntity(createConsultationDto, doctorEntity, patientEntity);
		
		var consultationSaved = consultationRepository.save(consultation);
		
		return consultationSaved.getConsultationId();
	}
	
	@Transactional(readOnly = true)
	public OutputSimpleConsultationDto findSimpleConsultationById(String consultationId) {
		
		var id = UUID.fromString(consultationId);
		var consultationEntity = consultationRepository
				.findById(id)
				.map(consultation -> new OutputSimpleConsultationDto(
						consultation.getConsultationId().toString(),
						consultation.getDateTime().toString(),
						consultation.getStatus().getType(),
						consultation.getValue(),
						consultation.getDoctor().getFirstName(),
						consultation.getDoctor().getCrm(),
						consultation.getPatient().getFirstName(),
						consultation.getPatient().getCpf()
						)).orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));
		
		return consultationEntity;
		
	}
	
}
