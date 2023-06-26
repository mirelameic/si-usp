/*

  Finalmente, a versão genérica da pilha.

  A proposta desta versão é que ela funcione como uma PilhaObject,
  maximizando a reutilização do código, já que uma única implementação
  é capaz de manipular objetos de qualquer tipo.

  Mas também permitir que os "clientes/usuários" de PilhaGenerica possam
  manifestar suas intenções quanto ao uso da pilha (se será usada para
  guardar objetos do tipo Integer, Strings, Produto, Alunos, etc), e que
  durante a compilação se verifique se o uso está coerente com a intenção
  manifestada.

  O recurso de tipos genéricos permite atingir estes dois objetivos!

  A declaração da classe PilhaGenerica define uma variável (ou parâmetro
  de tipo) T usada para representar um tipo de objeto (um nome de classe)
  que está em "aberto". O tipo do array usado como espaço de armazanamento, 
  o tipo do parâmetro recebido pelo metodo push(...), e o tipo do objeto
  devolvido como retorno do pop() são todos declarados em função de T.
  Apesar de não definirmos exatamente quem é T, temos uma garantia de que
  o tipo do array, do parametro recebido por push(...) e o tipo do objeto
  devolvido por pop() serão o mesmo. 

  O código de um "cliente/usuário" desta classe, ao instanciar uma 
  PilhaGenerica, também irá declarar qual tipo real "substituirá" T 
  (Integer, String, Produto, Aluno, etc) e, com base nesta declaração, 
  o compilador será capaz de verificar se o uso da pilha está adequado. 
  Ou seja: se só serão guardados na pilha objetos do tipo especificado 
  para "substituir" T; e se o objeto devolvido em uma remoção será atribuído
  a uma variável de tipo compatível com o que foi escolhido para T. Isso
  irá prevenir problemas que só se manifestariam em tempo de execução 
  caso ainda estivessemos usando PilhaObject. Ou seja, passa a existir
  a tão almejada verificação de segurança de tipos em tempo de compilação!

*/

class PilhaGenerica <T> { // variáveis de tipo são declaradas dentro de uma seção delimitada pelos símbolos < e >.

	private T [] dados;
	private int topo;

	@SuppressWarnings("unchecked")
	public PilhaGenerica(int max){

		dados = (T []) new Object[max];
		topo = 0;
	}

	public void push(T obj){

		if(topo < dados.length) {

			dados[topo] = obj;
			topo++;
		}
		else{ 
			throw new IllegalStateException("Pilha Cheia");
		}
	}

	public T pop(){

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

public class TestePilhaGenerica {

	// Método que ilustra o uso da PilhaGenerica 
	// para guardar valores inteiros.

	public static void testeInt(){

		// Na linha abaixo, em que é feita a instanciação de uma pilha
		// genérica, define-se que a variável de tipo T, para esta instância
		// particular, deve ser equivalente ao tipo Integer...

		PilhaGenerica<Integer> p = new PilhaGenerica<Integer>(10);

		p.push(10); // ... assim o compilador verificará se apenas objetos
		p.push(20); // do tipo Integer serão passados como parâmetro das
		p.push(30); // chamadas a push(...). Note que não estamos instanciando
		p.push(40); // explicitamente objetos do tipo Integer, pois estamos
		p.push(50); // contando com a ajudinha do compilador para realizar o
			    // "auto-boxing" dos valores int.
	
		System.out.println(p);	

		for(int i = 0; i < 3; i++) {

			// No caso das chamadas a pop(), o tipo do retorno também 
			// estará atrelado ao tipo Integer, permitindo ao compilador
			// verificar se o valor devolvido está sendo atribuído em
			// uma variável de tipo compatível. Observe também que
			// nenhuma conversão de tipo (cast) precisa ser realizada. Note
			// ainda que, mais uma vez, contamos com a ajudinha do compilador
			// para fazer o "auto-unboxing" automático, convertendo objetos
			// do tipo Integer em valores primitivos do tipo int.

			int x = p.pop();
			System.out.println("Elemento removido: " + x); 
		}
		
		System.out.println(p);	
	}

	// Método que ilustra o uso da PilhaGenerica 
	// para guardar objetos do tipo Produto.

	public static void testeProduto(){

		PilhaGenerica<Produto> p = new PilhaGenerica<Produto>(10);

		p.push(new Produto("Prod 1", 25.0));
		p.push(new Produto("Prod 2", 50.0));
		p.push(new Produto("Prod 3", 99.0));
		p.push(new Produto("Prod 4", 147.0));
		p.push(new Produto("Prod 5", 229.9));

		System.out.println(p);	

		for(int i = 0; i < 3; i++) {

			Produto x = p.pop();
			System.out.println("Elemento removido: " + x); 
		}
		
		System.out.println(p);	
	}

	public static void main(String [] args){

		testeInt();

		System.out.println("-------------------------------------------------------------------------");
	
		testeProduto();
	}
}
