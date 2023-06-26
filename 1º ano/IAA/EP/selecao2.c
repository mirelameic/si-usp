#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
/********************************************************************************
----------------------------------EP IAA-----------------------------------------
nome: Mirela Mei
número usp: 11208392
********************************************************************************/

void calcTime(int size);
float time_diff(struct timespec *start, struct timespec *end);
int selecao2(int array[], int i, int n, int menor, int maior);
int particao(int array[], int n, int menor, int maior);
void print(int array[], int n);


int main(){
    calcTime(10000);
    return 0;
}

void calcTime(int size){
    int* array = (int*) malloc(sizeof(int)*size);
    struct timespec start, end;
    double total;

    srand(time(NULL));
    
    int i;
    for(i = 0; i <=size; i++) array[i] = rand();
    
    clock_gettime(CLOCK_REALTIME, &start);
    int mediana = selecao2(array, size/2, size, 0, size - 1);
    clock_gettime(CLOCK_REALTIME, &end);

    /*printf("Mediana: %d \n\n", mediana);
    printf("Array: \n\n");
    print(array, size);*/

    total = time_diff(&start, &end);
    printf("Tamanho do array: %d \n\n", size);
    printf("Tempo de processamento: %0.10f segundos \n\n", total);

}

float time_diff(struct timespec *start, struct timespec *end){
    return (end->tv_sec - start->tv_sec) + 1e-9*(end->tv_nsec - start->tv_nsec);
}

void swap(int a, int b){
    int aux = a;
    a = b;
    b = aux;
}

int particao(int array[], int n, int menor, int maior){
    int pivo = array[maior];
    int i = menor - 1;
    for(int j = menor; j < maior; j++){
        if(array[j] <= pivo){
            i++;
            swap(array[i], array[j]);
        }
    }
    swap(array[i+1], array[maior]);
    return i+1;
}

int selecao2(int array[], int i, int n, int menor, int maior){
    int q = particao(array, n, menor, maior);
    if(n == 1){
        return array[0];
    }
    if(i - 1 < q){
        n=q-1;
        return selecao2(array, i, n, menor, q-1);
    }
    else if(q == i - 1){
        return array[q];
    }
    else{
        n=q-1;
        return selecao2(array, i, n, q+1, maior);
    }
}

void print(int array[], int n){
    int i;
    for (i = 0; i<n; i++) printf("%d: %d ", i, array[i]);
    printf("\n\n");
}

