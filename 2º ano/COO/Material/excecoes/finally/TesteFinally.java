/*

  Execute este programa variando de 1 a 4 o parâmetro fornecido pela
  linha de comando e verifique como se dá o fluxo de execução em cada
  um dos casos. Observe, em particular, que o bloco finally *sempre* 
  é executado, independente do que acontece durante a execução do 
  programa.

  Mesmo que uma exceção para a qual não estamos preparados aconteça 
  (ou seja, para a qual não exista um bloco catch), acarretando no
  encerramento abruto da execução do programa, ainda assim o bloco
  finally (associado ao try no qual a exceção é lançada) irá ser 
  executado.

*/

public class TesteFinally {

	public static void main(String [] args){
	
		System.out.println("Inicio.");

		int teste = Integer.parseInt(args[0]);

		try{

			System.out.println("Abertura do bloco try.");

			if(teste == 1) {

				// Este trecho de código não 
				// lança nenhuma exceção.

				int a = 10 + 20;
			}

			if(teste == 2){

				// Este trecho de código lança uma
				// ArithmeticException que é capturada
				// em um bloco catch. 

				int b = 10 / 0;
			}

			if(teste == 3){

				// Este trecho de código lança uma
				// ArrayIndexOutOfBoundsException que 
				// é capturada em outro bloco catch. 
	
				int [] v = new int[10];
				v[10] = 1234;
			}

			if(teste == 4){
			
				// Este trecho de código lança uma
				// NullPointerException que não é 
				// capturada em nenhum bloco catch.

				Object o = null;
				o.toString();
			}

			System.out.println("Fechamento do bloco try.");
		}
		catch(ArithmeticException e){

			System.out.println("Arithmetic exception.");
		}
		catch(ArrayIndexOutOfBoundsException e){

			System.out.println("Array index out of bounds exception.");
		}
		finally{

			// O que está neste bloco sempre executa, mesmo que uma
			// exceção não prevista (para a qual não há um bloco catch)
			// aconteça!

			System.out.println("Finally.");
		}

		// Linhas de código que seguem um bloco try/catch/finally 
		// não tem sua execução garantida! Se uma exceção para a qual 
		// não há um bloco catch for lançada dentro do bloco try, isso
		// configurá uma situação em que a exceção não foi tratada de 
		// fato, interrompendo imediatamente a execução do método atual
		// (neste exemplo, do método main) após a execução do finally, e 
		// causando o encaminhamento da exceção para quem chamou o método
		// atual (neste exemplo, a própria JVM que nada pode fazer para  
		// contornar a situação de erro, causando o encerramento abrupto 
		// do programa).
		 
		System.out.println("Fim!");
	}
}
