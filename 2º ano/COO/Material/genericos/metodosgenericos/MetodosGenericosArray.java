/*

  Classe com exemplos de métodos genéricos que manipulam arrays genéricos.
  Cada método apresenta uma implementação possível para resolver o seguinte
  problema: dado um array de elementos, substituir todos aqueles considerados
  maior que "max" pelo valor definido em "novo".

  Um método genérico não é muito diferente de uma classe genérica, com a
  diferença de que o escopo das variáveis de tipos genéricos está restrito
  ao corpo do método. Outra diferença está na seção de parâmetros de tipo,
  que deve ser declarada imediatamente antes do tipo do retorno do método.

  Nos exemplos apresentados exploramos ainda a possibilidade de se definir
  um teto para uma variável de tipo, usando a palavra chave "extends". Por
  exemplo, ao definirmos uma seção de parametros de tipo da seguinte forma
  "<T extends Number>", estamos declarando que T é uma variável que representa
  uma classe que pode ser qualquer coisa desde que seja Number ou derivado
  (direta ou indiretamente) de Number. O uso do "extends" para definir tetos
  para as variáveis de tipo permite restringir os tipos de dados aceitos por 
  um método genérico, bem como utilizar dentro de métodos genéricos recursos
  mais especializados dos objetos declarados em função da variável de tipo.

  Uma diferença prática importante entre o uso de classes e métodos genéricos 
  está no fato de que quando trabalhamos com uma classe genérica, tipicamente
  especificamos de forma explícita o que irá "substutuir" a variável de tipo 
  (T por exemplo) na instanciação do objeto genérico. Já com métodos genéricos, 
  os "clientes/usuários" tipicamente *não* definem de forma explícita o que irá 
  substituir T. Normalmente, o compilador infere qual a classe adequada para 
  substituir T a partir dos tipos dos argumentos passados em uma chamada do
  método genérico.
 
  Um ponto outro interessante ilustrado neste exemplo são as situações em que 
  a verificação da segunça de tipos em tempo de compilação falha, levando à 
  quebra do programa  durante a execução.

*/

public class MetodosGenericosArray {

	
	// Versão não-genérica. Ao declararmos que ela aceita array de Number, e os
        // demais parâmetros do tipo Number, ela também aceitará array e parâmetros 
	// de classes que sejam subtipos de Number. Um problema ao qual esta versão
	// está sujeita é receber um array do subtipo A, e "novo" do subtipo B. Neste
	// cenário, na primeira vez que condição dentro do if for verdadeira, haverá
	// a execução de uma atribuição inválida, o que irá quebrar a execução do 
	// programa. É uma situação em que sintáticamente o programa está correto,
	// mas há um problema que se manifesta em tempo de execução. 

	public static void substituiMaioresQue1(Number [] array, Number max, Number novo){

		for(int i = 0; i < array.length; i++){
		
			if(array[i].doubleValue() > max.doubleValue()) array[i] = novo;
		}
	}

	// Versão genérica que define Number como teto para a variável de tipo T.
	// Como tem-se certeza que tanto os elementos presentes no array, quanto os
	// demais parâmetros recebidos pelo método são Numbers ou derivados, então
	// podemos utilizar recursos específicos de Number para implementar o método,
	// no caso o método doubleValue(). Esta versão genérica também está sujeita à
	// situação descrita para o método anterior, ao misturarmos os tipos do array e
	// do parâmetro "novo" e deixarmos o compilador inferir automaticamente quem é T.
	// Por outro lado, se nós mesmos, em uma chamada do método, definirmos de forma
	// explicita quem é T, o compilador conseguirá verificar se o tipo dos três 
	// parâmetros recebidos é o mesmo, prevenindo o cenário problemático descrito.
	
	public static <T extends Number> void substituiMaioresQue2(T [] array, T max, T novo){

		for(int i = 0; i < array.length; i++){
		
			if(array[i].doubleValue() > max.doubleValue()) array[i] = novo;
		}
	}

	// outra versão genérica que define Comparable<T> como teto para a variável de tipo T.
	// Como tem-se certeza que tanto os elementos presentes no array, quanto os demais 
	// parâmetros recebidos pelo método são objetos que implementam a interface
	// Comparable<T> (ou seja, são comparáveis com outros objetos de mesmo tipo), então
	// podemos utilizar o método compareTo(...), específico declarado nesta interface, 
	// na implementação deste método genérico. Esta versão é imune ao problema descrito
	// para as duas versões anteriores pois ao declararmos que o tipo T deve ser comparável
	// com ele mesmo, isso acaba impedindo a passagem de parâmetros que misturam tipos diferentes
	// (afinal, Integer só é comparável com Integer, Double apenas com Double -- com o mesmo 
 	// valendo para as demais classes empacotadoras de tipos primitivos -- e, adicionalmente,
	// Number não é comparável com Number).
	// 
	// Além disso, esta versão que implementa essencialmente a mesma funcionalidade, é
	// capaz de manipular uma gama muito maior de arrays genéricos. Por exemplo, esta versão
	// do método é capaz de receber um array de Strings, substituindo as strings consideradas
	// maiores que "max" por uma nova String, uma vez que Strings são comparáveis com elas
	// mesmo. Listas de qualquer nova classe que venhamos a criar e que implemente a interface 
	// Comparable também serão aceitas por esta versão do método!

