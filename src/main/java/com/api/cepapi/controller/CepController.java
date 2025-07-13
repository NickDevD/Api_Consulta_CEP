package com.api.cepapi.controller;

import com.api.cepapi.dto.EnderecoDTO;
import com.api.cepapi.service.ApiServicie; // Importar seu serviço
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/v1") // Prefixo para todas as rotas neste controller
//@CrossOrigin(origins = "http://127.0.0.1:3000") // Utilizado para o front encontrar o back-end
@Tag(name="CEP", description = "Operação para consulta de CEP")
public class CepController {

    private final ApiServicie apiServicie; // Injetar seu serviço

    // Construtor para injeção de dependência (Spring Boot faz isso automaticamente)
    public CepController(ApiServicie apiServicie) {
        this.apiServicie = apiServicie;
    }


    @Operation(summary = "Obter uma entrada de senha por ID",
            description = "Retorna uma entrada de senha específica pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha encontrada",
                    content = @Content(schema = @Schema(implementation = EnderecoDTO.class))),
            @ApiResponse(responseCode = "404", description = "Senha não encontrada")
    })

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