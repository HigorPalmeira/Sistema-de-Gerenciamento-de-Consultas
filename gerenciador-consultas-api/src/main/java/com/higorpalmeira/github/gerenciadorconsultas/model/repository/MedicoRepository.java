package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, UUID> {
	
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
	Optional<Medico> findByCrm(String crm);
	
	/*
	 * Busca por um médico com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser procurado.
	 * @return Optional com Doctor presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Medico> findByEmail(String email);
	
	List<Medico> findByNome(String nome);
	
	List<Medico> findByNomeContainingIgnoreCase(String nome);
	
	List<Medico> findBySobrenome(String sobrenome);
	
	List<Medico> findBySobrenomeContainingIgnoreCase(String sobrenome);
	
	/*
	 * Busca por todos os médicos com o Status fornecido.
	 * 
	 * @param status O Status a ser procurado.
	 * @return Lista com todos os médicos com o status fornecido, ou uma lista vazia.
	 * */
	List<Medico> findAllByStatus(TipoStatusConta status);
	
	/*
	 * Busca por todos os médicos que não tenham o Status fornecido.
	 * 
	 * @param status O Status a ser evitado.
	 * @return Lista com todos os médicos com o status fornecido, ou uma lista vazia.
	 * */
	List<Medico> findAllByStatusNot(TipoStatusConsulta status);
	
	/*
	 * Busca por todos os médicos que possuam a Especialidade fornecida.
	 * 
	 * @param especialidade A Especialidade a ser procurada.
	 * @return Lista com todos os médicos com a especialidade fornecida, ou uma lista vazia.
	 * */
	List<Medico> findAllByEspecialidade(Especialidade especialidade);
	
}
