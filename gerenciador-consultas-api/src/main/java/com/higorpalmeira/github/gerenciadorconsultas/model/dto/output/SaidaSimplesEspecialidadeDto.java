package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.util.UUID;

public class SaidaSimplesEspecialidadeDto {
	
	private UUID id;
	
	private String descricao;
	
	private int medicosAssociados;
	
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
	
	public int getMedicosAssociados() {
		return medicosAssociados;
	}
	
	public void setMedicosAssociados(int medicosAssociados) {
		this.medicosAssociados = medicosAssociados;
	}

}
