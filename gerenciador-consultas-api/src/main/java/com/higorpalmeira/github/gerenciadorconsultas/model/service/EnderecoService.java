package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.OutputAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.exceptions.ResourceNotFoundException;
import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.AddressMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	private EnderecoRepository enderecoRepository;
	
	private AddressMapper addressMapper;
	
	public EnderecoService(EnderecoRepository enderecoRepository, AddressMapper addressMapper) {
		this.enderecoRepository = enderecoRepository;
		this.addressMapper = addressMapper;
	}
	
	@Transactional
	public UUID criarEndereco(CreateAddressDto criarEnderecoDto) {
		
		var endereco = addressMapper.createToAddress(criarEnderecoDto);
		
		var enderecoSalvo = enderecoRepository.save(endereco);
		
		return enderecoSalvo.getId();
		
	}
	
	@Transactional(readOnly = true)
	public OutputAddressDto buscarEnderecoPorId(String enderecoId) {
		
		var id = UUID.fromString(enderecoId);
		var enderecoEntidade = enderecoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		return addressMapper.addressToOutputAddressDto(enderecoEntidade);
		
	}
	
	@Transactional(readOnly = true)
	public List<OutputAddressDto> listarTodosEnderecos() {
		
		var enderecos = enderecoRepository
				.findAll().stream()
				.map(endereco -> addressMapper.addressToOutputAddressDto(endereco))
				.toList();
		
		return enderecos;
		
	}
	
	@Transactional
	public void atualizarEnderecoPorId(String enderecoId, UpdateAddressDto atualizarEnderecoDto) {
		
		var id = UUID.fromString(enderecoId);
		var enderecoEntidade = enderecoRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado com ID: " + id));
		
		addressMapper.updateAddressFromUpdateAddressDto(atualizarEnderecoDto, enderecoEntidade);
		
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
