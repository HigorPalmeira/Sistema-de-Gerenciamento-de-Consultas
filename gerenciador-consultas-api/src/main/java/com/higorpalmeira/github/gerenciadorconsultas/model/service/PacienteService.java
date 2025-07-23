package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Endereco;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultaMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EnderecoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.PacienteMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EnderecoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PacienteRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class PacienteService {

	private PacienteRepository pacienteRepository;
	
	private EnderecoRepository enderecoRepository;

	private PacienteMapper pacienteMapper;
	
	private ConsultaMapper consultaMapper;
	
	private EnderecoMapper enderecoMapper;

	public PacienteService(PacienteRepository pacienteRepository, EnderecoRepository enderecoRepository, PacienteMapper pacienteMapper, EnderecoMapper enderecoMapper, ConsultaMapper consultaMapper) {
		this.pacienteRepository = pacienteRepository;
		this.enderecoRepository = enderecoRepository;
		this.pacienteMapper = pacienteMapper;
		this.enderecoMapper = enderecoMapper;
		this.consultaMapper = consultaMapper;
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

		Paciente paciente = pacienteMapper.criarPacienteDtoParePaciente(criarPacienteDto);
		
		Endereco endereco = enderecoMapper
				.criarEnderecoDtoParaEndereco(criarPacienteDto.getEndereco());
		
		paciente.setEndereco(endereco);

		var pacienteSalvo = pacienteRepository.save(paciente);

		return pacienteSalvo.getId();

	}

	@Transactional(readOnly = true)
	public SaidaSimplesPacienteDto buscarSaidaSimplesPacientePorId(String pacienteId) {
		
		var id = UUID.fromString(pacienteId);
		Paciente pacienteEntidade = pacienteRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + id));

		SaidaSimplesPacienteDto pacienteDto = pacienteMapper
				.pacienteParaSaidaSimplesPacienteDto(pacienteEntidade);
		
		SaidaEnderecoDto enderecoDto = enderecoMapper
				.EnderecoParaSaidaEnderecoDto(pacienteEntidade.getEndereco());
		
		pacienteDto.setEndereco(enderecoDto);
		
		return pacienteDto;

	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesPacienteDto> listarTodosSaidaSimplesPaciente() {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAll();
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaSimplesPacienteDto dto = pacienteMapper.pacienteParaSaidaSimplesPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;

	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesPacienteDto> listarTodosSaidaSimplesPacienteAtivos() {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAllByStatus(TipoStatusConta.ATIVO);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaSimplesPacienteDto dto = pacienteMapper.pacienteParaSaidaSimplesPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesPacienteDto> listarTodosSaidaSimplesPacienteInativos() {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAllByStatus(TipoStatusConta.INATIVO);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaSimplesPacienteDto dto = pacienteMapper.pacienteParaSaidaSimplesPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesPacienteDto> listarTodosSaidaSimplesPacientePorDataNascimento(LocalDate dataNascimento) {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAllByDataNascimento(dataNascimento);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaSimplesPacienteDto dto = pacienteMapper.pacienteParaSaidaSimplesPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
	}

	@Transactional(readOnly = true)
	public List<SaidaDetalhadaPacienteDto> listarTodosSaidaDetalhadaPaciente() {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAll();
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaDetalhadaPacienteDto dto = pacienteMapper.pacienteParaSaidaDetalhadaPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					List<SaidaSimplesConsultaDto> consultaDto = paciente.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultaDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaPacienteDto> listarTodosSaidaDetalhadaPacienteAtivos() {

		List<Paciente> listaPacientes = pacienteRepository
				.findAllByStatus(TipoStatusConta.ATIVO);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaDetalhadaPacienteDto dto = pacienteMapper.pacienteParaSaidaDetalhadaPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					List<SaidaSimplesConsultaDto> consultaDto = paciente.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultaDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;

	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaPacienteDto> listarTodosSaidaDetalhadaPacienteInativos() {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAllByStatus(TipoStatusConta.INATIVO);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaDetalhadaPacienteDto dto = pacienteMapper.pacienteParaSaidaDetalhadaPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					List<SaidaSimplesConsultaDto> consultaDto = paciente.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultaDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaPacienteDto> listarTodosSaidaDetalhadaPacientePorDataNascimento(LocalDate dataNascimento) {
		
		List<Paciente> listaPacientes = pacienteRepository
				.findAllByDataNascimento(dataNascimento);
		
		var listaPacientesDto = listaPacientes.stream()
				.map(paciente -> {
					
					SaidaDetalhadaPacienteDto dto = pacienteMapper.pacienteParaSaidaDetalhadaPacienteDto(paciente);
					
					SaidaEnderecoDto enderecoDto = enderecoMapper
							.EnderecoParaSaidaEnderecoDto(paciente.getEndereco());
					
					dto.setEndereco(enderecoDto);
					
					List<SaidaSimplesConsultaDto> consultaDto = paciente.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultaDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaPacientesDto;
		
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
		
		var enderecoDto = atualizarPacienteDto.getEndereco();
		
		
		
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
