package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

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

}
