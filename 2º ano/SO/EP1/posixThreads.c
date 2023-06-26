#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#define NUM_THREADS 15 //definindo o numero de threads

void *HelloWorld(void *threadid){ //função que a thread irá executar
   long numeroThread = (long)threadid;
   printf("Hello, World! Thread #%ld\n", numeroThread); //printa Hello World e o numero da thread
   pthread_exit(NULL); //termina a chamada da thread
}

int main(){
   pthread_t threads[NUM_THREADS];
   int erro; //variável para um possível erro na criação da thread
   long t; //usado no loop
   for(t=0; t<NUM_THREADS+1; t++){
      erro = pthread_create(&threads[t], NULL, HelloWorld, (void *)t); //cria threads e guarda o retorno
      pthread_join(*threads, NULL); //faz as threads esperarem a execução das outras
      if (erro){ //caso de erro, printa na tela 
         printf("Erro na criação da thread...");
         exit(-1);
      }
   }
   pthread_exit(NULL); //finaliza todas as threads
}