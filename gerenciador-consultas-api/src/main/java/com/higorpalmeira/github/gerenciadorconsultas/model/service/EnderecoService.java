package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.EnderecoMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EnderecoRepository;
import com.higorpalmeira.github.gerenciadorconsultas.util.Formatter;

@Service
public class EnderecoService {
	
	private EnderecoRepository enderecoRepository;
	
	private EnderecoMapper addressMapper;
	
	public EnderecoService(EnderecoRepository enderecoRepository, EnderecoMapper addressMapper) {
		this.enderecoRepository = enderecoRepository;
		this.addressMapper = addressMapper;
	}
	
	@Transactional
	public UUID criarEndereco(CriarEnderecoDto criarEnderecoDto) {
		
		var endereco = addressMapper.criarEnderecoDtoParaEndereco(criarEnderecoDto);
		endereco.setCep( Formatter.clearCpfCepCrmTelefone(endereco.getCep()) );
		
		var enderecoSalvo = enderecoRepository.save(endereco);
		
		return enderecoSalvo.getId();
		
	}
	
	@Transactional(readOnly = true)
	public SaidaEnderecoDto buscarEnderecoPorId(String enderecoId) {
		
		var id = UUID.fromString(enderecoId);
		var enderecoEntidade = enderecoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		enderecoEntidade.setCep( Formatter.ofCep(enderecoEntidade.getCep()) );
		
		return addressMapper.EnderecoParaSaidaEnderecoDto(enderecoEntidade);
		
	}
	
	@Transactional(readOnly = true)
	public List<SaidaEnderecoDto> listarTodosEnderecos() {
		
		var enderecos = enderecoRepository
				.findAll().stream()
				.map(endereco -> addressMapper.EnderecoParaSaidaEnderecoDto(endereco))
				.toList();
		
		return enderecos;
		
	}
	
	@Transactional
	public void atualizarEnderecoPorId(String enderecoId, AtualizarEnderecoDto atualizarEnderecoDto) {
		
		var id = UUID.fromString(enderecoId);
		var enderecoEntidade = enderecoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		addressMapper.atualizarEnderecoDeAtualizarEnderecoDto(atualizarEnderecoDto, enderecoEntidade);
		
	}
	
	@Transactional
	public void deletarEnderecoPorId(String enderecoId) {
		
		var id = UUID.fromString(enderecoId);
		var enderecoExiste = enderecoRepository.existsById(id);
		
		if (enderecoExiste) {
			enderecoRepository.deleteById(id);
		}
		
	}

}
