#include <stdio.h>
#include <stdlib.h>

#include "grafo_listaadj.h"

// espaço Ω (V²)
// inicializaGrafo ----- O(V)
// imprimeGrafo    ----- O(V+A)
// insereAresta    ----- O(1)
// existeAresta    ----- O(V)
// obtemPesoAresta ----- O(V)
// removeAresta    ----- O(V)
// listaAdjVazia   ----- O(1)
// proxListaAdj    ----- O(1)
// liberaGrafo     ----- O(V+A)

bool inicializaGrafo(Grafo* grafo, int nv);
void imprimeGrafo(Grafo* grafo);
bool insereAresta(Grafo* grafo, int v1, int v2, Peso peso);
bool existeAresta(int v1, int v2, Grafo* grafo);
Peso obtemPesoAresta(int v1, int v2, Grafo* grafo);
bool removeAresta(Grafo* grafo, int v1, int v2);
bool listaAdjVazia(int v, Grafo* grafo);
bool verificaValidadeVertice(int v, Grafo* grafo);
Apontador proxListaAdj(int v, Grafo* grafo, Apontador atual);
void liberaGrafo(Grafo* grafo);

bool inicializaGrafo(Grafo* grafo, int nv){
    int i;

    if(nv <= 0){
        fprintf(stderr, "ERRO, numero de vertices deve ser positivo");
        return false;
    }

    grafo->numVertices = nv;
    if(!(grafo->listaAdj = (Apontador*) calloc(nv+1, sizeof(Apontador)))){
        fprintf(stderr, "ERRO, falha na alocacao de memoria da funcao inicializaGrafo");
        return false;
    }
    grafo->numArestas = 0;
    return true;
}

void imprimeGrafo(Grafo* grafo){
    int i;
    Aresta* atual;

    printf("Lista de adjacencia:\n");
    for(i=1; i<=grafo->numVertices; i++){
        printf("%d: ", i);
        atual = grafo->listaAdj[i];
        while(atual){
            printf("[%d, peso: %d] ", atual->vdest, atual->peso);
            atual = atual->prox;
        }
        printf("\n");
    }
}

bool insereAresta(Grafo* grafo, int v1, int v2, Peso peso){
    if(grafo == NULL || v1 < 1 || v1 > grafo->numVertices || v2 < 1 || v2 > grafo->numVertices){
        fprintf(stderr, "ERRO: parametros invalidos para insereAresta");
        return false;
    }

    Aresta* novaAresta = (Aresta*) malloc(sizeof(Aresta));
    if(novaAresta == NULL){
        fprintf(stderr, "ERRO: falha na alocacao de memoria em insereAresta");
        return false;
    }

    novaAresta->vdest = v2;
    novaAresta->peso = peso;
    novaAresta->prox = grafo->listaAdj[v1];
    grafo->listaAdj[v1] = novaAresta;
    grafo->numArestas++;
    return true;
}

bool existeAresta(int v1, int v2, Grafo* grafo){
    Apontador q;
    if(!(verificaValidadeVertice(v1, grafo) && verificaValidadeVertice(v2, grafo))) return false;

    q = grafo->listaAdj[v1];
    while((q != NULL) && q->vdest != v2) q = q->prox;
    if(q != NULL) return true;
    return false;
}

Peso obtemPesoAresta(int v1, int v2, Grafo* grafo){
    Aresta* aresta;
    if (!verificaValidadeVertice(v1, grafo) || !verificaValidadeVertice(v2, grafo))
        return AN;

    aresta = grafo->listaAdj[v1];
    while (aresta != NULL){
        if (aresta->vdest == v2)
            return aresta->peso;
        aresta = aresta->prox;
    }
    return AN;
}


bool removeAresta(Grafo* grafo, int v1, int v2){
    if (v1 < 1 || v1 > grafo->numVertices || v2 < 1 || v2 > grafo->numVertices){
        fprintf(stderr, "ERRO, vertices invalidos\n");
        return false;
    }

    Aresta* anterior = NULL;
    Aresta* atual = grafo->listaAdj[v1];
    while (atual != NULL){
        // encontrou a aresta
        if (atual->vdest == v2){
            if (anterior == NULL){
                grafo->listaAdj[v1] = atual->prox;
            }else{
                anterior->prox = atual->prox;
            }
            free(atual);
            grafo->numArestas--;
            return true;
        }
        anterior = atual;
        atual = atual->prox;
    }
    // aresta não encontrada
    return false;
}

bool listaAdjVazia(int v, Grafo* grafo){
    if(!verificaValidadeVertice(v, grafo)) return false;
    return (grafo->listaAdj[v]==NULL);
}

bool verificaValidadeVertice(int v, Grafo* grafo){
    if (v < 1 || v > grafo->numVertices){
        fprintf(stderr, "ERRO: Vértice inválido\n");
        return false;
    }
    return true;
}

Apontador proxListaAdj(int v, Grafo* grafo, Apontador atual){
    if(atual == NULL){
        fprintf(stderr, "atual == NULL\n");
        return VERTICE_INVALIDO;
    }
    return (atual->prox);
}

void liberaGrafo(Grafo* grafo){
    int v;
    Apontador p;

    for(v=0; v<=grafo->numVertices; v++){
        while((p=grafo->listaAdj[v]) != NULL){
            grafo->listaAdj[v] = p->prox;
            p->prox = NULL;
            free(p);
        }
    }

    grafo->numVertices = 0;
    free(grafo->listaAdj);
    grafo->listaAdj = NULL;
}

