/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.external.ExtEnderecoClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
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
    
    private final ExtEnderecoClient extClient;
    
    private final ObjectMapper mapper;
    
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
            JOptionPane.showMessageDialog(null, "O CEP \"" + cepLimpo + "\" informado é inválido! Informe um válido.\nEx.: ##.###-###");
            return null;
        }
        
        CriarEnderecoDto enderecoDto = new CriarEnderecoDto();
        
        try {
            
            HttpResponse<String> response = this.extClient.getEndereco(cepLimpo);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                enderecoDto = this.mapper.readValue(response.body(), CriarEnderecoDto.class);
                
            } else if (response.statusCode() == 400) {
                JOptionPane.showMessageDialog(null, "Houve um erro na requisição. O CEP pode não estar formatado!", "Má Requisição", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(EnderecoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return enderecoDto;
        
    }
    
}
