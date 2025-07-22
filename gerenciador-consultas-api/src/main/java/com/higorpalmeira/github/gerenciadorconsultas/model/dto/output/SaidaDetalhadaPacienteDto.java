package com.higorpalmeira.github.gerenciadorconsultas.model.dto.output;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

public class SaidaDetalhadaPacienteDto {
	
	private UUID id;
	
	private String nome;
	
	private String sobrenome;
	
	private String cpf;
	
	private LocalDate dataNascimento;
	
	private TipoGenero genero;
	
	private TipoStatusConta status;
	
	private String telefone;
	
	private String email;
	
	private SaidaEnderecoDto endereco;
	
	private List<SaidaSimplesConsultaDto> consultas;

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

	public TipoStatusConta getStatus() {
		return status;
	}

	public void setStatus(TipoStatusConta status) {
		this.status = status;
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

	public SaidaEnderecoDto getEndereco() {
		return endereco;
	}

	public void setEndereco(SaidaEnderecoDto endereco) {
		this.endereco = endereco;
	}

	public List<SaidaSimplesConsultaDto> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<SaidaSimplesConsultaDto> consultas) {
		this.consultas = consultas;
	}

}
