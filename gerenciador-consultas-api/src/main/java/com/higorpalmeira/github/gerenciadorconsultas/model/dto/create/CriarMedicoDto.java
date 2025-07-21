package com.higorpalmeira.github.gerenciadorconsultas.model.dto.create;

import java.util.UUID;

public class CriarMedicoDto {
	
	private String nome;
	
	private String sobrenome;
	
	private String crm;
	
	private String telefone;
	
	private String email;

	private UUID especialidadeId;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getEspecialidadeId() {
		return especialidadeId;
	}

	public void setEspecialidadeId(UUID especialidadeId) {
		this.especialidadeId = especialidadeId;
	}
	
}
