package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {
	
	/*
	 * Verifica se existe uma especialidade com a descrição fornecida.
	 * 
	 * @param description A descrição da especialidade a ser verificado.
	 * @return true se uma especialidade com esta descrição existir, caso contrário false.
	 * */
	boolean existsByDescription(String description);

}
