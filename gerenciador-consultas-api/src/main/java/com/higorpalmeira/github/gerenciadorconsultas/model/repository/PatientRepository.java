package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;

@Repository
public interface PatientRepository extends JpaRepository<Paciente, UUID> {
	
	/*
	 * Verifica se existe um paciente com o CPF fornecido.
	 * 
	 * @param cpf O CPF a ser verificado.
	 * @return true se um paciente com este CPF existir, caso contrário false.
	 * */
	boolean existsByCpf(String cpf);
	
	/*
	 * Verifica se existe um paciente com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser verifciado.
	 * @return true se um paciente com este e-mail existir, caso contrário false.
	 * */
	boolean existsByEmail(String email);
	
	/*
	 * Busca por um paciente com o CPF fornecido.
	 * 
	 * @param cpf O CPF a ser procurado.
	 * @return Optional com Patient presente se o paciente for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Paciente> findByCpf(String cpf);
	
	/*
	 * Busca por um paciente com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser procurado.
	 * @return Optional com Patient presente se o paciente for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Paciente> findByEmail(String email);

}
