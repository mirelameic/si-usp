public class MesasPizzaria {
    
    public int quantidadeLugares;
    public boolean disponivel;
    public String clienteAlocado;
    
    public MesasPizzaria(int quantidadeLugares, boolean disponivel) {
        this.quantidadeLugares = quantidadeLugares;
        this.disponivel = disponivel;
    }

    public String getClienteAlocado() {
        return clienteAlocado;
    }

    public void setClienteAlocado(String clienteAlocado) {
        this.clienteAlocado = clienteAlocado;
    }

    public int getQuantidadeLugares() {
        return quantidadeLugares;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}
