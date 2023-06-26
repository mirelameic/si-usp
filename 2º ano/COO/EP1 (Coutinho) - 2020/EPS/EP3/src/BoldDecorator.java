import java.text.NumberFormat;

public class BoldDecorator extends BaseDecorator {

	private Produto produtoNegrito;
	private final String NEGRITO = "<span style=\"font-weight:bold\">";
	
	public BoldDecorator(Produto p) {
		super(p);
		this.produtoNegrito = p;
	}

	@Override
	public String formataParaImpressao() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return NEGRITO + produtoNegrito.formataParaImpressao() + FECHA_TAG;
	}

}
