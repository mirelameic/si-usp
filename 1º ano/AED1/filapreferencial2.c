#include "filapreferencial.h"

PFILA criarFila(){
    PFILA res = (PFILA) malloc(sizeof(FILAPREFERENCIAL));
    res->cabeca = (PONT) malloc(sizeof(ELEMENTO));
    res->inicioNaoPref = res->cabeca;
    res->cabeca->id = -1;
    res->cabeca->idade = -1;
    res->cabeca->ant = res->cabeca;
    res->cabeca->prox = res->cabeca;
    return res;
}

int tamanho(PFILA f){
	PONT atual = f->cabeca->prox;
	int tam = 0;
	while (atual != f->cabeca){
		atual = atual->prox;
		tam++;
	}
	return tam;
}

PONT buscarID(PFILA f, int id){
	PONT atual = f->cabeca->prox;
	while (atual != f->cabeca){
		if(atual->id==id) return atual;
		atual = atual->prox;
	}
	return NULL;
}

void exibirLog(PFILA f){
	int numElementos = tamanho(f);
	printf("\nLog fila [elementos: %i]\t- Inicio:", numElementos);
	PONT atual = f->cabeca->prox;
	while (atual != f->cabeca){
		printf(" [%i;%i]", atual->id, atual->idade);
		atual = atual->prox;
	}
	printf("\n                       \t-    Fim:");
	atual = f->cabeca->ant;
	while (atual != f->cabeca){
		printf(" [%i;%i]", atual->id, atual->idade);
		atual = atual->ant;
	}
	printf("\n\n");
}


int consultarIdade(PFILA f, int id){
	PONT atual = f->cabeca->prox;
	while (atual != f->cabeca){
		if(atual->id==id) return atual->idade;
		atual = atual->prox;
	}
	return -1;
}






bool inserirPessoaNaFila(PFILA f, int id, int idade){
	PONT novo = buscarID(f, id); //verifica se ja esta inserido
	if(novo!=NULL || id<0 || idade<0) return false;
	novo = (PONT) malloc(sizeof(ELEMENTO));
	novo->id = id;
	novo->idade = idade;
	if(novo->idade>=IDADEPREFERENCIAL){ // MAIOR OU IGUAL 60
		if(f->cabeca->prox==f->cabeca){	//FILA VAZIA
			novo->prox = f->cabeca;
			novo->ant = f->cabeca;
			f->cabeca->prox = novo;
			f->cabeca->ant = novo;
		}else if(f->cabeca->prox->idade<IDADEPREFERENCIAL){	 //SO NAO PREFERENCIAIS NA FILA
			novo->prox = f->cabeca->prox;
			novo->ant = f->cabeca;
			f->cabeca->prox->ant = novo;
			f->cabeca->prox = novo;
		}else if(f->inicioNaoPref==f->cabeca){	//SO PREFERENCIAIS NA FILA
			novo->prox = f->cabeca;
			novo->ant = f->cabeca->ant;
			f->cabeca->ant->prox = novo;
			f->cabeca->ant = novo;
		}else{	// OS DOIS
		    novo->prox = f->inicioNaoPref;
			novo->ant = f->inicioNaoPref->ant;
			f->inicioNaoPref->ant->prox = novo;
			f->inicioNaoPref->ant = novo;
		}
	}else{ //MENOR QUE 60
		if(f->cabeca->prox==f->cabeca){ //FILA VAZIA
			novo->prox = f->cabeca;
			novo->ant = f->cabeca;
			f->cabeca->prox = novo;
			f->cabeca->ant = novo;
			f->inicioNaoPref = novo;
		}else if(f->inicioNaoPref==f->cabeca){	//SO PREFERENCIAIS NA FILA
			novo->prox = f->cabeca;
			novo->ant = f->cabeca->ant;
			f->cabeca->ant->prox = novo;
			f->cabeca->ant = novo;
			f->inicioNaoPref = novo;
		}else{	//RESTO
			novo->prox = f->cabeca;
			novo->ant = f->cabeca->ant;
			f->cabeca->ant->prox = novo;
			f->cabeca->ant = novo;
		}
	}
	return true;
}



bool atenderPrimeiraDaFila(PFILA f, int* id){
	if(f->cabeca->prox==f->cabeca) return false;	//verifica se esta vazia
	*id = f->cabeca->prox->id; //retorna o valor no endereco
	PONT atender = f->cabeca->prox;
	if(f->cabeca->prox->prox==f->cabeca){	//SO TEM UMA PESSOA NA FILA
		if(f->cabeca->prox==f->inicioNaoPref) f->inicioNaoPref = f->cabeca;
		f->cabeca->prox = f->cabeca;
		f->cabeca->ant = f->cabeca;	
	}else if(f->cabeca->prox==f->inicioNaoPref){	//SO TEM NAO PREFERENCIAL NA FILA
		f->inicioNaoPref = f->inicioNaoPref->prox;
		f->cabeca->prox = f->inicioNaoPref;
		f->inicioNaoPref->ant = f->cabeca;
	}else{	//RESTO
		f->cabeca->prox = f->cabeca->prox->prox;
		f->cabeca->prox->ant = f->cabeca;
	}
	free(atender);
	return true;
}



bool desistirDaFila(PFILA f, int id){
	PONT desistir = buscarID(f, id);
	if(desistir==NULL) return false;	//verifica se o desistente eh valido
	if(f->cabeca->prox->prox==f->cabeca){	//SO TEM UMA PESSOA NA FILA
		if(f->cabeca->prox==f->inicioNaoPref) f->inicioNaoPref = f->cabeca;
		f->cabeca->prox = f->cabeca;
		f->cabeca->ant = f->cabeca;
	}else if(f->cabeca->prox==desistir){	// DESISTENTE EH O PRIMEIRO DA FILA
		if(desistir==f->inicioNaoPref) f->inicioNaoPref = desistir->prox;
		f->cabeca->prox = desistir->prox;
		f->cabeca->prox->ant = f->cabeca;
	}else{	//RESTO
		desistir->ant->prox = desistir->prox;
		desistir->prox->ant = desistir->ant;
	}
	free(desistir);
	return true;
}