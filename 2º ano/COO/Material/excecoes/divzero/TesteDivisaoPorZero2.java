import java.util.*;

/*

  Neste novo exemplo, "tentamos" tratar a situação do denominador igual 
  a zero, mas sem aproveitar ainda o mecanismo de tratamento de exceções 
  disponível no Java.

  Note que não podemos usar a estratégia de devolver um valor especial
  a quem chama o método div(), de modo a sinalizar que a divisão não pode
  ser efetuada, dado que qualquer valor inteiro é um resultado válido 
  para uma operção de divisão (a não ser que alterassemos o tipo de retorno
  do método, mas deixemos essa ideia para o próximo exemplo).

  Assim, diante da impossibilidade de "avisar" quem chamou o método que
  algo de errado aconteceu (b == 0), nos resta apenas imprimir uma mensagem
  de erro e interromper a execução do programa. O que, de certa forma, não é
  muito melhor do que simplesmente deixar a divisão acontecer e programa 
  ter sua execução encerrada de forma abrupta, visto que não damos chance ao
  usuário do método div() de se recuperar da situaçao de erro.

*/

public class TesteDivisaoPorZero2 {

	public static int div(int a, int b){

		if(b != 0){

			int c = a / b;
			return c;
		}
		else {
			System.out.println("Denominador igual a zero!");
			System.exit(1);

			// este return está aqui apenas para que o código 
			// compile, mas ele nunca chegará a ser executado.
			return 0; 
		}
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
