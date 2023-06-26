public class SalaInexistente extends Throwable{
    public String getMessageName() {
        return "Sala indicada não existe. Insira um nome válido.";
    }
}