package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SimpleOutputConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;
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
		if (createConsultationDto.getValue() < 0.0f) {
			throw new InvalidDataException("Invalid value.");
		}
		
		var doctorId = createConsultationDto.getDoctorId();
		var doctorEntity = doctorRepository
				.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		
		var patientId = createConsultationDto.getPatientId();
		var patientEntity = patientRepository
				.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));
		
		var consultation = consultationMapper.createToConsultation(createConsultationDto, doctorEntity, patientEntity);
		
		var consultationSaved = consultationRepository.save(consultation);
		
		return consultationSaved.getConsultationId();
	}
	
	@Transactional(readOnly = true)
	public SimpleOutputConsultationDto findSimpleConsultationById(String consultationId) {
		
		var id = UUID.fromString(consultationId);
		var consultationEntity = consultationRepository
				.findById(id)
				.map(consultation -> consultationMapper.consultationToSimpleOutputConsultationDto(consultation)
						).orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));
		
		return consultationEntity;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputConsultationDto> listSimpleConsultations() {
		
		var consultations = consultationRepository
				.findAll().stream()
				.map(consultation -> consultationMapper.consultationToSimpleOutputConsultationDto(consultation)
						).toList();
		
		return consultations;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputConsultationDto> listSimpleConsultationsActive() {
		
		var consultations = consultationRepository
				.findAllByStatusNot(TipoStatusConsulta.INATIVA).stream()
				.map(consultation -> consultationMapper.consultationToSimpleOutputConsultationDto(consultation))
				.toList();
				
		
		return consultations;
		
	}
	
	@Transactional(readOnly = true)
	public List<SimpleOutputConsultationDto> listSimpleConsultationsScheduled() {
		
		var consultations = consultationRepository
				.findAllByStatus(TipoStatusConsulta.AGENDADA).stream()
				.map(consultation -> consultationMapper.consultationToSimpleOutputConsultationDto(consultation))
				.toList();
		
		return consultations;
		
	}
	
	@Transactional
	public void updateConsultation(String consultationId, UpdateConsultationDto updateConsultationDto) {
		
		var id = UUID.fromString(consultationId);
		var consultationEntity = consultationRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consultation not found with ID: " + id));
		
		if (updateConsultationDto.getValue() < 0.0f || Float.isNaN(updateConsultationDto.getValue())) {
			throw new InvalidDataException("Invalid value.");
		}
		
		var patientId = updateConsultationDto.getPatientId();
		var patientEntity = patientRepository
				.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));
		
		var doctorId = updateConsultationDto.getDoctorId();
		var doctorEntity = doctorRepository
				.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + doctorId));
		
		
		consultationMapper.updateConsultationFromUpdateConsultationDto(updateConsultationDto, doctorEntity, patientEntity, consultationEntity);
		
	}
	
	@Transactional
	public void deleteConsultationById(String consultationId) {
		
		var id = UUID.fromString(consultationId);
		var consultationEntity = consultationRepository
				.findById(id);
		
		consultationEntity.ifPresent(consultation -> {
			consultation.setStatus(TipoStatusConsulta.INATIVA);
		});
		
	}
	
}
