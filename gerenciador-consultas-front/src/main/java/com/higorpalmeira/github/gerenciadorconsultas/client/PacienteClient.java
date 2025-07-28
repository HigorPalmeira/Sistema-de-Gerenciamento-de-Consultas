/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.http.HttpClient;

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
    }
    
}
