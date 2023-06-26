#include <stdlib.h>
#include <stdio.h>
#define true 1
#define false 0
#define IDADEPREFERENCIAL 60

typedef int bool;

typedef struct aux{
  int id;
  int idade;
  struct aux* prox;
} ELEMENTO, * PONT;

typedef struct{
  PONT inicio;
  PONT fimPref;
  PONT inicioNaoPref;
  PONT fim;
} FILAPREFERENCIAL, * PFILA;


PFILA criarFila(){
	PFILA res = (PFILA) malloc(sizeof(FILAPREFERENCIAL));
	res->inicio = NULL;
	res->fimPref = NULL;
	res->inicioNaoPref = NULL;
	res->fim = NULL;
	return res;
}

int tamanho(PFILA f){
	PONT atual = f->inicio;
	int tam = 0;
	while(atual){
		atual = atual->prox;
		tam++;
	}
	return tam;
}

PONT buscarID(PFILA f, int id){
	PONT atual = f->inicio;
	 while(atual){
		if(atual->id == id) return atual;
		atual = atual->prox;
	}
	return NULL;
}

void exibirLog(PFILA f){
	int numElementos = tamanho(f);
	printf("\nLog fila [elementos: %i] - Inicio:", numElementos);
	PONT atual = f->inicio;
	while(atual){
		printf(" [%i;%i]", atual->id, atual->idade);
		atual = atual->prox;
	}
	printf("\n\n");
}

int consultarIdade(PFILA f, int id){
	PONT atual = f->inicio;
	 while(atual){
		if(atual->id == id) return atual->idade;
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
		if(tamanho(f)==0){	//FILA VAZIA
			f->inicio = novo;
			f->fimPref = novo;
			f->fim = novo;
			novo->prox = NULL;
		}else if(f->fimPref==NULL){	 //SO NAO PREFERENCIAIS NA FILA
			f->inicio = novo;
			f->fimPref = novo;
			novo->prox = f->inicioNaoPref;
		}else if(f->inicioNaoPref==NULL){	//SO PREFERENCIAIS NA FILA
			f->fimPref->prox = novo;
			f->fimPref = novo;
			f->fim = novo;
			novo->prox = NULL;
		}else{	// OS DOIS
			f->fimPref->prox = novo;
			f->fimPref = novo;
			novo->prox = f->inicioNaoPref;
		}
	}else{ //MENOR QUE 60
		if(tamanho(f)==0){ //FILA VAZIA
			f->inicio = novo;
			f->inicioNaoPref = novo;
			f->fim = novo;
			novo->prox = NULL;
		}else if(f->fimPref==NULL){	//SO NAO PREFERENCIAIS NA FILA
			f->fim->prox = novo;
			f->fim = novo;
			novo->prox = NULL;
		}else if(f->inicioNaoPref==NULL){	//SO PREFERENCIAIS NA FILA
			f->fimPref->prox = novo;
			f->inicioNaoPref = novo;
			f->fim = novo;
			novo->prox = NULL;
		}else{	// OS DOIS
			f->fim->prox = novo;
			f->fim = novo;
			novo->prox = NULL;
		}
	}
	return true;
}




bool atenderPrimeiraDaFila(PFILA f, int* id){
	if(tamanho(f)==0) return false;	//verifica se esta vazia
	*id = f->inicio->id; //retorna o valor no endereco
	PONT atender = f->inicio;
	if(f->fim==atender){	//SO TEM UMA PESSOA NA FILA
		if(f->fimPref){ //PESSOA EH PREFERENCIAL
			f->inicio = NULL;
			f->fimPref = NULL;
			f->fim = NULL;
		}else{	//PESSOA NAO EH PREFERENCIAL
			f->inicio = NULL;
			f->inicioNaoPref = NULL;
			f->fim = NULL;
		}	
	}else if(f->fimPref==NULL){	//SO TEM NAO PREFERENCIAL NA FILA
		f->inicio = atender->prox;
		f->inicioNaoPref = atender->prox;
	}else if(f->inicioNaoPref==NULL){	//SO TEM PREFERENCIAL NA FILA
		f->inicio = atender->prox;
	}else if(f->inicio!=f->inicioNaoPref){
		f->inicio = atender->prox;	//OS DOIS POREM MAIS DE UM PREFERENCIAL
	}else{	//OS DOIS POREM APENAS UM PREFERENCIAL
		f->inicio = atender->prox;
		f->inicioNaoPref = NULL;
	}
	free(atender);
	return true;
}




PONT buscarIDAnterior(PFILA f, int id){	//FUNCAO AUXILIAR PARA BUSCAR O ID ANTERIOR DO QUE VAI DESISTIR
	PONT atual = f->inicio;
	PONT anterior;
	while(atual->id != id){
	 	anterior = atual;
		atual = atual->prox;
	}
	if(atual->id==id) return anterior;
	return NULL;
}

