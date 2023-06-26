#include <stdio.h>
#include <stdlib.h>
#include "buscaProf.c"


//1. Escreva um algoritmo para contar a quantidade de laços em um grafo.
void contaLacos(VERTICE* g, int V){
    int resp = 0;
    for (int i = 0; i < V; i++){
        ADJACENCIA* p = g[i].inicio;
        while (p){
            if (i == p->verticeDestino) resp++;
            p = p->prox;
        }
    }
    printf("quantidade de laços: %i \n", resp);
}

//2. Remover todos os laços encontrados.
void excluiLacos(VERTICE *g, int V){
    int resp = 0;
    for (int i = 0; i < V; i++){
        ADJACENCIA* p = g[i].inicio;
        while (p){
            if (i == p->verticeDestino){
                excluiAresta(g, i, p->verticeDestino);
                printf("excluindo laço do V%i - %i \n", i, p->verticeDestino);
                resp++;
            }
            p = p->prox;
        }
    }
    printf("laços excluídos: %i \n", resp);
}

int main(){
    VERTICE* grafo = inicializaGrafo(6);
    insereAresta(grafo, 0, 2);
    insereAresta(grafo, 0, 3);
    insereAresta(grafo, 0, 0);
    insereAresta(grafo, 1, 2);
    insereAresta(grafo, 1, 3);
    insereAresta(grafo, 2, 2);
    insereAresta(grafo, 2, 3);
    insereAresta(grafo, 2, 5);
    insereAresta(grafo, 3, 3);
    insereAresta(grafo, 4, 4);
    insereAresta(grafo, 4, 5);
    insereAresta(grafo, 4, 1);
    insereAresta(grafo, 5, 0);
    insereAresta(grafo, 5, 1);
    insereAresta(grafo, 5, 2);
    
    imprimeGrafo(grafo, 6);
    contaLacos(grafo, 6);
    excluiLacos(grafo, 6);
    imprimeGrafo(grafo, 6);
}