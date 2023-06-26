import java.time.*;

public class Reserva{
    private Sala salaReservada;
    private Horario horarioReserva;

    //construtor
    public Reserva(Sala salaReservada, LocalDateTime dataInicial, LocalDateTime dataFinal) throws HorarioConflitante{
        horarioReserva = new Horario(dataInicial, dataFinal);
        this.salaReservada = salaReservada;
        tentaInserir();
    }

    //método que tenta inserir a reserva na sala indicada
    public void tentaInserir() throws HorarioConflitante{
        if(!salaReservada.insereReserva(this)) throw new HorarioConflitante();
    }

    @Override
    public String toString(){
        return ("Sala "+salaReservada.getNome()+"\n" + "Início: "+horarioReserva.getDataInicial().format(Format.FORMATTER2)+"\n Fim: "+horarioReserva.getDataFinal().format(Format.FORMATTER2)+"\n\n")
        .replace(",", "").replace("[", "").replace("]","");
    }

    public Sala getSalaReservada(){
        return salaReservada;
    }

    public LocalDateTime getDataInicial(){
        return horarioReserva.getDataInicial();
    }

    public LocalDateTime getDataFinal(){
        return horarioReserva.getDataFinal();
    }
}