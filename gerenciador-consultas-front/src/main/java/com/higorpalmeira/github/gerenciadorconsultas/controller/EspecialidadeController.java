/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.java.com.higorpalmeira.github.gerenciadorconsultas.controller;

import main.java.com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarEspecialidadeDto;

/**
 *
 * @author higor
 */
public class EspecialidadeController {
    
    public EspecialidadeController() {
        
    }
    
    public void criar_especialidade(String descricao) {
        
        CriarEspecialidadeDto especialidadeDto = new CriarEspecialidadeDto();
        especialidadeDto.setDescription(descricao);
        
    }
    
}
