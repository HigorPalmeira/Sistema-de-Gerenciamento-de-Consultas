package com.higorpalmeira.github.gerenciadorconsultas.model.dto.update;

import java.time.LocalDateTime;
import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

public class AtualizarConsultaDto {

	private LocalDateTime dataHora;
	
	private TipoStatusConsulta status;
	
	private String observacoes;
	
	private float valor;
	
	private UUID medicoId;
	
	private UUID pacienteId;

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public TipoStatusConsulta getStatus() {
		return status;
	}

	public void setStatus(TipoStatusConsulta status) {
		this.status = status;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
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
