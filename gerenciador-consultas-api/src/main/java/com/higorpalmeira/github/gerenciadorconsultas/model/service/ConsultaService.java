package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultaMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EspecialidadeMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.MedicoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.PacienteMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.ConsultaRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.MedicoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PacienteRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class ConsultaService {

	private ConsultaRepository consultaRepository;
	
	private MedicoRepository medicoRepository;
	
	private PacienteRepository pacienteRepository;
	
	private ConsultaMapper consultaMapper;
	
	private MedicoMapper medicoMapper;
	
	private PacienteMapper pacienteMapper;
	
	private EspecialidadeMapper especialidadeMapper;
	
	public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, 
			PacienteRepository pacienteRepository, ConsultaMapper consultaMapper, MedicoMapper medicoMapper,
			PacienteMapper pacienteMapper, EspecialidadeMapper especialidadeMapper) {
		this.consultaRepository = consultaRepository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
		this.consultaMapper = consultaMapper;
		this.pacienteMapper = pacienteMapper;
		this.medicoMapper = medicoMapper;
		this.especialidadeMapper = especialidadeMapper;
	}
	
	@Transactional
	public UUID criarConsulta(CriarConsultaDto criarConsultaDto) {
		
		if (!Validator.ValorValidation(criarConsultaDto.getValor())) {
			throw new InvalidDataException("Valor inválido.");
		}
		
		if (!Validator.DataHoraValidation(criarConsultaDto.getDataHora())) {
			throw new InvalidDataException("Data e hora inválido.");
		}
		
		var tempConsulta = consultaRepository
				.findByDataHora(criarConsultaDto.getDataHora());
		
		if (tempConsulta.isPresent()) {
			throw new DataConflictException("Consulta já cadastrada no horário informado.");
		}
		
		var medicoId = criarConsultaDto.getMedicoId();
		var medicoEntidade = medicoRepository
				.findById(medicoId)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + medicoId));
		
		var pacienteId = criarConsultaDto.getPacienteId();
		var pacienteEntidade = pacienteRepository
				.findById(pacienteId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + pacienteId));
		
		var consulta = consultaMapper.criarConsultaDtoParaConsulta(criarConsultaDto);
		consulta.setMedico(medicoEntidade);
		consulta.setPaciente(pacienteEntidade);
		
		var consultaSalva = consultaRepository.save(consulta);
		
		return consultaSalva.getId();
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesConsultaDto buscarSaidaSimplesConsultaPorId(String consultaId) {
		
		var id = UUID.fromString(consultaId);
		
		Consulta consulta = consultaRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
		
		SaidaSimplesConsultaDto consultaDto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
		
		SaidaSimplesMedicoDto medicoDto = medicoMapper
				.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		SaidaSimplesPacienteDto pacienteDto = pacienteMapper
				.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
		
		consultaDto.setMedico(medicoDto);
		consultaDto.setPaciente(pacienteDto);
		
		return consultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesConsultaDto buscarSaidaSimplesConsultaPorDataHora(LocalDateTime dataHora) {
		
		if (!Validator.DataHoraValidation(dataHora)) {
			throw new InvalidDataException("Data e hora da consulta inválida!");
		}
		
		Consulta consulta = consultaRepository
				.findByDataHora(dataHora)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com data e hora: " + dataHora.toString()));
		
		SaidaSimplesConsultaDto consultaDto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
		
		SaidaSimplesMedicoDto medicoDto = medicoMapper.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		SaidaSimplesPacienteDto pacienteDto = pacienteMapper.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
		
		consultaDto.setMedico(medicoDto);
		consultaDto.setPaciente(pacienteDto);
		
		return consultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsulta() {
		
		List<Consulta> listaConsultas = consultaRepository
				.findAll();
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorValor(BigDecimal valor) {
		
		if (!Validator.ValorValidation(valor)) {
			throw new InvalidDataException("Valor Inválido!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByValor(valor);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorValorMaiorQue(BigDecimal valor) {
		
		if (!Validator.ValorValidation(valor)) {
			throw new InvalidDataException("Valor Inválido!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByValorGreaterThan(valor);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorValorMenorIgualQue(BigDecimal valor) {
		
		if (!Validator.ValorValidation(valor)) {
			throw new InvalidDataException("Valor Inválido!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByValorLessThanEqual(valor);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorIntervaloValor(BigDecimal valorInicial, BigDecimal valorFinal) {
		
		if (!Validator.ValorValidation(valorInicial)) {
			throw new InvalidDataException("Valor Inicial Inválido!");
		}
		
		if (!Validator.ValorValidation(valorFinal)) {
			throw new InvalidDataException("Valor Final Inválido!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByValorBetween(valorInicial, valorFinal);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorObservacoes(String observacoes) {
		
		if (observacoes == null || observacoes.isBlank()) {
			throw new InvalidDataException("Observações Inválidas!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByObservacoesContainingIgnoreCase(observacoes);
		
		var listaConsultasDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultasDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorDataHoraAntes(LocalDateTime dataHora) {
		
		if (dataHora == null) {
			throw new InvalidDataException("Data e Hora Inválida!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByDataHoraBefore(dataHora);
		
		var listaConsultasDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultasDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorDataHoraDepois(LocalDateTime dataHora) {
		
		if (dataHora == null) {
			throw new InvalidDataException("Data e Hora Inválida!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByDataHoraAfterOrderByDataHoraAsc(dataHora);
		
		var listaConsultasDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultasDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorDataHoraNoIntervalo(LocalDateTime dataHoraInicial, LocalDateTime dataHoraFinal) {
		
		if (dataHoraInicial == null) {
			throw new InvalidDataException("Data e Hora Inicial Inválida!");
		}
		
		if (dataHoraFinal == null) {
			throw new InvalidDataException("Data e Hora Final Inválida!");
		}
		
		List<Consulta> listaConsultas = consultaRepository
				.findByDataHoraBetween(dataHoraInicial, dataHoraFinal);
		
		var listaConsultasDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultasDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaAtiva() {
		
		List<Consulta> listaConsultas = consultaRepository
				.findAllByStatusNot(TipoStatusConsulta.INATIVA);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaPorStatus(String status) {
		
		if (status == null || status.isBlank()) {
			throw new InvalidDataException("Status Inválido!");
		}
		
		TipoStatusConsulta eStatus = TipoStatusConsulta.fromTipo(status);
		
		List<Consulta> listaConsultas = consultaRepository
				.findAllByStatus(eStatus);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(consulta.getMedico().getEspecialidade());
					
					medicoDto.setEspecialidade(especialidadeDto);
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional
	public void atualizarConsultaPorId(String consultaId, AtualizarConsultaDto atualizarConsultaDto) {
		
		var id = UUID.fromString(consultaId);
		var consultaEntidade = consultaRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
		
		if (!Validator.ValorValidation(atualizarConsultaDto.getValor())) {
			throw new InvalidDataException("Valor inválido.");
		}
		
		var patientId = atualizarConsultaDto.getPacienteId();
		var pacienteEntidade = pacienteRepository
				.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + patientId));
		
		var medicoId = atualizarConsultaDto.getMedicoId();
		var medicoEntidade = medicoRepository
				.findById(medicoId)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + medicoId));
		
		
		consultaMapper.atualizarConsultaDeAtualizarConsultaDto(atualizarConsultaDto, consultaEntidade);
		
		consultaEntidade.setMedico(medicoEntidade);
		consultaEntidade.setPaciente(pacienteEntidade);
	}
	
	@Transactional
	public void deletarConsultaPorId(String consultaId) {
		
		var id = UUID.fromString(consultaId);
		var consultaEntidade = consultaRepository
				.findById(id);
		
		consultaEntidade.ifPresent(consulta -> {
			consulta.setStatus(TipoStatusConsulta.INATIVA);
		});
		
	}
	
}
