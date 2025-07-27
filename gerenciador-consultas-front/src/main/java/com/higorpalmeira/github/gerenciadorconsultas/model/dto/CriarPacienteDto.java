/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto;

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

    private LocalDate dataNascimento;

    private TipoGenero genero;

    private String telefone;

    private String email;

    private CriarEnderecoDto endereco;

}
