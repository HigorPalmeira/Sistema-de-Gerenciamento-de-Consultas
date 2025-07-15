package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateConsultationDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.ConsultationRepository;

@Service
public class ConsultationService {

	private ConsultationRepository consutationRepository;
	
	public ConsultationService(ConsultationRepository consultationRepository) {
		this.consutationRepository = consultationRepository;
	}
	
	@Transactional
	public UUID createConsultation(CreateConsultationDto createConsultationDto) {
		
		// criar validação para o datetime
		if (createConsultationDto.value() < 0.0f) {
			throw new InvalidDataException("Invalid value.");
		}
		
		return null;
	}
	
}
