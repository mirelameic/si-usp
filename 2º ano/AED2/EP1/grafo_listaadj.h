#include <stdbool.h>
#include <stdlib.h>
#include <stdio.h>

#define INFINITY 999999

typedef struct Node {
    int vertex;
    float weight;
    struct Node* next;
} Node;

typedef struct {
    int vertices;
    Node** adjLists;
} Graph;


Graph* createGraph(int vertices);
Node* createNode(int vertex, float weight);
void addEdge(Graph* graph, int src, int dest, float weight);
void printGraph(Graph* graph);
bool isConnected(Graph* graph, int src, int dest);
float getWeight(Graph* graph, int src, int dest);