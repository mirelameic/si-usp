#include <stdio.h>
#include <stdlib.h>
#include <semaphore.h>
#include <pthread.h>
#include <unistd.h>
#define N 5
#define ESQUERDA (nfilosofo + (N - 1)) % N //vizinho da esquerda
#define DIREITA (nfilosofo + 1) % N //vizinho da direita
#define PENSANDO 0
#define FAMINTO 1
#define COMENDO 2

void *filosofo (void *num);
void pegaHashi(int);
void largaHashi(int);
void teste(int);

sem_t mutex;
sem_t semaforo[N];
int estado[N]; //pensando, faminto ou comendo
int nfilosofo[N] = {0, 1, 2, 3, 4};

int main(){
    int i;
    pthread_t thread_id[N]; //identificando a thread de cada filosofo
    sem_init (&mutex, 0, 1); //inicializa o semaforo binario
    for (i = 0; i < N; i++){
        sem_init(&semaforo[i], 0, 0); //inicializa os semaforos binarios para cada filosofo
    }
    for (i = 0; i < N; i++){
        pthread_create(&thread_id[i], NULL, filosofo, &nfilosofo[i]); //cria as threads
        printf("[?] Filosofo %d esta pensando\n", i + 1);
    }
    for (i = 0; i < N; i++){
        pthread_join(thread_id[i], NULL);
        return(0);
    }
}

void *filosofo(void*num){ //funcao que controla o filosofo
    for(int j = 0; j < 5; j++){
        int *i = num;
        sleep(1);
        pegaHashi(*i);
        sleep(1);
        largaHashi(*i);
    }
}

void pegaHashi(int nfilosofo){ //funcao que faz o filosofo pegar o hashi
    sem_wait(&mutex); //entra na regiao critica
    estado[nfilosofo] = FAMINTO;
    printf("[*] Filosofo %d esta faminto\n", nfilosofo + 1);
    teste(nfilosofo); //tenta pegar 2 hashis
    sem_post(&mutex); //sai da regiao critica
    sem_wait(&semaforo[nfilosofo]); //bloqueia se os hashis nao foram pegos
}

void largaHashi(int nfilosofo){ //funcao que faz o filosofo largar o hashi
    sem_wait(&mutex); //entra na regiao critica
    estado[nfilosofo] = PENSANDO;
    printf("[+] Filosofo %d largou os hashis %d e %d\n", nfilosofo + 1, ESQUERDA + 1, nfilosofo + 1);
    printf("[?] Filosofo %d esta pensando\n", nfilosofo + 1);
    teste(ESQUERDA); //verifica se o vizinho da esquerda pode comer agora
    teste(DIREITA); //verifica se o vizinho da direita pode comer agora
    sem_post(&mutex); //sai da regiao critica
}

void teste(int nfilosofo){ //funcao que testa se o hashi esta disponivel
    if(estado[nfilosofo] == FAMINTO && estado[ESQUERDA] != COMENDO && estado[DIREITA] != COMENDO){
        estado[nfilosofo] = COMENDO;
        printf("[-] Filosofo %d pegou os hashis %d e %d\n", nfilosofo + 1, ESQUERDA + 1, nfilosofo + 1);
        printf("[!] Filosofo %d esta comendo\n", nfilosofo + 1);
        sem_post(&semaforo[nfilosofo]);
    }
}