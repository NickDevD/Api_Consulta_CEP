package com.api.cepapi.service;

import com.api.cepapi.dto.EnderecoDTO; // Novo pacote do DTO
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service; // Importar esta anotação

@Service // Anotação que determina a classe como um serviço spring
public class ApiServicie {

    public EnderecoDTO getEnderecosDTO(String cep) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://viacep.com.br/ws/" + cep + "/json/"))
                .build();

        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(httpResponse.body(), EnderecoDTO.class);

        } catch (Exception e) {
            System.err.println("Erro ao consultar CEP: " + e.getMessage()); // Use System.err para erros
            return null; // Retorne null ou lance uma exceção mais específica
        }
    }
}
