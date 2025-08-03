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
	
	List<Paciente> findByNome(String nome);
	
	List<Paciente> findByNomeContainingIgnoreCase(String nome);
	
	List<Paciente> findBySobrenome(String sobrenome);
	
	List<Paciente> findBySobrenomeContainingIgnoreCase(String sobrenome);
	
	List<Paciente> findAllByGenero(TipoGenero genero);
	
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
	 * @return Lista com todos os pacientes com o status fornecido, ou uma lista vazia.
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
