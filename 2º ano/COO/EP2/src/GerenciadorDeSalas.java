import java.util.*;
import java.time.*;

public class GerenciadorDeSalas{
    //lista de salas
    private List<Sala> listaDeSalas = new ArrayList<>();

    ////////////////////////SALAS\\\\\\\\\\\\\\\\\\\\\\\
    
    //adiciona sala na lista
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao){
        Sala novaSala = new Sala(nome, capacidadeMaxima, descricao, "n/a");
        adicionaSala(novaSala);
    }

    //remove sala da lista
    public void removeSalaChamada(String nomeDaSala) throws SalaInexistente{
        listaDeSalas.remove(buscaSala(nomeDaSala));
    }

    //retorna a lista de salas
    public List<Sala> listaDeSalas(){
        return this.listaDeSalas;
    }

    //adiciona sala ja instanciada
    public void adicionaSala(Sala novaSala){
        listaDeSalas.add(novaSala);
    }

    //adiciona uma observação na sala
    public void setObs(String nomeDaSala, String obs) throws SalaInexistente{
        buscaSala(nomeDaSala).setObs(obs);
    }

    //busca sala na lista
    public Sala buscaSala(String nome) throws SalaInexistente{
        for(Sala sala : this.listaDeSalas){
            if(sala.getNome().equals(nome)){
                return sala;
            }
        }
        throw new SalaInexistente();
    }

    //imprime a lista de salas
    public void mostraSalas(){
        System.out.println(Format.BLUE+listaDeSalas.toString().replace(",", "").replace("[", "").replace("]","").replace("L", " L").replace(" S", "S")+Format.RESET+"\n");
    }

    ////////////////////////RESERVAS\\\\\\\\\\\\\\\\\\\\\\\
    
    //busca a sala e adiciona uma reserva
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal) throws SalaInexistente, HorarioConflitante{
        return new Reserva(buscaSala(nomeDaSala), dataInicial, dataFinal);
    }

    //cancela a reserva na sala
    public void cancelaReserva(Reserva cancelada){
        if(cancelada.getSalaReservada().cancelaReserva(cancelada)){
            System.out.println(Format.GREEN+"Reserva da sala "+cancelada.getSalaReservada().getNome() +" cancelada com sucesso!"+Format.RESET+"\n");
        }else{
            System.out.println(Format.RED+"Não foi possível cancelar, a reserva indicada não existe."+Format.RESET+"\n");
        }
    }

    //busca a sala e retorna a lista de reservas
    public Collection<Reserva> reservasParaSala(String nomeSala) throws SalaInexistente{
        return buscaSala(nomeSala).getReservas();
    }

    //imprime as reservas da sala
    public void imprimeReservasDaSala(String nomeSala) throws SalaInexistente{
        System.out.println("----- RESERVAS -----");
        System.out.println(Format.GREEN+reservasParaSala(nomeSala).toString().replace(",", "").replace("[", "").replace("]","").replace(" F","F").replace(" S","S")+Format.RESET);
    }
}