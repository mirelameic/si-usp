import java.util.ArrayList;
import java.util.List;

public class Pedido {

        private int id;
        private int IdCliente;
        private List<ItemCardapio> itens = new ArrayList<>();
        
        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }
        
        public int getIdCliente() {
                return IdCliente;
        }

        public void setIdCliente(int idCliente) {
                IdCliente = idCliente;
        }
        
        public List<ItemCardapio> getItens() {
                return itens;
        }

        public int totalPedido(){
                int valorTotal = 0;
                for (ItemCardapio itemCardapio : itens) {
                        valorTotal += itemCardapio.getValor();
                }
                return valorTotal;
        }
}
