// Modificação do exemplo TesteLista1, em que é feito
// o uso de interfaces visando reduzir a redundância de 
// código nos métodos que filtram os elementos da lista.

class Lista {

	private int [] a;
	private int livre;

	public Lista(int max){

		a = new int[max];
		livre = 0;
	}

	public Lista(int [] v){

		a = new int[v.length];
		livre = 0;
		
		for(int i : v) adiciona(i);
	}

	public void adiciona(int i){

		a[livre] = i;
		livre++;
	}

	public void imprime(){

		System.out.print("lista:");

		for(int i = 0; i < livre; i++){

			System.out.print(" " + a[i]);
		}

		System.out.println();
	}

	// Nesta versão tem-se apenas um método para filtrar
	// os elementos. O critério que define se um elemento 
	// deve ou não ser adicionado na resposta esta implementado
	// no objeto "crit", do tipo "Criterio".
	//
	// "Criterio" é uma interface, ou seja, define qual
	// o conjunto de métodos que deve ser oferecido por 
	// uma classe concreta que implementa um determinado 
	// critério de filtragem. Assim, "Critério" é supertipo
	// de todas as classes que implementam algum critério e
	// isso permite que o método filtra possa trabalhar com
	// variadas implementações de critérios, sem conhecer de 
	// fato o tipo real do objeto recebido como argumento do método.  

	public Lista filtra(Criterio crit){

		Lista res = new Lista(a.length);

		for(int i = 0; i < livre; i++){

			if(crit.verifica(a[i])) res.adiciona(a[i]);
		}

		return res;
	}

}

// Interface "Critério". Define o conjunto de métodos que 
// uma classes que implementa um critério deve disponibilizar.
// Note que não existe implementação. Podemos dizer, portanto,
// que a interface define uma "categoria" de objetos e o que eles
// "devem" fazer, mas não "como" fazer. O "como" fazer fica a cargo
// das classes que implementam cada um dos critérios.
//
// Observe que, apesar de todas as classes que definem algum
// tipo de critério possuirem o método "verifica", a implementação
// de cada critério específico varia muito. Assim, neste exemplo,
// usar interface para definir um supertipo comum é muito mais 
// adequado do que usar herança. Herança só é indicado, se as 
// subclasses podem de fato aproveitar alguma implementação existente
// na superclasse (o que claramente não aconteceria neste exemplo).

interface Criterio {

	// Uma instancia de critério recebe um valor x
	// e devolve um boolean indicando se x deve ou não
	// ser adicionado na sublista resposta.

	public boolean verifica(int x);
}

// Implementação de um critério para filtrar os elementos pares

class Pares implements Criterio {

	public boolean verifica(int x){

		return (x % 2 == 0);
	}
}

// Implementação de um critério para filtrar os elementos iguais a zero

class Zeros implements Criterio {

	public boolean verifica(int x){

		return (x == 0);
	}
}

// Implementação de um critério para filtrar os elementos maiores que um certo valor

class MaioresQue implements Criterio {

	private int k;

	public MaioresQue(int k){

		this.k = k;
	}

	public boolean verifica(int x){

		return (x > k);
	}
}

// Implementação de um critério para filtrar os elementos menores que um certo valor

class MenoresQue implements Criterio {

	private int k;

	public MenoresQue(int k){

		this.k = k;
	}

	public boolean verifica(int x){

		return (x < k);
	}
}

// Implementação de um critério para filtrar pelos elementos que estão entre dois valores.
// Note que este critério foi implementado pela composição de dois outros critérios já
// existentes.

class Entre implements Criterio {

	private Criterio c1, c2;

	public Entre(int a, int b){

		c1 = new MaioresQue(a);
		c2 = new MenoresQue(b);
	}

	public boolean verifica(int x){

		return (c1.verifica(x) && c2.verifica(x));
	}
}

// A partir da ideia de criar um critério a partir de outros já existentes, podemos
// criar critérios que implementam os operadores booleanos: And, Or, Not

class And implements Criterio {

	private Criterio c1, c2;

	public And(Criterio c1, Criterio c2){

		this.c1 = c1;
		this.c2 = c2;
	}

	public boolean verifica(int x){

		return (c1.verifica(x) && c2.verifica(x));
	}
}

class Or implements Criterio {

	private Criterio c1, c2;

	public Or(Criterio c1, Criterio c2){

		this.c1 = c1;
		this.c2 = c2;
	}

	public boolean verifica(int x){

		return (c1.verifica(x) || c2.verifica(x));
	}
}

class Not implements Criterio {

	private Criterio c;

	public Not(Criterio c){

		this.c = c;
	}

	public boolean verifica(int x){

		return (!c.verifica(x));
	}
}

// Classe principal

public class TesteLista2 {

	public static void main(String [] args){

		int [] v = { 7, 0, 3, 2, 1, 4, 8, 9, 6, 5, 9, 3, 4, 1, 2, 3, 3, 5, 0, 3, 2, 3, 4, 0 };

		Lista l, res;

		l = new Lista(v);
		l.imprime();

		res = l.filtra(new Pares());
		res.imprime();

		res = l.filtra(new Zeros());
		res.imprime();

		res = l.filtra(new MaioresQue(5));
		res.imprime();

		res = l.filtra(new Entre(1, 4));
		res.imprime();

		res = l.filtra(new Not(new Zeros()));
		res.imprime();

		res = l.filtra(new Or(new Pares(), new MaioresQue(6)));
		res.imprime();

		res = l.filtra(new And(new MenoresQue(4), new Not(new Pares())));
		res.imprime();

		System.out.println("---------------------------------------------------------");
	}
}

