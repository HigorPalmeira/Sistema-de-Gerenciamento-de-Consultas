package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.util.List;
import java.util.UUID;

public class SaidaDetalhadaEspecialidadeDto {

	private UUID id;
	
	private String descricao;
	
	private List<SimpleOutputDoctorDto> medicos;

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

	public List<SimpleOutputDoctorDto> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<SimpleOutputDoctorDto> medicos) {
		this.medicos = medicos;
	}
	
}
