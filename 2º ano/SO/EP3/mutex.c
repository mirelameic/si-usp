#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

int saldo = 1;
//inicializa o mutex
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

//função que deposita 1 real
void *deposita(void *str){
  while(saldo<100){
    //lock no mutex
    pthread_mutex_lock(&mutex);
    //seção crítica
    saldo = saldo + 1;
    printf("Saldo (depositando) = $%d,00 \n", saldo);
    //unlock no mutex
    pthread_mutex_unlock(&mutex);
  }
  pthread_exit(NULL);
}

//função que retira 1 real
void *retira(void *str){
  while(saldo>0){
    //lock no mutex
    pthread_mutex_lock(&mutex);
    //seção crítica
    saldo = saldo - 1;
    printf("Saldo (retirando) = $%d,00 \n", saldo);
    //unlock no mutex
    pthread_mutex_unlock(&mutex);
  }
  pthread_exit(NULL);
}

int main(){
  pthread_t idp0, idp1;
  //criando as threads
  pthread_create(&idp0, NULL, deposita, NULL);
  pthread_create(&idp1, NULL, retira, NULL);
  //finalizando
  pthread_exit(NULL);
  return 0;
}