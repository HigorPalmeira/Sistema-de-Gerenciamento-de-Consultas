package com.higorpalmeira.github.gerenciadorconsultas.model.dto.create;

import java.time.LocalDate;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;

public class CriarPacienteDto {
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private TipoGenero genero;
	
	private String telefone;
	
	private String email;
	
	private CriarEnderecoDto endereco;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public TipoGenero getGenero() {
		return genero;
	}

	public void setGenero(TipoGenero genero) {
		this.genero = genero;
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

	public CriarEnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(CriarEnderecoDto endereco) {
		this.endereco = endereco;
	}
	
}
