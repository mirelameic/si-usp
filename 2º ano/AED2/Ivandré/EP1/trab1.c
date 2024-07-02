#include <stdio.h>
#include <stdlib.h>
//#include <conio.h>
#include <malloc.h>

int grupo(){
    return 17;
}

int nroUSP1(){
    return 11208392;
}

int nroUSP2(){
    return 10366884;
}

//--------------------------------------------------------------------------------------//
typedef struct adj{
    int custo;
    int v;
    struct adj* prox;
}NO;

typedef struct{
    NO* inicio;
    int tipo;
}VERTICE;

typedef struct fila{
    NO* inicio;
    NO* fim;
    int qtd;
}FILA;

NO* EncontrarCaminho(VERTICE* g, int V, int v1, int t);
void buscaLargura(VERTICE* grafo, int v1, int tipo, int* visitado, int V);
NO* computarCaminho(VERTICE* grafo, FILA* F, int via[], int vi, int vf, int dist);
int somarCustos(NO* no);
void iniciaFila(FILA* fila);
int insereFila(FILA* fila, int custo, int v);
NO* removeFila(FILA* fila);

void iniciaFila(FILA *fila){
    fila->fim = NULL;
    fila->inicio = NULL;
    fila->qtd = 0;
}

int insereFila(FILA* fila, int custo, int v){
    // fila nao existe
    if (fila == NULL) return 0;

    NO* elemento = (NO*) malloc(sizeof(NO));
    if (elemento == NULL) return 0;

    elemento->custo = custo;
    elemento->v = v;
    elemento->prox = NULL;

    // fila vazia
    if (fila->fim == NULL)
        fila->inicio = elemento;
    else
        fila->fim->prox = elemento;

    fila->fim = elemento;
    fila->qtd++;
    return 1;
}

NO* removeFila(FILA* fila){
    // fila nao existe
    if (fila == NULL)
        return NULL;

    // fila vazia
    if (fila->inicio == NULL)
        return NULL;

    NO* inicial = fila->inicio;
    fila->inicio = fila->inicio->prox;

    // fila ficou vazia
    if (fila->inicio == NULL)
        fila->fim = NULL;

    // free(inicial);
    fila->qtd--;
    return inicial;
}

void imprimeFila(FILA* fi){
    if (fi == NULL) return;
    NO* no = fi->inicio;
    while (no != NULL){
        printf("--- V%d (peso %d)", no->v, no->custo);
        no = no->prox;
    }
    printf("\n");
}

NO* computarCaminho(VERTICE* grafo, FILA* F, int via[], int vi, int vf, int dist){
    NO* lista;
    int distancia = dist;
    int caminho[dist + 1];

    int k;
    for (k = 0; k < dist + 1; k++)
        caminho[k] = 0;

    int i = vf;
    caminho[dist] = vf;

    //inverte o vetor
    while (distancia > 1){
        distancia--;
        if (via[i] == -1)
            break;
        caminho[distancia] = via[i];
        i = via[i];
        
    }
    //insere os vertices na fila
    insereFila(F, 0, caminho[0]);
    int j;
    for (j = 0; j<=dist; j++){
        lista = grafo[caminho[j]].inicio;
        
        while(lista){
            if (lista->v == caminho[j+1]){
                insereFila(F, lista->custo, lista->v);
                break;
            }else {
                lista = lista->prox;
            }
        }

        printf("\n");    
    }
    imprimeFila(F);
    printf("\n\n");
    return F->inicio;
}

int somarCustos(NO* no){
    int custo = 0;
    while(no){
        custo = custo + no->custo;
        no = no->prox;
    }
    return custo;
}

NO* buscaLargura(VERTICE* grafo, int V, int v1, int tipo){
    FILA F;
    FILA caminho;
    NO* listaAdj;
    NO* proxFila;
    int distancia[V];
    int via[V];
    int menorDist = -1;

    NO* primeiro = NULL;
    NO* empate;

    iniciaFila(&F);
    iniciaFila(&caminho);

    printf("---------> buscando um vértice de tipo %d, partindo de V%d\n\n", tipo, v1);

    // marca todos os vértices como não-visitados
    int i;
    for (i = 0; i < V; i++)
        distancia[i] = -1;
    // reseta vias
    for (i = 0; i < V; i++)
        via[i] = -1;

    // marca vértice inicial como visitado e insere na fila
    distancia[v1] = 0;
    insereFila(&F, 0, v1);

    // enquanto a fila nao estiver vazia
    while (F.inicio){
        // pega o próximo da fila
        proxFila = removeFila(&F);
        // pega a lista de adjacências do proximo
        listaAdj = grafo[proxFila->v].inicio;

        // enquanto houver adjacências
        while(listaAdj){
            // se adjacente não foi visitado
            if(distancia[listaAdj->v] == -1){
                // soma a distancia, adc caminho e insere na fila
                distancia[listaAdj->v] = distancia[proxFila->v] + 1;
                via[listaAdj->v] = proxFila->v;
                insereFila(&F, listaAdj->custo, listaAdj->v);

                // se o tipo for o mesmo da busca
                if(grafo[listaAdj->v].tipo == tipo){
                    printf("achou: V%d [tipo %d]\n", listaAdj->v, grafo[listaAdj->v].tipo);
                    printf("distancia de V%d: %d\n\n", v1, distancia[listaAdj->v]);
                    // se for o primeiro encontrado
                    if(menorDist == -1){
                        printf("É O PRIMEIRO\n\n");
                        menorDist = distancia[listaAdj->v];
                        primeiro = computarCaminho(grafo, &caminho, via, v1, listaAdj->v, menorDist);

                    // se for empate
                    }else if(menorDist == distancia[listaAdj->v]){
                        printf("DEU EMPATE\n");
                        FILA empatou;
                        iniciaFila(&empatou);
                        empate = computarCaminho(grafo, &empatou, via, v1, listaAdj->v, menorDist);

                        int c1 = somarCustos(primeiro);
                        int c2 = somarCustos(empate);
                        printf("custo do primeiro: %d, custo do empate: %d\n\n", c1, c2);
                        if(c1<c2) return primeiro;
                        return empate;
                    }
                }
            }
            listaAdj = listaAdj->prox;
        }
    }
    return primeiro;
}

