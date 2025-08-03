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
	
	/*
	 * Busca pela Consulta com a data e hora específica.
	 * 
	 * @param dataHora A data e hora específica da busca.
	 * @return Optional com Consulta presente se a consulta for encontrada, caso contrário um Optional vazio.
	 * */
	Optional<Consulta> findByDataHora(LocalDateTime dataHora);
	
	/*
	 * Busca por todas as Consultas antes da data e hora específicas.
	 * 
	 * @param dataLimite A data e hora limite da busca.
	 * @return Lista com todas as consultas antes da data e hora especificada, ou uma lista vazia.
	 * */
	List<Consulta> findByDataHoraBefore(LocalDateTime dataLimite);
	
	/*
	 * Busca por todas as Consultas depois da data e hora específicas.
	 * 
	 * @param dataLimite A data e hora limite da busca.
	 * @return Lista com todas as consultas depois da data e hora especificada, ou uma lista vazia.
	 * */
	List<Consulta> findByDataHoraAfter(LocalDateTime dataInicial);
	
	/*
	 * Busca por todas as Consultas dentro de um intervalo de data e hora.
	 * 
	 * @param dataLimite A data e hora limite da busca.
	 * @return Lista com todas as consultas no intervalo de data e hora especificado, ou uma lista vazia.
	 * */
	List<Consulta> findByDataHoraBetween(LocalDateTime inicioDoIntervalo, LocalDateTime fimDoIntervalo);
	
	/*
	 * Busca por todas as Consultas depois da data e hora específicas, ordenadas pela data (os mais próximos primeiro).
	 * 
	 * @param dataLimite A data e hora limite da busca.
	 * @return Lista com todas as consultas depois da data e hora especificada, ou uma lista vazia.
	 * */
	List<Consulta> findByDataHoraAfterOrderByDataHoraAsc(LocalDateTime dataHora);
	
	/*
	 * Busca por todas as Consultas que contenham as observações especificadas.
	 * 
	 * @param observacoes As observações a serem buscadas.
	 * @return Lista com todas as consultas que contenham as observações especificadas, ou uma lista vazia.
	 * */
	List<Consulta> findByObservacoesContainingIgnoreCase(String observacoes);
	
	/*
	 * Busca por todas as Consultas que contenham o valor especificado.
	 * 
	 * @param valor O valor exato a ser procurado.
	 * @return Lista com todas as consultas com o valor exato especificado, ou uma lista vazia.
	 * */
	List<Consulta> findByValor(BigDecimal valor);
	
	/*
	 * Busca por todas as Consultas que contenham o valor maior que o específicado.
	 * 
	 * @param valor O valor limite a ser procurado.
	 * @return Lista com todas as consultas com o valor maior que o especificado, ou uma lista vazia.
	 * */
	List<Consulta> findByValorGreaterThan(BigDecimal valor);
	
	/*
	 * Busca por todas as Consultas que contenham o valor menor ou igual que o específicado.
	 * 
	 * @param valor O valor limite a ser procurado.
	 * @return Lista com todas as consultas com o valor menor ou igual que o especificado, ou uma lista vazia.
	 * */
	List<Consulta> findByValorLessThanEqual(BigDecimal valor);
	
	/*
	 * Busca por todas as Consultas que contenham o valor entre o intervalo especificado.
	 * 
	 * @param valorInicial O valor do início do intervalo.
	 * @param valoFinal O valor final do intervalo.
	 * @return Lista com todas as consultas com o valor maior que o especificado, ou uma lista vazia.
	 * */
	List<Consulta> findByValorBetween(BigDecimal valorInicial, BigDecimal valorFinal);
	
	/*
	 * Busca por todas as Consultas que contenham o status especificado.
	 * 
	 * @param status O status a ser procurado.
	 * @return Lista com todas as consultas com o status especificado, ou uma lista vazia.
	 * */
	List<Consulta> findAllByStatus(TipoStatusConsulta status);
	
	/*
	 * Busca por todas as Consultas que não contenham o status especificado.
	 * 
	 * @param status O status a ser evitado.
	 * @return Lista com todas as consultas com o status especificado, ou uma lista vazia.
	 * */
	List<Consulta> findAllByStatusNot(TipoStatusConsulta status);

}
