package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "sgc_especialidade")
public class Especialidade {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	
	@Column (name = "descricao")
	private String descricao;
	
	@OneToMany(mappedBy = "especialidade", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Doctor> medicos = new ArrayList<>();
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Especialidade() {
		
	}

	public Especialidade(String descricao, Instant creationTimestamp, Instant updateTimestamp) {
		super();
		this.descricao = descricao;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Especialidade(UUID id, String descricao, Instant creationTimestamp, Instant updateTimestamp) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public List<Doctor> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Doctor> medicos) {
		this.medicos = medicos;
	}

}
