# desafio-cast
Comparação de documentos na BASE64.

# desafio
• Prover dois http endponts que aceite um JSON base64 de dados binários de entrada.
    o <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
• Prover um endponint com resultado da diferença dois documentos passados.
    o <host>/v1/diff/<ID>
• Os resultados devem ser em JSON seguindo os seguintes requisitos.
    o Se os documentos forem igual, retorne "Documentos <ID> idênticos"
    o Se os documentos não forem do mesmo tamanho, retorne "Documentos <ID> com tamanhos diferentes"
    o Se os documentos forem do mesmo tamanho não é necessário um diff real, somente: Retornar em qual posição (Offset) os documentos se diferem.

# configuração do ambiente

1. Utilizar o JDK 1.8+
2. Executar o comando mvn clean install
3. Rodar a aplicação SpringBoot

# usando a API
Para verificar o funcionamento da API, será necessário os seguintes passos:

1. Ter ou baixar o Isomnia e/ou Postman
2. Criar uma requisição POST com a seguinte configuração:
        Headers
        {
            "Content-Type" : "application/json"
        }
        Body - raw
        {
	        "dados" : "YWJjZGU="
        }
3. Para os documentos referentes a posição esquerda, enviar a requisição para a URL: "http://localhost:8080/v1/diff/1/left"
4. Aguarde o resultado. Se sucesso(Status 200), a mensagem será essa: 
    {
        "Resultado": "O documento esquerdo foi salvo com sucesso!"
    }
5. Para os documentos referentes a posição direta, repita o passo 2, 3 e 4 com a seguinte url: "http://localhost:8080/v1/diff/1/right"
6. Aguarde o resultado da requisição. Se sucesso(Status 200), a mensagem será essa: 
    {
        "Resultado": "O documento direito foi salvo com sucesso!"
    }
7. Após as duas requisições forem criadas e salvas, crie outra do tipo GET, com a seguinte URL: "http://localhost:8080/v1/diff/1"

* Dependendo dos parâmetros enviados, o retorno será de acordo com as regras citadas no desafio acima.
    -- Considere como exemplo a busca de dois documentos iguais.

8. Se sucesso(Status 200) para os paramêtros salvos anteriormente, a requisição retornará essa mensagem:
    {
        "Resultado": "Documentos 1 idênticos"
    }
