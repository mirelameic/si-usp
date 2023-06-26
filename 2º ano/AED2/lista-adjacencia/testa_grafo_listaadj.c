#include "grafo_listaadj.c"

int main(){
    Grafo g1;
    int numVertices;

    inicializaGrafo(&g1, 10);
    insereAresta(&g1, 1, 2, 10);
    insereAresta(&g1, 1, 3, 5);
    insereAresta(&g1, 4, 3, 5);
    insereAresta(&g1, 3, 1, 5);
    imprimeGrafo(&g1);
    
    return 0;
}