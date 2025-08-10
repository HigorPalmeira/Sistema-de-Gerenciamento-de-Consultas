/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.higorpalmeira.github.gerenciadorconsultas.client.ConsultaClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author higor
 */
public class ConsultaService {
    
    private final String LOCATION = "Location";
    
    private final ConsultaClient client;
    
    private final ObjectMapper mapper;
    
    public ConsultaService(ConsultaClient consultaClient) {
        client = consultaClient;
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }
    
    public boolean criarConsulta(LocalDateTime dataHora, BigDecimal valor, String observacoes, UUID idMedico, UUID idPaciente) {
        
        if (dataHora == null) {
            return false;
        }
        
        if (valor == null || valor.scale() != 2 || (valor.compareTo(BigDecimal.ZERO) < 0) || valor.compareTo( new BigDecimal("99999.99") ) > 0) {
            return false;
        }
        
        if (idMedico == null || idPaciente == null) {
            return false;
        }
        
        CriarConsultaDto criarConsultaDto = new CriarConsultaDto(dataHora, observacoes, valor, idMedico, idPaciente);
        
        try {
            
            HttpResponse response = client.criarConsulta(criarConsultaDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_CRIACAO.getTipo()) {
                
                Optional<String> locationHeader = response.headers().firstValue(LOCATION);
                
                if (locationHeader.isPresent()) {
                    
                    String locationUrl = locationHeader.get();
                    
                    URI uri = new URI(locationUrl);
                    String path = uri.getPath();
                    
                    String idPath = path.substring(path.lastIndexOf("/") + 1);
                    
                    try {
                        UUID.fromString(idPath);
                    } catch(Exception e) {
                        return false;
                    }
                    
                }
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (IOException | URISyntaxException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar uma nova Consulta!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
}
