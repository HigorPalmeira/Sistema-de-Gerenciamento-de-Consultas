/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author higor
 */
public class MedicoClient {
    
    private final String URL_API = "http://localhost:8080/v1/medico";
    
    private final HttpClient client;
    
    private final ObjectMapper mapper;
    
    public MedicoClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }
    
    public HttpResponse criarMedico(CriarMedicoDto criarMedicoDto) {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(criarMedicoDto) ))
                .build();
        
    }
    
}