bool desistirDaFila(PFILA f, int id){
	PONT desistir = buscarID(f, id);
	if(desistir==NULL) return false;	//verifica se o desistente eh valido
	if(desistir->prox==NULL && tamanho(f)==1){	//DESISTENTE EH O UNICO NA FILA
		if(f->inicioNaoPref==NULL){	//DESISTENTE EH PREFERENCIAL
			f->inicio = NULL;
			f->fimPref = NULL;
			f->fim = NULL;
		}else{	//NAO EH PREFERENCIAL
			f->inicio = NULL;
			f->inicioNaoPref = NULL;
			f->fim = NULL;
		}
	}else if (desistir->prox==NULL){	//DESISTENTE ESTA NO FIM MAS NAO EH O UNICO NA FILA
		if(f->fimPref==desistir){	//FILA SO TEM PREFERENCIAIS
			PONT anterior = buscarIDAnterior(f,id);
			anterior->prox = NULL;
			f->fimPref = anterior;
			f->fim = anterior;
		}else if(f->fimPref==NULL){	//FILA NAO TEM PREFERENCIAIS
			PONT anterior = buscarIDAnterior(f,id);
			anterior->prox = NULL;
			f->fim = anterior;
		}else{	//TEM OS DOIS
			PONT anterior = buscarIDAnterior(f,id);
			anterior->prox = NULL;
			f->inicioNaoPref = NULL;
			f->fim = anterior;
		}
	}else if(f->inicio==desistir){	//DESISTENTE ESTA NO INICIO
		if(f->inicioNaoPref==desistir){	//DESISTENTE NAO EH PREFERENCIAL
			f->inicio = desistir->prox;
			f->inicioNaoPref = desistir->prox;
		}else if(f->inicio!=f->fimPref){	//DESISTENTE EH PREFERENCIAL E TEM UM PROXIMO PREFERENCIAL TBM
			f->inicio = desistir->prox;
		}else{	//DESISTENTE EH PREFERENCIAL MAS NAO TEM PROXIMO PREFERENCIAL
			f->inicio = desistir->prox;
			f->inicioNaoPref = NULL;
		}
	}else if(f->inicioNaoPref==desistir && f->inicio!=desistir){  //DESISTENTE ESTA APENAS NO INICIO DA FILA NAO PREFERENCIAL
		f->inicioNaoPref = desistir->prox;
		f->fimPref->prox = desistir->prox;
	}else if(f->fimPref==desistir){	//DESISTENTE ESTA NO FIM DA PREFERENCIAL
		f->fimPref = buscarIDAnterior(f,id);
		f->fimPref->prox = desistir->prox;
	}else{	//NENHUMA DAS ANTERIORES
		PONT anterior = buscarIDAnterior(f,id);
		anterior->prox = desistir->prox;
	}
	free(desistir);
	return true;
}









