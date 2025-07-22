/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarMedicoDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class MedicoService {
    
    private final String URL_API = "localhost:8080/v1/medico";
    
    public UUID criarMedico(CriarMedicoDto criarMedicoDto) {
        
        UUID id = null;
        
        try {
            
            HttpClient client = HttpClient.newHttpClient();
            
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .header("Content-Type", "application/json")
                    .POST(BodyPublishers.ofString(criarMedicoDto.toString()))
                    .build();
            
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            String[] path = response.uri().getPath().split("/");
            id = UUID.fromString(path[path.length-1]);
            
        } catch(IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        return id;
        
    }
    
}
