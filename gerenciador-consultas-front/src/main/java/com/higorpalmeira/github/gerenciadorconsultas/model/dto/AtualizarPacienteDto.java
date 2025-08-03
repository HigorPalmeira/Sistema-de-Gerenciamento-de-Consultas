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
    
}
