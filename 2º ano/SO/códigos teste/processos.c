#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(void) {
    int i;
    pid_t pid;

    //Verificação se o fork deu certo
    if ((pid = fork()) < 0){
        perror("fork");
        exit(1);
    }
    if (pid == 0){
        //O código aqui dentro será executado no processo filho
        printf("Hello World do Filho\n");
    }
    else{
        //O código neste trecho será executado no processo pai
        printf("Hello World do Pai\n");
    }

    exit(0);
}