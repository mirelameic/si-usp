import java.util.ArrayList;

public class Estoque implements StrategyFiltro {

	private int qtd;
	
	public Estoque(int qtd) {
		this.qtd = qtd;
	}
	
	@Override
	public ArrayList<Produto> filtrar(ArrayList<Produto> listaProdutos) {
		ArrayList<Produto> listaFiltrada = new ArrayList<Produto>();
		
		listaProdutos.forEach(p -> {
			if(p.getQtdEstoque() <= qtd) listaFiltrada.add(p);
		});
		
		return listaFiltrada;
	}

}
