## 🚀 API de Consulta de CEP Fullstack

Uma aplicação completa para consulta de informações de endereços a partir de um CEP (Código de Endereçamento Postal) brasileiro. Este projeto integra um **backend robusto desenvolvido em Spring Boot** com um **frontend simples em HTML, CSS e JavaScript puro**, tudo empacotado para fácil execução via Docker. A API se integra com o serviço público do ViaCEP para fornecer detalhes de endereço.

## ✨ Funcionalidades

### Backend (API RESTful)
* **Consulta de CEP:** Obtém detalhes de endereço (logradouro, bairro, cidade, estado, etc.) a partir de um CEP fornecido.
* **API RESTful:** Expõe um endpoint simples e claro para consumo.
* **Tratamento de Erros:** Lida com CEPs não encontrados ou inválidos.
* **Documentação OpenAPI (Swagger UI):** Documentação interativa da API gerada automaticamente.

### Frontend (Aplicação Web Estática)
* **Interface Amigável:** Permite que usuários digitem um CEP e visualizem os resultados de forma clara.
* **Integração Transparente:** Comunica-se diretamente com o backend da API para obter os dados.

### Orquestração e Deploy
* **Dockerização:** O projeto é empacotado em um único contêiner Docker para portabilidade e execução simplificada.
* **Docker Compose:** Utiliza o Docker Compose para gerenciar o build e a inicialização da aplicação em ambiente conteinerizado.
* **Integração Contínua (CI) com GitHub Actions:** Automação da construção e publicação da imagem Docker no Docker Hub a cada atualização do código.

## 🛠️ Tecnologias Utilizadas

### Backend
* **Linguagem:** Java 22
* **Framework:** Spring Boot (versão mais recente estável)
* **Gerenciador de Dependências:** Maven
* **API Externa:** ViaCEP
* **JSON Processing:** Jackson Databind (incluso no Spring Web)
* **API de HTTP:** `java.net.http.HttpClient` (Java 11+)
* **Documentação API:** Springdoc OpenAPI (Swagger UI)

### Frontend
* **Linguagens:** HTML, CSS, JavaScript (puro)

### Infraestrutura/Deploy
* **Containerização:** Docker
* **Orquestração Local:** Docker Compose
* **Automação CI/CD:** GitHub Actions

---

## 📁 Estrutura do Projeto

O projeto segue uma estrutura de pacotes lógica para o backend e integra o frontend nos diretórios de recursos estáticos do Spring Boot, otimizando o empacotamento em JARs.
```
.
├── .github/                                            // Configurações do GitHub Actions
│   └── workflows/
│       └── docker-image.yml                            // Workflow para construir e publicar a imagem Docker
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── api
│       │           └── cepapi
│       │               ├── CepapiApplication.java      // Classe principal do Spring Boot
│       │               ├── config
│       │               │   └── WebConfig.java          // (Opcional) Configuração para static resources em 'webapp'
│       │               ├── controller
│       │               │   └── CepController.java      // Endpoint REST da API
│       │               ├── model
│       │               │   └── EnderecoDTO.java        // DTO para dados de endereço
│       │               └── service
│       │                   └── ApiService.java         // Lógica para consumir ViaCEP
│       └── resources
│           ├── static/                                 // SEU FRONTEND ESTÁ AQUI (RECOMENDADO)
│           │   ├── index.html
│           │   ├── script.js
│           │   └── style.css
│           └── application.properties                  // Arquivo de configuração do Spring Boot
│   └── test/
│       └── ...
├── docker-compose.yml                                  // Arquivo de configuração do Docker Compose
├── Dockerfile                                          // Instruções para construir a imagem Docker do projeto
├── pom.xml                                             // Gerenciamento de dependências Maven
└── README.md

```
---

## 🚀 Como Executar a Aplicação (Fullstack via Docker)

A maneira mais recomendada para executar este projeto completo é utilizando Docker e Docker Compose.

### Pré-requisitos
* **Java Development Kit (JDK) 17 ou superior.** (Necessário apenas para o build local com Maven)
* **Apache Maven** (Necessário apenas para o build local com Maven)
* **Docker Desktop** (ou Docker Engine) instalado e rodando.
* Uma IDE Java como IntelliJ IDEA (recomendado) ou VS Code com extensões Java.

### Passos

