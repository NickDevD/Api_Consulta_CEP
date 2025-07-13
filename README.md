## 🚀 API de Consulta de CEP com Spring Boot
Uma aplicação backend desenvolvida em Spring Boot para consultar informações de endereços a partir de um CEP (Código de Endereçamento Postal) brasileiro. Esta API integra-se com a API pública do ViaCEP e fornece um endpoint RESTful simples para ser consumido por aplicações front-end.

# ✨ Funcionalidades
Consulta de CEP: Obtém detalhes de endereço (logradouro, bairro, cidade, estado, etc.) a partir de um CEP fornecido.

API RESTful: Expõe um endpoint fácil de usar para consumo por outras aplicações.

Tratamento de Erros: Lida com CEPs não encontrados ou inválidos.

Configuração CORS: Permite requisições de diferentes origens (front-end em portas/domínios diferentes).

Documentação OpenAPI (Swagger UI): Documentação interativa da API gerada automaticamente.

# 🛠️ Tecnologias Utilizadas
- Linguagem: Java 17+

- Framework: Spring Boot (versão mais recente estável)

- Gerenciador de Dependências: Maven

- API Externa: ViaCEP

- JSON Processing: Jackson Databind (incluso no Spring Web)

- API de HTTP: java.net.http.HttpClient (Java 11+)

- Documentação API: Springdoc OpenAPI (Swagger UI)

# 📁 Estrutura do Projeto
O projeto segue uma estrutura de pacotes lógica para facilitar a organização:
```
.
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── api
│   │   │           └── cepapi
│   │   │               ├── CepapiApplication.java      // Classe principal do Spring Boot
│   │   │               ├── config
│   │   │               │   └── WebConfig.java          // Configuração CORS
│   │   │               ├── controller
│   │   │               │   └── CepController.java      // Endpoint REST da API
│   │   │               ├── model
│   │   │               │   └── EnderecoDTO.java        // DTO para dados de endereço
│   │   │               └── service
│   │   │                   └── ApiServicie.java        // Lógica para consumir ViaCEP
│   │   └── resources
│   │       └── application.properties                  // Arquivo de configuração do Spring Boot
│   └── test
│       └── java
│           └── com
│               └── cepapp
│                   └── apicep
│                       └── ...                         
├── pom.xml                                             
└── README.md                                           
```

## 🚀 Como Executar a Aplicação (Backend)
Siga os passos abaixo para clonar e executar a aplicação Spring Boot localmente.

# Pré-requisitos
- Java Development Kit (JDK) 17 ou superior.

- Apache Maven (geralmente já vem com IDEs como IntelliJ).

- Uma IDE Java como IntelliJ IDEA (recomendado) ou VS Code com extensões Java.

# Passos

- Clone o Repositório:

Bash
```
git clone [URL_DO_SEU_REPOSITORIO]
cd nome-do-seu-repositorio
```
- Abra o Projeto na sua IDE:

No IntelliJ IDEA: File > Open... e selecione a pasta raiz do projeto clonado.

Aguarde o Maven baixar todas as dependências (pode demorar um pouco na primeira vez).


- Verifique a Configuração CORS (Muito Importante!):

Abra o arquivo src/main/java/com/cepapp/apicep/config/WebConfig.java (ou o local onde você o criou).

Certifique-se de que a origem do seu front-end (onde o HTML está sendo servido) está listada em allowedOrigins. Exemplos comuns:

```
http://localhost:3000 (para React, Vue, Angular padrão)

http://127.0.0.1:5500 (para Live Server do VS Code)

http://localhost:8080 (se o front-end for servido pelo próprio Spring Boot ou para testes no mesmo domínio)
```

- Java
// Exemplo de WebConfig.java
```
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000", "http://127.0.0.1:5500") // Adicione suas origens aqui
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```
- Execute a Aplicação Spring Boot:

Localize a classe principal CepappApplication.java (geralmente em src/main/java/com/cepapp/apicep/CepappApplication.java).

Clique no botão verde de "play" (executar) ao lado do método main para iniciar o servidor Spring Boot.

Você verá logs no console indicando que a aplicação foi iniciada, geralmente na porta 8080.

# 🧪 Testando a API (Backend)
Com a aplicação em execução, você pode testar os endpoints:

- Endpoint de Consulta de CEP
URL: http://localhost:8080/api/cep/{cep}

- Método: GET
Exemplo: http://localhost:8080/api/cep/01001000

- Você pode testar usando:

- Navegador: Cole a URL de exemplo no seu navegador. A resposta será um JSON.

- Ferramentas de API: Utilize ferramentas como Postman ou Insomnia para fazer requisições GET e inspecionar a resposta.

- Documentação Interativa (Swagger UI)
Acesse a documentação interativa da API (gerada pelo Springdoc OpenAPI) em:
http://localhost:8080/swagger-ui.html

Aqui você pode ver todos os endpoints disponíveis, seus parâmetros, modelos de resposta e até mesmo testar as requisições diretamente na interface.


# 🌐 Como Usar o Front-end de Exemplo
Um arquivo index.html básico é fornecido como exemplo de como consumir esta API.

- Salve o Arquivo HTML:

- Salve o código HTML/CSS/JavaScript do exemplo na pasta frontend, que esta dentro de assets, em uma pasta no seu computador.

- Abra no Navegador:

- Dê um duplo clique no arquivo index.html ou arraste-o para a janela do navegador. Ele será aberto diretamente.

- Alternativamente (Recomendado para Desenvolvimento): Use uma extensão como o "Live Server" no VS Code para servir o arquivo HTML. Isso geralmente o abre em http://127.0.0.1:5500 ou http://localhost:5500, que é o cenário que você configurou no CORS.

# 👨‍💻Interaja:

- Com o back-end Spring Boot rodando (na porta 8080) e o front-end aberto (na porta 3000/5500 ou file://), digite um CEP no campo e clique em "Consultar".

- Os dados do endereço devem aparecer na tela, demonstrando a comunicação entre o front-end e o back-end.

# 🤝 Contribuição
Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar bugs, sinta-se à vontade para abrir uma issue ou enviar um pull request.

# 📄 Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.
