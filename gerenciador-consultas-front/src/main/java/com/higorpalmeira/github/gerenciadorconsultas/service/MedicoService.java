/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.MedicoClient;
import java.net.http.HttpResponse;

/**
 *
 * @author higor
 */
public class MedicoService {
    
    private final String LOCATION = "Location";
    
    private final MedicoClient client;
    
    private final ObjectMapper mapper;
    
    public MedicoService(MedicoClient medicoClient) {
        
        client = medicoClient;
        mapper = new ObjectMapper();
        
    }
    
    public boolean criarMedico() {
        
        HttpResponse response = client.criarMedico(criarMedicoDto);
        
    }
    
}
