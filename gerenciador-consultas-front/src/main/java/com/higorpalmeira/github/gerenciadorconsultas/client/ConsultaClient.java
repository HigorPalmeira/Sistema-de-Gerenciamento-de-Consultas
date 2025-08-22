/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.atualizar.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarConsultaDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author higor
 */
public class ConsultaClient {
    
    private final String URL_API = "http://localhost:8080/v1/consulta";
    
    private final HttpClient client;
    
    private final ObjectMapper mapper;
    
    public ConsultaClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    
    public HttpResponse<String> criarConsulta(CriarConsultaDto criarConsultaDto) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(criarConsultaDto)))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> editarConsulta(String idConsulta, AtualizarConsultaDto atualizarConsultaDto) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idConsulta))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(atualizarConsultaDto)))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidaSimplesConsultaPorId(String id) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + id))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarConsultaPorDataHora(LocalDateTime dataHora) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/datahora/" + dataHora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"))))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> listarTodasConsultasAtiva() throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
}
