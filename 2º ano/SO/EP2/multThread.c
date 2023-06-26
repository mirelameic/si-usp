#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>

int count = 0; int vez = 0; //variáveis globais inicializadas

//função que a thread P0 irá executar
void *EsperaOciosaP0(void *threadid){
   int meuID = 0; int outroID = 1;
   long *id = (long*) threadid;

   while(count<20){
      while(vez != meuID){}//DoNothing
      //seção crítica
      count++;
      printf("Valor do count dentro da secao critica de P0: %d \n", count);
      vez = outroID;
      //seção não crítica
      printf("Thread P0 executando fora da secao critica. ID: %ld \n", *id);
    }
  pthread_exit(NULL);
}

//função que a thread P1 irá executar
void *EsperaOciosaP1(void *threadid){
   int meuID = 1; int outroID = 0;
   long *id = (long*) threadid;

   while(count<20){
      while(vez != meuID){}//DoNothing
      //seção crítica
      count++;
      printf("Valor do count dentro da secao critica de P1: %d \n", count);
      vez = outroID;
      //seção não crítica
      printf("Thread P1 executando fora da secao critica. ID: %ld \n", *id);
    }
  pthread_exit(NULL);
}

int main(){
  pthread_t idp0, idp1;
  //criando as threads
  pthread_create(&idp0, NULL, EsperaOciosaP0, (void*)&idp0);
  pthread_create(&idp1, NULL, EsperaOciosaP1, (void*)&idp1);
  //finaliza as threads
  pthread_exit(NULL);  
  return 0;
}