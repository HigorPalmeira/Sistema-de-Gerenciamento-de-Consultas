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
public class AtualizarMedicoDto {

    private String nome;

    private String sobrenome;

    private String crm;

    private String telefone;

    private String email;

    private TipoStatusConta status;

    private UUID especialidadeId;

}
