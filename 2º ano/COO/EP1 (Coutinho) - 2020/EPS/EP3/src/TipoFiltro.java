import java.util.ArrayList;

public class TipoFiltro implements StrategyFiltro {

	String filtro;
	
	public TipoFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	@Override
	public ArrayList<Produto> filtrar(ArrayList<Produto> listaProdutos) {
		ArrayList<Produto> listaFiltrada = new ArrayList<Produto>();
		
		listaProdutos.forEach(p -> {
			if(p.getCategoria() == filtro) listaFiltrada.add(p);
		});
		
		return listaFiltrada;
	}
}
