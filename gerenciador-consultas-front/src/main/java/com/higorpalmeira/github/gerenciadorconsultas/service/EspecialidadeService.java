/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SaidaSimplesEspecialidadeDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class EspecialidadeService {
    
    private final String URL_API = "http://localhost:8080/v1/especialidade";
    
    public SaidaSimplesEspecialidadeDto getSaidaSimplesEspecialidadeDto(UUID id) {
        
        SaidaSimplesEspecialidadeDto especialidadeDto = new SaidaSimplesEspecialidadeDto();
        
        try {
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API + "/" + id.toString()))
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            ObjectMapper mapper = new ObjectMapper();
            
            especialidadeDto = mapper.readValue(response.body(), SaidaSimplesEspecialidadeDto.class);
            
        } catch(IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        
        return especialidadeDto;
        
    }
    
    public List<SaidaSimplesEspecialidadeDto> listarSaidaSimplesEspecialidadeDto() {
        
        List<SaidaSimplesEspecialidadeDto> especialidades = new ArrayList<>();
        
        try {
            
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API))
                    .GET()
                    .build();
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            ObjectMapper mapper = new ObjectMapper();
            
            especialidades = mapper.readValues(response.body(), new TypeReference<List<SaidaSimplesEspecialidadeDto>>(){ });
            
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        
        return especialidades;
        
    }
    
}
