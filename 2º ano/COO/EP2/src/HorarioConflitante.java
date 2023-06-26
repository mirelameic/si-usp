public class HorarioConflitante extends Throwable{
    public String getMessageName() {
        return "Sala já está reservada para o horário indicado.";
    }
}
