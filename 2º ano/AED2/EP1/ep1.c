/*
    Mirela Mei - 11208392
    https://github.com/mirelameic/cargoheight-optimization
*/

#include "grafo_func.h"
#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Uso: %s arquivo_entrada\n", argv[0]);
        return 1;
    }

    FILE* inputFile = fopen(argv[1], "r");
    if (!inputFile) {
        printf("Não foi possível abrir o arquivo de entrada.\n");
        return 1;
    }

    int numVertices, numEdges, numQueries;
    if(fscanf(inputFile, "%d %d %d", &numVertices, &numEdges, &numQueries) != 3){
        printf("Entrada incorreta.\n");
        return 1;
    }

    Graph* graph = createGraph(numVertices);
    int src, dest;
    float height;
    for (int i = 0; i < numEdges; i++) {
        if(fscanf(inputFile, "%d %d %f", &src, &dest, &height) != 3 || src>dest){
            printf("Entrada incorreta.\n");
            return 1;
        }
        addEdge(graph, src, dest, height);
    }

    FILE* outputFile = fopen("saida.txt", "w");
    for (int i = 0; i < numQueries; i++) {
        int src, dest;
        if(fscanf(inputFile, "%d %d", &src, &dest) != 2){
            printf("Entrada incorreta.\n");
            return 1;
        };
        float minPath;
        findWeight(graph, src, dest, &minPath);
        fprintf(outputFile, "%.1f\n", minPath);
    }


    fclose(inputFile);
    fclose(outputFile);

    return 0;
}
