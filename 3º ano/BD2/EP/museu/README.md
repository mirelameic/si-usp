# Museu - BD2

EP da disciplina Banco de Dados 2: criação de um modelo de Museu; utilizando JDBC, Maven e PostgreSQL.

## Folder Structure

- `src`: códigos fonte Java
- `target`: arquivos compilados
- `pom`: dependências maven

## Project Structure (MVC Pattern)

- `connection`: conexão com o PostgreSQL
- `model`: camada que possui a lógica da aplicação
    - `bean`: classes unidade do sistema
    - `dao`: responsável por trocar informações com o SGBD e fornecer operações CRUD e de pesquisas
- `main`: visualização/interação com o usuário
- `bd`: tabelas e inserções do banco utilizado