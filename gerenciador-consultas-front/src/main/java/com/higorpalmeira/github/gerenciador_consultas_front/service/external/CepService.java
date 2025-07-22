package com.higorpalmeira.github.gerenciador_consultas_front.service.external;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciador_consultas_front.model.dto.external.EnderecoDto;

public class CepService {
	
	public EnderecoDto getEndereco(String cep) {
		
		EnderecoDto enderecoDto = new EnderecoDto();
		
		try {
			
			HttpClient client = HttpClient.newHttpClient();
			
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://viacep.com.br/ws/" + cep + "/json/")).build();
			
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			
			ObjectMapper mapper = new ObjectMapper();
			
			enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);
			
		} catch(Exception e) {
			
			System.out.println(e.getMessage());
			;
		}
		
		return enderecoDto;
		
	}

}
