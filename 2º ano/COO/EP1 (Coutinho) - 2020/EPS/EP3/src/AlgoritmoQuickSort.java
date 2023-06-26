public class AlgoritmoQuickSort implements StrategyAlgoritmo {

	@Override
	public void ordenar(StrategyOrdenacao ordenacao, Produto[] produtos) {
		recursaoQuickSort(0, produtos.length - 1, ordenacao, produtos);
		
		if(ordenacao.ordemDecrescente() && produtos.length > 1) {
			for(int i = 0; i < produtos.length/2; i++) {
				Produto aux = produtos[i];
				produtos[i] = produtos[(produtos.length) - (i + 1)];
				produtos[(produtos.length) - (i + 1)] = aux;
			}
		}
	}
	
	public int particiona(int ini, int fim, StrategyOrdenacao ordenacao, Produto[] produtos) {
		Produto x = produtos[ini];
		int i = (ini - 1);
		int j = (fim + 1);
		
		while(true) {
			if(ordenacao.getPropriedade(x) instanceof String) {
				do{ 
					j--;
				} while(ordenacao.getPropriedade(produtos[j]).toString().compareToIgnoreCase((String) ordenacao.getPropriedade(x)) > 0);
				
				do {
					i++;
				} while(ordenacao.getPropriedade(produtos[i]).toString().compareToIgnoreCase((String) ordenacao.getPropriedade(x)) > 0);
			} else {
				do {
					j--;
				} while (Double.valueOf(ordenacao.getPropriedade(produtos[j]).toString()) > Double.valueOf(ordenacao.getPropriedade(x).toString())); 
				
				do {
					i++;
				} while (Double.valueOf(ordenacao.getPropriedade(produtos[i]).toString()) < Double.valueOf(ordenacao.getPropriedade(x).toString()));
			}
			
			if(i < j) {
				Produto aux = produtos[i];
				produtos[i] = produtos[j];
				produtos[j] = aux;
			} else {
				return j;
			}
		}
	}
	
	public void recursaoQuickSort(int ini, int fim, StrategyOrdenacao ordenacao, Produto[] produtos) {
		if(ini < fim) {
			int q = particiona(ini, fim, ordenacao, produtos);
			recursaoQuickSort(ini, q, ordenacao, produtos);
			recursaoQuickSort(q+1, fim, ordenacao, produtos);
		}
	}
}
