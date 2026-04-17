
# Gestão de Medicamentos

Sistema completo para **cadastro e gerenciamento de usuários e medicamentos**, com controle de consumo, alertas de uso e histórico, desenvolvido em **Java + Spring Boot** e totalmente **containerizado com Docker**.

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring_Boot-4.0.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" />
</p>

---

## Sobre o projeto

Este projeto foi desenvolvido no **Bootcamp I 2026** com o objetivo de aplicar conceitos de:

*   API REST
*   Arquitetura em camadas
*   Relacionamentos entre entidades
*   Validações
*   Tratamento de exceções
*   Persistência de dados
*   Dockerização

O sistema permite controlar **quais medicamentos cada usuário utiliza**, incluindo informações detalhadas sobre **horário, frequência, dosagem, alertas e confirmação de consumo**.

***

## Pré-requisitos

Instale apenas:

| Ferramenta     | Download                                         | Verificar instalação |
| -------------- | ------------------------------------------------ | -------------------- |
| Docker Desktop | <https://www.docker.com/products/docker-desktop> | `docker --version`   |
| Git            | <https://git-scm.com>                            | `git --version`      |

> ❗ Não é necessário instalar Java ou MySQL localmente para rodar com Docker.

***

## Como rodar o projeto (3 comandos)

```bash
# 1. Clone o repositório
git clone https://github.com/euudanilo/bootcamp-uninter.git
cd bootcamp-uninter

# 2. Suba a aplicação + banco
docker compose up --build

# 3. Acesse a API
# http://localhost:8080
```

🔹 Na primeira execução o `--build` pode demorar alguns minutos.  
🔹 Nas próximas execuções, use apenas:

```bash
docker compose up
```

***

## Estrutura do projeto

```text
src/main/java/com/example/remedios
├── controller
│   ├── UsuarioController.java
│   ├── MedicamentoController.java
│   └── UsuarioMedicamentoController.java
├── dtos
│   ├── usuario
│   ├── medicamento
│   └── usuario_medicamento
├── entities
│   ├── Usuario.java
│   ├── Medicamento.java
│   ├── Endereco.java
│   ├── UsuarioMedicamento.java
│   ├── UsuarioMedicamentoId.java
│   └── StatusAlerta.java
├── exceptions
│   ├── ResourceNotFoundException.java
│   ├── BusinessException.java
│   └── GlobalExceptionHandler.java
├── repository
│   ├── UsuarioRepository.java
│   ├── MedicamentoRepository.java
│   └── UsuarioMedicamentoRepository.java
└── services
    ├── UsuarioService.java
    ├── MedicamentoService.java
    └── UsuarioMedicamentoService.java
```

***

## Modelo de domínio

### Usuário

*   idUsuario
*   nome
*   telefone
*   email
*   endereço (rua, número, complemento, bairro, CEP, cidade, estado)

### Medicamento

*   idMedicamento
*   nomeComercial
*   nomeGenerico
*   quantidade
*   formaUso
*   observacao

### UsuárioMedicamento

*   idUsuario
*   idMedicamento
*   horarioUso
*   frequenciaUso
*   dosagem
*   dataHorarioAlerta
*   statusAlerta
*   dataHorarioConsumo
*   confirmacaoConsumo

***

## Endpoints da API

### Usuários

| Método | Endpoint         | Descrição        |
| ------ | ---------------- | ---------------- |
| POST   | `/usuarios`      | Cadastra usuário |
| GET    | `/usuarios`      | Lista usuários   |
| GET    | `/usuarios/{id}` | Busca usuário    |
| PUT    | `/usuarios/{id}` | Atualiza usuário |
| DELETE | `/usuarios/{id}` | Remove usuário   |

***

### Medicamentos

| Método | Endpoint             | Descrição            |
| ------ | -------------------- | -------------------- |
| POST   | `/medicamentos`      | Cadastra medicamento |
| GET    | `/medicamentos`      | Lista medicamentos   |
| GET    | `/medicamentos/{id}` | Busca medicamento    |
| PUT    | `/medicamentos/{id}` | Atualiza medicamento |
| DELETE | `/medicamentos/{id}` | Remove medicamento   |

***

### Usuário x Medicamento

| Método | Endpoint                                            | Descrição                     |
| ------ | --------------------------------------------------- | ----------------------------- |
| POST   | `/usuario-medicamentos`                             | Associa usuário e medicamento |
| GET    | `/usuario-medicamentos`                             | Lista associações             |
| GET    | `/usuario-medicamentos/{idUsuario}/{idMedicamento}` | Busca associação              |
| PUT    | `/usuario-medicamentos/{idUsuario}/{idMedicamento}` | Atualiza dados                |
| DELETE | `/usuario-medicamentos/{idUsuario}/{idMedicamento}` | Remove associação             |

***

## Funcionalidades implementadas

*   CRUD completo de usuários
*   CRUD completo de medicamentos
*   Relacionamento N:N entre usuário e medicamento
*   Registro de dados de consumo
*   Controle de alertas de uso
*   Enumeração de status de alerta
*   Validação automática de campos
*   Tratamento global de exceções
*   Persistência com MySQL
*   Dockerização com Docker Compose
*   Arquitetura em camadas (Controller, Service, Repository)

***

## Variáveis de ambiente

Configuradas no `docker-compose.yml`:

| Variável                     | Valor padrão                         | Descrição      |
| ---------------------------- | ------------------------------------ | -------------- |
| MYSQL\_DATABASE              | remedios\_db                         | Nome do banco  |
| MYSQL\_ROOT\_PASSWORD        | root                                 | Senha do MySQL |
| SPRING\_DATASOURCE\_URL      | jdbc:mysql://mysql:3306/remedios\_db | URL do banco   |
| SPRING\_DATASOURCE\_USERNAME | root                                 | Usuário        |
| SPRING\_DATASOURCE\_PASSWORD | root                                 | Senha          |

***

