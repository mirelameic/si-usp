#include <stdbool.h>

#define AN -1 //aresta nula
#define VERTICE_INVALIDO -1

typedef int Peso;
typedef struct str_aresta{
    int vdest;
    Peso peso;
    struct str_aresta* prox;
}Aresta;

typedef Aresta* Apontador;

typedef struct{
    Apontador* listaAdj;
    int numVertices;
    int numArestas;
}Grafo;