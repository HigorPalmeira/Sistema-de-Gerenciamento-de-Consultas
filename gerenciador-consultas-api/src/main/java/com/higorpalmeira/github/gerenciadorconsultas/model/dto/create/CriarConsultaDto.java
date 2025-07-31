package com.higorpalmeira.github.gerenciadorconsultas.model.dto.create;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CriarConsultaDto {
	
	private LocalDateTime dataHora;
	
	private String observacoes;
	
	private BigDecimal valor;
	
	private UUID medicoId;
	
	private UUID pacienteId;

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public UUID getMedicoId() {
		return medicoId;
	}

	public void setMedicoId(UUID medicoId) {
		this.medicoId = medicoId;
	}

	public UUID getPacienteId() {
		return pacienteId;
	}

	public void setPacienteId(UUID pacienteId) {
		this.pacienteId = pacienteId;
	}

}
