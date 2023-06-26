import java.util.ArrayList;

public class BuscaDesc implements StrategyFiltro {

	private String palavra_buscada;
	
	
	public BuscaDesc(String palavra_buscada) {
		this.palavra_buscada = palavra_buscada;
	}

	@Override
	public ArrayList<Produto> filtrar(ArrayList<Produto> listaProdutos) {
		ArrayList<Produto> listaFiltrada = new ArrayList<Produto>();
		
		listaProdutos.forEach(p -> {
			if(p.getDescricao().contains(palavra_buscada)) listaFiltrada.add(p);
		});
		
		return listaFiltrada;
	}
}
