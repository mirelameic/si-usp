#include <stdio.h>

// Criação da struct Stack para representar o tipo pilha.
// Permite agregar os "pedaços" de informação que compõe
// estado de uma pilha.

typedef struct {

	int values[100];
	int free;

} Stack;

void init(Stack * stack){

	stack->free = 0;
}

void push(Stack * stack, int value){

	stack->values[stack->free] = value;
	stack->free++;
}

int pop(Stack * stack){

	stack->free--;
	return stack->values[stack->free];	
}

int empty(Stack * stack){

	return stack->free == 0;
}

int main(){
	

	Stack stack;
	int value;
  
	init(&stack);
  
	while(scanf("%d", &value) == 1 && value > 0){
    
		push(&stack, value);	
  	}
  
	printf("Conteudo em ordem reversa:");
  
	while(!empty(&stack)) {
    
		value = pop(&stack);
    		printf(" %d", value);
  	}
	
	printf("\n");
  
	return 0;
}
