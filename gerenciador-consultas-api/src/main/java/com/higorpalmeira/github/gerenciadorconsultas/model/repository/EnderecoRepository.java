package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {

	/*
	 * Busca por um endereco com o CEP fornecido.
	 * 
	 * @param cep O CEP a ser procurado.
	 * @return Optional com Endereco presente se o endereco for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Endereco> findByCep(String cep);
	
}
