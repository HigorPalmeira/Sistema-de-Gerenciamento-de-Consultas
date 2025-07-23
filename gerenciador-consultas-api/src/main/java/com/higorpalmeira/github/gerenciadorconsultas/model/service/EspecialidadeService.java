package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EspecialidadeMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.MedicoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	private EspecialidadeRepository especialidadeRepository;
	
	private EspecialidadeMapper especialidadeMapper;
	
	private MedicoMapper medicoMapper;
	
	public EspecialidadeService(EspecialidadeRepository especialidadeRepository, EspecialidadeMapper especialidadeMapper, MedicoMapper medicoMapper) {
		this.especialidadeRepository = especialidadeRepository;
		this.especialidadeMapper = especialidadeMapper;
		this.medicoMapper = medicoMapper;
	}
	
	@Transactional
	public UUID criarEspecialidade(CriarEspecialidadeDto criarEspecialidadeDto) {
		
		var especialidade = especialidadeMapper.criarEspecialidadeDtoParaEspecialidade(criarEspecialidadeDto);
		
		var especialidadeSalva = especialidadeRepository.save(especialidade);
		
		return especialidadeSalva.getId();
		
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesEspecialidadeDto buscarSaidaSimplesEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		return especialidadeMapper.especialidadeParaSaidaSimplesEspecialidadeDto(especialidadeEntidade);
		
	}
	
	@Transactional(readOnly = true)
	public SaidaDetalhadaEspecialidadeDto buscarSaidaDetalhadaEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		SaidaDetalhadaEspecialidadeDto especialidadeDto = especialidadeMapper
				.especialidadeParaSaidaDetalhadaEspecialidadeDto(especialidadeEntidade);
		
		List<SaidaSimplesMedicoDto> listaMedicosDto = especialidadeEntidade.getMedicos().stream()
				.map(medicoMapper::medicoParaSaidaSimplesMedicoDto)
				.collect(Collectors.toList());
		
		especialidadeDto.setMedicos(listaMedicosDto);
		
		return especialidadeDto;
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesEspecialidadeDto> listarTodasSaidaSimplesEspecialidade() {
		
		var especialidades = especialidadeRepository
				.findAll().stream()
				.map(especialidade -> especialidadeMapper.especialidadeParaSaidaSimplesEspecialidadeDto(especialidade))
				.toList();
		
		return especialidades;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaEspecialidadeDto> listarTodasSaidaDetalhadaEspecialidade() {
		
		List<Especialidade> listaEspecialidades = especialidadeRepository
				.findAll();
		
		var listaEspecialidadesDto = listaEspecialidades.stream()
				.map(especialidade -> {
					
					SaidaDetalhadaEspecialidadeDto dto = especialidadeMapper.especialidadeParaSaidaDetalhadaEspecialidadeDto(especialidade);
					
					List<SaidaSimplesMedicoDto> medicosDto = especialidade.getMedicos().stream()
							.map(medicoMapper::medicoParaSaidaSimplesMedicoDto)
							.collect(Collectors.toList());
					
					dto.setMedicos(medicosDto);
					
					return dto;
					
				}).collect(Collectors.toList());
		
		return listaEspecialidadesDto;
		
	}
	
	@Transactional
	public void atualizarEspecialidadePorId(String especialidadeId, AtualizarEspecialidadeDto atualizarEspecialidadeDto) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		especialidadeMapper.atualizarEspecialidadeDeAtualizarEspecialidadeDto(atualizarEspecialidadeDto, especialidadeEntidade);
		
	}
	
	public void deletarEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeExiste = especialidadeRepository.existsById(id);
		
		if (especialidadeExiste) {
			especialidadeRepository.deleteById(id);
		}
		
	}

}
