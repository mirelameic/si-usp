ep1_matriz_11208392.exe: grafo_matrizadj.o grafo_func.o ep1.o
	gcc -o ep1_matriz_11208392.exe grafo_matrizadj.o grafo_func.o ep1.o

ep1_lista_11208392.exe: grafo_listaadj.o grafo_func.o ep1.o
	gcc -o ep1_lista_11208392.exe grafo_listaadj.o grafo_func.o ep1.o

grafo_matrizadj.o: grafo_matrizadj.c grafo_matrizadj.h
	gcc -c grafo_matrizadj.c

grafo_listaadj.o: grafo_listaadj.c grafo_listaadj.h
	gcc -c grafo_listaadj.c

grafo_func.o: grafo_func.c grafo_matrizadj.h grafo_listaadj.h
	gcc -c grafo_func.c -o grafo_func.o

ep1.o: ep1.c grafo_func.h grafo_matrizadj.h grafo_listaadj.h
	gcc -c ep1.c

clean:
	rm -f *.o *.exe