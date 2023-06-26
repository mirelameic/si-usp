#include "grafo_matrizadj.c"

int main(){
	Grafo g1;
	int numVertices;
	
	inicializaGrafo(&g1, 10);
	insereAresta(1, 1, 4, &g1);
	insereAresta(1, 2, 10, &g1);
	insereAresta(3, 4, 11, &g1);
	imprimeGrafo(&g1);
	
	return 0;

}
