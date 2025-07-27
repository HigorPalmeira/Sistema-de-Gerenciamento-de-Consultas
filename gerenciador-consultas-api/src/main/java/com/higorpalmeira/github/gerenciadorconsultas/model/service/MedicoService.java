package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.DataConflictException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.InvalidDataException;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.ConsultaMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EspecialidadeMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.MedicoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EspecialidadeRepository;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.MedicoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validator;

@Service
public class MedicoService {
	
	private MedicoRepository medicoRepository;
	
	private EspecialidadeRepository especialidadeRepository;
	
	private MedicoMapper medicoMapper;
	
	private EspecialidadeMapper especialidadeMapper;
	
	private ConsultaMapper consultaMapper;
	
	public MedicoService(MedicoRepository medicoRepository, EspecialidadeRepository especialidadeRepository, MedicoMapper medicoMapper, EspecialidadeMapper especialidadeMapper, ConsultaMapper consultaMapper) {
		this.medicoRepository = medicoRepository;
		this.especialidadeRepository = especialidadeRepository;
		this.medicoMapper = medicoMapper;
		this.especialidadeMapper = especialidadeMapper;
		this.consultaMapper = consultaMapper;
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
		
		if (medicoRepository.existsByTelefone(criarMedicoDto.getTelefone())) {
			throw new DataConflictException("Telefone já registrado no sistema.");
		}
		
		var especialidadeId = criarMedicoDto.getEspecialidadeId();
		var especialidadeEntidade = especialidadeRepository
				.findById(especialidadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada no ID: " + especialidadeId));
		
		var medico = medicoMapper.criarMedicoDtoParaMedico(criarMedicoDto);
		
		medico.setEspecialidade(especialidadeEntidade);
		
		var medicoSalvo = medicoRepository.save(medico);
		
		return medicoSalvo.getId();
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoPorId(String medicoId) {
		
		var id = UUID.fromString(medicoId);
		var medicoEntidade = medicoRepository
				.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(medicoEntidade.getEspecialidade());
		
		var medicoDto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medicoEntidade);
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		return medicoDto;
		
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoPorEmail(String email) {
		
		if (!Validator.EmailValidation(email)) {
			throw new InvalidDataException("Formato de e-mail inválido.");
		}
		
		var medicoEntidade = medicoRepository
				.findByEmail(email)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com o E-mail: " + email));
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(medicoEntidade.getEspecialidade());
		
		var medicoDto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medicoEntidade);
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		return medicoDto;
		
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoPorCrm(String crm) {
		
		if (!Validator.CRMValidation(crm)) {
			throw new InvalidDataException("CRM inválido.");
		}
		
		var medicoEntidade = medicoRepository
				.findByCrm(crm)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com CRM: " + crm));
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(medicoEntidade.getEspecialidade());
		
		var medicoDto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medicoEntidade);
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		return medicoDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarSaidaSimplesMedicoPorNome(String nome) {
		
		List<Medico> listaMedicos = medicoRepository
				.findByNomeContainingIgnoreCase(nome);
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaSimplesMedicoDto dto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedico() {
		
		List<Medico> listaMedicos = medicoRepository
				.findAll();
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaSimplesMedicoDto dto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedicoAtivos() {
		
		var listaMedicos = medicoRepository
				.findAllByStatus(TipoStatusConta.ATIVO);
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaSimplesMedicoDto dto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesMedicoDto> listarTodosSaidaSimplesMedicoInativos() {
		
		var listaMedicos = medicoRepository
				.findAllByStatus(TipoStatusConta.INATIVO);
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaSimplesMedicoDto dto = medicoMapper.medicoParaSaidaSimplesMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional(readOnly = true)
	public SaidaDetalhadaMedicoDto buscarSaidaDetalhadaMedicoPorId(String medicoId) {
	
		var id = UUID.fromString(medicoId);
		var medicoEntidade = medicoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
		
		SaidaDetalhadaMedicoDto medicoDto = medicoMapper.medicoParaSaidaDetalhadaMedicoDto(medicoEntidade);
		
		SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaSimplesEspecialidadeDto(medicoEntidade.getEspecialidade());
		
		medicoDto.setEspecialidade(especialidadeDto);
		
		List<SaidaSimplesConsultaDto> listaConsultasDto = medicoEntidade.getConsultas().stream()
				.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
				.collect(Collectors.toList());
		
		medicoDto.setConsultas(listaConsultasDto);
		
		return medicoDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaMedicoDto> listarTodosSaidaDetalhadaMedico() {
		
		List<Medico> listaMedicos = medicoRepository
				.findAll();
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaDetalhadaMedicoDto dto = medicoMapper.medicoParaSaidaDetalhadaMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					List<SaidaSimplesConsultaDto> consultasDto = medico.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultasDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaMedicoDto> listarTodosSaidaDetalhadaMedicoAtivos() {
		
		List<Medico> listaMedicos = medicoRepository
				.findAllByStatus(TipoStatusConta.ATIVO);
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaDetalhadaMedicoDto dto = medicoMapper.medicoParaSaidaDetalhadaMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					List<SaidaSimplesConsultaDto> consultasDto = medico.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultasDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaMedicoDto> listarTodosSaidaDetalhadaMedicoInativos() {
		
		List<Medico> listaMedicos = medicoRepository
				.findAllByStatus(TipoStatusConta.INATIVO);
		
		var listaMedicosDto = listaMedicos.stream()
				.map(medico -> {
					
					SaidaDetalhadaMedicoDto dto = medicoMapper.medicoParaSaidaDetalhadaMedicoDto(medico);
					
					SaidaSimplesEspecialidadeDto especialidadeDto = especialidadeMapper
							.especialidadeParaSaidaSimplesEspecialidadeDto(medico.getEspecialidade());
					
					dto.setEspecialidade(especialidadeDto);
					
					List<SaidaSimplesConsultaDto> consultasDto = medico.getConsultas().stream()
							.map(consultaMapper::consultaParaSaidaSimplesConsultaDto)
							.collect(Collectors.toList());
					
					dto.setConsultas(consultasDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaMedicosDto;
		
	}
	
	@Transactional
	public void atualizarMedicoPorId(String medicoId, AtualizarMedicoDto atualizarMedicoDto) {
		
		var id = UUID.fromString(medicoId);
		var medicoEntidade = medicoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado com ID: " + id));
		
		if (atualizarMedicoDto.getCrm() != null) {
			medicoRepository.findByCrm(atualizarMedicoDto.getCrm()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getId().equals(medicoEntidade.getId())) {
					throw new DataConflictException("O CRM já está sendo usado por outro médico.");
				}
			});
			
			medicoEntidade.setCrm(atualizarMedicoDto.getCrm());
		}
		
		if (atualizarMedicoDto.getEmail() != null) {
			medicoRepository.findByEmail(atualizarMedicoDto.getEmail()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getId().equals(medicoEntidade.getId())) {
					throw new DataConflictException("O e-mail já está sendo usado por outro médico.");
				}
				
				medicoEntidade.setEmail(atualizarMedicoDto.getEmail());
			});
		}
		
		if (atualizarMedicoDto.getTelefone() != null) {
			medicoRepository.findByTelefone(atualizarMedicoDto.getTelefone()).ifPresent(existingDoctor -> {
				if (!existingDoctor.getId().equals(medicoEntidade.getId())) {
					throw new DataConflictException("O telefone já está sendo usado por outro médico.");
				}
				
				medicoEntidade.setTelefone(atualizarMedicoDto.getTelefone());
			});
			
		}
		
		var especialidadeId = atualizarMedicoDto.getEspecialidadeId();
		var especialidadeEntidade = especialidadeRepository
				.findById(especialidadeId)
				.orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada no ID: " + especialidadeId));
		
		medicoEntidade.setEspecialidade(especialidadeEntidade);

		medicoMapper.atualizarMedicoDeAtualizarMedicoDto(atualizarMedicoDto, medicoEntidade);
		
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
