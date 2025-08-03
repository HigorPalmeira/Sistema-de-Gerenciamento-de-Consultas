/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import java.time.LocalDate;

/**
 *
 * @author higor
 */
public class CriarPacienteDto {

    private String nome;

    private String sobrenome;

    private String cpf;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private TipoGenero genero;

    private String telefone;

    private String email;

    private CriarEnderecoDto endereco;

    public CriarPacienteDto() {
    }

    public CriarPacienteDto(String nome, String sobrenome, String cpf, LocalDate dataNascimento, TipoGenero genero, String telefone, String email, CriarEnderecoDto endereco) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
        this.telefone = telefone;
        this.email = email;
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
