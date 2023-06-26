/*

  Exemplos similares ao encontrados no arquivo MetodosGenericosArray.java,
  porém adaptados para manipular Listas genéricas (usando a classe Lista,
  declarada abaixo) ao invés de arrays genéricos. O fato de estarmos manipulando
  objetos genéricos ao invés de arrays genéricos traz algumas mudanças de 
  funcionamento importantes. 

  ------------------------------------------------------------------------------

  A primeira delas é que a versão não genérica (que recebe está preparada
  para receber listas de Numbers) não aceita listas de coisas que sejam
  derivadas de Number. Isso porque, embora exista claramente uma relação
  de herança entre Number e Integer, por exemplo, tal relação não se aplica
  entre Lista<Number> e Lista<Integer>. Aparentemente tal afirmação parece
  contra intuitiva, mas uma forma fácil de perceber isso é pensar no sentido
  da herança. Quando dizemos que Integer é subtipo de Number, Integer deve
  ser capaz de fazer tudo que Number faz, e eventualmente mais alguma coisa.
  Se Lista<Integer> fosse subtipo de Lista<Number>, Lista<Integer> deveria
  ser capaz de fazer tudo que Lista<Number> faz, e eventualmente mais alguma
  coisa. Porém, Lista<Integer> é mais restrita em termos de comportamento do
  que Lista<Number> (enquanto a última é capaz de guardar elementos do tipo
  Integer, Long, Float, Double, etc, a primeira é apenas capaz de guardar
  elementos do tipo Integer).
  
  ------------------------------------------------------------------------------

  A segunda é que a primeria versão genérica apresentada neste arquivo (que 
  recebe uma lista de "coisas que sejam Numbers ou derivados de Number") é 
  imune ao problema observado na versão que manipula arrays genéricos. O que
  é algo bom, visto que o problema observado configura um caso em que a verificação
  de segurança de tipo falha.
  
  O problema da versão que manipula arrays genéricos é que ela permite
  misturar os tipos dos parâmetros, sem violar a declaração do método genérico.
  Enquanto que, se observarmos os tipos de operações que realizamos com o array 
  dentro do método, misturar os tipos não faz sentido.

  Por exemplo, se, em uma chamada do método, passamos por acidente "array" e 
  "max" sendo do tipo Integer e "novo" do tipo Double, e não definirmos 
  explicitamente quem deve "substituir" T, o compilador irá inferir que T é 
  Number, verificará que a chamada está coerente com a declaração do método
  e irá compilar o código. Contudo, durante a execução, obviamente haverá um
  problema quando se tentar atribuir um valor Double a uma das posições do
  array de Integer. A única maneira de barrar uma mistura acidental dos tipos
  dos parâmetros durante a compilação seria definindo explicitamente qual
  tipo/classe deve "substituir" T em uma certa chamada do método. Por exemplo,
  se definirmos explicitamente que T deve ser "substituido" por Float, então
  todos os parâmetros do método terão que, obrigatoriamente, ser Floats, ou
  o código não compilará.

  Voltando para a versão genérica apresentada neste código fonte (que manipula
  listas genéricas), ela é imune a este problema relatado (que ocorre com
  a versão que trata arrays genéricos) pois o compilador tem informações
  mais específicas para fazer a inferência do tipo genérico. Quando invocamos o
  método, e passamos como argumento uma lista genérica já criada que define 
  explicitamente quem é o T da lista, o T inferido para a chamda de método 
  deverá obrigatoriamente ser o mesmo e, consequentemente, "max" e "novo" deverão
  também ser do mesmo tipo. No caso de haver qualquer mistura acidental de 
  tipos haverá um erro de compilação.

*/


// Classe que implementa uma lista genérica "rudimentar".
 
class Lista<T> {

	T [] dados;
	int livre;

	@SuppressWarnings("unchecked")
	public Lista(int max){

		dados = (T []) new Object[max]; // por que não podemos simplesmente fazer: dados = new T[max]?
		livre = 0;
	}

	public void add(T x){

		if(livre < dados.length){

			dados[livre] = x;
			livre++;
		}
		else throw new IllegalStateException("Lista cheia!");
	}

	public int tamanho(){

		return livre;
	}

	public T get(int i){

		if(i < tamanho()){

			return dados[i];
		}
		else throw new IllegalArgumentException("Indice invalido!");
	}

	public T set(int i, T x){

		if(i < tamanho()){

			T old = dados[i];
			dados[i] = x;
			return old;
		}
		else throw new IllegalArgumentException("Indice invalido!");		
	}

	public String toString(){

		String s = "Lista: [";

		for(int i = 0; i < livre; i++){

			s += (" " + dados[i]);
		}

		s += "]";		
		
		return s;
	}
}

public class MetodosGenericosLista {

	
	public static void substituiMaioresQue1(Lista<Number> l, Number max, Number novo){

		for(int i = 0; i < l.tamanho(); i++){

			if(l.get(i).doubleValue() > max.doubleValue()) l.set(i, novo);
		}	
	}

