import java.util.*;

public class Sala{
    private String nome, local, observacoes;
    private int capacidade;

    //lista de reservas
    private Collection<Reserva> listaDeReservas = new ArrayList<>();

    //construtor
    public Sala(String nome, int capacidade, String local, String observacoes){
        this.nome = nome;
        this.capacidade = capacidade;
        this.local = local;
        this.observacoes = observacoes;
    }

    //adiciona reserva na lista
    public boolean insereReserva(Reserva reserva) throws HorarioConflitante{
        Iterator<Reserva> itr = listaDeReservas.iterator();
        while(itr.hasNext()){
            Reserva busca = itr.next();
            //checando se há reservas para o mesmo dia e horário
            if(reserva.getDataInicial().isEqual(busca.getDataInicial()) || reserva.getDataFinal().isEqual(busca.getDataFinal())){
                throw new HorarioConflitante();
            }else if(reserva.getDataInicial().isAfter(busca.getDataInicial()) && reserva.getDataInicial().isBefore(busca.getDataFinal())){
                throw new HorarioConflitante();
            }else if(reserva.getDataFinal().isAfter(busca.getDataInicial()) && reserva.getDataFinal().isBefore(busca.getDataFinal())){
                throw new HorarioConflitante();
            }else if(reserva.getDataInicial().isBefore(busca.getDataInicial()) && reserva.getDataFinal().isAfter(busca.getDataFinal())){
                throw new HorarioConflitante();
            }
        }
        //não há reservas conflitantes
        listaDeReservas.add(reserva);
        return true;
    }

    //remove reserva da lista
    public boolean cancelaReserva(Reserva cancelada){
        return listaDeReservas.remove(cancelada);
    }

    @Override
    public String toString(){
        return ("Sala "+nome+":\n" + "Local: "+local+"\n Capacidade: "+capacidade+" pessoas\n Observações: "+observacoes+"\n\n")
        .replace(",", "").replace("[", "").replace("]","");
    }

    public Collection<Reserva> getReservas(){
        return listaDeReservas;
    }

    public String getNome(){
        return nome;
    }

    public String getLocal(){
        return local;
    }

    public String getObs(){
        return observacoes;
    }

    public int getCapacidade(){
        return capacidade;
    }

    public void setObs(String observacoes){
        this.observacoes = observacoes;
    }
}