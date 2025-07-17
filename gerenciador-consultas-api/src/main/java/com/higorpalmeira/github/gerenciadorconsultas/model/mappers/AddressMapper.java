package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CreateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.OutputAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.UpdateAddressDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
	
	/*
	 * Cria uma entidade 'Address' com os dados do DTO.
	 * 
	 * @param createAddressDto O objeto com os dados.
	 * @return Address A entidade criada.
	 * */
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	Address createToAddress(CreateAddressDto createAddressDto);
	
	/*
	 * Cria um DTO de saída a partir da entidade 'Address'.
	 * 
	 * @param address Entidade a ser transformada.
	 * @return OutputAddressDto DTO de saída criado.
	 * */
	OutputAddressDto addressToOutputAddressDto(Address address);
	
	/*
	 * Atualiza a entidade 'Address' com os dados não nulos do DTO.
	 * 
	 * @param updateAddressDto O objeto com os dados para atualização.
	 * @param address A entidade que será atualizada (carregada do banco de dados).
	 * */
	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	void updateAddress(UpdateAddressDto updateAddressDto, @MappingTarget Address address);

}
