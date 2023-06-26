# Mapeamento Objeto-Relacional - Hibernate

Identificar as limitações e vantagens dop modelo objeto-relacional usando o Hibernate em Java. Criar casos de uso para testes e mapear problemas e vantagens herdados na junção dos dois modelos.
O contexto para essa investigação será um sistema de cadastramento de um museu. O sistema terá que cadastrar vários objetos diferentes, incluindo artistas, obras de arte e coleções, além de permitir a ação de operações CRUD.

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
