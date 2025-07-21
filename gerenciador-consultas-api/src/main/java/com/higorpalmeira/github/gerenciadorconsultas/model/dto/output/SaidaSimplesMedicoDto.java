package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

public class SaidaSimplesMedicoDto {
	
	private UUID id;

	private String nome;
	
	private String sobrenome;
	
	private String crm;
	
	private String telefone;
	
	private String email;
	
	private TipoStatusConta status;

	private SaidaSimplesEspecialidadeDto especialidade;
	
	private int consultas;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

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

	public TipoStatusConta getStatus() {
		return status;
	}

	public void setStatus(TipoStatusConta status) {
		this.status = status;
	}

	public SaidaSimplesEspecialidadeDto getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(SaidaSimplesEspecialidadeDto especialidade) {
		this.especialidade = especialidade;
	}

	public int getConsultas() {
		return consultas;
	}

	public void setConsultas(int consultas) {
		this.consultas = consultas;
	}

}
