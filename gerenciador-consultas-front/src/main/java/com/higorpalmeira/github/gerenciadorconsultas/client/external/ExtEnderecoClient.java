/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.client.external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

/**
 *
 * @author higor
 */
public class ExtEnderecoClient {
    
    private final String URL_API = "http://viacep";
    
    private final HttpClient client;
    
    public ExtEnderecoClient() {
        this.client = HttpClient.newHttpClient();
    }
    
    public void getEndereco(String cep) {
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL_API))
                .GET()
                .build();
        
    }
    
}
