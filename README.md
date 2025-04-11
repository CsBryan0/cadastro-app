# Desafio Técnico - Cadastro de Pessoas

Este projeto é a solução para o desafio técnico da empresa Evológica. A proposta consiste em um sistema de cadastro de pessoas, com funcionalidades completas de CRUD e interface web.

---

## Estrutura do projeto

- `cadastro/` → Backend em Java com Spring Boot
- `frontend/` → Frontend em React + TypeScript

---

## Funcionalidades implementadas

### Backend
- API REST em Spring Boot 3
- Cadastro, listagem, edição e exclusão de pessoas
- Filtros combináveis: nome (parcial), CPF, email (parcial), data de nascimento (intervalo)
- Validação com DTOs
- Documentação com Swagger (SpringDoc)
- Banco de dados em memória com H2

### Frontend
- Listagem de pessoas com filtros
- Cadastro de nova pessoa
- Edição e exclusão com confirmação
- Navegação com React Router
---

##  Como rodar o projeto

###  Pré-requisitos
- Node.js 18+
- Java 17+
- Maven

### 1. Rodar o backend

```bash
cd cadastro
./mvnw spring-boot:run
Acesse o Swagger: http://localhost:8080/swagger-ui.html

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Usuário: sa, senha: (em branco)
```

### 2. Rodar o frontend

```bash
cd frontend
npm install
npm run dev
```
Acesse: http://localhost:5173

---

## Decisões técnicas durante o desenvolvimento

- **Separação de responsabilidades**: optei por uma arquitetura tradicional em camadas no backend (Controller, Service, Repository, DTO, Entity), visando clareza e manutenção.
- **DTOs no backend**: utilizei DTOs para manter a separação entre os dados da requisição e a entidade do banco, além de facilitar validações e filtros.
- **Filtros combináveis com JPQL**: para atender ao requisito de múltiplos filtros dinâmicos, usei uma `@Query` personalizada que aceita parâmetros opcionais — uma solução simples, eficiente e legível.
- **Swagger com SpringDoc**: incluído para facilitar o uso e teste da API, além de melhorar a documentação automática.
- **React com TypeScript**: escolhido para maior segurança e organização no frontend, mesmo com tempo limitado.
- **React Router**: usado para navegação entre tela de listagem e tela de cadastro/edição.

---
## Considerações finais

Devido a problemas pessoais não foi possível cumprir o cronograma que estabeleci para a realização completa do projeto. Por causa disso acabei não conseguindo implementar todas as melhorias que eu queria, como:

- Melhorias no visual e na responsividade do frontend.
- Tratamento de exceções personalizadas.
- Uso de gerenciador de estados.
- Validações no front e no backend

Porém entreguei uma API funcional, com documentação e filtros. Além de uma interface web capaz de realizar todas as operações de CRUD.


