public class QtdEstoqueCresc implements StrategyOrdenacao {

	@Override
	public Object getPropriedade(Produto p) {
		return p.getQtdEstoque();
	}

	@Override
	public boolean ordemDecrescente() {
		return false;
	}
}
