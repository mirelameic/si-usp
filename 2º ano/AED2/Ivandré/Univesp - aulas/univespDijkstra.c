/*
Algoritmo de Dijkstra:
calcula o caminho mais curto, em termos do peso total das arestas, entre um nó inicial e todos os demais nós do grafo.
Para cada vértice v do grafo, tem-se um atributo d[v] que é um limite superior para o peso do caminho mais curto do nó inicial s a v. d[v] é uma estimativa do caminho mais curto, incialmente começa com infinito.
Também armazena-se o vértice que precede v(p[v] - precedente de v) no caminho mais curto de s a v.

obs.: os pesos das arestas não podem ser negativo e a implementação de existeAberto e menorDist é crucial pra velocidade do algoritmo (o que está aqui não é o melhor)

1. Faça a estimativa de distância de s a qualquer vértice ser infinita.
	Exceto a distancia de s a s, que é 0
	Ou seja, d[s] = 0 e d[v] = infinito para todo v!=s
2. Faça os precedentes dos nós serem um valor qualquer
	p[v] = -1
3. Marque todos os vértices como abertos
4. Enquanto houver vértice aberto
	Escolha o vértice aberto u cuja estimativa seja a menor dentre os demais
	Feche u
	Para todo nó aberto v na adjacência de u
		Some d[u] ao peso da aresta (u, v)
		Caso a soma seja menor que d[v], atualize d[v] e faça p[v] = u
*/
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#define true 1
#define false 0
typedef int bool;
typedef int TIPOPESO;

typedef struct adjacencia{
	int vertice; //vertice em que chega
	TIPOPESO peso;
	struct adjacencia *prox; //proximo da lista
}ADJACENCIA;


typedef struct vertice{
	//dados armazenados vão aqui
	ADJACENCIA *cab; //lista dos vertices em que ele chega
}VERTICE;

typedef struct grafo{
	int vertices;
	int arestas;
	VERTICE *adj; //arranjo de vertices
}GRAFO;


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


void inicializaD(GRAFO *g, int *d, int *p, int s){
	int v;
	for(v=0; v<g->vertices; v++){
		d[v] = INT_MAX/2;
		p[v] = -1;
	}
	d[s] = 0;
}

void relaxa(GRAFO *g, int *d, int *p, int u, int v){
	ADJACENCIA *ad = g->adj[u].cab;
	while(ad && ad->vertice != v) ad = ad->prox;
	
	if(ad){
		if(d[v] > d[u] + ad->peso){
			d[v] = d[u] + ad->peso;
			p[v] = u;
		}
	}
}

bool existeAberto(GRAFO* g, int* aberto){
	int i;
	for(i=0; i<g->vertices; i++)
		if (aberto[i]) return true;
	return false;
}

int menorDist(GRAFO* g, int* aberto, int* d){
	int i;
	for(i=0; i<g->vertices; i++)
		if(aberto[i]) break;
		
	if(i==g->vertices) return -1;
	
	int menor = i;
	for(i = menor+1; i<g->vertices; i++)
		if(aberto[i] && d[menor]>d[i]) menor = i;
	return menor;
}

int* dijkstra(GRAFO* g, int s){
	int *d = (int *) malloc(g->vertices*sizeof(int));
	int p[g->vertices];
	bool aberto[g->vertices];
	inicializaD(g, d, p, s);
	
	int i;
	for (i=0; i<g->vertices; i++) aberto[i] = true;
	
	while(existeAberto(g, aberto)){
		int u = menorDist(g, aberto, d);
		aberto[u] = false;
		ADJACENCIA *ad = g->adj[u].cab;
		while(ad){
			relaxa(g, d, p, u, ad->vertice);
			ad = ad->prox;
		}
	}	
	
	return d;
}

int main(void){
	GRAFO* gr = criaGrafo(6);
	
	criaAresta(gr, 0, 1, 10);
	criaAresta(gr, 0, 2, 5);
	criaAresta(gr, 2, 1, 3);
	criaAresta(gr, 1, 3, 1);
	criaAresta(gr, 2, 3, 8);
	criaAresta(gr, 2, 4, 2);
	criaAresta(gr, 4, 5, 6);
	criaAresta(gr, 3, 5, 4);
	criaAresta(gr, 3, 4, 4);
	
	int* r = dijkstra(gr, 0);
	
	int i;
	for(i=0; i<gr->vertices; i++)
		printf("D(v0 -> v%d) = %d\n", i, r[i]);
}







