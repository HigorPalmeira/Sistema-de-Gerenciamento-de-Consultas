package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldOutputDetailedPatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplePacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.PacienteMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PacienteRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PacienteService {

	private PacienteRepository pacienteRepository;

	private PacienteMapper pacienteMapper;

	public PacienteService(PacienteRepository pacienteRepository, PacienteMapper pacienteMapper) {
		this.pacienteRepository = pacienteRepository;
		this.pacienteMapper = pacienteMapper;
	}

	@Transactional
	public UUID criarPaciente(CriarPacienteDto criarPacienteDto) {

		if (!Validator.CPFValidation(criarPacienteDto.getCpf())) {
			throw new InvalidDataException("CPF inválido.");
		}

		if (!Validator.EmailValidation(criarPacienteDto.getEmail())) {
			throw new InvalidDataException("Formato de e-mail inválido.");
		}
		
		if (pacienteRepository.existsByCpf(criarPacienteDto.getCpf())) {
			throw new DataConflictException("CPF já registrado no sistema.");
		}
		
		if (pacienteRepository.existsByEmail(criarPacienteDto.getEmail())) {
			throw new DataConflictException("E-mail já registrado no sistema.");
		}

		var paciente = pacienteMapper.criarPacienteDtoParePaciente(criarPacienteDto);

		var pacienteSalvo = pacienteRepository.save(paciente);

		return pacienteSalvo.getId();

	}

	@Transactional(readOnly = true)
	public SaidaSimplePacienteDto buscarSaidaSimplesPacientePorId(String pacienteId) {
		
		var id = UUID.fromString(pacienteId);
		var pacienteEntidade = pacienteRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));

		return pacienteMapper.pacienteParaSaidaSimplesPacienteDto(pacienteEntidade);

	}
	
	@Transactional(readOnly = true)
	public OldOutputDetailedPatientDto findDetailedPatientById(String patientId) {
		
		return null;
		
	}

	@Transactional(readOnly = true)
	public List<SaidaSimplePacienteDto> listarTodosSaidaSimplesPaciente() {
		
		var pacientes = pacienteRepository
				.findAll().stream()
				.map(paciente -> pacienteMapper.pacienteParaSaidaSimplesPacienteDto(paciente)
						).toList();

		return pacientes;

	}

	@Transactional
	public void atualizarPacientePorId(String pacienteId, AtualizarPacienteDto atualizarPacienteDto) {

		var id = UUID.fromString(pacienteId);
		var pacienteEntidade = pacienteRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));
		
		if (atualizarPacienteDto.getCpf() != null) {
			pacienteRepository.findByCpf(atualizarPacienteDto.getCpf()).ifPresent(existingPatient -> {
				if (!existingPatient.getId().equals(pacienteEntidade.getId())) {
					throw new DataConflictException("O CPF já está sendo usado por outro paciente.");
				}
			});
			
			pacienteEntidade.setCpf(atualizarPacienteDto.getCpf());
		}
		
		if (atualizarPacienteDto.getEmail() != null) {
			pacienteRepository.findByEmail(atualizarPacienteDto.getEmail()).ifPresent(existingPatient -> {
				if (!existingPatient.getId().equals(pacienteEntidade.getId())) {
					throw new DataConflictException("O e-mail já está sendo usado por outro paciente.");
				}
			});
			
			pacienteEntidade.setEmail(atualizarPacienteDto.getEmail());
		}
		
		pacienteMapper.atualizarPacienteDeAtualizarPacienteDto(atualizarPacienteDto, pacienteEntidade);

	}

	@Transactional
	public void deletarPacientePorId(String pacienteId) {

		var id = UUID.fromString(pacienteId);
		var pacienteEntidade = pacienteRepository
				.findById(id);

		pacienteEntidade.ifPresent(paciente -> {
			paciente.setStatus(TipoStatusConta.INATIVO);
		});

	}

}
