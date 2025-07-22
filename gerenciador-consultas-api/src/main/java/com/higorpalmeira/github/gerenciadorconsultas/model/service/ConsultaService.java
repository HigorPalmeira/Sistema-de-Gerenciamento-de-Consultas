package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultaMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.ConsultaRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.MedicoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PacienteRepository;

@Service
public class ConsultaService {

	private ConsultaRepository consultaRepository;
	
	private MedicoRepository medicoRepository;
	
	private PacienteRepository pacienteRepository;
	
	private ConsultaMapper consultaMapper;
	
	public ConsultaService(ConsultaRepository consultaRepository, MedicoRepository medicoRepository, 
			PacienteRepository pacienteRepository, ConsultaMapper consultaMapper) {
		this.consultaRepository = consultaRepository;
		this.medicoRepository = medicoRepository;
		this.pacienteRepository = pacienteRepository;
		this.consultaMapper = consultaMapper;
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
		
		var consulta = consultaMapper.criarConsultaDtoParaConsulta(criarConsultaDto, medicoEntidade, pacienteEntidade);
		
		var consultaSalva = consultaRepository.save(consulta);
		
		return consultaSalva.getId();
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesConsultaDto buscarSaidaSimplesConsultaPorId(String consultaId) {
		
		var id = UUID.fromString(consultaId);
		var consultaEntidade = consultaRepository
				.findById(id)
				.map(consulta -> consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta)
						).orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada com ID: " + id));
		
		return consultaEntidade;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsulta() {
		
		var consultas = consultaRepository
				.findAll().stream()
				.map(consulta -> consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta)
						).toList();
		
		return consultas;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaAtiva() {
		
		var consultas = consultaRepository
				.findAllByStatusNot(TipoStatusConsulta.INATIVA).stream()
				.map(consulta -> consultaMapper.consultaParaSaidaSimplesConsultaDto(consulta))
				.toList();
				
		
		return consultas;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesConsultaDto> listarTodasSaidaSimplesConsultaAgendada() {
		
		var consultas = consultaRepository
				.findAllByStatus(TipoStatusConsulta.AGENDADA).stream()
				.map(consultation -> consultaMapper.consultaParaSaidaSimplesConsultaDto(consultation))
				.toList();
		
		return consultas;
		
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
		
		
		consultaMapper.atualizarConsultaDeAtualizarConsultaDto(atualizarConsultaDto, medicoEntidade, pacienteEntidade, consultaEntidade);
		
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
