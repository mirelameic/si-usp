#include<pthread.h>
#include<stdio.h>

int sum;
void *runner (void *param);

void *runner(void *param){
	int upper=atoi(param);
	int i;
	sum =0;
	for (i=1; i<=upper;i++)
	sum +=1;
	pthread_exit(0);
}

main(int argc, char *argv[]){
	pthread_t tid;
	pthread_attr_t attr;
	/* obtém atributos default */
	pthread_attr_init(&attr);
	/* cria a thread */
	pthread_create(&tid,&attr,runner,argv[1]);
	/* espera a thread acabar */
	pthread_join(tid,NULL);
	printf(“soma= %d\n”,sum);
}
