package com.api.cepapi.controller;

import com.api.cepapi.dto.EnderecoDTO;
import com.api.cepapi.service.ApiServicie; // Importar seu serviço
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/v1") // Prefixo para todas as rotas neste controller
//@CrossOrigin(origins = "http://127.0.0.1:3000") // Utilizado para o front encontrar o back-end
public class CepController {

    private final ApiServicie apiServicie; // Injetar seu serviço

    // Construtor para injeção de dependência (Spring Boot faz isso automaticamente)
    public CepController(ApiServicie apiServicie) {
        this.apiServicie = apiServicie;
    }

    @GetMapping("/cep/{cep}") // Mapeia requisições GET para /v1/cep/{cep}
    public ResponseEntity<EnderecoDTO> getCepInfo(@PathVariable String cep) {
        EnderecoDTO enderecosDTO = apiServicie.getEnderecosDTO(cep);
        if (enderecosDTO != null && enderecosDTO.getCep() != null) { // Verifica se o CEP foi encontrado (ajuste conforme seu DTO)
            return ResponseEntity.ok(enderecosDTO); // Retorna 200 OK com os dados
        } else {
            return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o CEP não for encontrado
        }
    }
}