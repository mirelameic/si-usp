public class DescricaoCresc implements StrategyOrdenacao {

	@Override
	public Object getPropriedade(Produto p) {
		return p.getDescricao();
	}

	@Override
	public boolean ordemDecrescente() {
		return false;
	}
}
