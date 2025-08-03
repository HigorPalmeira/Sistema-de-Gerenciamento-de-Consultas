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
	 * Verifica se existe um médico com o Telefone fornecido.
	 * 
	 * @param telefone O Telefone a ser verificado.
	 * @return true se um médico existir com este telefone, caso contrário false.
	 * */
	boolean existsByTelefone(String telefone);
	
	/*
	 * Busca por um médico com o CRM fornecido.
	 * 
	 * @param crm O CRM a ser procurado.
	 * @return Optional com Médico presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Medico> findByCrm(String crm);
	
	/*
	 * Busca por um médico com o E-mail fornecido.
	 * 
	 * @param email O E-mail a ser procurado.
	 * @return Optional com Médico presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Medico> findByEmail(String email);
	
	/*
	 * Busca por um médico com Telefone fornecido.
	 * 
	 * @param telefone O Telefone a ser procurado.
	 * @return Optional com Médico presente se o médico for encontrado, caso contrário um Optional vazio.
	 * */
	Optional<Medico> findByTelefone(String telefone);
	
	/*
	 * Busca por todos os médicos com o Nome exato do fornecido.
	 * 
	 * @param nome O Nome exato a ser procurado.
	 * @return Lista com todos os médicos com o nome fornecido, ou uma lista vazia.
	 * */
	List<Medico> findByNome(String nome);
	
	/*
	 * Busca por todos os médicos que contenham o Nome fornecido, ignorando case (maiúsculas ou minúsculas).
	 * 
	 * @param nome O Nome a ser procurado.
	 * @return Lista com todos os médicos com o nome fornecido, ou uma lista vazia.
	 * */
	List<Medico> findByNomeContainingIgnoreCase(String nome);
	
	/*
	 * Busca por todos os médicos com o Sobrenome exato do fornecido.
	 * 
	 * @param sobrenome O Sobrenome exato a ser procurado.
	 * @return Lista com todos os médicos com o nome fornecido, ou uma lista vazia.
	 * */
	List<Medico> findBySobrenome(String sobrenome);
	
	/*
	 * Busca por todos os médicos que contenham o Sobrenome fornecido, ignorando case (maiúsculas ou minúsculas).
	 * 
	 * @param sobrenome O Sobrenome a ser procurado.
	 * @return Lista com todos os médicos com o sobrenome fornecido, ou uma lista vazia.
	 * */
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
