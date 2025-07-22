package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.TipoStatusConta;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sgc_paciente")
public class Paciente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@Column(name = "cpf")
	private String cpf;
	
	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private TipoGenero genero;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TipoStatusConta status;
	
	@Column(name = "telefone")
	private String telefone;
	
	@Column(name = "email")
	private String email;
	
	@OneToOne (cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn (name = "endereco_id", referencedColumnName = "id")
	private Endereco endereco;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Consulta> consultas = new ArrayList<>();
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Paciente() {
		
	}

	public Paciente(UUID id, String nome, String sobrenome, String cpf, LocalDate dataNascimento, TipoGenero genero,
			TipoStatusConta status, String telefone, String email, Endereco endereco, List<Consulta> consultas,
			Instant creationTimestamp, Instant updateTimestamp) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.status = status;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.consultas = consultas;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Paciente(String nome, String sobrenome, String cpf, LocalDate dataNascimento, TipoGenero genero,
			TipoStatusConta status, String telefone, String email, Endereco endereco, List<Consulta> consultas,
			Instant creationTimestamp, Instant updateTimestamp) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.genero = genero;
		this.status = status;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;
		this.consultas = consultas;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
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
		return Objects.hash(consultas, cpf, creationTimestamp, dataNascimento, email, endereco, genero, id, nome,
				sobrenome, status, telefone, updateTimestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(consultas, other.consultas) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(email, other.email)
				&& Objects.equals(endereco, other.endereco) && genero == other.genero && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(sobrenome, other.sobrenome)
				&& status == other.status && Objects.equals(telefone, other.telefone)
				&& Objects.equals(updateTimestamp, other.updateTimestamp);
	}

}
