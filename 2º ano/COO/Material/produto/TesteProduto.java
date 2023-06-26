// Um exemplo ilustrando o uso de herança:

class Produto {

	private String descricao;
	private double preco;

	public Produto(String descricao, double preco){

		this.descricao = descricao;
		this.preco = preco;
	}

	public void setPreco(double d){

		preco = d;
	}

	public String getDescricao(){

		return descricao;
	}

	public double getPreco(){

		return preco;
	}
}


// O mecanismo de herança pode ser usado quando queremos
// criar um novo tipo (subclasse) a partir de um outro já 
// existente (classe base, ou superclasse), geralmente para
// adicionar novos comportamentos, ou fazer a alteração pontual
// de comportamentos já existentes. Note que, não faz muito
// sentido usar herança em cenários em que praticamente todo o 
// comportamento já existente (e herdado) pela subclasse deve ser
// alterado. Nestes casos, o ideal é usar interfaces.
// 
// No exemplo abaixo, a classe ProdutoContador estende a classe
// Produto, para anexar um comportamento a um que é herdado.
// Em particular, queremos que um ProdutoContador mantenha um
// contador que indique a quantidade de vezes que o método 'setPreco(...)'
// foi chamado. Para implementar este comportamento, o método 'setPreco(...)'
// é sobreescrito para além de atualizar o preço também incrementar
// o contador. Como a funcionalidade de atualização de preço propriamente
// dita já está implementada na superclasse, podemos reaproveitá-la ao
// invés de implementá-la novamente. Fazemos isso invocando o método
// 'setPreco(...)' da superclasse (usando a palavra chave "super"). A grande
// vantagem em chamar o método da superclasse é evitar a redundância
// na implementação da funcionalidade de atualização de preço. Se no
// futuro o processo pelo qual o preço de um produto é atualizado 
// sofrer mudanças, as atualizações estarão restritas ao método 'setPreco(...)'
// da superclasse (pois o método 'setPreco(...)' da subclasse delega a tarefa
// de atualização de preço propriamente dita à superclasse e em seguida
// apenas incrementa o contador).   

class ProdutoContador extends Produto {

	private int contador;
	
	public ProdutoContador(String descricao, double preco){

		super(descricao, preco);
		contador = 0;		
	}
	

	public void setPreco(double d){

		super.setPreco(d);
		contador++;
	}

	public int getContador(){

		return contador;
	}
}

public class TesteProduto {

	// Observe que o métodos aplicaDesconto e processaProduto manipulam
	// produtos sem saber o tipo concreto dos mesmos (isto é, se são
	// do tipo Produto ou ProdutosContador).

	public static void aplicaDesconto(Produto p, int desconto){

		double d = (100 - desconto) / 100.0;

		p.setPreco(p.getPreco() * d);
	}

	public static void processaProduto(Produto p){

		System.out.println("----------------------------------------------------------");
	

		System.out.println("Produto: " + p.getDescricao() + " --- R$" + p.getPreco());
		aplicaDesconto(p, 10);
		System.out.println("Produto: " + p.getDescricao() + " --- R$" + p.getPreco());
		aplicaDesconto(p, 20);
		System.out.println("Produto: " + p.getDescricao() + " --- R$" + p.getPreco());
	}

	public static void main(String [] args){

		Produto [] produtos = new Produto[3];

		produtos[0] = new ProdutoContador("Notebook", 2000.00);
		produtos[1] = new Produto("Teclado", 100.00);
		produtos[2] = new ProdutoContador("Monitor", 400.00);

		for(Produto p : produtos){

			processaProduto(p);

			if(p instanceof ProdutoContador){
				System.out.println("Quantidade de vezes que preço foi atualizado: " + ((ProdutoContador)p).getContador());
			}
		}
	}
}

