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
public class SaidaSimplesEspecialidadeDto {

    private UUID id;

    private String descricao;

    private int medicosAssociados;
    
    public SaidaSimplesEspecialidadeDto() {
        
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getMedicosAssociados() {
        return medicosAssociados;
    }

    public void setMedicosAssociados(int medicosAssociados) {
        this.medicosAssociados = medicosAssociados;
    }

    
}
