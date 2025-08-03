package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {
	
	Optional<Consulta> findByDataHora(LocalDateTime dataHora);
	
	List<Consulta> findByObservacoesContainingIgnoreCase(String observacoes);
	
	List<Consulta> findByValor(BigDecimal valor);
	
	List<Consulta> findAllByStatus(TipoStatusConsulta status);
	
	List<Consulta> findAllByStatusNot(TipoStatusConsulta status);

}
