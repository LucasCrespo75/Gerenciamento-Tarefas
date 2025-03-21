# Projeto Avaliação - Backend e Frontend

Este projeto tem como objetivo criar um sistema de avaliação utilizando Spring Boot, PostgreSQL, Docker e Docker Compose.

## Como rodar o projeto localmente
## Pré-requisitos
Antes de rodar o projeto, verifique se você possui os seguintes itens instalados no seu sistema:

 1. Docker e Docker Compose
Caso ainda não tenha o Docker instalado, siga as instruções de instalação abaixo:
 - Instalação do Docker
 - Instalação do Docker Compose
 - 
2. Git para clonar o repositório
Caso ainda não tenha o Git instalado, você pode baixá-lo em:
 - Instalar o Git

## Passos para rodar o projeto
**1. Clone o repositório**
Abra o terminal e execute o seguinte comando para clonar o repositório:
bash
Copiar
Editar
git clone https://github.com/SEU_USUARIO/projeto-avaliacao.git

Em seguida, acesse o diretório do projeto:
bash
Copiar
Editar
cd projeto-avaliacao

**2. Suba os contêineres com Docker Compose**
Agora, vamos subir todos os serviços necessários para rodar o projeto (backend, frontend e banco de dados). Para isso, use o comando abaixo:
bash
Copiar
Editar
docker-compose up --build

Este comando realiza as seguintes ações:

 - Constrói as imagens dos serviços definidos no arquivo docker-compose.yml.
 - Inicia os contêineres do backend, frontend e banco de dados PostgreSQL.
 - Mapeia as portas para permitir o acesso aos serviços localmente em seu navegador.
 - 
**3. Acessando os serviços**
Depois que o comando acima for executado e os contêineres estiverem em funcionamento, você pode acessar os serviços da seguinte forma:

 - User Service (user-service)
Porta interna: 8081 (onde o serviço está rodando dentro do contêiner).
Porta externa: 8082 (a porta que será mapeada para o host, permitindo o acesso ao serviço).
 - Task Service (task-service)
Porta interna: 8080 (onde o serviço está rodando dentro do contêiner).
Porta externa: 8080 (a mesma porta mapeada para o host).

 - Frontend (frontend)
Porta interna: 80 (onde o frontend está rodando dentro do contêiner).
Porta externa: 80 (a mesma porta mapeada para o host).

 - pgAdmin (pgadmin)
Porta interna: 80 (onde o pgAdmin está rodando dentro do contêiner).
Porta externa: 5050 (a porta que será mapeada para o host).

** 4. Resumo das portas externas** 

 - User Service: http://localhost:8082
 - Task Service: http://localhost:8080
 - Frontend: http://localhost
 - Banco de Dados (PostgreSQL): localhost:5432
 - pgAdmin: http://localhost:5050

## O banco de dados PostgreSQL será inicializado com um banco chamado tarefa_db. As credenciais de acesso são:

Usuário: postgres
Senha: root
Essas configurações estão definidas automaticamente no docker-compose.yml e não precisam ser alteradas, a menos que você deseje personalizar o banco.

** 5. Verificando os logs ** 
Caso queira acompanhar os logs de execução dos contêineres, use o comando:

bash
Copiar
Editar
docker-compose logs -f
Para ver apenas os logs de um serviço específico (por exemplo, o backend), execute:

bash
Copiar
Editar
docker-compose logs -f backend
6. Parando os contêineres
Quando terminar de usar o projeto, você pode parar os contêineres com o comando:

bash
Copiar
Editar
docker-compose down
Este comando vai parar e remover todos os contêineres, redes e volumes criados pelo Docker Compose.
