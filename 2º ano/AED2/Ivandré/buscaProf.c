/*
DFS: Depth-first Search
(buscar o mais fundo possível - estrutura resultante é uma árvore)

1. Define um nó inicial
2. Enquanto este nao for um nó objetivo ou final
	Escolha um de seus adjacentes ainda nao visitados
	Visite-o
2. Se o nó final for não objetivo
	Volte ao pai deste
	Se houver pai, repita. Senao escolha outro nó inicial

Flags:
0: nao visitado
1: descoberto
2: concluido

Pior caso (lista de adjacência):
    O(|V| + |A|)

Pior caso (matriz):
    O(V²)


*/

#include <stdio.h>
#include <stdlib.h>

#define true 1
#define false 0
#define bool int

#define NAO_VISITADO 0
#define DESCOBERTO 1
#define CONCLUIDO 2

//vertice de destino, peso e proximo
typedef struct adjacencia{
    int verticeDestino;
    int peso;
    struct adjacencia* prox;
}ADJACENCIA;

//cabeça da lista de adjacências (aresta) e outras informações
typedef struct vertice{
    int flag;
    int info;
    ADJACENCIA* inicio;
}VERTICE;

/*
//numero de vertices/arestas e o arranjo de vertices
typedef struct grafo{
    int vertices, arestas;
    VERTICE* adj;
}GRAFO;
*/

VERTICE* inicializaGrafo(int V){
    //alocando memoria para o grafo com qtd V de vértices
    VERTICE* grafo = (VERTICE*) malloc(sizeof(VERTICE)*V);

    //setando inicio como NULL para todos os vértices
    int i;
    for(i=0; i<V; i++) grafo[i].inicio = NULL;
    return grafo;
}

//recebe um grafo, i=origem, j=destino, anterior=ponteiro pra ponteiro do anterior
ADJACENCIA* buscaAresta(VERTICE* g, int i, int j, ADJACENCIA** anterior){
    *anterior = NULL;
    ADJACENCIA* p = g[i].inicio;
    //enquanto existir proximos
    while(p){
        //se p for igual ao destino, retorne p
        if(p->verticeDestino == j) return p;
        //senao, anterior recebe p e p recebe prox
        *anterior = p;
        p = p->prox;
    }
}

//para grafos dirigidos
bool insereAresta(VERTICE* g, int i, int j){
    ADJACENCIA* anterior;

    //busca se a aresta ja existe, e retorna false caso encontre
    ADJACENCIA* atual = buscaAresta(g, i, j, &anterior);
    if(atual) return false;

    atual = (ADJACENCIA*) malloc(sizeof(ADJACENCIA));
    atual->verticeDestino = j;
    atual->prox = g[i].inicio;
    //insere na frente da lista
    g[i]. inicio = atual;
}

bool excluiAresta(VERTICE *g, int i, int j){
    ADJACENCIA *ant;
    ADJACENCIA *atual = buscaAresta(g, i, j, &ant);
    if (!atual) return false;
    if (ant)
        ant->prox = atual->prox;
    else
        g[i].inicio = atual->prox;
    free(atual);
    return true;
}

void imprimeGrafo(VERTICE* grafo, int V){
    int i;
    for (i=0; i<V; i++){
        printf("V%d ", i);
        ADJACENCIA* p = grafo[i].inicio;
        while (p){
            printf("- %i ", p->verticeDestino);
            p = p->prox;
        }
        printf("\n");
    }
}

//lista ligada, i = origem da busca
void buscaProfundidade(VERTICE* g, int i){
    g[i].flag = DESCOBERTO;
    ADJACENCIA* p = g[i].inicio;

    while(p){
        if(g[p->verticeDestino].flag == NAO_VISITADO) buscaProfundidade(g, p->verticeDestino); //percurso

        p = p->prox; //vizinho adjacente
    }
    g[i].flag = CONCLUIDO;
}


/*int main(){
    VERTICE* grafo = inicializaGrafo(6);
    insereAresta(grafo, 0, 2);
    insereAresta(grafo, 0, 3);
    insereAresta(grafo, 0, 0);
    insereAresta(grafo, 1, 2);
    insereAresta(grafo, 1, 3);
    insereAresta(grafo, 2, 2);
    insereAresta(grafo, 2, 3);
    insereAresta(grafo, 3, 3);
    insereAresta(grafo, 4, 4);
    insereAresta(grafo, 5, 0);
    insereAresta(grafo, 5, 1);
    insereAresta(grafo, 5, 2);
    
    imprimeGrafo(grafo, 6);
    
    return 0;
}
*/

