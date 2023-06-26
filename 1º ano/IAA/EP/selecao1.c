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
int selecao1(int array[], int i, int n);
void ordena(int array[], int n);
void mergeSort(int array[], int esq, int dir);
void merge(int array[], int esq, int med, int dir);
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
    int mediana = selecao1(array, size/2, size);
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

int selecao1(int array[], int i, int n){
    ordena(array, n);
    return array[i];
}

void ordena(int array[], int n){
    mergeSort(array, 0, n - 1);
}

void mergeSort(int array[], int esq, int dir){
    if (esq < dir) {
        int med = esq + (dir - esq) / 2;
        mergeSort(array, esq, med);
        mergeSort(array, med + 1, dir);
        merge(array, esq, med, dir);
    }
}

void merge(int array[], int esq, int med, int dir){
    int i, j, k;
    int n1 = med - esq + 1;
    int n2 = dir - med;
  
    int arrayEsq[n1], arrayDir[n2];
  
    for (i = 0; i < n1; i++)
        arrayEsq[i] = array[esq + i];
    for (j = 0; j < n2; j++)
        arrayDir[j] = array[med + 1 + j];
  
    i=0, j=0, k=esq;
    while (i < n1 && j < n2) {
        if (arrayEsq[i] <= arrayDir[j]) {
            array[k] = arrayEsq[i];
            i++;
        }
        else {
            array[k] = arrayDir[j];
            j++;
        }
        k++;
    }
  
    while (i < n1) {
        array[k] = arrayEsq[i];
        i++;
        k++;
    }
  
    while (j < n2) {
        array[k] = arrayDir[j];
        j++;
        k++;
    }
}

void print(int array[], int n){
    int i;
    for (i = 0; i<n; i++) printf("%d: %d ", i, array[i]);
    printf("\n\n");
}
