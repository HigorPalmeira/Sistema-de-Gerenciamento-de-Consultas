/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConta;
import java.time.LocalDate;

/**
 *
 * @author higor
 */
public class AtualizarPacienteDto {
    
    private String nome;
    
    private String sobrenome;
    
    private String cpf;
    
    private LocalDate dataNascimento;
    
    private TipoGenero genero;
    
    private TipoStatusConta status;
    
    private String email;
    
    private String telefone;
    
    private AtualizarEnderecoDto endereco;

    public AtualizarPacienteDto() {
    }

    public AtualizarPacienteDto(String nome, String sobrenome, String cpf, LocalDate dataNascimento, TipoGenero genero, TipoStatusConta status, String email, String telefone, AtualizarEnderecoDto endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.status = status;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public AtualizarEnderecoDto getEndereco() {
        return endereco;
    }

    public void setEndereco(AtualizarEnderecoDto endereco) {
        this.endereco = endereco;
    }
    
}
