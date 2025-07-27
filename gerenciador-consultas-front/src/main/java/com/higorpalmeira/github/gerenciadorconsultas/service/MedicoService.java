/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.MedicoClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validador;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author higor
 */
public class MedicoService {
    
    private final String LOCATION = "Location";
    
    private final MedicoClient client;
    
    private final ObjectMapper mapper;
    
    public MedicoService(MedicoClient medicoClient) {
        
        client = medicoClient;
        mapper = new ObjectMapper();
        
    }
    
    public boolean criarMedico(String nome, String sobrenome, String crm, String telefone, String email, UUID especialidadeId) {
        
        if ( (nome == null || nome.isBlank()) || (sobrenome == null || sobrenome.isBlank()) ) {
            return false;
        }
        
        if ( !Validador.isCrm(crm) ) {
            return false;
        }
        
        if ( !Validador.isEmail(email) ) {
            return false;
        }
        
        if ( !Validador.isTelefone(telefone) ) {
            return false;
        }
        
        if (especialidadeId == null) {
            return false;
        }
        
        CriarMedicoDto criarMedicoDto = new CriarMedicoDto(nome, sobrenome, crm, telefone, email, especialidadeId);
        
        try {
            
            HttpResponse response = client.criarMedico(criarMedicoDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_CRIACAO.getTipo()) {
                
                Optional<String> locationHeader = response.headers().firstValue(LOCATION);
                
                if (locationHeader.isPresent()) {
                    
                    String locationUrl = locationHeader.get();
                    
                    URI uri = new URI(locationUrl);
                    String path = uri.getPath();
                    
                    String idPath = path.substring(path.lastIndexOf("/") + 1);
                    
                    try {
                        UUID.fromString(idPath);
                    }catch(Exception e) {
                        return false;
                    }
                    
                }
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar um novo Médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public boolean editarMedico(UUID idMedico, String nome, String sobrenome, String crm, String telefone, String email, String status, UUID especialidadeId) {
        
        if (idMedico == null) {
            return false;
        }
        
        AtualizarMedicoDto atualizarMedicoDto = new AtualizarMedicoDto(nome, sobrenome, crm, telefone, email, TipoStatusConta.fromTipo(status), especialidadeId);
        
        try {
            
            HttpResponse<String> response = client.editarMedico(idMedico, atualizarMedicoDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA_EDICAO.getTipo()) {
                return true;
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o médico.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public boolean deletarMedico(UUID idMedico) {
        
        if (idMedico == null) {
            return false;
        }
        
        HttpResponse<String> response = client.deletarMedico(idMedico);
        
        
    }
    
}