	public static <T extends Number> void substituiMaioresQue2(Lista<T> l, T max, T novo){

		for(int i = 0; i < l.tamanho(); i++){

			if(l.get(i).doubleValue() > max.doubleValue()) l.set(i, novo);
		}	
	}

	public static <T extends Comparable<T>> void substituiMaioresQue3(Lista<T> l, T max, T novo){

		for(int i = 0; i < l.tamanho(); i++){

			if(l.get(i).compareTo(max) > 0) l.set(i, novo);
		}	
	}

	// Este método genérico (que recebe uma Lista genérica
        // de coisas que sejam Number ou derivadas de Number,
        // e devolve a soma de todos os valores numéricos 
	// armazenados na lista) ilustra uma notação alternativa
	// que pode ser usada para declarar métodos genéricos
	// sem que se precise declarar a seção com os parâmetros
	// de tipo. Esta notação alternativa pode ser útil quando,
	// no corpo do método, não há a necessidade de se usar
	// as variáveis de tipo (é o caso deste exemplo).

	public static double soma(Lista<? extends Number> l){
		
		double soma = 0.0;

		for(int i = 0; i < l.tamanho(); i++){

			soma += l.get(i).doubleValue();
		}

		return soma;	
	}

	public static void teste1(){

		Lista<Integer> l = new Lista<Integer>(10);

		l.add(10);
		l.add(20);
		l.add(30);
		l.add(40);
		l.add(50);
		l.add(60);
		l.add(70);
		l.add(80);

		System.out.println(l);
		
		/*		
		substituiMaioresQue1(l, 70, 0); // Por que está chamada causa um erro de compilação???
		System.out.println(l);
		*/

		substituiMaioresQue2(l, 50, 0); // E se trocarmos o último parâmetro por 0.1? O que acontece? Por que?
		System.out.println(l);
		substituiMaioresQue3(l, 20, 0); // E se trocarmos o último parâmetro por 0.1? O que acontece? Por que?
		System.out.println(l);
		System.out.println("SOMA: " + soma(l));
	}

	public static void teste2(){

		Lista<Number> l = new Lista<Number>(10);

		l.add(0.5);
		l.add(1);
		l.add(2.1);
		l.add(3);
		l.add(4.2);
		l.add(5);
		l.add(6.3);
		l.add(7);

		System.out.println(l);


		substituiMaioresQue1(l, 6, 0); // Por que está chamada *não* causa um erro de compilação???
		System.out.println(l);
		substituiMaioresQue2(l, 3, 0); // E se trocarmos o último parâmetro por 0.1? O que acontece? Por que?
		System.out.println(l);

		/*		
		substituiMaioresQue3(l, 1, 0); // Por que está chamada causa um erro de compilação???
		System.out.println(l);
		*/

		System.out.println("SOMA: " + soma(l));
	}

	public static void teste3(){

		Lista<Double> l = new Lista<Double>(10);

		l.add(10.1);
		l.add(20.2);
		l.add(30.3);
		l.add(40.4);
		l.add(50.5);
		l.add(60.6);
		l.add(70.7);
		l.add(80.8);

		System.out.println(l);

		/*		
		substituiMaioresQue1(l, 70.0, 0.1); // Por que está chamada causa um erro de compilação???
		System.out.println(l);
		*/

		substituiMaioresQue2(l, 50.0, 0.1); // E se trocarmos o último parâmetro por 0? O que acontece? Por que?
		System.out.println(l);
		substituiMaioresQue3(l, 20.0, 0.1); // E se trocarmos o último parâmetro por 0? O que acontece? Por que?
		System.out.println(l);
		System.out.println("SOMA: " + soma(l));
	}

	public static void teste4(){

		Lista<String> l = new Lista<String>(10);

		l.add("branco");
		l.add("preto");
		l.add("vermelho");
		l.add("verde");
		l.add("azul");
		l.add("amarelo");
		l.add("magenta");
		l.add("ciano");
		l.add("laranja");
		l.add("marrom");

		System.out.println(l);

		/*		
		substituiMaioresQue1(l, "laranja", "-------"); // Por que está chamada causa um erro de compilação???
		System.out.println(l);
		*/

		/*
		substituiMaioresQue2(l, "laranja", "-------"); // Por que está chamada também não compila?
		System.out.println(l);
		*/

		substituiMaioresQue3(l, "laranja", "-------"); // E se trocarmos o último parâmetro por um número? O que acontece? Por que?
		System.out.println(l);

		// System.out.println("SOMA: " + soma(l)); // E aqui, porque o soma não se aplica a lista de strings?
	}


	public static void main(String [] args){

		teste1();
		System.out.println("---------------------------------------------------------------------------");
		teste2();
		System.out.println("---------------------------------------------------------------------------");
		teste3();
		System.out.println("---------------------------------------------------------------------------");
		teste4();
	}
}
