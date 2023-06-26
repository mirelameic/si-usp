import java.util.ArrayList;

public class TodosProdutos implements StrategyFiltro {

	@Override
	public ArrayList<Produto> filtrar(ArrayList<Produto> listaProdutos) {
		return listaProdutos;
	}
}
