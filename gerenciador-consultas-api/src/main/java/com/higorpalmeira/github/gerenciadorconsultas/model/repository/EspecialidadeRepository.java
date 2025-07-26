package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Especialidade;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, UUID> {
	
	/*
	 * Verifica se existe uma especialidade com a descrição fornecida.
	 * 
	 * @param descricao A descrição da especialidade a ser verificado.
	 * @return true se uma especialidade com esta descrição existir, caso contrário false.
	 * */
	boolean existsByDescricao(String descricao);
	
	/*
	 * Busca por uma especialidade com a descrição fornecida.
	 * 
	 * @param descricao A descrição da especialidade a ser procurada.
	 * @return Optional com Especialidade se a especialidade for encontrada, caso contrário um Optional vazio.
	 * */
	Optional<Especialidade> findByDescricao(String descricao);
	
	/*
	 * Busca por especialidades cuja descrição contenha o termo pesquisado, ignorando maiúsculas/minúsculas.
	 * 
	 * @param descricao A descrição da especialidade a ser procurada.
	 * @return Lista com as especialidades que contenham a descrição buscada.
	 * */
	List<Especialidade> findByDescricaoContainingIgnoreCase(String descricao);
	
	/*
	 * Buscar por especialidades cuja descrição comece com o termo pesquisado.
	 * 
	 * @param descricao A descrição da especialidade a ser procurada.
	 * @return Lista com as especialidades que começam com o termo pesquisado.
	 * */
	List<Especialidade> findByDescricaoStartingWithIgnoreCase(String descricao);

}
