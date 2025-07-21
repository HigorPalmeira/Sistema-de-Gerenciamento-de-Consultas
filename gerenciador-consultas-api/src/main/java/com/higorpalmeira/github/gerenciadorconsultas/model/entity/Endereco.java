package com.higorpalmeira.github.gerenciadorconsultas.model.entity;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sgc_endereco")
public class Endereco {
	
	@Id
	@GeneratedValue (strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "cep")
	private String cep;
	
	@Column(name = "rua")
	private String rua;
	
	@Column(name = "complemento")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairro;
	
	@Column(name = "localidade")
	private String localidade;
	
	@Column(name = "uf")
	private String uf;
	
	@CreationTimestamp
	private Instant creationTimestamp;
	
	@UpdateTimestamp
	private Instant updateTimestamp;

	public Endereco() {
		
	}

	public Endereco(String cep, String rua, String complemento, String bairro, 
			String localidade, String uf, Instant creationTimestamp, 
			Instant updateTimestamp) {
		this.cep = cep;
		this.rua = rua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public Endereco(UUID id, String cep, String rua, String complemento,
			String bairro, String localidade, String uf, 
			Instant creationTimestamp, Instant updateTimestamp) {
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.creationTimestamp = creationTimestamp;
		this.updateTimestamp = updateTimestamp;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
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
		return Objects.hash(bairro, cep, complemento, creationTimestamp, id, localidade, rua, uf, updateTimestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(bairro, other.bairro) && Objects.equals(cep, other.cep)
				&& Objects.equals(complemento, other.complemento)
				&& Objects.equals(creationTimestamp, other.creationTimestamp) && Objects.equals(id, other.id)
				&& Objects.equals(localidade, other.localidade) && Objects.equals(rua, other.rua)
				&& Objects.equals(uf, other.uf) && Objects.equals(updateTimestamp, other.updateTimestamp);
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", cep=" + cep + ", rua=" + rua + ", complemento=" + complemento + ", bairro="
				+ bairro + ", localidade=" + localidade + ", uf=" + uf + ", creationTimestamp=" + creationTimestamp
				+ ", updateTimestamp=" + updateTimestamp + "]";
	}

}
