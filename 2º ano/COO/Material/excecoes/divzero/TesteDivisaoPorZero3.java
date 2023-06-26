import java.util.*;

/*

  Uma forma melhor de tratar o problema da divisão por zero,
  permitindo informar aos "usuários" do método div() que a 
  divisão não pode ser efetuada, é alterar o tipo de retorno
  do método.

  Para implementar esta ideia, criamos uma classe ResultadoDiv, 
  que contem um status de erro e o resultado da divisão propriamente
  dito, quando esta pode ser efetuada.

  A desvantagem desta abordagem é que para qualquer caso similar
  é necessária a criação de novas classes para serem usadas como
  retorno. É, entretanto, um avanço em relação ao exemplo anterior, 
  uma vez "usuários" de div() conseguem saber se a operação foi
  bem sucedida ou não e, em caso negativo, podem se recuperar da
  situação de erro.

  Por outro lado, note que o método main volta a apresentar
  uma estrutura em que o código da lógica principal se mistura
  ao código responsável por tratar as situações de erro.

*/

class ResultadoDiv {

	private boolean status;
	private int resultado;

	public ResultadoDiv(boolean status, int resultado){

		this.status = status;
		this.resultado = resultado;
	}

	public boolean getStatus(){

		return this.status;
	}

	public int getResultado(){

		return this.resultado;
	}
}

public class TesteDivisaoPorZero3 {

	public static ResultadoDiv div(int a, int b){

		if(b != 0){

			return new ResultadoDiv(true, a / b);
		}

		return new ResultadoDiv(false, 0);
	}

	public static void main(String [] args){

		int a, b;
		ResultadoDiv c;
		Scanner scanner = new Scanner(System.in);

		System.out.print("a: ");
		a = scanner.nextInt();

		System.out.print("b: ");		
		b = scanner.nextInt();
		
		c = div(a, b);

		if(c.getStatus()){
		
			System.out.println("Resultado: " + a + " / " + b + " = " + c.getResultado());
		}
		else {
			System.out.println("Divisão por zero!");
		}

		System.out.println("Fim do programa.");
	}
}

