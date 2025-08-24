# API RESTful de Usuários 

Este é um projeto de prática de backend com **Spring Boot**, com foco em **autenticação e autorização usando JWT (JSON Web Tokens) e Spring Security**. O objetivo é consolidar conceitos de Spring, persistência, testes e segurança.

> ⚠️ Projeto em desenvolvimento: funcionalidades e estrutura podem mudar durante a implementação.

## 🔧 Tecnologias

| Camada          | Tecnologias/Frameworks            |
|-----------------|----------------------------------|
| Backend         | Java 21, Spring Boot 3.5.5       |
| Segurança       | Spring Security, JWT             |
| Persistência    | Spring Data JPA, H2 Database    |
| Modelagem       | DTOs, MapStruct                  |
| Testes          | JUnit 5, Mockito, MockMvc        |
| Utilitários     | Lombok                           |

## ⚙️ Funcionalidades

- CRUD de usuários e entidades relacionadas
- Autenticação e autorização via JWT
- Validação de dados usando Spring Validation
- Tratamento global de exceções
- Testes unitários e de integração com MockMvc

## 📁 Estrutura Prevista do Projeto

```text
src/
├─ main/
│  ├─ java/
│  │  ├─ controller/
│  │  ├─ service/
│  │  ├─ repository/
│  │  ├─ model/
│  │  ├─ dto/
│  │  ├─ security/
│  │  └─ exception/
│  └─ resources/
│     ├─ application.properties
│     └─ data.sql
└─ test/
   └─ java/
```
# 




