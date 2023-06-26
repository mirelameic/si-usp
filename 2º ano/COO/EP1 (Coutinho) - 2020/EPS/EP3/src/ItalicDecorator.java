import java.text.NumberFormat;

public class ItalicDecorator extends BaseDecorator {
	
	private Produto produtoItalico;
	private final String Italic = "<span style=\"font-style:italic\">";
	
	public ItalicDecorator(Produto p) {
		super(p);
		this.produtoItalico = p;
	}

	@Override
	public String formataParaImpressao() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return Italic + produtoItalico.formataParaImpressao() + FECHA_TAG;
	}
}