	public static <T extends Comparable<T>> void substituiMaioresQue3(T [] array, T max, T novo){

		for(int i = 0; i < array.length; i++){
		
			if(array[i].compareTo(max) > 0) array[i] = novo;
		}
	}

	// metodo genérico auxiliar que recebe um vetor de elementos do
	// tipo T e imprime seu conteúdo. Este exemplo de método genérico
	// não chega a tirar grande proveito do recurso, já que não há
	// "uso inadequado" que possa ser verificado em tempo de compilação.
	// Ou seja, não seria muito diferente de uma versão que recebe um 
	// array de Object.

	public static <T> void printArray(T [] array){

		System.out.print("array: [");

		for(T x : array){

			System.out.print(" " + x);
		}

		System.out.println(" ]");
	}

	public static void teste1(){

		Integer [] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};

		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue1(arr, 7, 0); // tente passar 0.1 como último parâmetro!
		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue2(arr, 5, 0); // tente passar 0.1 como último parâmetro!
		printArray(arr);
		MetodosGenericosArray.<Integer>substituiMaioresQue2(arr, 3, 0); // tente passar 0.1 como último parâmetro! 
										// Note que nesta chamada estamos definindo
										// explicitamente quem deve "substituir" T 
										// ao invés de deixar o compilador inferir 
										// isso com base nos tipos dos parâmetros 
										// passados na chamada.
		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue3(arr, 1, 0); // tente passar 0.1 como último parâmetro!
		printArray(arr);

		// Tente explicar o que acontece quando passamos como último 
		// parâmetro o valor double 0.1, em cada uma das 4 chamadas
		// acima. Note que o método substituiMaioresQue2(...) é chamado
		// duas vezes mas, enquanto na primeira chamada é o compilador 
		// quem infere T, na segunda chamada somos nós mesmos que 
		// indicamos que classe concreta deve "substituir" T.

		// Pergunta: em quais das 4 chamadas de métodos acima podemos
		// dizer que o compilador "falha" ao verificar a segurança de 
		// tipo durante a compilação?
	}

	public static void teste2(){

		Double [] arr = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7, 8.8, 9.9};

		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue1(arr, 7.71, 0.0);
		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue2(arr, 5.51, 0.0);
		printArray(arr);
		MetodosGenericosArray.<Double>substituiMaioresQue2(arr, 5.51, 0.0);
		printArray(arr);
		MetodosGenericosArray.substituiMaioresQue3(arr, 3.31, 0.0);
		printArray(arr);

		// Tenta explicar o que acontece quando passamos como último 
		// parâmetro o valor int 0, para cada uma das 4 chamadas
		// acima. Note que o método substituiMaioresQue2(...) é chamado
		// duas vezes mas, enquanto na primeira chamada é o compilador 
		// quem infere T, na segunda chamada somos nós mesmos que 
		// indicamos que classe concreta deve "substituir" T.

		// Pergunta: em quais das 4 chamadas de métodos acima podemos
		// dizer que o compilador "falha" ao verificar a segurança de 
		// tipo durante a compilação?
	}

	public static void teste3(){

		Number [] arr = {1, 2.2, 3, 4.4, 5, 6.6, 7, 8.8, 9};

		
		printArray(arr);
		
		
		MetodosGenericosArray.substituiMaioresQue1(arr, 7, 0);		
		printArray(arr);		
		MetodosGenericosArray.substituiMaioresQue2(arr, 5, 0);
		printArray(arr);
		
		/*		
		MetodosGenericosArray.substituiMaioresQue3(arr, 3, 0);
		printArray(arr);
		*/

		// Pergunta 1: para as duas primeiras chamadas, serão aceitos, 
		// para o último parâmetro, tanto valores inteiros como valores
		// double?

		// Pergunta 2: porque a terceira chamada não compila? 
	
	}

	public static void main(String [] args){
	
		teste1();
		System.out.println("----------------------------------------------------------");
		teste2();
		System.out.println("----------------------------------------------------------");
		teste3();
	}
}
