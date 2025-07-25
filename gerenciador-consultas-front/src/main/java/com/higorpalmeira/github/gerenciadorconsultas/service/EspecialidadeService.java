/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.EspecialidadeClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SaidaSimplesEspecialidadeDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
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
public class EspecialidadeService {

    private final String URL_API = "http://localhost:8080/v1/especialidade";
    private final String LOCATION = "Location";
    
    private final EspecialidadeClient client;
    
    public EspecialidadeService(EspecialidadeClient especialidadeClient) {
        
        client = especialidadeClient;
        
    }

    public boolean criarEspecialidade(CriarEspecialidadeDto criarEspecialidadeDto) {

        try {
            
            HttpResponse response = client.criarEspecialidade(criarEspecialidadeDto);
            
            if (response.statusCode() == 201) {
                
                Optional<String> locationHeader = response.headers().firstValue(LOCATION);
                
                if (locationHeader.isPresent()) {
                    
                    String locationUrl = locationHeader.get();
                    
                    URI uri = new URI(locationUrl);
                    String path = uri.getPath();
                    
                    String idPath = path.substring(path.lastIndexOf("/") + 1);
                    
                    try {
                        UUID.fromString(idPath);
                    } catch (Exception e) {
                        return false;
                    }
                    
                }
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar uma nova Especialidade!\nErro: " + ex.getMessage(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }

        return false;
        
    }

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

        } catch (IOException | InterruptedException e) {
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

            especialidades = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesEspecialidadeDto>>() {
            });

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return especialidades;

    }

}
