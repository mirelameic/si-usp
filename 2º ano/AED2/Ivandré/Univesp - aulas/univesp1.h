//Representação por matriz

#include <stdio.h>
#include <stdlib.h>
#define true 1
#define false 0
typedef int bool;
typedef int TIPOPESO;

typedef struct grafo{
	int vertices;
	int arestas;
	TIPOPESO **adj;
}GRAFO;
