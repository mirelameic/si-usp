/*

  A classe Pilha, declarada logo abaixo, implementa uma estrutura de dados 
  mínima do tipo pilha. Podemos dizer que é uma classe bem encapsulada, 
  em que os atributos que mantem o estado interno da pilha estão bem 
  protegidos dos "clientes/usuários" da estrutura, que por sua vez apenas
  podem requisitar a adição ou remoção de elementos da mesma.

  Apesar disso, no que diz respeito ao reaproveitamento de código, esta 
  implementação é ruim, pois só é capaz de armazenar valores inteiros.
  Havendo a necessidade de guardar na pilha elementos de outros tipos 
  seríamos obrigados a criar novas versões desta classe, que seriam 
  essencialmente cópias desta, mudando-se apenas o tipo do array "dados", 
  do argumento recebido no método push(...) e do tipo de valor devolvido
  em pop(). Haveria neste caso uma óbvia redundância de código (algo que
  sempre devemos evitar).

  Entretanto, como o comportamento que uma pilha deve implementar (guardar 
  e remover elementos, respeitando uma certa ordem de entrada e saída) 
  independe do tipo daquilo que é guardado, nos parece razoável pensar em
  uma única implementação, capaz de armazenar qualquer tipo de elemento.

  O que nos leva para a próxima versão desta classe: PilhaObject.

*/

class Pilha {

	private int [] dados;
	private int topo;

	public Pilha(int max){

		dados = new int[max];
		topo = 0;
	}

	public void push(int valor){

		if(topo < dados.length) {

			dados[topo] = valor;
			topo++;
		}
		else{ 
			throw new IllegalStateException("Pilha Cheia");
		}
	}

	public int pop(){

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

  Classe "cliente/usuária" da classe Pilha. O método main essencialmente
  implementa um teste simples da estrutura, instanciando um objeto do tipo
  Pilha, realizando a adição de alguns elementos e, em seguida, fazendo
  algumas remoções.

*/

public class TestePilha {

	public static void main(String [] args){

		if(args.length != 3){

			System.out.println("Uso: java " + TestePilha.class.getName() + " <capacidade pilha> <# adicoes> <# remocoes>");
			System.exit(1);
		}

		int capacidade = Integer.parseInt(args[0]);
		int num_adicoes = Integer.parseInt(args[1]);
		int num_remocoes = Integer.parseInt(args[2]);

		Pilha p = new Pilha(capacidade);
	
		for(int i = 0; i < num_adicoes; i++){
	
			p.push(i);
		}

		System.out.println(p);

		for(int i = 0; i < num_remocoes; i++){

			int x = p.pop();
			System.out.println("Elemento removido: " + x); 
		}

		System.out.println(p);	
	}
}
