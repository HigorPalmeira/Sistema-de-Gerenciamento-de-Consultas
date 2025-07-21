package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EspecialidadeMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService {
	
	private EspecialidadeRepository especialidadeRepository;
	
	private EspecialidadeMapper specialityMapper;
	
	public EspecialidadeService(EspecialidadeRepository especialidadeRepository, EspecialidadeMapper specialityMapper) {
		this.especialidadeRepository = especialidadeRepository;
		this.specialityMapper = specialityMapper;
	}
	
	@Transactional
	public UUID criarEspecialidade(CriarEspecialidadeDto criarEspecialidadeDto) {
		
		var especialidade = specialityMapper.criarEspecialidadeDtoParaEspecialidade(criarEspecialidadeDto);
		
		var especialidadeSalva = especialidadeRepository.save(especialidade);
		
		return especialidadeSalva.getId();
		
	}
	
	@Transactional(readOnly = true)
	public SaidaSimplesEspecialidadeDto buscarSaidaSimplesEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		return specialityMapper.specialityToSimpleOutputSpecialityDto(especialidadeEntidade);
		
	}
	
	@Transactional(readOnly = true)
	public SaidaDetalhadaEspecialidadeDto buscarSaidaDetalhadaEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.map(especialidade -> specialityMapper.specialityToDetailedOutputSpecialityDto(especialidade))
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		return especialidadeEntidade;
	}
	
	@Transactional(readOnly = true)
	public List<SaidaSimplesEspecialidadeDto> listarTodasSaidaSimplesEspecialidade() {
		
		var especialidades = especialidadeRepository
				.findAll().stream()
				.map(especialidade -> specialityMapper.specialityToSimpleOutputSpecialityDto(especialidade))
				.toList();
		
		return especialidades;
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaDetalhadaEspecialidadeDto> listarTodasSaidaDetalhadaEspecialidade() {
		
		var especialidades = especialidadeRepository
				.findAll().stream()
				.map(especialidade -> specialityMapper.specialityToDetailedOutputSpecialityDto(especialidade))
				.toList();
		
		return especialidades;
		
	}
	
	@Transactional
	public void atualizarEspecialidadePorId(String especialidadeId, UpdateSpecialityDto atualizarEspecialidadeDto) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeEntidade = especialidadeRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		specialityMapper.updateSpecialityFromUpdateSpecialityDto(atualizarEspecialidadeDto, especialidadeEntidade);
		
	}
	
	public void deletarEspecialidadePorId(String especialidadeId) {
		
		var id = UUID.fromString(especialidadeId);
		var especialidadeExiste = especialidadeRepository.existsById(id);
		
		if (especialidadeExiste) {
			especialidadeRepository.deleteById(id);
		}
		
	}

}
