/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarMedicoDto;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class MedicoService {
    
    private final String URL_API = "localhost:8080/v1/medico";
    
    public UUID criarMedico(CriarMedicoDto criarMedicoDto) {
        
        try {
            
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(""))
                    .POST(BodyPublishers.ofString(criarMedicoDto))
                    .build();
            
        } catch(Exception e) {
            
        }
        
    }
    
}
