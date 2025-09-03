/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConta;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class SaidaDetalhadaMedicoDto {

    private UUID id;

    private String nome;

    private String sobrenome;

    private String crm;

    private String telefone;

    private String email;

    private TipoStatusConta status;

    private SaidaSimplesEspecialidadeDto especialidade;

    private List<SaidaSimplesConsultaDto> consultas;

    public SaidaDetalhadaMedicoDto() {
    }

    public SaidaDetalhadaMedicoDto(UUID id, String nome, String sobrenome, String crm, String telefone, String email, TipoStatusConta status, SaidaSimplesEspecialidadeDto especialidade, List<SaidaSimplesConsultaDto> consultas) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.crm = crm;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
        this.especialidade = especialidade;
        this.consultas = consultas;
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

    public List<SaidaSimplesConsultaDto> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<SaidaSimplesConsultaDto> consultas) {
        this.consultas = consultas;
    }
    
}
