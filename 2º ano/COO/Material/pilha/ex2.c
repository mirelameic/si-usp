#include <stdio.h>

// Melhor separação de responsabilidades, com implementação
// das operações associadas à estrutura de dados em funções
// separadas do método main. Mas os pedaços de informação que
// compõe o estado da pilha ainda estão "soltos" no código. 
// Cabe ao "usuário" da pilha saber que há uma associação entre
// os "pedaços" de informação que representam uma pilha, para
// realizar as chamadas das funções adequadamente.

void push(int * stack, int * free, int value){

	stack[*free] = value;
	(*free)++;
}

int pop(int * stack, int * free){

	(*free)--;
	return stack[*free];	
}

int empty(int * stack, int * free){

	return *free == 0;
}

int main(){
	
	int stack[100];
	int free = 0;
	int value;

	while(scanf("%d", &value) == 1 && value > 0){

		push(stack, &free, value);	
	}

	printf("Conteudo em ordem reversa:");
	
	while(!empty(stack, &free)) {

		value = pop(stack, &free);
		printf(" %d", value);
	}
	
	printf("\n");

	return 0;
}
