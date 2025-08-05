# Documentação da API Médica

## Introdução

Esta documentação detalha a API Médica, que permite o gerenciamento de pacientes, médicos e consultas, além de um sistema de autenticação robusto. A API foi desenvolvida para ser intuitiva e fácil de integrar, seguindo os princípios RESTful.

### Endpoints de documentação com swagger
**GET /v3/api-docs**

**GET /swagger-ui.html**

## Visão Geral da Aplicação

A API Médica é composta por diversos módulos, cada um responsável por uma área específica do sistema. Os principais módulos incluem:

- **Autenticação**: Gerencia o acesso e a segurança da API.
- **Pacientes**: Permite o cadastro, consulta, atualização e exclusão de informações de pacientes.
- **Médicos**: Permite o cadastro, consulta, atualização e exclusão de informações de médicos.
- **Consultas**: Gerencia o agendamento e cancelamento de consultas.

## Autenticação

A autenticação na API é realizada via token JWT (JSON Web Token). Para acessar os endpoints protegidos, é necessário primeiro obter um token de autenticação através do endpoint de login.

### Endpoint de Login

**POST /login**

Este endpoint é utilizado para autenticar um usuário e obter um token JWT.

**Corpo da Requisição (Request Body):**

`DadosAutenticacao`

| Campo   | Tipo   | Descrição               | Obrigatório |
|---------|--------|-------------------------|-------------|
| `login` | string | Nome de usuário         | Sim         |
| `senha` | string | Senha do usuário        | Sim         |

**Exemplo de Requisição:**

```json
{
  "login": "usuario.teste",
  "senha": "123456"
}
```

**Resposta (Response):**

Um token JWT será retornado em caso de sucesso.

