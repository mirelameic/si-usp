//#include "grafo_matrizadj.h"
#include "grafo_listaadj.h"

#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

void findWeight(Graph* graph, int src, int dest, float* maxPath);
void findWeightDFS(Graph* graph, int currentVertex, int dest, bool* visited, float* minWeight, float* maxPath);