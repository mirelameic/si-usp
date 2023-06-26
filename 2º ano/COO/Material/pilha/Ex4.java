import java.util.*;

// Versão Java que usufrui dos recursos oferecidos por
// um linguagem orientada a objetos. Classe Stack está
// bem encapsulada, restringindo acesso aos atributos
// que mantem o estado interno da estrutura de dados e
// só disponibilizando publicamente os métodos que as 
// classes "usuárias" precisam chamar para usar a pilha
// de forma adequada.

class Stack {

	private int [] values;
	private int free;

	public Stack(){

		values = new int[100];
		free = 0;
	}

	public void push(int value){

		values[free] = value;
		free++;
	}

	public int pop(){

		free--;
		return values[free];
	}

	public boolean empty(){

		return free == 0;
	}
}

public class Ex4 {

	public static void main(String [] args){

		int value;
		Stack stack = new Stack();		
		Scanner scanner = new Scanner(System.in);

		while( (value = scanner.nextInt()) > 0){

			stack.push(value);	
		}

		System.out.print("Conteudo em ordem reversa:");
	
		while(!stack.empty()) {

			value = stack.pop();
			System.out.print(" " + value);
		}

		System.out.println();
	}
}
