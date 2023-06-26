public class Cliente{

    private int ID;
    private static Pedido pedidoRealizado = new Pedido();
    private int mesaReservada;
    private boolean possuiReserva = false;

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public int getMesaReservada() {
        return mesaReservada;
    }
    
    public void setMesaReservada(int mesaReservada) {
        this.mesaReservada = mesaReservada;
    }

    public boolean possuiReserva() {
        return possuiReserva;
    }

    public void setPossuiReserva(boolean possuiReserva) {
        this.possuiReserva = possuiReserva;
    }

    public Pedido getPedidoRealizado() {
        return pedidoRealizado;
    }

    public void setPedidoRealizado(Pedido p) {
        Cliente.pedidoRealizado = p;
    }

    public void fecharPedido(){
        Cliente.pedidoRealizado.getItens().clear();
    }
}