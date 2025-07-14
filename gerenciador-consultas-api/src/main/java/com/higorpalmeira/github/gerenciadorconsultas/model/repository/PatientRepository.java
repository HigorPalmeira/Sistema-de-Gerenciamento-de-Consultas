package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
	
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

}
