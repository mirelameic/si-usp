import java.time.format.DateTimeFormatter;

public class Format{
    //formatos de exibicao
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM");
    public static final DateTimeFormatter FORMATTER1 = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter FORMATTER2 = DateTimeFormatter.ofPattern("dd/MM 'as' HH:mm");
    public static final DateTimeFormatter FORMATTER3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    //cores
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
}