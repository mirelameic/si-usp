// MIRELA MEI (11208392)

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class MergeSort{
    static void merge(int arr[], int l, int m, int r){ 

        int n1 = m - l + 1; 
        int n2 = r - m; 

        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        for (int i=0; i<n1; ++i) L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) R[j] = arr[m + 1+ j]; 
  
        int i = 0, j = 0; 
        int k = l;

        while(i<n1 && j<n2){ 
            if(L[i] <= R[j]){ 
                arr[k] = L[i]; 
                i++; 
            }else{ 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
        while(i<n1){ 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
        while(j<n2){ 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
    static void sort(int arr[], int l, int r){ 
        if(l<r){
            int m = (l+r)/2; 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
            merge(arr, l, m, r); 
        } 
    } 
  
    static void printArray(int arr[]){ 
        int n = arr.length; 
        for (int i=0; i<n; ++i) System.out.println(arr[i] + " "); 
        System.out.println(); 
    }   
}


public class Epiaa{
  public static void main(String[] args) throws IOException{
	String nome = "1K_Array_";
	String[] arqTXT = new String[1000];
	int x = 0;
	int cont = 1;
	int contArq = 0;
    String txt = "";
    
	while(cont<=250){
			txt = "C:\\Users\\Mirel\\Desktop\\ARRAY\\"  + nome + cont + ".txt";
				
		try{
			long tempoCargaInicial = System.nanoTime();
			
			FileReader arq = new FileReader(txt);
			BufferedReader lerArq = new BufferedReader(arq);
 
			int tam = Integer.parseInt(lerArq.readLine());
			int[] array = new int[tam]; 	
			
			
			for (int i=0; i<tam; i++){
				int linha = Integer.parseInt(lerArq.readLine());
				array [i] = linha;
			}
			
			long tempoCargaFinal = System.nanoTime();
			long tempoCarga = tempoCargaFinal - tempoCargaInicial;

			long inicioOrd = System.nanoTime();
			MergeSort.sort(array, 0, tam-1);
			long fimOrd = System.nanoTime();
			long tempoOrd = fimOrd - inicioOrd;
			
			
			arqTXT [contArq] = nome + cont + " " + tam + " " + tempoCarga + " " + tempoOrd + " NP350XAA-XF4BR " + "merge_sort " + "Java/8 " + "Windows_10 " + "64_bit " + "nao_ordenado " + "11208392";
			
			System.out.println(arqTXT[contArq]);
			
			if (cont==250){
				x++;
				if(x==1){
					cont = 0;
					nome = "10K_Array_";	
				} else if(x==2){
					cont = 0;
					nome = "100K_Array_";
				}else if(x==3){
					cont = 0;
					nome = "1M_Array_";
				}else{
					break;
				}
			}			
			cont ++;
			contArq++;

		}catch(IOException e){
			System.out.println("Erro ao abrir: ");
			System.out.println(txt);
			break;
		}
	}
  }
}