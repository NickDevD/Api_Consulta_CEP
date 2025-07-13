 // URL da sua API Spring Boot
        const API_BASE_URL = 'http://localhost:8080/v1/cep/';

        async function consultarCep() {
            const cepInput = document.getElementById('cepInput');
            const resultadoDiv = document.getElementById('resultado');

            let cep = cepInput.value.trim();

            // Limpar CEP para conter apenas números
            cep = cep.replace(/\D/g, ''); // Remove tudo que não for dígito

            if (cep.length !== 8) {
                resultadoDiv.innerHTML = '<p class="error">Por favor, digite um CEP válido com 8 dígitos.</p>';
                return;
            }

            resultadoDiv.innerHTML = '<p class="loading">Carregando...</p>';
            resultadoDiv.className = ''; // Limpa classes de estilo anteriores

            try {
                const response = await fetch(`${API_BASE_URL}${cep}`);

                if (!response.ok) {
                    // Se a resposta não for OK (status 2xx)
                    if (response.status === 404) {
                        throw new Error('CEP não encontrado ou inválido. Por favor, verifique o número.');
                    } else {
                        // Para outros erros HTTP (e.g., 500 Internal Server Error)
                        throw new Error(`Erro na consulta: Status ${response.status}`);
                    }
                }

                const data = await response.json();

                // Verificar se a API ViaCEP retornou "erro": true
                // Isso acontece para CEPs que não existem, mesmo com status 200 do seu backend
                if (data.erro === true) {
                     resultadoDiv.innerHTML = '<p class="error">CEP não encontrado ou inexistente.</p>';
                     return;
                }

                // Exibir os resultados na página
                resultadoDiv.innerHTML = `
                    <p><strong>CEP:</strong> ${data.cep || 'N/A'}</p>
                    <p><strong>Logradouro:</strong> ${data.logradouro || 'N/A'}</p>
                    <p><strong>Complemento:</strong> ${data.complemento || 'N/A'}</p>
                    <p><strong>Bairro:</strong> ${data.bairro || 'N/A'}</p>
                    <p><strong>Cidade:</strong> ${data.localidade || 'N/A'}</p>
                    <p><strong>Estado:</strong> ${data.uf || 'N/A'}</p>
                    <p><strong>DDD:</strong> ${data.ddd || 'N/A'}</p>
                `;
            } catch (error) {
                resultadoDiv.innerHTML = `<p class="error">Erro ao consultar CEP: ${error.message}</p>`;
            }
        }