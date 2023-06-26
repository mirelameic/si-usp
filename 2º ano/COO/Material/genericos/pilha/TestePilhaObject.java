/*

  PilhaObject é a nova versão da classe Pilha. Sua diferença para
  a primeira é que esta agora manipula elementos do tipo Object.
  O array "dados", que é o espaço de armazenamento da estrutura é
  agora um array de referencias para Objects, o método push(...)
  recebe como argumento uma referência para um Object, e o método
  pop() devolve uma referência para um Object. Apesar destas mudanças,
  a lógica que rege o funcionamento desta pilha é, em essencia, a mesma
  que já estava implementada na versão anterior.

  A grande novidade desta versão é que qualquer objeto, que seja instância
  de uma classe derivada (direta ou indiretamente) de Object, pode ser 
  guardado na pilha. E como *TODA* classe deriva direta ou indiretamente de
  Object, então esta pilha simplesmente aceitará OBJETOS DE QUALQUER tipo.
  
  A exceção do que pode ser guardado fica para os elementos de tipo primitivo
  (int, long, float, double, etc) que NÃO SÃO objetos. Mas, mesmo nestes
  casos, é possível usar as classes empacotadoras (Integer, Long, Float, 
  Double, etc) para representar valores primitivos como objetos (e ainda 
  podemos  contar com uma mãozinha do compilador com o recurso de "auto-boxing"
  e  "auto-unboxing" para facilitar a nossa vida).

  Na prática, portanto, é possível guardar QUALQUER TIPO de informaçao nesta 
  versão da pilha. Contudo, ela possui algumas desvantagens:

  1) Objetos de diferentes tipos poderão ser guardados em uma mesma pilha. 
     Apesar possívelmente haver situações em que isso é o que realmente 
     queremos, é mais usual que cada instância da pilha seja usada apenas 
     para guardar objetos de um mesmo tipo. Nesta versão da pilha, nenhum
     tipo de verificação será feita de modo a alertar para um possível uso
     "equivocado" da mesma, quando forem misturados elementos de tipos 
     diferentes dentro de uma mesma pilha.

  2) Ao remover um elemento da pilha, receberemos como retorno uma referencia
     para um Object. Mas, para que possamos realizar qualquer operação mais
     específica com o objeto devolvido, precisamos saber qual o tipo concreto
     do objeto, e realizar uma conversão do tipo da referência (cast). Como a
     execução bem sucedida (ou não) da conversão é algo que só será determinado
     em tempo de execução, um uso "equivocado" da pilha (por exemplo, guardando
     um elemento de um tipo diferente do esperado, e fazendo uma conversão
     inválida na remoção), só será manifestará em tempo de execução, com o
     lançamento de uma ClassCastException que pode causar uma finalização abrupta
     da execução do programa. A primeira vista pode não parecer algo tão ruim,
     mas é sempre pior ter que lidar com problemas que se manifestam em tempo de
     execução do que lidar com aqueles que se manifestam em tempo de compilação.
     Algum tipo de verificação durante a compilação que avisasse sobre um eventual
     uso "equivocado" da pilha certamente seria útil neste sentido.

  Ainda assim, é uma versão bastante superior à apresentada no exemplo anterior.
  Apesar dos incovenientes (necessidade de casts, potencial uso equivocado, e a 
  não verificação de problemas que se manifestam apenas durante a execução), o
  objetivo de maximinar a reutilização de código é atingido.

*/

class PilhaObject {

	private Object [] dados;
	private int topo;

	public PilhaObject(int max){

		dados = new Object[max];
		topo = 0;
	}

	public void push(Object obj){

		if(topo < dados.length) {

			dados[topo] = obj;
			topo++;
		}
		else{ 
			throw new IllegalStateException("Pilha Cheia");
		}
	}

	public Object pop(){

		if(topo > 0){

			topo--;
			return dados[topo];
		}

		throw new IllegalStateException("Pilha Vazia");
	}

	public String toString(){

		String s = "Pilha:";

		for(int i = 0; i < topo; i++){

			s += (" " + dados[i]);

		}

		return s;
	}	
}

/* 

  Em um cenário em que queremos garantir que uma pilha só possa ser
  utilizada para guardar um certo tipo de elemento (int, no caso 
  particular deste exemplo), e ao mesmo tempo não desejamos replicar
  a lógica de funcionamento da pilha, já presente em PilhaObject, o
  uso de composição para criar uma nova classe pode ser uma alternativa 
  interessante.

  A classe PilhaInt ilustra uma solução (ainda que com suas desvantagens)
  que mescla razoavelmente bem o princípio de maximizar a reutilizção de
  código com a presença de verificação em tempo de compilação quanto ao 
  correto uso da pilha.

  Note que a classe PilhaInt é apenas uma "casca" para uma PilhaObject.
  Todo o trabalho "pesado" feito por uma instância de PilhaInt será, de fato,
  realizado pelo objeto PilhaObject mantido como atributo da instância
  (caracterizando, assim, a REUTILIZAÇÃO DE CÓDIGO).
  
  Além disso, como PilhaInt declara de forma precisa que push(...) só
  aceita valores do tipo int, e que pop() irá devolver sempre valores do
  tipo int, o compilador será capaz de verificar durante a compilação se os
  "clientes/usuários" de PilhaInt a estão utilizando da forma correta
  (oferecendo a SEGURANÇA DA VERIFICAÇÃO DE TIPOS EM TEMPO DE COMPILAÇÃO). 
 
*/