int main() {
	PFILA f = criarFila();
	int id;
	int idade;
	bool res;

	printf("################# INSERINDO #######################\n");

	exibirLog(f);
	res = inserirPessoaNaFila(f, -1, -2);
	if(res) printf("Insercao retornou true (0).\n");
	else printf("Insercao retornou false (0). [OK]\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 1, 21);
	if(res) printf("Insercao retornou true (1). [OK]\n");
	else printf("Insercao retornou false (1).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 2, 11);
	if(res) printf("Insercao retornou true (2). [OK]\n");
	else printf("Insercao retornou false (2).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 3, 31);
	if(res) printf("Insercao retornou true (3). [OK]\n");
	else printf("Insercao retornou false (3).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 4, 71);
	if(res) printf("Insercao retornou true (4). [OK]\n");
	else printf("Insercao retornou false (4).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 5, 61);
	if(res) printf("Insercao retornou true (5). [OK]\n");
	else printf("Insercao retornou false (5).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 6, 81);
	if(res) printf("Insercao retornou true (6). [OK]\n");
	else printf("Insercao retornou false (6).\n");
	exibirLog(f);


	printf("################# ATENDENDO #######################\n");
	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (7), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (7).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (8), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (8).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (9), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (9).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (10), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (10).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (11), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (11).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (12), id=%i. [OK]\n",id);
	else printf("Atendimento retornou false (12).\n");

	exibirLog(f);
	res = atenderPrimeiraDaFila(f, &id);
	if(res) printf("Atendimento retornou true (13), id=%i.\n",id);
	else printf("Atendimento retornou false (13). [OK]\n");
	exibirLog(f);



	printf("################# INSERINDO PARTE 2 ###############\n");

	exibirLog(f);
	res = inserirPessoaNaFila(f, 7, 72);
	if(res) printf("Insercao retornou true (14). [OK]\n");
	else printf("Insercao retornou false (14).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 8, 22);
	if(res) printf("Insercao retornou true (15). [OK]\n");
	else printf("Insercao retornou false (15).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 9, 60);
	if(res) printf("Insercao retornou true (16). [OK]\n");
	else printf("Insercao retornou false (16).\n");
	exibirLog(f);
	res = inserirPessoaNaFila(f, 10, 42);
	if(res) printf("Insercao retornou true (17). [OK]\n");
	else printf("Insercao retornou false (17).\n");
	exibirLog(f);



	printf("################# SAINDO DA FILA ##################\n");

	exibirLog(f);
	res = desistirDaFila(f, 6);
	if(res) printf("Desistindo da fila retornou true (18).\n");
	else printf("Desistindo da fila retornou false (18). [OK]\n");
	exibirLog(f);
	res = desistirDaFila(f, 7);
	if(res) printf("Desistindo da fila retornou true (19). [OK]\n");
	else printf("Desistindo da fila retornou false (19).\n");
	exibirLog(f);
	res = desistirDaFila(f, 8);
	if(res) printf("Desistindo da fila retornou true (20). [OK]\n");
	else printf("Desistindo da fila retornou false (20).\n");
	exibirLog(f);
	res = desistirDaFila(f, 9);
	if(res) printf("Desistindo da fila retornou true (21). [OK]\n");
	else printf("Desistindo da fila retornou false (21).\n");
	exibirLog(f);
	res = desistirDaFila(f, 10);
	if(res) printf("Desistindo da fila retornou true (22). [OK]\n");
	else printf("Desistindo da fila retornou false (22).\n");
	exibirLog(f);

	return 0;
}


/*################# INSERINDO #######################

Log fila [elementos: 0] - Inicio:

Insercao retornou false (0). [OK]

Log fila [elementos: 0] - Inicio:

Insercao retornou true (1). [OK]

Log fila [elementos: 1] - Inicio: [1;21]

Insercao retornou true (2). [OK]

Log fila [elementos: 2] - Inicio: [1;21] [2;11]

Insercao retornou true (3). [OK]

Log fila [elementos: 3] - Inicio: [1;21] [2;11] [3;31]

Insercao retornou true (4). [OK]

Log fila [elementos: 4] - Inicio: [4;71] [1;21] [2;11] [3;31]

Insercao retornou true (5). [OK]

Log fila [elementos: 5] - Inicio: [4;71] [5;61] [1;21] [2;11] [3;31]

Insercao retornou true (6). [OK]

Log fila [elementos: 6] - Inicio: [4;71] [5;61] [6;81] [1;21] [2;11] [3;31]

################# ATENDENDO #######################

Log fila [elementos: 6] - Inicio: [4;71] [5;61] [6;81] [1;21] [2;11] [3;31]

Atendimento retornou true (7), id=4. [OK]

Log fila [elementos: 5] - Inicio: [5;61] [6;81] [1;21] [2;11] [3;31]

Atendimento retornou true (8), id=5. [OK]

Log fila [elementos: 4] - Inicio: [6;81] [1;21] [2;11] [3;31]

Atendimento retornou true (9), id=6. [OK]

Log fila [elementos: 3] - Inicio: [1;21] [2;11] [3;31]

Atendimento retornou true (10), id=1. [OK]

Log fila [elementos: 2] - Inicio: [2;11] [3;31]

Atendimento retornou true (11), id=2. [OK]

Log fila [elementos: 1] - Inicio: [3;31]

Atendimento retornou true (12), id=3. [OK]

Log fila [elementos: 0] - Inicio:

Atendimento retornou false (13). [OK]

Log fila [elementos: 0] - Inicio:

################# INSERINDO PARTE 2 ###############

Log fila [elementos: 0] - Inicio:

Insercao retornou true (14). [OK]

Log fila [elementos: 1] - Inicio: [7;72]

Insercao retornou true (15). [OK]

Log fila [elementos: 2] - Inicio: [7;72] [8;22]

Insercao retornou true (16). [OK]

Log fila [elementos: 3] - Inicio: [7;72] [9;60] [8;22]

Insercao retornou true (17). [OK]

Log fila [elementos: 4] - Inicio: [7;72] [9;60] [8;22] [10;42]

################# SAINDO DA FILA ##################

Log fila [elementos: 4] - Inicio: [7;72] [9;60] [8;22] [10;42]

Desistindo da fila retornou false (18). [OK]

Log fila [elementos: 4] - Inicio: [7;72] [9;60] [8;22] [10;42]

Desistindo da fila retornou true (19). [OK]

Log fila [elementos: 3] - Inicio: [9;60] [8;22] [10;42]

Desistindo da fila retornou true (20). [OK]

Log fila [elementos: 2] - Inicio: [9;60] [10;42]

Desistindo da fila retornou true (21). [OK]

Log fila [elementos: 1] - Inicio: [10;42]

Desistindo da fila retornou true (22). [OK]

Log fila [elementos: 0] - Inicio:
*/