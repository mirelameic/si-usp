#include "univesp2.h"
#include <stdio.h>
#include <stdlib.h>
#define true 1
#define false 0

//cria uma coleção de nós, sem arestas
GRAFO* criaGrafo(int v){
	//alocando memoria pro grafo e inicializando campos vertices/arestas
	GRAFO *g = (GRAFO *) malloc(sizeof(GRAFO));
	g->vertices = v;
	g->arestas = 0;
	//alocando memoria pro arranjo de adjacencias, com o numero de vertices
	g->adj = (VERTICE *) malloc(v*sizeof(VERTICE));
	
	//null em todos os ponteiros (grafo sem arestas)
	int i;
	for (i=0; i<v; i++){
	g->adj[i].cab = NULL;
	return(g);
	}
}

//adicionando arestas
ADJACENCIA *criaAdj(int v, int peso){
	//alocando memória pra um nó
	ADJACENCIA *temp = (ADJACENCIA *) malloc(sizeof(ADJACENCIA));
	//vértice alvo e peso da aresta, prox será null
	temp->vertice = v;
	temp->peso = peso;
	temp-> prox = NULL;
	return (temp);
}

//cria uma aresta indo de vi até vf
//PARA CRIAR UMA ARESTA NAO DIRIGIDA, CHAMA-SE 2X A FUNCAO
bool criaAresta(GRAFO *gr, int vi, int vf, TIPOPESO p){
	//grafo null
	if(!gr) return false;
	//nó final menor doq 0 ou maior igual n vértices
	if(vf<0 || vf>=gr->vertices) return false;
	//nó inicial menor doq 0 ou maior igual n vértices
	if(vi<0 || vi>=gr->vertices) return false;
	//adjacencia recebe vertice final e o peso
	ADJACENCIA *novo = criaAdj(vf, p);
	//coloca adj na lista do vertice inicial
	novo->prox = gr->adj[vi].cab;
	gr->adj[vi].cab = novo;
	gr->arestas++;
	return true;
}


void imprime(GRAFO *gr){
	printf("Vértices: %d. Arestas: %d. \n", gr->vertices, gr->arestas);
	int i;
	for(i=0; i<gr->vertices; i++){
		printf("v%d: ", i);
		ADJACENCIA *ad = gr->adj[i].cab;
		while(ad){
			printf("v%d(%d) ", ad->vertice, ad->peso);
			ad = ad->prox;
		}
		printf("\n");
	}
}

int main(void){
	GRAFO* gr = criaGrafo(5);
	criaAresta(gr, 0, 1, 2);
	criaAresta(gr, 1, 2, 4);
	criaAresta(gr, 2, 0, 12);
	criaAresta(gr, 2, 4, 40);
	criaAresta(gr, 3, 1, 3);
	criaAresta(gr, 4, 3, 8);
	
	imprime(gr);
}














