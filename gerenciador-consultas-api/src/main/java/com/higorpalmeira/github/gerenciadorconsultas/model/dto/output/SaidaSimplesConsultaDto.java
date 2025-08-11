package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

public class SaidaSimplesConsultaDto {
	
	private UUID idConsulta;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
	private LocalDateTime dataHora;
	
	private TipoStatusConsulta status;
	
	private String observacoes;
	
	private BigDecimal valor;
	
	private SaidaSimplesMedicoDto medico;
	
	private SaidaSimplesPacienteDto paciente;

	public UUID getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(UUID idConsulta) {
		this.idConsulta = idConsulta;
	}

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

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
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
