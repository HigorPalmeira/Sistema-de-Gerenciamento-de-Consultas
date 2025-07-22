/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import java.util.UUID;

/**
 *
 * @author higor
 */
public class CriarMedicoDto {

    private String nome;

    private String sobrenome;

    private String crm;

    private String telefone;

    private String email;

    private UUID especialidadeId;
    
    public CriarMedicoDto() {
        
    }

    public CriarMedicoDto(String nome, String sobrenome, String crm, String telefone, String email, UUID especialidadeId) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.crm = crm;
        this.telefone = telefone;
        this.email = email;
        this.especialidadeId = especialidadeId;
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

    public UUID getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(UUID especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    @Override
    public String toString() {
        return "{\"nome\": \"" + this.nome 
                + "\", \"sobrenome\": \"" + this.sobrenome 
                + "\", \"crm\": \"" + this.crm 
                + "\", \"telefone\": \"" + this.telefone 
                + "\", \"email\": \"" + this.email 
                + "\", \"especialidadeId\": \"" + this.especialidadeId + "\"}";
    }
    
    
}
