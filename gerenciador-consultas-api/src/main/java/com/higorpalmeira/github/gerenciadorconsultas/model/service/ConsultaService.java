package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultaMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.MedicoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.PacienteMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.ConsultaRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.MedicoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PacienteRepository;

@Service
public class ConsultaService {

	private ConsultaRepository consultaRepository;
	
	private MedicoRepository medicoRepository;
	
	private PacienteRepository pacienteRepository;
	
	private ConsultaMapper consultaMapper;
	
	private MedicoMapper medicoMapper;
	
	private PacienteMapper pacienteMapper;
	
	public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, 
			PacienteRepository pacienteRepository, ConsultaMapper consultaMapper, MedicoMapper medicoMapper,
			PacienteMapper pacienteMapper) {
		this.consultaRepository = consultaRepository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
		this.consultaMapper = consultaMapper;
		this.pacienteMapper = pacienteMapper;
	}
	
	@Transactional
	public UUID criarConsulta(CriarConsultaDto criarConsultaDto) {
		
		// criar validação para o datetime
		if (Float.isNaN(criarConsultaDto.getValor()) || criarConsultaDto.getValor() < 0.0f) {
			throw new InvalidDataException("Valor inválido.");
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
		
		SaidaSimplesPacienteDto pacienteDto = pacienteMapper
				.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
		
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
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
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
					
					dto.setMedico(medicoDto);
					
					SaidaSimplesPacienteDto pacienteDto = pacienteMapper
							.pacienteParaSaidaSimplesPacienteDto(consulta.getPaciente());
					
					dto.setPaciente(pacienteDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaConsultaDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaAgendada() {
		
		List<Consulta> listaConsultas = consultaRepository
				.findAllByStatus(TipoStatusConsulta.AGENDADA);
		
		var listaConsultaDto = listaConsultas.stream()
				.map(consulta -> {
					
					SaidaSimplesConsultaDto dto = consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta);
					
					SaidaSimplesMedicoDto medicoDto = medicoMapper
							.medicoParaSaidaSimplesMedicoDto(consulta.getMedico());
					
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
		
		if (atualizarConsultaDto.getValor() < 0.0f || Float.isNaN(atualizarConsultaDto.getValor())) {
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
