import java.util.*;

/*

  O programa abaixo le três valores de notas (valores númericos do tipo double),
  e calcula a nota média, imprimindo a mesma na saída. Assumindo que o usuário
  sempre irá alimentar o programa com valores do tipo double, ainda assim é preciso
  verificar se cada valor fornecido pelo usuário está dentro do intervalo entre
  0.0 e 10.0. Um valor fora deste intervalo configura uma situação de erro e o 
  programa encerra sua execução imprimindo uma mensagem ao usuário.

  Observe como neste exemplo a lógica principal do programa (leitura das notas e 
  cálculo da média) está misturada com a lógica de validação dos valores fornecidos
  pelo usuário e tratamento adequado para valores de notas inválidos.

*/

public class TesteMedia1 {

	private static Scanner scanner = new Scanner(System.in);

	public static double leDouble(){

		return scanner.nextDouble();
	}

        public static void main(String [] args){

                double a = leDouble();
        
                if( a < 0 || a > 10.0 ){

                        System.out.println("Nota fora do intervalo.");
                        System.exit(1);
                }

                double b = leDouble();

                if( b < 0 || b > 10.0 ){

                        System.out.println("Nota fora do intervalo");
                        System.exit(1);
                }

                double c = leDouble();

                if( c < 0 || c > 10.0 ){

                        System.out.println("Nota fora do intervalo");
                        System.exit(1);
                }

		double media = (a + b + c) / 3.0;

                System.out.println("Media = " + media);
        }
}

