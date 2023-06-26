import java.util.*;
import java.time.*;

public class Participante{
    private String nome;

    //lista de horários para cada participante
    private Collection<Horario> listaDeHorarios = new ArrayList<>();

    //construtor
    public Participante(String nome){
        this.nome = nome;
    }

    //adiciona novo horário na lista
    public void setHorario(LocalDateTime dataInicial, LocalDateTime dataFinal){
        Horario addHorario = new Horario(dataInicial, dataFinal);
        listaDeHorarios.add(addHorario);
    }

    @Override
    public String toString(){
        return ("Disponibilidade de "+nome+":\n" + (listaDeHorarios)+"\n").replace(",", "").replace("[", "").replace("]","");
    }

    public String getNome(){
        return nome;
    }

    public Collection<Horario> getListaHorarios(){
        return listaDeHorarios;
    }
}