import java.util.*;

/*

  Esta terceira versão emprega o mecanismo para tratamento de exceções
  disponível pela plataforma Java. Neste exemplo aproveitamos uma classe de 
  exceção já disponível biblioteca padrão do Java, a classe Exception.

  Observe como o código referete à logica principal do programa agora está 
  separado da lógica para tratamento de erro, melhorando a organização e a
  clareza do código.

  Observação: a obrigatoriedade de tratar a exceção lançada, seja capturando-a
  com um bloco catch, seja encaminhando-a através da declaração "throws" só se
  aplica pois a classe Exception é do tipo "verificada" (checked). Ou seja, o
  compilador irá verifica se o código está, de fato, tratando (de alguma forma)
  uma eventual exceção. 

*/

public class TesteMedia3 {

	private static Scanner scanner = new Scanner(System.in);

	// Nesta nova versão, leDouble() continua responsável pela 
	// validação da nota. Porém, no caso do valor lido estar 
	// fora do intervalo esperado, ao invés de devolver um valor
	// especial (-1) para sinalizar a situação de erro, uma exceção
	// será lançada.
	//
	// Uma exceção nada mais é do que um objeto que encapsula 
	// informações sobre o erro e/ou situação excepcional, bem como 
	// o estado do programa no instante que o mesmo ocorre. Um objeto
	// de exceção é lançado pela instrução "throw". 
	//
	// Como agora existe a possibilidade de que, durante sua execução, 
	// leDouble() lance uma exceção, e nossa intenção não é tratá-la 
	// localmente (ou seja, dentro do próprio leDouble()), mas sim 
	// "encaminhá-lá" a quem fez a chamada do método (o método main),
	// leDouble() precisa declarar que exceções (do tipo Exception) podem
	// ser lançadas eventualmente. Para isso, usa-se o "throws" para 
	// complementar a declaração do método. 
	//
	// Quando, durante uma execução do método leDouble(), for encontrado
	// um valor fora do intervalo esperado, e um objeto do tipo Exception 
	// for lançado, isso irá acarretar na imediata interrupção da execução 
	// do método, e no encaminhamento do objeto de exceção para quem o tiver 
	// invocado (método main, no nosso exemplo). O método main passará então
	// a ser o responsável por lidar com a situação excepcional.

	public static double leDouble() throws Exception{

		double x = scanner.nextDouble();

		if( x < 0.0 || x > 10.0 ) throw new Exception("nota fora do intervalo.");

		return x;
	}

	// O método main, sendo "usuário" de leDouble(), e "ciente" de que
	// exceções do tipo Exception podem eventualmente ser lançadas pelo
	// mesmo, precisa estar preparado para tratá-las. Temos neste caso 
	// duas opções: (A) capturar e tratar localmente no próprio main, 
	// usando um bloco catch (que é o que fazemos neste exemplo); ou (B)
	// não tratar localmente, encaminhando a exceção de forma implícita 
	// para quem chamou o main (neste caso a exceção chegaria até a JVM, 
	// provocando o encerramento abrupto do programa). Se estivessemos
	// optado pela pela opção (B), a declaração do método main deveria
	// ser complementada (usando o "throws") para declarar que este também
	// pode, eventualmente, lançar exceções do tipo Exception.

        public static void main(String [] args){

		// Todo o código passível de lançar exeções 
		// é colocado dentro de um bloco try.
		
		try{

		        double a = leDouble();
		        double b = leDouble();
		        double c = leDouble();
			double media = (a + b + c) / 3.0;
		        
			System.out.println("Media = " + media);
		}

		// Se algo der errado e uma exceção do tipo 
		// Exception for lançada o fluxo de execução 
		// do programa será imediatamente desviado para
		// este bloco catch. Aqui é nossa chance de
		// contornar a situação, permitindo que o programa
		// continue sua execução sem ser encerrado de
		// forma abrupta.

		catch(Exception e){

			// Na pratica nosso tratamento de erro não
			// resolve o problema, apenas imprime o 
			// estado do stack trace no instante que o 
			// objeto de exceção foi instanciado.

			// Entretanto a impressão do stack trace pode
			// ser de grande utilizada para depurar o 
			// programa e enteder as causas do lançamento
			// da exceção.

			e.printStackTrace();
		}

		// Além disso, o efetivo tratamento da exceção permite
		// que o programa seja encerrado de forma "limpa", o que
		// pode ser comprovado pela execução da linha abaixo com
		// a impressão da mensagem "Fim". 

		System.out.println("Fim!");
        }
}

