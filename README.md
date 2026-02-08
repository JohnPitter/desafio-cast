# Desafio Cast

<div align="center">

![Java](https://img.shields.io/badge/Java-8+-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-green?style=for-the-badge&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

**Comparacao de Documentos em BASE64**

*API REST para comparacao de documentos binarios codificados em Base64*

[API](#api-endpoints) •
[Instalacao](#instalacao) •
[Uso](#uso)

</div>

---

## Overview

API REST para comparacao de documentos em BASE64. Recebe dois documentos codificados, armazena e compara retornando se sao identicos, tem tamanhos diferentes, ou em qual posicao (offset) se diferem.

---

## API Endpoints

| Metodo | Endpoint | Descricao |
|--------|----------|-----------|
| `POST` | `/v1/diff/{id}/left` | Envia documento da esquerda |
| `POST` | `/v1/diff/{id}/right` | Envia documento da direita |
| `GET` | `/v1/diff/{id}` | Retorna resultado da comparacao |

### Resultados Possiveis

| Cenario | Resposta |
|---------|----------|
| Documentos identicos | `"Documentos {id} identicos"` |
| Tamanhos diferentes | `"Documentos {id} com tamanhos diferentes"` |
| Mesmo tamanho, conteudo diferente | Offset onde os documentos se diferem |

---

## Instalacao

### Requisitos

| Requisito | Versao |
|-----------|--------|
| JDK | 8+ |
| Maven | 3.6+ |

### Quick Start

```bash
# Clone o repositorio
git clone https://github.com/JohnPitter/desafio-cast.git
cd desafio-cast

# Build
mvn clean install

# Execute
mvn spring-boot:run
```

A API estara disponivel em `http://localhost:8080`

---

## Uso

### Enviar Documento Esquerdo

```bash
curl -X POST http://localhost:8080/v1/diff/1/left \
  -H "Content-Type: application/json" \
  -d '{"dados": "YWJjZGU="}'
```

### Enviar Documento Direito

```bash
curl -X POST http://localhost:8080/v1/diff/1/right \
  -H "Content-Type: application/json" \
  -d '{"dados": "YWJjZGU="}'
```

### Comparar Documentos

```bash
curl http://localhost:8080/v1/diff/1
```

---

## Formato de Entrada

```json
{
  "dados": "YWJjZGU="
}
```

| Campo | Tipo | Descricao |
|-------|------|-----------|
| `dados` | string | Documento codificado em BASE64 |

---

## License

MIT
