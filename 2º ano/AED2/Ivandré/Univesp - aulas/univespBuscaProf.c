//Busca em Profundidade: as arestas são exploradas a partir do vertice v mais recentemente descoberto, que ainda tem arestas nao exploradas saindo dele.
//quando todas as arestas de v sao exploradas, a busca volta ao vertice anterior a v (backtracking) para seguir as arestas ainda nao exploradas.
/*
1. Define um nó inicial
2. Enquanto este nao for um nó objetivo ou final
	Escolha um de seus adjacentes ainda nao visitados
	Visite-o
2. Se o nó final for não objetivo
	Volte ao pai deste
	Se houver pai, repita. Senao escolha outro nó inicial
*/

 #include <stdio.h>
 #include <stdlib.h>
 #define true 1
 #define false 0
 
 typedef int bool;
 typedef int TIPOPESO;
 
 #define BRANCO 0
 #define AMARELO 1
 #define VERMELHO 2
 
 typedef struct adjacencia{
 	int vertice;
 	TIPOPESO peso;
 	struct adjacencia *prox;
 }ADJACENCIA;
 
 typedef struct vertice{
 	ADJACENCIA *cab;
 }VERTICE;
 
 typedef struct grafo{
 	int vertice;
 	int arestas;
 	VERTICE *adj;
 }GRAFO;
 
 void visitaP(GRAFO *g, int u, int *cor){
 	//ao visitar um nó, marca como amarelo
 	cor[u] = AMARELO;
 	//visita-se suas adjacencias recursivamente
 	ADJACENCIA *v = g->adj[u].cab;
 	//enquanto houver adjacencias
 	while(v){
 		//se a cor for branco, visitar
 		if (cor[v->vertice]==BRANCO) visitaP(g, v->vertice, cor);
 		//vai pra proxima adjacencia do pai
 		v = v->prox;
 	}
 	//marca ele como vermelho
 	cor[u] = VERMELHO;
 }
 
 void profundidade(GRAFO *g){
 	//indice do vertice no arranjo adj em GRAFO sera o mesmo do arranjo cor
 	int cor[g->vertices];
 	
 	int u;
 	//inicializa todos os vertices com branco
 	for(u=0; u<g->vertices; u++){
 		cor[u] = BRANCO;
 	}
 	//para cada um dos vertices brancos, visita em profundidade
 	for(u=0; u<g->vertices; u++){
 		if(cor[u] == BRANCO) visitaP(g, u, cor);
 	}
 }
