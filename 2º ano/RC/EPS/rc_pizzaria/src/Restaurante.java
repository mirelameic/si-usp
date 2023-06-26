import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Restaurante {

    private static Restaurante _pizzariaAAA;

    private static List<MesasPizzaria> mesas = new ArrayList<>();
    private static List<ItemCardapio> cardapio = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private List<Cliente> clientesConectados = new LinkedList<>();
    private List<Avaliacao> avaliacoesPizzaria = new ArrayList<>();
    
    private Restaurante(List<MesasPizzaria> mesas, List<ItemCardapio> cardapio, List<Pedido> pedidos) {
        Restaurante.mesas = mesas;
        Restaurante.cardapio = cardapio;
        Restaurante.pedidos = pedidos;
    }

    public static Restaurante GetRestauranteSingleton()
	{
		if(_pizzariaAAA == null)
			_pizzariaAAA = new Restaurante(mesas, cardapio, pedidos);
		
		return _pizzariaAAA;
	}
    
    
    public void abrirPizzaria(){
        mesas.clear();
        cardapio.clear();
        clientesConectados.clear();
        pedidos.clear();

        mesas.add(new MesasPizzaria(2, true));
        mesas.add(new MesasPizzaria(2, true));
        mesas.add(new MesasPizzaria(4, true));
        mesas.add(new MesasPizzaria(4, true));
        mesas.add(new MesasPizzaria(6, true));
        mesas.add(new MesasPizzaria(6, true));
        
        cardapio.add(new PizzaMussarela());
        cardapio.add(new PizzaCalabresa());
        cardapio.add(new PizzaFrango());
        cardapio.add(new PizzaPalmito());
        cardapio.add(new PizzaChocolate());
        cardapio.add(new PizzaRomeuJulieta());
    }
    
    public boolean reservarMesa(String qntPessoas, int clienteID){
        int i = 0;
        while(i < mesas.size()){
            if(mesas.get(i).disponivel && mesas.get(i).getQuantidadeLugares() >= Integer.parseInt(qntPessoas)){
                mesas.get(i).disponivel = false;
                for (Cliente cliente : clientesConectados) {
                    if(cliente.getID() == clienteID){
                        cliente.setMesaReservada(i);
                        cliente.setPossuiReserva(true);
                    }
                }
                return true;
            }
            i++;
        }
        return false;
    }

    public void adicionarAoPedido(int codigoItem, Pedido p){
        switch (codigoItem){
            case 1:
                PizzaMussarela pm = new PizzaMussarela();
                p.getItens().add(pm);
                //fazer contagem para deixar print no server mais bonito
                System.out.println("Pizza de Mussarela adicionada ao pedido");
            break;
            case 2:
                PizzaCalabresa pc = new PizzaCalabresa();
                p.getItens().add(pc);
                System.out.println("Pizza de Calabresa adicionada ao pedido");
            break;
            case 3:
                PizzaFrango pf = new PizzaFrango();
                p.getItens().add(pf);
                System.out.println("Pizza de Frango adicionada ao pedido");
            break;
            case 4:
                PizzaPalmito pp = new PizzaPalmito();
                p.getItens().add(pp);
                System.out.println("Pizza de palmito adicionada ao pedido.");
            break;
            case 5:
                PizzaChocolate pch = new PizzaChocolate();
                p.getItens().add(pch);
                System.out.println("Pizza de chocolate adicionada ao pedido");
            break;
            case 6:
                PizzaRomeuJulieta prj = new PizzaRomeuJulieta();
                p.getItens().add(prj);
                System.out.println("Pizza de Romeu e Julieta adicionado ao pedido");
        }
    }

    public void clienteEntrou(Cliente cliente){
        clientesConectados.add(cliente);
    }

    public void adicionarPedidoCliente(int ID, Pedido p){
        for (Cliente cliente : clientesConectados) {
            if(cliente.getID() == ID){
                if(!cliente.getPedidoRealizado().getItens().isEmpty()){
                    cliente.getPedidoRealizado().getItens().addAll(p.getItens());
                } else{
                    cliente.setPedidoRealizado(p);
                }
            }
        }
    } 

    public boolean fecharPedido(int ID){
        for (Cliente cliente : clientesConectados) {
            if(cliente.getID() == ID){
                //TODO: retornar valor do pedido para cobranca da pizzaria caso fosse necessario
                cliente.getPedidoRealizado().getItens().clear();
                cliente.setPossuiReserva(false);
                this.getMesas().get(cliente.getMesaReservada()).disponivel = true;
                return true;
            }
        }
        return false;
    }

    public void adicionarAvaliacao(int nota, String comentario){

        Avaliacao avaliacaoCliente = new Avaliacao(nota, comentario);
        avaliacaoCliente.setNota(nota);
        avaliacaoCliente.setComentario(comentario);
        avaliacoesPizzaria.add(avaliacaoCliente);
    }

    public Cliente acharCliente(int ID){
        for (Cliente cliente : clientesConectados) {
            if(cliente.getID() == ID) return cliente;
        }
        return null;
    }

    public List<MesasPizzaria> getMesas() {
        return mesas;
    }

    public List<ItemCardapio> getCardapio() {
        return cardapio;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Pedido p) {
        Restaurante.pedidos.add(p);
    }
        
    public List<Cliente> getClientesConectados() {
        return clientesConectados;
    }
}
