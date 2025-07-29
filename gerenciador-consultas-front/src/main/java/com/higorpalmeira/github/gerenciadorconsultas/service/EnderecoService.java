/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.external.ExtEnderecoClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validador;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author higor
 */
public class EnderecoService {
    
    private ExtEnderecoClient extClient;
    
    private ObjectMapper mapper;
    
    public EnderecoService(ExtEnderecoClient extClient) {
        this.extClient = extClient;
        this.mapper = new ObjectMapper();
    }
    
    public CriarEnderecoDto pesquisarEnderecoPorCep(String cep) {
        
        if (cep == null) {
            return null;
        }
        
        String cepLimpo = cep.trim().replaceAll("[.-]", "");
        
        if (!Validador.isCep(cepLimpo)) {
            JOptionPane.showMessageDialog(null, "O CEP informado é inválido! Informe um válido.\nEx.: ##.###-###");
            return null;
        }
        
        CriarEnderecoDto enderecoDto = null;
        
        try {
            
            HttpResponse<String> response = this.extClient.getEndereco(cepLimpo);
            
            enderecoDto = this.mapper.readValue(response, CriarEnderecoDto.class);
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(EnderecoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return enderecoDto;
        
    }
    
}
