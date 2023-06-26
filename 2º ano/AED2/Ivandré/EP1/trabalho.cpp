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

void iniciaFila(FILA* fila){
    fila->fim = NULL;
    fila->inicio = NULL;
    fila->qtd = 0;
}

int insereFila(FILA* fila, int custo, int v){
    // fila nao existe
    if(fila == NULL) return 0;

    NO* elemento = (NO*) malloc(sizeof(NO));
    if(elemento == NULL) return 0;

    elemento->custo = custo;
    elemento->v = v;
    elemento->prox = NULL;

    // fila vazia
    if(fila->fim == NULL)
        fila->inicio = elemento;
    else
        fila->fim->prox = elemento;

    fila->fim = elemento;
    fila->qtd++;
    return 1;
}

NO* removeFila(FILA* fila){
    // fila nao existe
    if(fila == NULL)
        return NULL;

    // fila vazia
    if(fila->inicio == NULL)
        return NULL;

    NO* inicial = fila->inicio;
    fila->inicio = fila->inicio->prox;

    // fila ficou vazia
    if(fila->inicio == NULL)
        fila->fim = NULL;

    fila->qtd--;
    return inicial;
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
    while(distancia > 1){
        distancia--;
        if(via[i] == -1)
            break;
        caminho[distancia] = via[i];
        i = via[i];
        
    }
    //insere os vertices na fila
    insereFila(F, 0, caminho[0]);
    int j;
    for(j = 0; j<=dist; j++){
        lista = grafo[caminho[j]].inicio;
        
        while(lista){
            if(lista->v == caminho[j+1]){
                insereFila(F, lista->custo, lista->v);
                break;
            }else{
                lista = lista->prox;
            }
        }  
    }
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

    // marca todos os vértices como não-visitados
    int i;
    for(i = 0; i < V; i++)
        distancia[i] = -1;
    // reseta vias
    for(i = 0; i < V; i++)
        via[i] = -1;

    // marca vértice inicial como visitado e insere na fila
    distancia[v1] = 0;
    insereFila(&F, 0, v1);

    // enquanto a fila nao estiver vazia
    while(F.inicio){
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
                    // se for o primeiro encontrado
                    if(menorDist == -1){
                        menorDist = distancia[listaAdj->v];
                        primeiro = computarCaminho(grafo, &caminho, via, v1, listaAdj->v, menorDist);

                    // se for empate
                    }else if(menorDist == distancia[listaAdj->v]){
                        FILA empatou;
                        iniciaFila(&empatou);
                        empate = computarCaminho(grafo, &empatou, via, v1, listaAdj->v, menorDist);

                        int c1 = somarCustos(primeiro);
                        int c2 = somarCustos(empate);
                    
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
int main(){

}

