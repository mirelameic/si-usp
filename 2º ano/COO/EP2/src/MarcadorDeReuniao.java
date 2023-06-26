import java.util.*;
import java.time.*;

public class MarcadorDeReuniao{
    //Intervalo de data da reunião
    private LocalDate dataInicial, dataFinal;

    //lista de participantes
    private Collection<Participante> participantesConvidados = new ArrayList<>();

    //construtores
    public MarcadorDeReuniao(){}

    public MarcadorDeReuniao(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        marcarReuniaoEntre(dataInicial, dataFinal, listaDeParticipantes);
    }

    //definição dos participantes da reunião
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes){
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        //adicionando os participantes na lista
        for(String nomePart : listaDeParticipantes){
            participantesConvidados.add(new Participante(nomePart));
        }
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Marcando reunião entre os dias: " +Format.RED+dataInicial.format(Format.FORMATTER) + " e " + dataFinal.format(Format.FORMATTER)+ Format.RESET);
        System.out.println("Participantes convidados: " +Format.RED +listaDeParticipantes+ Format.RESET);
        System.out.println("------------------------------------------------------------------------------");   
    }
    
    //cada participante define um horário disponível
    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim){
        //checando se a data está dentro do intervalo indicado
        if(inicio.toLocalDate().isEqual(dataInicial) || fim.toLocalDate().isEqual(dataFinal) || (fim.toLocalDate().isAfter(dataInicial)&&fim.toLocalDate().isBefore(dataFinal))){
            boolean foiConvidado = false;
            for(Participante part : participantesConvidados){
                if(part.getNome().equals(participante)){
                    part.setHorario(inicio, fim);
                    foiConvidado = true;
                    break;
                }
            }
            //checando se o participante foi convidado
            if(!foiConvidado){
                System.out.println(Format.RED+"O participante "+participante+ " não pode adicionar um horário, pois não está na lista de convidados."+Format.RESET+"\n\n");
            }
        }else{
            System.out.println(Format.RED+"O horário enviado está fora do intervalo determinado: \n Início: "+ 
            inicio.format(Format.FORMATTER3) +"\n Fim: "+ fim.format(Format.FORMATTER3)+"\n\n"+Format.RESET);
        }
    }

    //sobreposição dos horários
    public void mostraSobreposicao(){
        System.out.println(Format.YELLOW+participantesConvidados.toString().replace(",", "").replace("[", "").replace("]","").replace(" D","D")+Format.RESET);
    }
}