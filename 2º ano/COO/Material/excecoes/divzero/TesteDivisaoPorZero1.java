import java.util.*;

/*

  Neste exemplo, não fazemos qualquer tipo de tratamento de erro,
  embora dois problemas possam acontecer durante a execução do 
  programa: quando o valor de b for igual a zero, e quando o usuário
  digitar um valor não inteiro como entrada para a ou b. Em qualquer
  um dos casos uma exceção será lançada e como não fazemos nenhum tipo
  de tratamento, isso irá provocar o encerramento abrupto da execução
  do programa.

  Note que, como em ambos os casos as exceções lançadas são do tipo
  unchecked (não-verificadas), o compilador não nos alertará de que
  elas podem eventualmente acontecer, e nem nos obrigará a tratá-las.

*/

public class TesteDivisaoPorZero1 {

	public static int div(int a, int b){

		int c = a / b;

		return c;
	}

	public static void main(String [] args){

		int a, b, c;
		Scanner scanner = new Scanner(System.in);

		System.out.print("a: ");
		a = scanner.nextInt();

		System.out.print("b: ");		
		b = scanner.nextInt();
		
		c = div(a, b);
		System.out.println("Resultado: " + a + " / " + b + " = " + c);
		System.out.println("Fim do programa.");
	}
}
