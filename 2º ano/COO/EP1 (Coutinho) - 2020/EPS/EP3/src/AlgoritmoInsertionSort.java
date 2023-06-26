public class AlgoritmoInsertionSort implements StrategyAlgoritmo {

	
	@Override
	public void ordenar(StrategyOrdenacao ordenacao, Produto[] produtos) {
		for(int i = 0; i < produtos.length; i++) {
			Produto x = produtos[i];
			int j = (i-1);
			
			while(j >= 0) {
				if(ordenacao.getPropriedade(x) instanceof String) {
					if(( (String) ordenacao.getPropriedade(x)).compareToIgnoreCase(( (String) ordenacao.getPropriedade(produtos[j]))) < 0) {
						produtos[j+1] = produtos[j];
						j--;
					} else {
						break;
					}
				} else {
					if((Double.valueOf(ordenacao.getPropriedade(x).toString())) < (Double.valueOf(ordenacao.getPropriedade(produtos[j]).toString()))) {
						produtos[j+1] = produtos[j];
						j--;
					} else {
						break;
					}
				}
			}
			produtos[j+1] = x;
		}
		if(ordenacao.ordemDecrescente() && produtos.length > 1) {
			for(int i = 0; i < produtos.length/2; i++) {
				Produto aux = produtos[i];
				produtos[i] = produtos[(produtos.length) - (i + 1)];
				produtos[(produtos.length) - (i + 1)] = aux;
			}
		}
	}
}
