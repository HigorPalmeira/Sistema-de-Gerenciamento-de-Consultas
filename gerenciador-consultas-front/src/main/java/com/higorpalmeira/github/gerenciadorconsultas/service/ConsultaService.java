/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.higorpalmeira.github.gerenciadorconsultas.client.ConsultaClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    
    public SaidaSimplesConsultaDto buscarConsultaPorDataHora(LocalDateTime dataHora) {
        
        if (dataHora == null) {
            return null;
        }
        
        SaidaSimplesConsultaDto consultaDto = new SaidaSimplesConsultaDto();
        
        try {
            
            HttpResponse<String> response = client.buscarConsultaPorDataHora(dataHora);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                consultaDto = mapper.readValue(response.body(), SaidaSimplesConsultaDto.class);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição da Consulta! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todas as consultas!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return consultaDto;
        
    }
    
    public List<SaidaSimplesConsultaDto> listarTodasConsultasAtivas() {
        
        List<SaidaSimplesConsultaDto> listaConsultas = new ArrayList<>();
        
        try {
            
            HttpResponse<String> response = client.listarTodasConsultasAtiva();
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                listaConsultas = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesConsultaDto>>() { });
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição das Consultas! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todas as consultas!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return listaConsultas;
    }
    
}
