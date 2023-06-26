import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedHashSet;

public class PizzariaServer {

    //porta utilizada pelo servidor para ouvir solicitações dos clientes
    public static final int PORTA = 4000;
    private ServerSocket serverSocket;
    private final static LinkedHashSet<ClientSocket> clientesConectados = new LinkedHashSet<ClientSocket>();

    //método para instanciar o serverSocket
    public void start(){

        try {

            serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor aberto na porta " + PORTA);
            clientConnectionLoop();

        } catch (IOException ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
        }
    }

    //método de loop infinito que aguarda as conexões dos clientes
    private void clientConnectionLoop() throws IOException{
        while(true){
            ClientSocket clientSocket = new ClientSocket(serverSocket.accept());

            new Thread( () -> clientMessageLoop(clientSocket) ).start();
        }
    }

    public void clientMessageLoop(ClientSocket clientSocket){
        Restaurante pizzaria = Restaurante.GetRestauranteSingleton();
        String comando;
        try{

            while((comando = clientSocket.getMessage()) != null){
                clientesConectados.add(clientSocket);
                System.out.println("Comando recebido do cliente " + clientSocket.getRemoteSocketAddress() + ": " + comando);

                String[] seqComandos = comando.split(" ");

                //TODO: implementar Enums para os comandos?

                //reservar mesa
                if(seqComandos[0].equals("1")){
                    Cliente clienteSolicitante = new Cliente();
                    clienteSolicitante.setID(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)));
                    pizzaria.clienteEntrou(clienteSolicitante);
                    enviarMsgCliente(clientSocket, "Seu número de cliente é: " + clienteSolicitante.getID());

                    boolean mesaDisponivel = pizzaria.reservarMesa(seqComandos[1], clienteSolicitante.getID());
                    if(mesaDisponivel){
                       System.out.println("Mesa " + clienteSolicitante.getMesaReservada() + " reservada para o cliente " + clienteSolicitante.getID());
                       enviarMsgCliente(clientSocket, "Mesa " + clienteSolicitante.getMesaReservada() + " reservada para o cliente " + clienteSolicitante.getID());
                    } else{
                        enviarMsgCliente(clientSocket, "Nao ha mesas disponiveis.");
                    }
                }

                //fazer pedido
                else if(seqComandos[0].equals("2")){
                    String msg = "";
                    boolean done = false;

                    //seleciona o cliente a ser editado na lista da pizzaria
                    Cliente selecionado = new Cliente();
                    for (Cliente cliente : pizzaria.getClientesConectados()) {
                        if(cliente.getID() == Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11))){
                            selecionado = cliente;
                        }
                    }
                    if(!selecionado.possuiReserva()){
                        enviarMsgCliente(clientSocket, "Nao eh possivel realizar um pedido sem reservar uma mesa.");
                        System.out.println("Solicitacao de pedido do cliente negada. Cliente nao possui mesa reservada.");
                    } else{
                        try{
                            Pedido p = new Pedido();
                            p.setIdCliente(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)));

                            for (int i = 1; i < seqComandos.length-1; i++) {
                                if(!seqComandos[i].isBlank()){
                                    pizzaria.adicionarAoPedido(Integer.parseInt(seqComandos[i]), p);
                                }
                                if(i == seqComandos.length-2) done = true;
                            }
                            pizzaria.adicionarPedidoCliente(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)), p);
                            
                            msg = "Pedido realizado pelo cliente " + clientSocket.getRemoteSocketAddress().toString().substring(11) + " no valor de " + p.totalPedido() + " reais.\n Caso ja tenha realizado um pedido anterior, os itens serao adicionados a ele.";
                            System.out.println("Pedido realizado pelo cliente " + clientSocket.getRemoteSocketAddress().toString().substring(11) + " no valor de " + p.totalPedido() + " reais.");
                        }finally{
                            if(done) enviarMsgCliente(clientSocket, msg);
                        }
                    }
                }

                //fechar pedido
                else if(seqComandos[0].equals("3")){
                    Cliente solicitante = pizzaria.acharCliente(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)));
                    if(!solicitante.possuiReserva()){
                        enviarMsgCliente(clientSocket, "Reserve uma mesa e realize um pedido para liberar este comando.");
                    } else{
                        
                        enviarMsgCliente(clientSocket, "Seu pedido no valor de: " + solicitante.getPedidoRealizado().totalPedido() + " reais sera finalizado e sua mesa numero: " + solicitante.getMesaReservada() + " liberada.");
                        if(pizzaria.fecharPedido(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)))){
                            enviarMsgCliente(clientSocket, "Seu pedido foi finalizado e sua mesa liberada. Favor aguardar garçom para realizar pagamento.");
                        }
                    }
                }
                
                //deixar avaliacao
                else if(seqComandos[0].equals("4")){
                    pizzaria.adicionarAvaliacao(Integer.parseInt(seqComandos[1]), seqComandos[2]);
                    System.out.println("Avaliacao adicionada.");
                }

                //desconectar
                else if(seqComandos[0].equals("5")){
                    System.out.println("Cliente " + Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)) + " se desconectou.");
                    for (ClientSocket solicitante : clientesConectados) {
                        if(solicitante.equals(clientSocket)){
                            clientesConectados.remove(solicitante);
                        }
                    }

                    Cliente remover = pizzaria.acharCliente(Integer.parseInt(clientSocket.getRemoteSocketAddress().toString().substring(11)));
                    pizzaria.getClientesConectados().remove(remover);  
                    clientSocket.close();
                }

                else if(seqComandos[0].equals("cardapio")){
                    enviarMsgCliente(clientSocket, cardapio());
                }
                    
            }

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void enviarMsgCliente(ClientSocket sender, String msg){
       sender.sendMessage(msg);
    }

    public static void main(String[] args) {

        PizzariaServer servidorPizzaria = new PizzariaServer();

        //padrao de projeto singleton para garantir que so exista uma instancia de restaurante utilizada
        Restaurante pizzaria = Restaurante.GetRestauranteSingleton();
        pizzaria.abrirPizzaria();
        servidorPizzaria.start();

    }

    private static String cardapio(){
        String cardapio = "";
        cardapio = cardapio.concat("============================ CARDAPIO ============================\n");
        cardapio = cardapio.concat("Insira o número do item desejado\n");
        cardapio = cardapio.concat("1 - Pizza Mussarela (R$ 20,00)\n");
        cardapio = cardapio.concat("2 - Pizza Calabresa (R$ 30,00)\n");
        cardapio = cardapio.concat("3 - Pizza Frango (R$ 22,00)\n");
        cardapio = cardapio.concat("4 - Pizza Palmito (R$ 23,00)\n");
        cardapio = cardapio.concat("5 - Pizza Chocolate (R$ 26,00)\n");
        cardapio = cardapio.concat("6 - Pizza Romeu e Julieta (R$ 26,00)\n");
        cardapio = cardapio.concat("0 - Parar.\n");
        return cardapio;
    }
}
