import java.util.*;

/*

  Neste quarto exemplo fazemos o tratamento tanto do problema da
  divisão por zero, quanto de entradas inválidas (valores não 
  inteiros), usando o mecanismo para tratamento de exceções do Java.

*/

public class TesteDivisaoPorZero4 {

	public static int div(int a, int b){

		int c = a / b;
		return c;
	}

	public static void main(String [] args){

		// Todo o código que pode lançar uma exceção
		// é colocado dentro do bloco try. Se uma exceção
		// ocorre durante durante a execução deste bloco
		// a execução do código dentro do try é interrompida
		// e o fluxo de execução é desviado para o bloco
		// catch correspondente (isto é, aquele capaz de tratar
		// a exceção que ocorreu). A execução do programa continua
		// na linha seguinte ao fechamento do último bloco catch.

		// O mecanismo de tratamento de exceções permite a
		// separação da lógica principal do programa/método, 
		// da lógica específica para tratamento de erros, 
		// melhorando a legibilidade do código e facilitando
		// manutenção e depuração. A lógica principal fica no 
		// bloco try, enquanto a lógica específica para tratamento 
		// de erros fica nos blocos catch.

		// Além disso, quando as exceções que podem ser lançadas 
		// são do tipo verificadas, isto é, não são do tipo 
		// RuntimeException (o que não é o caso deste exemplo) o 
		// compilador nos obriga a capturar a exceção (em blocos 
		// try/catch, como fazemos neste exemplo, embora não sejamos 
		// obrigados) ou a declarar que o método em questão pode lançar 
		// determinado tipo de exceção (usando a clausula throws). 
		// Com isso, a chance do erro ser negligênciado pelo
		// programador dimunui drasticamente.
		 
		try {
			int a, b, c;
			Scanner scanner = new Scanner(System.in);

			System.out.print("a: ");
			a = scanner.nextInt();
	
			System.out.print("b: ");		
			b = scanner.nextInt();
			
			c = div(a, b);
			System.out.println("Resultado: " + a + " / " + b + " = " + c);
		}
		catch(ArithmeticException e){
		
			System.out.println("Divisão por zero!");
			e.printStackTrace();
		}
		catch(InputMismatchException e){

			System.out.println("Entrada inválida!");
			e.printStackTrace();
		}

		System.out.println("Fim do programa.");
	}
}

