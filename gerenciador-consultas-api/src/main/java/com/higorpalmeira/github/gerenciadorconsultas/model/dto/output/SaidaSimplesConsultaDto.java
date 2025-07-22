package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.time.LocalDateTime;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

public class SaidaSimplesConsultaDto {
	
	private LocalDateTime dataHora;
	
	private TipoStatusConsulta status;
	
	private String observacoes;
	
	private float valor;
	
	private SaidaSimplesMedicoDto medico;
	
	private SaidaSimplesPacienteDto paciente;

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

	public SaidaSimplesMedicoDto getMedico() {
		return medico;
	}

	public void setMedico(SaidaSimplesMedicoDto medico) {
		this.medico = medico;
	}

	public SaidaSimplesPacienteDto getPaciente() {
		return paciente;
	}

	public void setPaciente(SaidaSimplesPacienteDto paciente) {
		this.paciente = paciente;
	}

}
