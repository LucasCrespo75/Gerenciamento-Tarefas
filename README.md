# Projeto Avaliação - Backend e Frontend

Este projeto tem como objetivo criar um sistema de avaliação utilizando tecnologias como **Spring Boot**, **PostgreSQL**, **Docker** e **Docker Compose**.

## Como rodar o projeto localmente

### Pré-requisitos:

1. **Docker** e **Docker Compose** instalados no seu sistema. Caso não tenha, siga as instruções para instalação:
   - [Docker - Instalação](https://docs.docker.com/get-docker/)
   - [Docker Compose - Instalação](https://docs.docker.com/compose/install/)

2. **Git** para clonar o repositório. Caso não tenha, instale o Git a partir de [aqui](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git).

### Passos para rodar o projeto:

1. **Clone o repositório**:

   No seu terminal, execute o seguinte comando:

   ```bash
   git clone https://github.com/SEU_USUARIO/projeto-avaliacao.git
   cd projeto-avaliacao

2. **Suba os contêineres com Docker Compose**:

Para rodar o projeto localmente, utilize o Docker Compose. No diretório do projeto, execute:

bash
Copiar
Editar
docker-compose up --build

Este comando irá:
 - Construir as imagens dos serviços.
 - Iniciar os contêineres do backend, frontend e banco de dados PostgreSQL.
 - Mapear as portas para acesso local.

**Acessando os serviços**:
 - Backend: O backend estará disponível em http://localhost:8080.
 - Frontend: Caso tenha frontend, estará disponível em http://localhost.
 - Banco de Dados (PostgreSQL): O banco de dados estará rodando na porta 5432.

**Configuração do banco de dados**:

O banco de dados PostgreSQL será inicializado com um banco chamado tarefa_db. As credenciais de acesso são:

Usuário: postgres
Senha: root
