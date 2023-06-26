import java.util.*;
import java.time.*;

class Main{
    public static void main (String[] args){
        //indicando a data da reunião
        LocalDate dataInicial = LocalDate.of(2021, 10, 1);
        LocalDate dataFinal = LocalDate.of(2021, 10, 7);

        //indicando participantes convocados para a reunião
        Collection<String> listaDeParticipantes = new ArrayList<>();
        Collections.addAll(listaDeParticipantes, "Maria", "Joao", "Jose", "Vanessa");

        //instanciando um marcador de reunião
        MarcadorDeReuniao marcador = new MarcadorDeReuniao(dataInicial, dataFinal, listaDeParticipantes);

        //participantes informam a disponibilidade (alguns informam mais de uma data)
        marcador.indicaDisponibilidadeDe("Maria", LocalDateTime.of(2021, 10, 02, 17, 00), LocalDateTime.of(2021, 10, 02, 18, 00));
        marcador.indicaDisponibilidadeDe("Joao", LocalDateTime.of(2021, 10, 03, 10, 15), LocalDateTime.of(2021, 10, 03, 11, 00));
        marcador.indicaDisponibilidadeDe("Jose", LocalDateTime.of(2021, 10, 02, 10, 15), LocalDateTime.of(2021, 10, 02, 11, 00));
        marcador.indicaDisponibilidadeDe("Maria", LocalDateTime.of(2021, 10, 05, 19, 15), LocalDateTime.of(2021, 10, 05, 20, 15));
        marcador.indicaDisponibilidadeDe("Vanessa", LocalDateTime.of(2021, 10, 03, 10, 00), LocalDateTime.of(2021, 10, 03, 11, 00));
        marcador.indicaDisponibilidadeDe("Joao", LocalDateTime.of(2021, 10, 05, 06, 15), LocalDateTime.of(2021, 10, 05, 07, 15));
        marcador.indicaDisponibilidadeDe("Jose", LocalDateTime.of(2021, 10, 05, 16, 00), LocalDateTime.of(2021, 10, 05, 17, 00));
        marcador.indicaDisponibilidadeDe("Vanessa", LocalDateTime.of(2021, 10, 05, 21, 00), LocalDateTime.of(2021, 10, 05, 22, 00));
        marcador.indicaDisponibilidadeDe("Joao", LocalDateTime.of(2021, 10, 06, 11, 00), LocalDateTime.of(2021, 10, 06, 12, 00));

        //indicando horários fora do intervalo que foi dado (ERRO)
        marcador.indicaDisponibilidadeDe("Maria", LocalDateTime.of(2021, 10, 10, 11, 00), LocalDateTime.of(2021, 10, 10, 12, 00));
        marcador.indicaDisponibilidadeDe("Jose", LocalDateTime.of(2021, 9, 12, 11, 00), LocalDateTime.of(2021, 9, 12, 12, 00));

        //indicando participantes que não foram convidados (ERRO)
        marcador.indicaDisponibilidadeDe("Pedro", LocalDateTime.of(2021, 10, 06, 11, 00), LocalDateTime.of(2021, 10, 06, 12, 00));
        marcador.indicaDisponibilidadeDe("Lucas", LocalDateTime.of(2021, 10, 06, 11, 00), LocalDateTime.of(2021, 10, 06, 12, 00));

        //mostra sobreposição
        marcador.mostraSobreposicao();

        try{
            //instanciando um gerenciador de salas
            GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();

            //adicionando salas
            gerenciador.adicionaSalaChamada("1", 120, "pátio");
            gerenciador.adicionaSalaChamada("2", 70, "piso 3");
            gerenciador.adicionaSalaChamada("3", 55, "biblioteca");
            gerenciador.adicionaSalaChamada("4", 100, "elefante branco");
            gerenciador.setObs("4", "levar notebook");

            //removendo sala
            gerenciador.removeSalaChamada("2");

            //remove sala que não existe (ERRO)
            //gerenciador.removeSalaChamada("5");

            //mostrando salas
            gerenciador.mostraSalas();

            //instanciando varias reservas
            Reserva reserva = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 04, 11, 15), LocalDateTime.of(2021, 10, 04, 11, 45));
            Reserva reserva2 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 06, 21, 00), LocalDateTime.of(2021, 10, 06, 22, 00));
            Reserva reserva3 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 06, 06, 15), LocalDateTime.of(2021, 10, 06, 07, 45));
            Reserva reserva4 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 06, 11, 00), LocalDateTime.of(2021, 10, 06, 12, 00));
            Reserva reserva5 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 07, 11, 00), LocalDateTime.of(2021, 10, 07, 12, 00));

            //reservas com data conflitante (ERRO)
            //Reserva reserva6 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 07, 11, 00), LocalDateTime.of(2021, 10, 07, 12, 00));
            //Reserva reserva7 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 07, 12, 00), LocalDateTime.of(2021, 10, 07, 11, 30));
            //Reserva reserva8 = gerenciador.reservaSalaChamada("4", LocalDateTime.of(2021, 10, 07, 10, 00), LocalDateTime.of(2021, 10, 07, 13, 00));

            //imprime as reservas
            gerenciador.imprimeReservasDaSala("4");

            //cancela a reserva
            gerenciador.cancelaReserva(reserva2);

            //cancela reserva inexistente (ERRO)
            gerenciador.cancelaReserva(reserva2);

            //imprime de novo
            gerenciador.imprimeReservasDaSala("4");

        }catch(SalaInexistente salaInexistente){
            System.out.println(salaInexistente.getMessageName());
        }catch(HorarioConflitante horarioConflitante){
            System.out.println(horarioConflitante.getMessageName());
        }
    }
}