package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Doctor;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
	
	/*
	 * Verifica se existe um médico com o CRM fornecido.
	 * 
	 * @param crm O CRM a ser verificado.
	 * @return true se um médico com este CPF existir, caso contrário false.
	 * */
	boolean existsByCrm(String crm);
	
	/*
	 * Verifica se existe um médico com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser verificado.
	 * @return true se um médico existir com este e-mail, caso contrário false.
	 * */
	boolean existsByEmail(String email);
	
	/*
	 * Busca por um médico com o CRM fornecido.
	 * 
	 * @param crm O CRM a ser procurado.
	 * @return Optional com Doctor presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Doctor> findByCrm(String crm);
	
	/*
	 * Busca por um médico com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser procurado.
	 * @return Optional com Doctor presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Doctor> findByEmail(String email);
	
	List<Doctor> findAllByStatus(TipoStatusConta status);
	
}
