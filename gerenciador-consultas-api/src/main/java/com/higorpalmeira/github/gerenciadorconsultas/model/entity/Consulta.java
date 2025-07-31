package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConsulta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_consulta")
public class Consulta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "data_hora", nullable = false, unique = true)
	private LocalDateTime dataHora;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TipoStatusConsulta status;
	
	@Column(name = "observacoes")
	private String observacoes;
	
	@Column(name = "valor", precision = 10, scale = 2)
	private BigDecimal valor;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Consulta() {
		
	}

	public Consulta(LocalDateTime dataHora, TipoStatusConsulta status, String observacoes, BigDecimal valor, Medico medico,
			Paciente paciente, Instant creationTimestamp, Instant updateTimestamp) {
		this.dataHora = dataHora;
		this.status = status;
		this.observacoes = observacoes;
		this.valor = valor;
		this.medico = medico;
		this.paciente = paciente;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Consulta(UUID id, LocalDateTime dataHora, TipoStatusConsulta status, String observacoes, BigDecimal valor,
			Medico medico, Paciente paciente, Instant creationTimestamp, Instant updateTimestamp) {
		this.id = id;
		this.dataHora = dataHora;
		this.status = status;
		this.observacoes = observacoes;
		this.valor = valor;
		this.medico = medico;
		this.paciente = paciente;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
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

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Instant getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(Instant creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public Instant getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Instant updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(creationTimestamp, dataHora, id, medico, observacoes, paciente, status, updateTimestamp,
				valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		return Objects.equals(creationTimestamp, other.creationTimestamp) && Objects.equals(dataHora, other.dataHora)
				&& Objects.equals(id, other.id) && Objects.equals(medico, other.medico)
				&& Objects.equals(observacoes, other.observacoes) && Objects.equals(paciente, other.paciente)
				&& status == other.status && Objects.equals(updateTimestamp, other.updateTimestamp)
				&& Objects.equals(valor, other.valor);
	}

	
}
