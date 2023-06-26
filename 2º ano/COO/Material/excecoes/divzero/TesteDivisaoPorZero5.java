import java.util.*;

/*

  Este exemplo é similar ao TesteDivisaoPorZero4, mas neste
  exemplo implementamos nossa própria classe de exceção. Ele
  também ilustra o uso da cláusula throws para indicar que o
  método div() pode lançar uma DivisaoPorZeroException, e da 
  instrução throw usada para efetivamente lançar uma exceção,
  a partir do método div(), quando o parâmetro referente ao
  denominador possui valor igual a zero.

  Experimente remover o trecho "throws DivisaoPorZeroException"
  da declaração do método div() e tente compilar o código para 
  ver o que acontece. Experimente também remover o trecho
  "catch(DivisãoPorZeroException e) {...}" do método main() e 
  tente compilar o código novamente para ver o que acontece.

*/


// A criação da nossa própra classe de exceção, no contexto
// deste exemplo, nos traz duas vantagens: (i) permite melhor
// discriminação do tipo do erro, afinal esta classe representa
// uma situação mais particular do que a representada por uma
// ArithmeticException, o que permite um tratamento de erro mais
// especializado para esta situação; e (ii) por ser derivada
// de Exception, ela se enquadra no grupo das checked exceptions
// (exceções verificadas), literalmente obrigando todos os "usuários"
// de div() tratar as exceções que eventualmente podem ser lançadas
// durante uma chamada ao método.
//
// PERGUNTA: quais as efeitos de se declarar a classe abaixo 
// como subclasse de RuntimeException ao invés Exception?

class DivisaoPorZeroException extends Exception {

	public DivisaoPorZeroException(String msg){

		super(msg);
	}
}

public class TesteDivisaoPorZero5 {

	public static int div(int a, int b) throws DivisaoPorZeroException{

		if(b == 0){
			
			String msg = "divisao por zero ao dividir " + a + " por " + b;
			DivisaoPorZeroException e = new DivisaoPorZeroException(msg);
			throw e;
		}

		return a / b;
	}

	public static void main(String [] args){
		 
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
		catch(DivisaoPorZeroException e){
		
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

