package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
	
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
	
	/*
	 * Busca por todos os pacientes com o Nome exato do fornecido.
	 * 
	 * @param nome O Nome exato a ser procurado.
	 * @return Lista com todos os pacientes com o nome fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findByNome(String nome);
	
	/*
	 * Busca por todos os pacientes que contenham o Nome fornecido, ignorando case (maiúsculas ou minúsculas).
	 * 
	 * @param nome O Nome a ser procurado.
	 * @return Lista com todos os pacientes com o nome fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findByNomeContainingIgnoreCase(String nome);
	
	/*
	 * Busca por todos os pacientes com o Sobrenome exato do fornecido.
	 * 
	 * @param sobrenome O Sobrenome exato a ser procurado.
	 * @return Lista com todos os pacientes com o sobrenome fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findBySobrenome(String sobrenome);
	
	/*
	 * Busca por todos os pacientes que contenham o Sobrenome, ignorando case (maiúsculas ou minúsculas).
	 * 
	 * @param sobrenome O Sobrenome exato a ser procurado.
	 * @return Lista com todos os pacientes com o sobrenome fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findBySobrenomeContainingIgnoreCase(String sobrenome);
	
	/*
	 * Busca por todos os pacientes com o Genero fornecido.
	 * 
	 * @param genero O Genero a ser procurado.
	 * @return Lista com todos os pacientes com o genero fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findAllByGenero(TipoGenero genero);
	
	/*
	 * Busca por todos os pacientes que não tenham o Genero fornecido.
	 * 
	 * @param genero O Genero a ser evitado.
	 * @return Lista com todos os pacientes com o genero exceto o fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findAllByGeneroNot(TipoGenero genero);

	/*
	 * Busca por todos os pacientes com o Status fornecido.
	 * 
	 * @param status O Status a ser procurado.
	 * @return Lista com todos os pacientes com o status fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findAllByStatus(TipoStatusConta status);
	
	/*
	 * Busca por todos os pacientes que não tenham o Status fornecido.
	 * 
	 * @param status O Status a ser evitado.
	 * @return Lista com todos os pacientes com o status exceto o fornecido, ou uma lista vazia.
	 * */
	List<Paciente> findAllByStatusNot(TipoStatusConta status);
	
	/*
	 * Busca por todos os pacientes com a Data de Nascimento fornecida.
	 * 
	 * @param dataNascimento A Data de Nascimento a ser procurado.
	 * @return Lista com todos os pacientes com a data de nascimento fornecida, ou uma lista vazia.
	 * */
	List<Paciente> findAllByDataNascimento(LocalDate dataNascimento);
}
