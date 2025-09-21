# sprint03 - API de Assessor de Investimentos

## Descrição do Projeto

Esta é uma API REST desenvolvida em Java com Spring Boot que simula o backend de um assessor de investimentos. A aplicação gerencia usuários, seus perfis de risco, produtos de investimento, contas e posições (carteira de investimentos).

A funcionalidade principal é a capacidade de fornecer sugestões de investimento personalizadas, recomendando produtos compatíveis com o perfil de risco de cada usuário.

### Funcionalidades Principais

- CRUD completo para Usuários (`User`) com perfis de risco (`CONSERVADOR`, `MODERADO`, `ARROJADO`).
- CRUD para Produtos de investimento (`Product`) com diferentes níveis de risco (`BAIXO`, `MEDIO`, `ALTO`).
- Gestão de Contas de investimento (`Account`) associadas aos usuários.
- Gestão de Posições (`Position`), permitindo simular a "compra" de ativos para uma conta.
- Endpoint de Sugestões que retorna uma lista de produtos recomendados com base no perfil do usuário.
- Tratamento de erros centralizado e respostas de API padronizadas.

## Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot 3.x
  - **Módulos:** Spring Web, Spring Data JPA, Spring Validation
- **Banco de Dados:** H2 (Padrão para desenvolvimento), compatível com PostgreSQL para produção.
- **Migrações de Banco:** Flyway
- **Build Tool:** Maven
- **Utilitários:** Lombok

## Passos de Configuração e Execução

### Pré-requisitos

- Java (JDK) 17 ou superior.
- Apache Maven 3.8 ou superior.

### Execução

1. **Clone o repositório:**

   ```bash
   git clone [https://github.com/seu-usuario/investidor-api.git](https://github.com/seu-usuario/investidor-api.git)
   cd investidor-api
   ```

2. **Execute a aplicação com o Maven Wrapper:**

   > O projeto utiliza um banco de dados em memória (H2) por padrão, então não é necessária nenhuma configuração de banco para iniciar.

   ```bash
   ./mvnw spring-boot:run
   ```

3. **Verificação:**
   Após a execução, a API estará disponível em `http://localhost:8080`. Você verá no console uma mensagem indicando que a aplicação foi iniciada com sucesso.

## Exemplos de Requisições e Respostas

A seguir, exemplos das principais operações da API. Utilize uma ferramenta como Postman ou Insomnia para testar.

---

### 1. Criar um Usuário

- **Endpoint:** `POST /users`
- **Descrição:** Cadastra um novo usuário no sistema.

**Requisição (Body):**

```json
{
  "name": "Carlos Conservador",
  "email": "carlos.conservador@email.com",
  "birthDate": "1980-01-10",
  "profile": "CONSERVADOR"
}
```

**Resposta (Status `201 Created`):**

```json
{
  "id": "e7a3b3f0-4c3e-4f5a-8b1e-9c3d4b2a1f0e",
  "name": "Carlos Conservador",
  "email": "carlos.conservador@email.com",
  "birthDate": "1980-01-10",
  "profile": "CONSERVADOR"
}
```

---

### 2. Criar um Produto de Investimento

- **Endpoint:** `POST /products`
- **Descrição:** Cadastra um novo produto de investimento.

**Requisição (Body):**

```json
{
  "ticker": "SELIC29",
  "name": "Tesouro Selic 2029",
  "riskLevel": "BAIXO"
}
```

**Resposta (Status `201 Created`):**

```json
{
  "id": "a9b8c7d6-5e4f-3a2b-1c0d-9e8f7a6b5c4d",
  "ticker": "SELIC29",
  "name": "Tesouro Selic 2029"
}
```

---

### 3. Criar uma Conta para um Usuário

- **Endpoint:** `POST /accounts`
- **Descrição:** Cria uma nova conta de investimentos associada a um usuário existente.

**Requisição (Body):**

```json
{
  "accountNumber": "12345-6",
  "userId": "e7a3b3f0-4c3e-4f5a-8b1e-9c3d4b2a1f0e"
}
```

**Resposta (Status `201 Created`):**

```json
{
  "id": "f5e4d3c2-1b0a-9e8f-7a6b-5c4d3e2f1b0a",
  "accountNumber": "12345-6",
  "userId": "e7a3b3f0-4c3e-4f5a-8b1e-9c3d4b2a1f0e"
}
```

---

### 4. Realizar um Investimento (Criar Posição)

- **Endpoint:** `POST /positions`
- **Descrição:** Simula a compra de um ativo para uma conta.

**Requisição (Body):**

```json
{
  "accountId": "f5e4d3c2-1b0a-9e8f-7a6b-5c4d3e2f1b0a",
  "productId": "a9b8c7d6-5e4f-3a2b-1c0d-9e8f7a6b5c4d",
  "quantity": 2
}
```

**Resposta (Status `200 OK`):**

```json
{
  "positionId": "b1c2d3e4-5f6a-7b8c-9d0e-1f2a3b4c5d6e",
  "quantity": 2,
  "product": {
    "id": "a9b8c7d6-5e4f-3a2b-1c0d-9e8f7a6b5c4d",
    "ticker": "SELIC29",
    "name": "Tesouro Selic 2029"
  }
}
```

---

### 5. Obter Sugestões de Investimento

- **Endpoint:** `GET /users/{userId}/suggestions`
- **Descrição:** Retorna uma lista de produtos recomendados para o perfil de risco do usuário.

**Requisição (URL com o ID do usuário conservador):**
`http://localhost:8080/users/e7a3b3f0-4c3e-4f5a-8b1e-9c3d4b2a1f0e/suggestions`

**Resposta (Status `200 OK`):**

```json
[
  {
    "id": "a9b8c7d6-5e4f-3a2b-1c0d-9e8f7a6b5c4d",
    "ticker": "SELIC29",
    "name": "Tesouro Selic 2029"
  }
]
```
