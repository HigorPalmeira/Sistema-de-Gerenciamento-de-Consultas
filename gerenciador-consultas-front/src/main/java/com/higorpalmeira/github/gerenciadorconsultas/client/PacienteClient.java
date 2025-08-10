/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.atualizar.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarPacienteDto;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class PacienteClient {
    
    private final String URL_API = "http://localhost:8080/v1/paciente";
    
    private final HttpClient client;
    
    private final ObjectMapper mapper;
    
    public PacienteClient() {
        this.client = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    
    public HttpResponse<String> criarPaciente(CriarPacienteDto criarPacienteDto) throws JsonProcessingException, IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString( mapper.writeValueAsString(criarPacienteDto) ))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> editarPaciente(UUID idPaciente, AtualizarPacienteDto atualizarPacienteDto) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idPaciente.toString()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(atualizarPacienteDto)))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> deletarPaciente(UUID idPaciente) throws IOException, InterruptedException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idPaciente.toString()))
                .DELETE()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidaSimplesPacienteDtoPorId(UUID idPaciente) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/" + idPaciente.toString()))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidaSimplesPacienteDtoPorCpf(String cpf) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/cpf/" + cpf))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidaSimplesPacienteDtoPorEmail(String email) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/email/" + email))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidasSimplesPacienteDtoPorNome(String nome) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/nome/" + nome))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> buscarSaidasSimplesPacienteDtoPorSobrenome(String sobrenome) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/sobrenome/" + sobrenome))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> listarSaidaSimplesPacienteDto() throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
        
    }
    
    public HttpResponse<String> listarSaidaSimplesPacienteDtoPorStatus(String status) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/status/" + status))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
    }
    
    public HttpResponse<String> listarSaidaSimplesPacienteDtoPorDataNascimento(String dataNascimento) throws IOException, InterruptedException {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API + "/datanascimento/" + dataNascimento))
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        return response;
    }
    
}
