#include <stdio.h>
#include "grafo_matrizadj.h"

// espaço Ω (V²)
// inicializaGrafo ----- O(V²)
// imprimeGrafo    ----- O(V²)
// insereAresta    ----- O(1)
// existeAresta    ----- O(1)
// obtemPesoAresta ----- O(1)
// removeAresta    ----- O(1)
// listaAdjVazia   ----- O(V)
// proxListaAdj    ----- O(V)

bool inicializaGrafo(Grafo* grafo, int nv);
void imprimeGrafo(Grafo* grafo);
void insereAresta(int v1, int v2, Peso peso, Grafo* grafo);
bool verificaValidadeVertice(int v, Grafo* grafo);
bool existeAresta(int v1, int v2, Grafo* grafo);
Peso obtemPesoAresta(int v1, int v2, Grafo* grafo);
bool listaAdjVazia (int v, Grafo* grafo);
int proxListaAdj(int v, Grafo* grafo, int atual);


bool inicializaGrafo(Grafo* grafo, int nv){
	int i, j;
	
	if (nv > MAXNUMVERTICES){
		fprintf(stderr, "ERRO, numero de vertices maior que o permitido \n");
	return false;
	}
	
	if(nv <= 0){
		fprintf(stderr, "ERRO, numero de vertices deve ser positivo \n");
		return false;
	}
	
	grafo->numVertices = nv;
	grafo->numArestas = 0;
	for(i=1; i<=grafo->numVertices; i++){
		for(j=1; j<=grafo->numVertices; j++){
			grafo->mat[i][j] = AN;
		}
	}
	return true;
}

void imprimeGrafo(Grafo* grafo){
    int i, j;
	printf("Matriz de adjacencia: \n");
    for (i=1; i<=grafo->numVertices; i++){
        for (j=1; j<=grafo->numVertices; j++){
            if (grafo->mat[i][j] != AN){
                printf("%d ", grafo->mat[i][j]);
            } else {
                printf("- ");
            }
        }
        printf("\n");
    }
}

void insereAresta(int v1, int v2, Peso peso, Grafo* grafo){
	if (!(verificaValidadeVertice(v1, grafo) && verificaValidadeVertice(v2, grafo))) return;
	grafo->mat[v1][v2] = peso;
	grafo->numArestas++;
}

bool verificaValidadeVertice(int v, Grafo* grafo){
	if (v > grafo->numVertices){
		fprintf(stderr, "ERRO, numero de vertices maior que o permitido \n");
	return false;
	}
	
	if(v <= 0){
		fprintf(stderr, "ERRO, numero de vertices deve ser positivo \n");
	return false;
	}
		
	return true;
}

bool existeAresta(int v1, int v2, Grafo* grafo){
    if (verificaValidadeVertice(v1, grafo) && verificaValidadeVertice(v2, grafo) && grafo->mat[v1][v2] != AN){
        return true;
    } else {
        return false;
    }
}

Peso obtemPesoAresta(int v1, int v2, Grafo* grafo) {
    if (!verificaValidadeVertice(v1, grafo) || !verificaValidadeVertice(v2, grafo)) {
        return AN;
    }
    return grafo->mat[v1][v2];
}

bool removeAresta(int v1, int v2, Peso* peso, Grafo* grafo){
	if (!(verificaValidadeVertice(v1, grafo) && verificaValidadeVertice(v2, grafo))) return false;

	if(grafo->mat[v1][v2] != AN){
		*peso = grafo->mat[v1][v2];
		grafo->mat[v1][v2] = AN;
		grafo->numArestas--;
		return true;
	}
	return false;
}

bool listaAdjVazia (int v, Grafo* grafo){
	if(!verificaValidadeVertice(v, grafo)) return true;

	int i;
	for(i=1; i<=grafo->numVertices; i++){
		if(grafo->mat[v][i] != AN) return false;
	}
	return true;
}

int proxListaAdj(int v, Grafo* grafo, int atual){
	if(!verificaValidadeVertice(v, grafo)) return VERTICE_INVALIDO;

	atual++;
	while((atual<=grafo->numVertices) && (grafo->mat[v][atual]==AN)) atual ++;

	if(atual>grafo->numVertices) return VERTICE_INVALIDO;

	return atual;
}


