package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "sgc_medico")
public class Medico {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "nome", nullable = false, length = 128)
	private String nome;
	
	@Column(name = "sobrenome", nullable = false)
	private String sobrenome;
	
	@Column(name = "crm", nullable = false, unique = true, length = 13)
	private String crm;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private TipoStatusConta status;
	
	@Column(name = "telefone", nullable = false, length = 11)
	private String telefone;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn (name = "especialidade_id")
	private Especialidade especialidade;
	
	@OneToMany(mappedBy = "medico", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	private List<Consulta> consultas = new ArrayList<>();
	
	@CreationTimestamp
	@Column(updatable = false)
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Medico() {
	
	}

	public Medico(UUID id, String nome, String sobrenome, String crm, TipoStatusConta status, String telefone,
			String email, Especialidade especialidade, List<Consulta> consultas, Instant creationTimestamp,
			Instant updateTimestamp) {
		this.id = id;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.crm = crm;
		this.status = status;
		this.telefone = telefone;
		this.email = email;
		this.especialidade = especialidade;
		this.consultas = consultas;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Medico(String nome, String sobrenome, String crm, TipoStatusConta status, String telefone, String email,
			Especialidade especialidade, List<Consulta> consultas, Instant creationTimestamp,
			Instant updateTimestamp) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.crm = crm;
		this.status = status;
		this.telefone = telefone;
		this.email = email;
		this.especialidade = especialidade;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
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

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
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
		return Objects.hash(consultas, creationTimestamp, crm, email, especialidade, id, nome, sobrenome, status,
				telefone, updateTimestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		return Objects.equals(consultas, other.consultas) && Objects.equals(creationTimestamp, other.creationTimestamp)
				&& Objects.equals(crm, other.crm) && Objects.equals(email, other.email)
				&& Objects.equals(especialidade, other.especialidade) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(sobrenome, other.sobrenome)
				&& status == other.status && Objects.equals(telefone, other.telefone)
				&& Objects.equals(updateTimestamp, other.updateTimestamp);
	}

}
