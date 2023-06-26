import java.time.*;

public class Horario{
    private LocalDateTime dataInicial, dataFinal;

    //construtor
    public Horario(LocalDateTime dataInicial, LocalDateTime dataFinal){
        this.dataFinal = dataFinal;
        this.dataInicial = dataInicial;
    }

    @Override
    public String toString(){
        return ("Dia "+dataInicial.format(Format.FORMATTER) + " - das " + dataInicial.format(Format.FORMATTER1) +" as "+ dataFinal.format(Format.FORMATTER1) 
        +"\n").replace(",", "").replace("[", "").replace("]","");
    }

    public LocalDateTime getDataInicial(){
        return dataInicial;
    }

    public LocalDateTime getDataFinal(){
        return dataFinal;
    }
}