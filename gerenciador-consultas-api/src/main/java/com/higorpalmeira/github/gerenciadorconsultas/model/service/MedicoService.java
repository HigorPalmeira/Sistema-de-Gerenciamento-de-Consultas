package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedDoctorDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.MedicoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.MedicoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EspecialidadeRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class MedicoService {
	
	private MedicoRepository medicoRepository;
	
	private EspecialidadeRepository especialidadeRepository;
	
	private MedicoMapper medicoMapper;
	
	public MedicoService(MedicoRepository medicoRepository, EspecialidadeRepository especialidadeRepository, MedicoMapper medicoMapper) {
		this.medicoRepository = medicoRepository;
		this.especialidadeRepository = especialidadeRepository;
		this.medicoMapper = medicoMapper;
	}
	
	@Transactional
	public UUID criarMedico(CriarMedicoDto criarMedicoDto) {
		
		if (!Validator.CRMValidation(criarMedicoDto.getCrm())) {
			throw new InvalidDataException("CRM inválido.");
		}
		
		if (!Validator.EmailValidation(criarMedicoDto.getEmail())) {
			throw new InvalidDataException("Formato de e-mail inválido.");
		}
		
		if (medicoRepository.existsByCrm(criarMedicoDto.getCrm())) {
			throw new DataConflictException("CRM já registrado no sistema.");
		}
		
		if (medicoRepository.existsByEmail(criarMedicoDto.getEmail())) {
			throw new DataConflictException("E-mail já registrado no sistema.");
		}
		
		var especialidadeId = criarMedicoDto.getEspecialidadeId();
		var especialidadeEntidade = especialidadeRepository
				.findById(especialidadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada no ID: " + especialidadeId));
		
		var medico = medicoMapper.criarMedicoDtoParaMedico(criarMedicoDto, especialidadeEntidade);
		
		var medicoSalvo = medicoRepository.save(medico);
		
		return medicoSalvo.getId();
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoPorId(String medicoId) {
		
		var id = UUID.fromString(medicoId);
		var medicoEntidade = medicoRepository
				.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
		
		return medicoMapper.medicoParaSaidaSimplesMedicoDto(medicoEntidade);
		
	}
	
	@Transactional(readOnly = true)
	public OldOutputDetailedDoctorDto findDetailedDoctorById(String doctorId) {
		
		return null;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedico() {
		
		var medicos = medicoRepository
				.findAll().stream()
				.map(medico -> medicoMapper.medicoParaSaidaSimplesMedicoDto(medico)
						).toList();
		
		return medicos;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedicoAtivos() {
		
		var medicos = medicoRepository
				.findAllByStatus(TipoStatusConta.ATIVO).stream()
				.map(medico -> medicoMapper.medicoParaSaidaSimplesMedicoDto(medico)
						).toList();
		
		return medicos;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedicoInativos() {
		
		var medicos = medicoRepository
				.findAllByStatus(TipoStatusConta.INATIVO).stream()
				.map(medico -> medicoMapper.medicoParaSaidaSimplesMedicoDto(medico)
						).toList();
		
		return medicos;
		
	}
	
	@Transactional
	public void atualizarMedicoPorId(String medicoId, AtualizarMedicoDto atualizarMedicoDto) {
		
		var id = UUID.fromString(medicoId);
		var doctorEntity = medicoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
		
		if (atualizarMedicoDto.getCrm() != null) {
			medicoRepository.findByCrm(atualizarMedicoDto.getCrm()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getId().equals(doctorEntity.getId())) {
					throw new DataConflictException("O CRM já está sendo usado por outro médico.");
				}
			});
			
			doctorEntity.setCrm(atualizarMedicoDto.getCrm());
		}
		
		if (atualizarMedicoDto.getEmail() != null) {
			medicoRepository.findByEmail(atualizarMedicoDto.getEmail()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getId().equals(doctorEntity.getId())) {
					throw new DataConflictException("O e-mail já está sendo usado por outro médico.");
				}
				
				doctorEntity.setEmail(atualizarMedicoDto.getEmail());
			});
		}
		
		var especialidadeId = atualizarMedicoDto.getEspecialidadeId();
		var especialidadeEntidade = especialidadeRepository
				.findById(especialidadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada no ID: " + especialidadeId));
		
		medicoMapper.atualizarMedicoDeAtualizarMedicoDto(atualizarMedicoDto, especialidadeEntidade, doctorEntity);
		
	}
	
	@Transactional
	public void deletarMedicoPorId(String medicoId) {
		
		var id = UUID.fromString(medicoId);
		var medicoEntidade = medicoRepository
				.findById(id);
		
		medicoEntidade.ifPresent(doctor -> {
			doctor.setStatus(TipoStatusConta.INATIVO);
		});
		
	}

}