class PilhaInt {

 	// Esta classe implementa uma pilha que aceita apelas
	// valores do tipo int, usando o princípio de composição.

	private PilhaObject pilha; 

	public PilhaInt(int max){

		pilha = new PilhaObject(max);
	}

	public void push(int i){

		// apesar de "pilha" aceitar qualquer tipo de objeto,
		// a declaração deste método garante que apenas valores
		// inteiros serão recebidos com argumento, e apenas objetos
		// do tipo Integer serão guardados em "pilha".

		pilha.push(new Integer(i));
	}

	public int pop(){

		Object obj = pilha.pop();

		// Como temos a garantia de que apenas objetos do tipo 
		// Integer serão guardados em "pilha", a conversão abaixo
		// sempre será bem sucedida, afastando o risco de falhas
		// na conversão durante a execução.

		Integer i = (Integer) obj;

		// Além disso, este método sempre irá devolver o valor int
		// associado ao objeto Integer removido da pilha. Dessa forma,
		// os usuários de PilhaInt, quado chamarem este método, não
		// precisarão ser responsáveis por realizar conversões de tipo.

		return i.intValue();
	}

	public String toString(){

		return pilha.toString();
	}
}

// Classe criada para para o exemplo que usa uma 
// pilha para guardar instâncias de Produto.

class Produto {

	private String descricao;
	private double preco;

	public Produto(String descricao, double preco){

		this.descricao = descricao;
		this.preco = preco;
	}

	public String toString(){

		return "['" + descricao + "': R$ " + preco + "]";
	}
}

public class TestePilhaObject {

	// método que ilustra o uso de PilhaObject 
	// para guardar valores inteiros.

	public static void testeInt(){

		PilhaObject p = new PilhaObject(10);

		// A rigor não seria necessário instanciar os objetos
		// do tipo Integer. Caso não tivessemos criados as 
		// instâncias "na mão" o compilador nos daria uma
		// mãozinha com o recurso de "auto-boxing" e faria isso
		// por nós, gerando um código compilado equivalente ao
		// código abaixo:

		p.push(new Integer(10));
		p.push(new Integer(20)); 
		p.push(new Integer(30)); // experimente empilhar a string "30" ao invés de um objeto Integer. O que acontece?
		p.push(new Integer(40));
		p.push(new Integer(50));

		System.out.println(p);	

		for(int i = 0; i < 3; i++) {

			// Da mesma forma, poderiamos contar com a ajuda
			// do compilador e do "auto-unboxing" para converter
			// o objeto devolvido pelo metodo pop() diretamente
			// para o tipo int, o que geraria um código compilado
			// equivalente às linhas de código abaixo: 

			Integer obj = (Integer) p.pop(); // esta conversão funcionará 100% da vezes?
			int x = obj.intValue();
			System.out.println("Elemento removido: " + x); 
		}
		
		System.out.println(p);	
	}

	// método que ilustra o uso de PilhaObject 
	// para guardar objetos do tipo Produto.

	public static void testeProduto(){

		PilhaObject p = new PilhaObject(10);

		p.push(new Produto("Prod 1", 25.0));
		p.push(new Produto("Prod 2", 50.0));
		p.push(new Produto("Prod 3", 99.0)); // experimente empilhar a string "Prod 3: R$ 99.0" ao invés de um objeto do tipo Produto. 
		p.push(new Produto("Prod 4", 147.0));
		p.push(new Produto("Prod 5", 229.9));

		System.out.println(p);	

		for(int i = 0; i < 3; i++) {

			Produto x = (Produto) p.pop(); // esta conversão funcionará 100% da vezes?
			System.out.println("Elemento removido: " + x); 
		}
		
		System.out.println(p);	
	}

	// método que ilustra o uso de PilhaInt 
	// para guardar valores inteiros.

	public static void testePilhaInt(){

		PilhaInt p = new PilhaInt(10);

		p.push(11); // apenas valores do tipo int podem ser guardados na pilha.
		p.push(21); // Tentativas de guardar valores/objetos de quaisquer outro 
		p.push(32); // tipo serão rejeitadas, causando erros de compilação!
		p.push(42);
		p.push(53);

		System.out.println(p);	

		for(int i = 0; i < 3; i++) {

			int x = p.pop(); // pop() SEMPRE devolverá um valor do tipo int.
			System.out.println("Elemento removido: " + x); 
		}
		
		System.out.println(p);	
	}

	public static void main(String [] args){

		testeInt();
		
		System.out.println("-------------------------------------------------------------------------");

		testeProduto();

		System.out.println("-------------------------------------------------------------------------");

		testePilhaInt();
	}
}

