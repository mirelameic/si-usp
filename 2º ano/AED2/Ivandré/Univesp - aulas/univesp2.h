//Representação por lista de adjacência
/* 
Buscas são mais rápidas em listas de adjacência, pois, dado um nó, já tem-se uma lista de seus vizinhos. Na matriz, há necessidade de varrer as linhas.
Testar a existência de vértice entre dois nós é mais rápido com matrizes. É necessário varrer a lista de adjacência até achar o vértice.
Para encontrar os predecessores com matriz, basta olhar a coluna correspondente ao nó. Na lista, deve-se varrer todas para achar quais chegam no nó.

Matriz de adj: grafos densos. testes de arestas, identificação de predecessores etc.
Listas de adj: grafos esparsos. operações que tenham como base um caminho de um vértice ao outro.
*/

#include <stdio.h>
#include <stdlib.h>
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