```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

**Segurança:**

Este endpoint não requer autenticação prévia.

## Pacientes

O módulo de pacientes permite gerenciar as informações dos pacientes cadastrados no sistema.

### Listar Pacientes

**GET /pacientes**

Retorna uma lista paginada de pacientes.

**Parâmetros de Consulta (Query Parameters):**

`paginacao` (Pageable)

| Campo   | Tipo    | Descrição                               | Obrigatório |
|---------|---------|-----------------------------------------|-------------|
| `page`  | integer | Número da página (inicia em 0)          | Sim         |
| `size`  | integer | Quantidade de itens por página (mínimo 1) | Sim         |
| `sort`  | array   | Critérios de ordenação                  | Não         |

**Resposta (Response):**

`PageDadosListagemPaciente`

| Campo           | Tipo    | Descrição                               |
|-----------------|---------|-----------------------------------------|
| `totalPages`    | integer | Total de páginas                        |
| `totalElements` | integer | Total de elementos                      |
| `content`       | array   | Lista de `DadosListagemPaciente`        |

`DadosListagemPaciente`

| Campo   | Tipo   | Descrição            |
|---------|--------|----------------------|
| `id`    | integer | ID do paciente       |
| `nome`  | string | Nome do paciente     |
| `email` | string | Email do paciente    |
| `cpf`   | string | CPF do paciente      |

**Segurança:**

Requer token JWT.

### Cadastrar Paciente

**POST /pacientes**

Cadastra um novo paciente no sistema.

**Corpo da Requisição (Request Body):**

`DadosCadastroPaciente`

| Campo      | Tipo     | Descrição                  | Obrigatório |
|------------|----------|----------------------------|-------------|
| `nome`     | string   | Nome completo do paciente  | Sim         |
| `email`    | string   | Email do paciente          | Sim         |
| `telefone` | string   | Telefone do paciente       | Sim         |
| `cpf`      | string   | CPF do paciente            | Sim         |
| `endereco` | object   | Objeto `DadosEndereco`     | Sim         |

`DadosEndereco`

| Campo         | Tipo   | Descrição                               | Obrigatório |
|---------------|--------|-----------------------------------------|-------------|
| `logradouro`  | string | Logradouro (rua, avenida, etc.)         | Sim         |
| `bairro`      | string | Bairro                                  | Sim         |
| `cep`         | string | CEP (formato: `dddddddd`)               | Sim         |
| `cidade`      | string | Cidade                                  | Sim         |
| `numero`      | string | Número do imóvel                        | Não         |
| `complemento` | string | Complemento (apto, bloco, etc.)         | Não         |
| `uf`          | string | Unidade Federativa (UF)                 | Sim         |

**Exemplo de Requisição:**

```json
{
  "nome": "João da Silva",
  "email": "joao.silva@example.com",
  "telefone": "(11) 98765-4321",
  "cpf": "12345678900",
  "endereco": {
    "logradouro": "Rua das Flores",
    "bairro": "Centro",
    "cep": "01000000",
    "cidade": "São Paulo",
    "numero": "123",
    "complemento": "Apto 101",
    "uf": "SP"
  }
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Paciente cadastrado com sucesso."
```

**Segurança:**

Requer token JWT.

### Atualizar Paciente

**PUT /pacientes**

Atualiza as informações de um paciente existente.

**Corpo da Requisição (Request Body):**

`DadosAtualizaPaciente`

| Campo      | Tipo     | Descrição                  | Obrigatório |
|------------|----------|----------------------------|-------------|
| `id`       | integer  | ID do paciente a ser atualizado | Sim         |
| `nome`     | string   | Novo nome do paciente      | Não         |
| `telefone` | string   | Novo telefone do paciente  | Não         |
| `endereco` | object   | Novo objeto `DadosEndereco` | Não         |

**Exemplo de Requisição:**

```json
{
  "id": 1,
  "nome": "João da Silva Atualizado",
  "telefone": "(11) 99999-8888"
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Paciente atualizado com sucesso."
```

**Segurança:**

Requer token JWT.

### Ativar Paciente

**PUT /pacientes/ativar/{id}**

Ativa um paciente inativo no sistema.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do paciente a ser ativado | Sim         |

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Paciente ativado com sucesso."
```

**Segurança:**

Requer token JWT.

### Detalhar Paciente

**GET /pacientes/{id}**

Retorna os detalhes de um paciente específico.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do paciente a ser detalhado | Sim         |

**Resposta (Response):**

Retorna uma string com os detalhes do paciente.

```json
"Detalhes do paciente com ID 1."
```

**Segurança:**

Requer token JWT.

### Excluir Paciente

**DELETE /pacientes/{id}**

Exclui um paciente do sistema.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do paciente a ser excluído | Sim         |

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Paciente excluído com sucesso."
```

**Segurança:**

Requer token JWT.

## Médicos

O módulo de médicos permite gerenciar as informações dos médicos cadastrados no sistema.

### Listar Médicos

**GET /medicos**

Retorna uma lista paginada de médicos.

**Parâmetros de Consulta (Query Parameters):**

`paginacao` (Pageable)

| Campo   | Tipo    | Descrição                               | Obrigatório |
|---------|---------|-----------------------------------------|-------------|
| `page`  | integer | Número da página (inicia em 0)          | Sim         |
| `size`  | integer | Quantidade de itens por página (mínimo 1) | Sim         |
| `sort`  | array   | Critérios de ordenação                  | Não         |

**Resposta (Response):**

`PageDadosListagemMedico`

| Campo           | Tipo    | Descrição                               |
|-----------------|---------|-----------------------------------------|
| `totalPages`    | integer | Total de páginas                        |
| `totalElements` | integer | Total de elementos                      |
| `content`       | array   | Lista de `DadosListagemMedico`          |

`DadosListagemMedico`

| Campo         | Tipo   | Descrição            |
|---------------|--------|----------------------|
| `id`          | integer | ID do médico         |
| `nome`        | string | Nome do médico       |
| `email`       | string | Email do médico      |
| `crm`         | string | CRM do médico        |
| `especialidade` | string | Especialidade do médico |

**Segurança:**

Requer token JWT.

### Cadastrar Médico

**POST /medicos**

Cadastra um novo médico no sistema.

**Corpo da Requisição (Request Body):**

`DadosCadastroMedico`

| Campo         | Tipo     | Descrição                  | Obrigatório |
|---------------|----------|----------------------------|-------------|
| `nome`        | string   | Nome completo do médico    | Sim         |
| `email`       | string   | Email do médico            | Sim         |
| `telefone`    | string   | Telefone do médico         | Sim         |
| `crm`         | string   | CRM do médico              | Sim         |
| `especialidade` | string   | Especialidade do médico (ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA) | Sim         |
| `endereco`    | object   | Objeto `DadosEndereco`     | Sim         |

**Exemplo de Requisição:**

```json
{
  "nome": "Dr. Carlos Souza",
  "email": "carlos.souza@example.com",
  "telefone": "(11) 97777-6666",
  "crm": "123456",
  "especialidade": "CARDIOLOGIA",
  "endereco": {
    "logradouro": "Avenida Principal",
    "bairro": "Jardins",
    "cep": "02000000",
    "cidade": "São Paulo",
    "numero": "456",
    "complemento": "Sala 202",
    "uf": "SP"
  }
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Médico cadastrado com sucesso."
```

**Segurança:**

Requer token JWT.

### Atualizar Médico

**PUT /medicos**

Atualiza as informações de um médico existente.

**Corpo da Requisição (Request Body):**

`DadosAtualizarMedico`

| Campo      | Tipo     | Descrição                  | Obrigatório |
|------------|----------|----------------------------|-------------|
| `id`       | integer  | ID do médico a ser atualizado | Sim         |
| `nome`     | string   | Novo nome do médico        | Não         |
| `telefone` | string   | Novo telefone do médico    | Não         |
| `endereco` | object   | Novo objeto `DadosEndereco` | Não         |

**Exemplo de Requisição:**

```json
{
  "id": 1,
  "nome": "Dr. Carlos Souza Atualizado",
  "telefone": "(11) 96666-5555"
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Médico atualizado com sucesso."
```

**Segurança:**

Requer token JWT.

### Ativar Médico

**PUT /medicos/ativar/{id}**

Ativa um médico inativo no sistema.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do médico a ser ativado | Sim         |

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Médico ativado com sucesso."
```

**Segurança:**

Requer token JWT.

### Detalhar Médico

**GET /medicos/{id}**

Retorna os detalhes de um médico específico.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do médico a ser detalhado | Sim         |

**Resposta (Response):**

Retorna uma string com os detalhes do médico.

```json
"Detalhes do médico com ID 1."
```

**Segurança:**

Requer token JWT.

### Excluir Médico

**DELETE /medicos/{id}**

Exclui um médico do sistema.

**Parâmetros de Caminho (Path Parameters):**

| Campo | Tipo    | Descrição            | Obrigatório |
|-------|---------|----------------------|-------------|
| `id`  | integer | ID do médico a ser excluído | Sim         |

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Médico excluído com sucesso."
```

**Segurança:**

Requer token JWT.

## Consultas

O módulo de consultas permite agendar e cancelar consultas.

### Agendar Consulta

**POST /consultas**

Agenda uma nova consulta.

**Corpo da Requisição (Request Body):**

`DadosAgendamentoConsulta`

| Campo           | Tipo     | Descrição                  | Obrigatório |
|-----------------|----------|----------------------------|-------------|
| `idMedico`      | integer  | ID do médico (opcional)    | Não         |
| `idPaciente`    | integer  | ID do paciente             | Sim         |
| `data`          | string   | Data e hora da consulta (formato: `yyyy-MM-ddTHH:mm:ss`) | Sim         |
| `especialidade` | string   | Especialidade desejada (ORTOPEDIA, CARDIOLOGIA, GINECOLOGIA, DERMATOLOGIA) | Não         |

**Exemplo de Requisição:**

```json
{
  "idPaciente": 1,
  "idMedico": 2,
  "data": "2025-08-15T10:00:00",
  "especialidade": "CARDIOLOGIA"
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Consulta agendada com sucesso."
```

**Segurança:**

Requer token JWT.

### Cancelar Consulta

**DELETE /consultas**

Cancela uma consulta existente.

**Corpo da Requisição (Request Body):**

`DadosCancelamentoConsulta`

| Campo      | Tipo     | Descrição                  | Obrigatório |
|------------|----------|----------------------------|-------------|
| `idConsulta` | integer  | ID da consulta a ser cancelada | Sim         |
| `motivo`   | string   | Motivo do cancelamento     | Sim         |

**Exemplo de Requisição:**

```json
{
  "idConsulta": 123,
  "motivo": "Paciente não poderá comparecer."
}
```

**Resposta (Response):**

Retorna uma string indicando o sucesso da operação.

```json
"Consulta cancelada com sucesso."
```

**Segurança:**

Requer token JWT.



