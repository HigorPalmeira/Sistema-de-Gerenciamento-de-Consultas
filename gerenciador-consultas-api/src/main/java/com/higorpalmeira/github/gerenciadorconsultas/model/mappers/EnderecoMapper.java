package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Endereco;

@Mapper(componentModel = "spring")
public abstract class EnderecoMapper {
	
	/*
	 * Cria uma entidade 'Endereco' com os dados do DTO.
	 * 
	 * @param criarEnderecoDto O objeto com os dados.
	 * @return Endereco A entidade criada.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	public abstract Endereco criarEnderecoDtoParaEndereco(CriarEnderecoDto criarEnderecoDto);
	
	/*
	 * Cria um DTO de saída a partir da entidade 'Endereco'.
	 * 
	 * @param endereco Entidade a ser transformada.
	 * @return EnderecoParaSaidaEnderecoDto DTO de saída criado.
	 * */
	public abstract SaidaEnderecoDto EnderecoParaSaidaEnderecoDto(Endereco endereco);
	
	/*
	 * Atualiza a entidade 'Endereco' com os dados não nulos do DTO.
	 * 
	 * @param atualizarEnderecoDto O objeto com os dados para atualização.
	 * @param endereco A entidade que será atualizada (carregada do banco de dados).
	 * */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	public abstract void updateAddressFromUpdateAddressDto(AtualizarEnderecoDto atualizarEnderecoDto, @MappingTarget Endereco endereco);

}
