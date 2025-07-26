/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.EspecialidadeClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SaidaSimplesEspecialidadeDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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
    
    private final ObjectMapper mapper;
    
    public EspecialidadeService(EspecialidadeClient especialidadeClient) {
        
        client = especialidadeClient;
        mapper = new ObjectMapper();
        
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
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar uma nova Especialidade!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }

        return false;
        
    }
    
    public boolean editarEspecialidade(UUID idEspecialidade, String descricaoEspecialidade) {
        
        if (idEspecialidade == null) {
            return false;
        }
        
        AtualizarEspecialidadeDto especialidadeDto = new AtualizarEspecialidadeDto();
        especialidadeDto.setDescricao(descricaoEspecialidade);
        
        try {
            
            HttpResponse<String> response = client.editarEspecialdiade(idEspecialidade.toString(), especialidadeDto);
            
            if (response.statusCode() == 200) {
                return true;
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar a especialidade.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
    }
    
    public List<SaidaSimplesEspecialidadeDto> buscarSaidaSimplesEspecialidadeDto(String descricao) {
        
        List<SaidaSimplesEspecialidadeDto> especialidadeDto = new ArrayList<>();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesEspecialidadeDtoPorDescricao(descricao);
            
            if (response.statusCode() == 200) {
                
                especialidadeDto = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesEspecialidadeDto>>() { });
                
                return especialidadeDto;
            }
            
            
        } catch (JsonProcessingException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar recuperar as informações da especialidade. Se o erro persistir contate o administrador do sistema.\nErro: " + ex.toString(), "Erro ao recuperar dados", JOptionPane.ERROR_MESSAGE);
        
        } catch (IOException | InterruptedException ex) {
        
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar a especialidade.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
        
        }
        
        return especialidadeDto;
        
    }

    public SaidaSimplesEspecialidadeDto getSaidaSimplesEspecialidadeDto(UUID id) {

        SaidaSimplesEspecialidadeDto especialidadeDto = new SaidaSimplesEspecialidadeDto();

        try {

            HttpClient localClient = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(URL_API + "/" + id.toString()))
                    .GET()
                    .build();

            HttpResponse<String> response = localClient.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();

            especialidadeDto = mapper.readValue(response.body(), SaidaSimplesEspecialidadeDto.class);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        return especialidadeDto;

    }

    public List<SaidaSimplesEspecialidadeDto> listarSaidaSimplesEspecialidadeDto() {

        List<SaidaSimplesEspecialidadeDto> listaEspecialidades = new ArrayList<>();
        
        try {
            HttpResponse<String> response = client.listarSaidaSimplesEspecialidadeDto();
            
            if (response.statusCode() == 200) {
                ObjectMapper mapper = new ObjectMapper();
            
                listaEspecialidades = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesEspecialidadeDto>>() {});

                return listaEspecialidades;
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição das Especialidades! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todas as especialidades!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return listaEspecialidades;

    }

}
