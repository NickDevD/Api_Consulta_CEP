package com.api.cepapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Indica ao Spring que esta classe contém configurações
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia todas as URLs da sua API (ou '/api/**' se preferir ser mais específico)
                // LISTE AQUI TODAS AS ORIGENS (DOMÍNIOS E PORTAS) DO SEU FRONT-END!
                .allowedOrigins("http://127.0.0.1:3000", // ESSA É A ORIGEM DO SEU FRONT-END MOSTRADA NO ERRO
                        "http://localhost:3000",    // É bom incluir localhost também
                        "http://localhost:8080")    // Opcional, para testes se precisar da mesma origem
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*") // Permite que o front-end envie qualquer cabeçalho personalizado
                .allowCredentials(true) // Permite o envio de cookies, cabeçalhos de autorização, etc.
                .maxAge(3600); // Tempo (em segundos) que a resposta preflight pode ser armazenada em cache
    }
}
