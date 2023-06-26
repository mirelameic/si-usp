import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PizzariaClient implements Runnable{
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private ClientSocket clienteSocket;
    private Scanner scanner;

    public PizzariaClient(){
        scanner = new Scanner(System.in);
    }

    //metodo para instanciar o Socket do cliente
    public void start() throws UnknownHostException, IOException{ //Baseado no vídeo https://www.youtube.com/watch?v=MtAfYUW7fJ4

        try{
            clienteSocket = new ClientSocket(
            new Socket(SERVER_ADDRESS, PizzariaServer.PORTA)    
            );

            //threads sao utilizadas para receber as mensagens do servidor, sem serem paradas por operacoes bloqueantes como a de leitura do scanner
            new Thread(this).start();

            System.out.println("Cliente " + clienteSocket.getLocalPort() + " conectado ao servidor " + SERVER_ADDRESS + ":" + PizzariaServer.PORTA);
            messageLoop();

        } finally{
            clienteSocket.close();
        }

    }

    @Override
    public void run(){
        String msg;
        while((msg = clienteSocket.getMessage()) != null){
            if(msg != null) System.out.println(msg);
        }
    }

    //loop para envio de mensagens ao servidor
    private void messageLoop(){
        String comando;
        String codigoItem;

        do{
            menu();
            comando = scanner.nextLine();

            switch (comando) {
                //exibir menu
                case "0":
                    menu();
                break;

                //reservar mesa    
                case "1":
                    System.out.println("Insira o número de pessoas: ");
                    String qntLugares = scanner.nextLine();

                    if(qntLugares.isBlank()){
                        System.out.println("Insira um numero valido: ");
                        qntLugares = scanner.nextLine();
                    }

                    comando = comando.concat(" ").concat(qntLugares);
                break;

                //fazer pedido
                case "2":
                    do{
                        solicitarCardapio(clienteSocket);
                        codigoItem = scanner.nextLine();

                        if(codigoItem.isBlank()){
                            System.out.println("Insira um codigo valido: ");
                            codigoItem = scanner.nextLine();
                        }

                        comando = comando.concat(" ").concat(codigoItem);
                    } while(!codigoItem.equalsIgnoreCase("0"));
                break;

                //fechar pedido e liberar mesa
                case "3":
                    System.out.println("Estamos processando sua solicitação.");
                break;
                //Avaliar
                case "4":
                    System.out.println("Por favor, informe uma nota de 0 a 10 para nosso atendimento: ");
                    String nota = scanner.nextLine();
                    
                    if(nota.isBlank()){
                        System.out.println("Insira uma nota valida: ");
                        nota = scanner.nextLine();
                    }

                    comando = comando.concat(" ").concat(nota);

                    System.out.println("Gostaria de deixar um comentario?\n 1 - Sim \n 2- Nao");
                    String comentar = scanner.nextLine();

                    if(comentar.isBlank()){
                        System.out.println("Insira um codigo valido: ");
                        comentar = scanner.nextLine();
                    }

                    if(comentar.equals("1")){
                        System.out.println("Insira seu comentario: ");
                        String comentario = scanner.nextLine();
                        comando = comando.concat(" ").concat(comentario);
                    } else{
                        comando = comando.concat(" ").concat("Sem comentario");
                    }
                break;
                case"5":
                    System.out.println("Desconectado.");
                break;

                //caso seja inserido um comando inválido
                default:
                    System.out.println("Comando invalido");
                    ajuda();
                break;
            }
            clienteSocket.sendMessage(comando);
        } while(!comando.equals("5"));

        scanner.close();
    }

    public static void main(String[] args) {

        try {
            PizzariaClient clienteDaPizzaria = new PizzariaClient();
            clienteDaPizzaria.start();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            System.out.println("Obrigado e volte sempre!");
        }
    }

    private static void menu() {
        System.out.print("============================ INICIO MENU ============================\n");
        System.out.println("- Insira o código do comando desejado:\n");
        System.out.println("(0) Exibir menu.\n");
        System.out.println("(1) Reservar mesa: Solicita a reserva de uma mesa.\n");
        System.out.println("(2) Fazer pedido: Adiciona o item desejado ao pedido.\n");
        System.out.println("(3) Fechar pedido: Encerra o pedido para liberar mesa e realizar pagamento.\n");
        System.out.println("(4) Avaliar: Deixe uma avaliacao para nosso atendimento.\n");
        System.out.println("(5) Desconectar: desconecta o cliente da pizzaria.\n");
        System.out.println("============================ FIM MENU ============================");
    }

    private static void ajuda(){
        System.out.println("Digite \"0\" para ver o menu do cliente.");
    }

    private static void solicitarCardapio(ClientSocket clientSocket){
        clientSocket.sendMessage("cardapio");
    }
}
