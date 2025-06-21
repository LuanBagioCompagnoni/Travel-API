# ✈️ Travel API

API RESTful de gerenciamento de destinos turísticos com avaliações, autenticação com JWT e controle de permissões baseado em usuários e funções (roles).

🔗 [👉 Documentação Postman](https://postman.co/workspace/My-Workspace~b7d2085c-386d-4ef5-8b1c-5daf16b16796/collection/32718812-0555fe6d-b79b-43e1-98dd-a718f271a23e?action=share&creator=32718812)

---

## 🧭 Funcionalidades

- **CRUD de destinos de viagem** (nome, local, descrição)
- **Sistema de avaliação por nota (1 a 10)**
- **Média e total de avaliações automáticas**
- **Autenticação via JWT**
- **Criação de usuários com roles**
- **Controle de acesso a rotas baseado em permissões**
- **Persistência de dados com PostgreSQL**

---

## 🛠️ Tecnologias utilizadas

- ☕ Java 17+
- 🌱 Spring Boot
- 🔐 Spring Security + JWT
- 🐘 PostgreSQL
- 🐳 Docker & Docker Compose
- 📦 Maven

---

## 📌 Endpoints

| Método | Rota                                | Descrição                                         | Autenticação |
|--------|-------------------------------------|--------------------------------------------------|---------------|
| POST   | `/auth/login`                       | Login de usuário, retorna token JWT              | ❌            |
| POST   | `/user`                             | Criação de usuário                               | ✅            |
| GET    | `/destination`                      | Lista todos os destinos                          | ✅            |
| GET    | `/destination/{id}`                 | Busca um destino por ID                          | ✅            |
| GET    | `/destination/getByTerm?term=term`  | Busca um destino por nome, localização ou descrição | ✅        |
| POST   | `/destination`                      | Cria um novo destino                             | ✅            |
| PUT    | `/destination/{id}`                 | Atualiza um destino completo                     | ✅            |
| PATCH  | `/destination/{id}`                 | Atualiza parcialmente um destino                 | ✅            |
| DELETE | `/destination/{id}`                 | Remove um destino                                | ✅            |
| POST   | `/destination-review/{id}/review`   | Adiciona uma avaliação a um destino              | ✅            |

> ✅ Requer envio do token JWT no header:  
> `Authorization: Bearer <seu_token>`

---

## 📝 Avaliações

- A nota da avaliação deve estar entre **1 e 10**
- A média e o total de reviews são **recalculados automaticamente**
- A avaliação atualiza o destino e persiste os dados no banco

---

## 👥 Usuários padrão criados

> Criados automaticamente via seed do banco (`seed.sql`)

| Username | Password | Role  |
|----------|----------|-------|
| `admin`  | `admin`  | ADMIN |
| `user`   | `user`   | USER  |

---

## 🚀 Como rodar o projeto

### Pré-requisitos

- Docker
- Docker Compose

### Preencher as variáveis de ambiente
Antes de iniciar a aplicação, é necessário preencher as variáveis de ambiente no arquivo `docker-compose.yml`. Essas variáveis controlam o acesso ao banco de dados e o comportamento do sistema.

- ### PostgresSQL
```yml
POSTGRES_USER:     # Nome de usuário do banco (ex: "admin")
POSTGRES_PASSWORD: # Senha do banco (ex: "admin")
```

- ### Travel-api
```yml
SPRING_DATASOURCE_USERNAME:     # Mesmo valor de POSTGRES_USER
SPRING_DATASOURCE_PASSWORD:     # Mesmo valor de POSTGRES_PASSWORD
JWT_SECRET:                     # Chave secreta usada para geração e validação do JWT (ex: texto aleatório seguro)
```
💡 Dica: você pode gerar uma chave segura para JWT_SECRET usando algum gerador online ou comandos como openssl rand -hex 32.

### Instruções

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/travel-api.git

# Acesse a pasta do projeto
cd travel-api

# Suba os containers
docker-compose up --build
```

### Isso iniciará:

- 🐘 Banco PostgreSQL na porta 5432
- ☕ API na porta 8080

---

#  🔐 Autenticação JWT
  Para acessar rotas protegidas:

### Faça login:

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "admin"
}
```

### Você receberá um token:

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5..."
}
```

### Envie esse token no header das requisições protegidas:

```http
Authorization: Bearer <seu_token>
```