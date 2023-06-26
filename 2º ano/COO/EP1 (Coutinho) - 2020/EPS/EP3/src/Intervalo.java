import java.util.ArrayList;

public class Intervalo implements StrategyFiltro {

	private int max;
	private int min;
	
	
	public Intervalo(int min, int max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public ArrayList<Produto> filtrar(ArrayList<Produto> listaProdutos) {
		ArrayList<Produto> listaFiltrada = new ArrayList<Produto>();
		
		listaProdutos.forEach(p -> {
			if(p.getPreco() >= min && p.getPreco() <= max) listaFiltrada.add(p);
		});
		
		return listaFiltrada;
	}
}
