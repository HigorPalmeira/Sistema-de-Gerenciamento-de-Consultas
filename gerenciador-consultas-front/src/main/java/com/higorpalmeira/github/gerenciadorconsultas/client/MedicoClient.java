/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarMedicoDto;
import java.io.IOException;
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
    
    public HttpResponse<String> criarMedico(CriarMedicoDto criarMedicoDto) throws JsonProcessingException, IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(criarMedicoDto) ))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> editarMedico(String idMedico, AtualizarMedicoDto atualizarMedicoDto) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idMedico))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(atualizarMedicoDto) ))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> deletarMedico(String idMedico) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idMedico))
                .DELETE()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> listarSaidaSimplesMedicoDto() throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
}
