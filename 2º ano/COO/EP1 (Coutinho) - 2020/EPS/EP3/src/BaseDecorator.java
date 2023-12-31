public abstract class BaseDecorator implements Produto {

	protected static final String FECHA_TAG = "</span>";
	public Produto produto;
	
	public BaseDecorator(Produto p) {
		this.produto = p;
	}
	
	@Override
	public void setQtdEstoque(int qtdEstoque) {
		produto.setQtdEstoque(qtdEstoque);
	}

	@Override
	public void setPreco(double preco) {
		produto.setPreco(preco);
	}

	@Override
	public int getId() {
		return produto.getId();
	}

	@Override
	public String getDescricao() {
		return produto.getDescricao();
	}

	@Override
	public String getCategoria() {
		return produto.getCategoria();
	}

	@Override
	public int getQtdEstoque() {
		return produto.getQtdEstoque();
	}

	@Override
	public double getPreco() {
		return produto.getPreco();
	}

	@Override
	public abstract String formataParaImpressao();
}