1.  **Clone o Repositório:**
    ```bash
    git clone [URL_DO_SEU_REPOSITORIO]
    cd nome-do-seu-repositorio # Navegue até a pasta raiz do projeto
    ```

2.  **Compile o Projeto Java:**
    É necessário compilar o projeto para gerar o arquivo JAR executável que será empacotado no contêiner Docker.
    ```bash
    mvn clean package
    ```
    Isso criará o arquivo `.jar` (ex: `seu-projeto-0.0.1-SNAPSHOT.jar`) dentro da pasta `target/`.

3.  **Construa e Inicie os Contêineres Docker:**
    Navegue até o diretório raiz do projeto (onde estão `docker-compose.yml` e `Dockerfile`) e execute:
    ```bash
    docker compose up --build -d
    ```
    * `--build`: Garante que a imagem Docker seja construída (ou reconstruída) com a versão mais recente do seu JAR.
    * `-d`: Inicia o contêiner em segundo plano (detached mode).

4.  **Acesse a Aplicação:**
    Após o contêiner iniciar (o que pode levar alguns segundos, verifique os logs se necessário), abra seu navegador e acesse:
    ```
    http://localhost:8080/
    ```
    Sua interface de consulta de CEP (frontend) deve carregar, e as consultas feitas através dela se comunicarão com o backend rodando no mesmo contêiner.

### Como Parar e Remover os Contêineres
Quando quiser parar a aplicação e remover os contêineres e redes criadas pelo Docker Compose:

```bash
docker compose down
```

# ☁️ Imagem Docker Automatizada com GitHub Actions
Este projeto utiliza GitHub Actions para automatizar o processo de construção e publicação da imagem Docker no Docker Hub (ou em outro Container Registry) sempre que houver um push para a branch principal (ex: main ou master). Isso garante que a imagem Docker mais recente e testada esteja sempre disponível.

### Como Funciona:
Você faz alterações no código e as envia (git push) para o repositório GitHub.

O GitHub Actions detecta o push e inicia um workflow pré-configurado (geralmente em .github/workflows/docker-image.yml).

### Este workflow:

- Faz o checkout do seu código.

- Realiza o build do projeto Java (via Maven).

- Constrói a imagem Docker a partir do seu Dockerfile.

- Autentica-se no Docker Hub (ou outro registro).

- Faz o push da imagem Docker para o seu repositório no Docker Hub (ex: seu-usuario-docker/api-consulta-cep:latest).

### Como Usar a Imagem Publicada em Outra Máquina:
Para executar a aplicação em qualquer outra máquina que tenha Docker instalado, basta puxar a imagem diretamente do Docker Hub e executá-la. Você não precisa clonar o repositório nem compilar o código Java localmente.

Bash

### Faça login no Docker CLI se a imagem for privada ou se for seu primeiro pull
```
docker login
```

### Puxe a imagem do Docker Hub
```
docker pull nickdocd/api_consulta_cep:latest  # ou a tag de versão específica, ex: :1.0
```
### Execute o contêiner, mapeando a porta 8080
```
docker run -p 8080:8080 nickdocd/api_consulta_cep:latest
```
Após executar o comando docker run, a aplicação estará disponível em http://localhost:8080/.

# 🧪 Testando a API (Backend)
Mesmo rodando o projeto fullstack, você ainda pode testar a API diretamente:

- Endpoint de Consulta de CEP
URL: http://localhost:8080/v1/cep/{cep}

- Método: GET

Exemplo: http://localhost:8080/v1/cep/01001000

### Você pode testar usando:

- Navegador: Cole a URL de exemplo no seu navegador. A resposta será um JSON.

- Ferramentas de API: Utilize ferramentas como Postman ou Insomnia para fazer requisições GET e inspecionar a resposta.

- Documentação Interativa (Swagger UI)
Acesse a documentação interativa da API (gerada pelo Springdoc OpenAPI) em:

http://localhost:8080/swagger-ui.html
Aqui você pode ver todos os endpoints disponíveis, seus parâmetros, modelos de resposta e até mesmo testar as requisições diretamente na interface.

## 🤝 Contribuição
Contribuições são bem-vindas! Se você tiver sugestões, melhorias ou encontrar bugs, sinta-se à vontade para abrir uma issue ou enviar um pull request.

📄 Licença
Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.
