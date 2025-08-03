/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarEspecialidadeDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 *
 * @author higor
 */
public class EspecialidadeClient {

    private final String URL_API = "http://localhost:8080/v1/especialidade";
    
    private final HttpClient client;
    
    private final ObjectMapper mapper;
    
    public EspecialidadeClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    public HttpResponse<String> criarEspecialidade(CriarEspecialidadeDto criarEspecialidadeDto) throws IOException, InterruptedException, URISyntaxException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(criarEspecialidadeDto.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

    public HttpResponse<String> editarEspecialdiade(String idEspecialidade, AtualizarEspecialidadeDto atualizarEspecialidadeDto) throws IOException, InterruptedException, JsonProcessingException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idEspecialidade))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(atualizarEspecialidadeDto) ))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> deletarEspecialidade(String idEspecialidade) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idEspecialidade))
                .DELETE()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidaSimplesEspecialidadeDtoPorDescricao(String descricao) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/descricao/" + descricao))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> listarSaidaSimplesEspecialidadeDto() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }

}
