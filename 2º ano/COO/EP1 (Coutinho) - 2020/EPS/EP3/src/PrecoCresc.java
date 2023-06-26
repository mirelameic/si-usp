public class PrecoCresc implements StrategyOrdenacao {

	@Override
	public Object getPropriedade(Produto p) {
		return p.getPreco();
	}

	@Override
	public boolean ordemDecrescente() {
		return false;
	}
}