NO* EncontrarCaminho(VERTICE* g, int V, int v1, int t){
    return buscaLargura(g, V, v1, t);
}

//---------------------------------------------------------
// use main() para fazer chamadas de teste ao seu programa
// mas nao entregue o codido de main() nem inclua nada
// abaixo deste ponto
//---------------------------------------------------------
VERTICE* inicializaGrafo(int V){
    // alocando memoria para o grafo com qtd V de vértices
    VERTICE* grafo = (VERTICE*) malloc(sizeof(VERTICE) * V);

    // setando inicio como NULL para todos os vértices
    int i;
    for (i = 0; i < V; i++)
    {
        grafo[i].inicio = NULL;
    }
    return grafo;
}

// inicializa o tipo do nó
void inicializaNo(VERTICE* grafo, int v, int tipo){
    grafo[v].tipo = tipo;
}

NO* inserirAdjacencia(int v2, int custo){
    NO* adj = (NO*) malloc(sizeof(NO));
    // vertice alvo da adj
    adj->v = v2;
    // custo da aresta
    adj->custo = custo;
    adj->prox = NULL;
    return adj;
}

void inserirAresta(VERTICE* grafo, int v1, int v2, int custo){
    // cria adj com vertice final e o custo da aresta
    NO* novo = inserirAdjacencia(v2, custo);
    // coloca adj na lista do vertice inicial
    // campo prox da lista de adj recebe o cabeça e cabeça recebe o novo
    novo->prox = grafo[v1].inicio;
    grafo[v1].inicio = novo;
}

void imprimeGrafo(VERTICE* grafo, int V){
    printf("\n--------------------GRAFO--------------------\n");
    int i;
    for (i = 0; i < V; i++){
        printf("VERTICE %d [tipo %d] ", i, grafo[i].tipo);
        NO* adj = grafo[i].inicio;
        while (adj){
            printf("--> V%d (peso %d) ", adj->v, adj->custo);
            adj = adj->prox;
        }
        printf("\n");
    }
    printf("\n");
}

int main(){
    VERTICE* grafo = inicializaGrafo(8);

    inicializaNo(grafo, 0, 0);
    inicializaNo(grafo, 1, 1);
    inicializaNo(grafo, 2, 2);
    inicializaNo(grafo, 3, 5);
    inicializaNo(grafo, 4, 4);
    inicializaNo(grafo, 5, 5);
    inicializaNo(grafo, 6, 4);
    inicializaNo(grafo, 7, 4);

    inserirAresta(grafo, 0, 1, 8);
    inserirAresta(grafo, 0, 2, 2);
    inserirAresta(grafo, 1, 2, 4);
    inserirAresta(grafo, 2, 0, 12);
    inserirAresta(grafo, 2, 3, 40);
    inserirAresta(grafo, 0, 6, 2);
    inserirAresta(grafo, 3, 1, 3);
    inserirAresta(grafo, 2, 5, 3);
    inserirAresta(grafo, 3, 4, 3);
    inserirAresta(grafo, 4, 3, 8);
    inserirAresta(grafo, 3, 5, 8);


    imprimeGrafo(grafo, 8);

    buscaLargura(grafo, 8, 0, 4);
    //buscaLargura(grafo, 8, 0, 5);

    NO* teste = EncontrarCaminho(grafo, 8, 0, 5);
    printf("TESTANDO EMPATE: \n");
    while(teste){
        
        printf("%d ", teste->v);
        teste = teste->prox;
    }
    printf("\n ");

    teste = EncontrarCaminho(grafo, 8, 0, 8);
    printf("TESTANDO VERT NAO ENCONTRADO: \n");
    while(teste){
        
        printf("%d ", teste->v);
        teste = teste->prox;
    }
    printf("\n ");


    VERTICE* grafo1 = inicializaGrafo(3);

    inicializaNo(grafo1, 0, 0);
    inicializaNo(grafo1, 1, 2);
    inicializaNo(grafo1, 2, 2);
    inserirAresta(grafo1, 0, 1, 8);
    inserirAresta(grafo1, 0, 2, 9);
    inserirAresta(grafo1, 1, 2, 2);
    EncontrarCaminho(grafo1, 3, 0, 2);
    imprimeGrafo(grafo1, 3);
}
