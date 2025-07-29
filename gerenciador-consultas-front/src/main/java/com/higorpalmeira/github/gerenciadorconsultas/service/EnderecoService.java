/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.external.ExtEnderecoClient;

/**
 *
 * @author higor
 */
public class EnderecoService {
    
    private ExtEnderecoClient extClient;
    
    private ObjectMapper mapper;
    
    public EnderecoService(ExtEnderecoClient extClient) {
        this.extClient = extClient;
        this.mapper = new ObjectMapper();
    }
    
}
