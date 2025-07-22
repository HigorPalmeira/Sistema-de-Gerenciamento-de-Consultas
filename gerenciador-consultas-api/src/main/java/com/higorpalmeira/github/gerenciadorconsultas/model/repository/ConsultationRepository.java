package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

@Repository
public interface ConsultationRepository extends JpaRepository<Consulta, UUID> {
	
	List<Consulta> findAllByStatus(TipoStatusConsulta status);
	
	List<Consulta> findAllByStatusNot(TipoStatusConsulta status);

}
