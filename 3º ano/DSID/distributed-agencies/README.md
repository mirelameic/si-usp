# Distributed Agencies

    Projeto da disciplina de Sistemas Distribuidos (EACH-USP)

O programa em questão é uma implementação em Java de um sistema distribuído baseado em RMI (Remote Method Invocation). O sistema é composto por várias classes que representam entidades como agências, agentes e mensagens, além de uma interface para o serviço de registro das agências (NamingService).

## Estrutura

- `src`: arquivos .java
  - `Agency`
  - `Agent`
  - `NamingService`
  - `User`
- `bin`: arquivos .class

## Classes
- `Agency`:
  Interface que define os métodos que uma agência deve implementar. Algumas das principais operações são adicionar um agente, mover um agente para outra agência, enviar mensagens entre agentes e destruir um agente.

- `AgencyImpl`:
  Implementação da interface Agency. Nesta classe, são realizadas operações como adicionar agentes, mover agentes entre agências, enviar e receber mensagens, além de gerenciar a lista de agentes da agência.

- `Message`:
  Classe que representa uma mensagem enviada entre agentes. Cada mensagem possui um identificador único, o ID do agente de origem, o ID do agente de destino e o conteúdo da mensagem.

- `Agent`:
   Classe que representa um agente do sistema. Cada agente possui um ID, um nome, uma referência para a agência a qual pertence e métodos para enviar e receber mensagens.

- `NamingService`:
  Interface que define os métodos que o serviço de registro das agências deve implementar, como obter uma agência pelo ID ou nome, adicionar agentes e mover agentes entre agências.

- `NamingServiceImpl`:
  Implementação da interface NamingService. Nesta classe, são registradas as agências e agentes disponíveis no sistema, e é fornecida uma maneira de acessar as informações das agências e agentes registrados.

- `NamingServiceServer`:
  Classe responsável por iniciar o serviço de registro das agências (NamingService) e torná-lo disponível para as outras partes do sistema.

- `UserInterface`:
  Responsável por interagir com o usuário, exibir mensagens no console e obter comandos de entrada do usuário. Ela fornece métodos simples para exibir mensagens, ler comandos e lidar com erros de exceção.

- `Server`:
  Classe responsável por iniciar uma agência específica, tornando-a disponível para os clientes e registrando-a no serviço de registro das agências.

- `Client`:
  Classe que representa o cliente do sistema distribuído. O cliente pode interagir com o sistema através de comandos no terminal, como listar agências, criar agentes, mover agentes entre agências e enviar mensagens.

## Comandos do cliente
  - `show-commands`: Exibe a lista de comandos disponíveis no cliente.

  - `bind <agency_name>`: Conecta o cliente a uma agência específica pelo nome. Após executar este comando, o cliente poderá interagir com a agência selecionada.

  - `list-agencies`: Lista todas as agências disponíveis no sistema, mostrando seus nomes e IDs.

  - `create-agent`: Cria um novo agente e o associa a uma agência específica. O cliente solicitará o ID do agente e o nome da agência onde o agente deve ser criado.

  - `move-agent`: Move um agente de uma agência para outra. O cliente solicitará o ID do agente que será movido e o nome da agência de destino.

  - `list-agents`: Lista todos os agentes registrados no sistema, mostrando seus IDs e nomes, agrupados por agência.

  - `message`: Envia uma mensagem de um agente para outro. O cliente solicitará o ID do agente de origem, o ID do agente de destino e o conteúdo da mensagem.

  - `quit`: Encerra o cliente, terminando sua execução.

#
## Compilar
- Dentro da pasta `distributed-agencies`, execute o comando:
  ~~~ 
  $ find src -name "*.java" -print | xargs javac -d bin
  ~~~
## Executar
- Dentro da pasta `distributed-agencies`, execute os seguintes comandos:
  - Abra um terminal:
  ~~~ 
  $ cd bin; rmiregistry
  ~~~
  - Abra outro terminal:
  ~~~
  $  java -cp bin User.NamingServiceServer
  ~~~
  - Abra outro terminal. Troque `<agency_name>` pelo nome da agência que será criada e `<port>` pelo número da porta que deseja conectar:
  ~~~
  $ java -cp bin User.Server <agency_name> <port>
  ~~~
  - Abra outro terminal:
  ~~~
  $ java -cp bin User.Client
  ~~~