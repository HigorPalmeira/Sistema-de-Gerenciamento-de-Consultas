/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.PacienteClient;

/**
 *
 * @author higor
 */
public class PacienteService {
    
    private final String LOCATION = "Location";
    
    private final PacienteClient client;
    
    private final ObjectMapper mapper;
    
    public PacienteService(PacienteClient pacienteClient) {
        this.client = pacienteClient;
        this.mapper = new ObjectMapper();
    }
    
}
