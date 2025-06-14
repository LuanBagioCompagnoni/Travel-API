# ✈️ Travel API

API simples de gerenciamento de destinos turísticos, com sistema de avaliações por nota.

🔗 [👉 Documentação Postman](https://postman.co/workspace/My-Workspace~b7d2085c-386d-4ef5-8b1c-5daf16b16796/collection/32718812-0555fe6d-b79b-43e1-98dd-a718f271a23e?action=share&creator=32718812)

---

## 🧭 Funcionalidades

- **CRUD de destinos de viagem** (nome, local, descrição)
- **Cálculo automático da média de avaliações**
- Armazenamento **em memória** (temporário)

---

## 🛠️ Tecnologias utilizadas

- ☕ Java 17+
- 🌱 Spring Boot
- 📦 Maven

---

## 📌 Endpoints

| Método | Rota                                        | Descrição                                                                      |
|--------|---------------------------------------------|--------------------------------------------------------------------------------|
| GET    | `/destination`                              | Lista todos os destinos                                                        |
| GET    | `/destination/{id}`                         | Busca um destino por ID                                                        |
| GET    | `/destination/getByTerm?term=term`          | Busca um destino por um texto (nome || localização || descrição)               |
| POST   | `/destination`                              | Cria um novo destino                                                           |
| PUT    | `/destination/{id}`                         | Atualiza um destino (completo)                                                 |
| PATCH  | `/destination/{id}`                         | Atualiza parcialmente um destino                                               |
| DELETE | `/destination/{id}`                         | Remove um destino                                                              |
| POST   | `/destination-review/{id}/review`           | Adiciona uma avaliação a um destino                                            |

---

## 📝 Avaliações

- A nota da avaliação deve estar entre 1 e 10
- A média e o total de reviews são recalculados automaticamente após cada avaliação

---

# 💡 Observações
- Os dados não são persistidos em banco de dados

- Ao reiniciar a aplicação, todos os dados são perdidos

- Ideal para fins de estudo e prática com APIs REST usando Spring Boot


---

# 🚀 Como rodar o projeto

``` bash
# Clone o repositório
git clone https://github.com/seu-usuario/travel-api.git

# Acesse a pasta do projeto
cd travel-api

# Rode com sua IDE ou via terminal
./mvnw spring-boot:run

```
