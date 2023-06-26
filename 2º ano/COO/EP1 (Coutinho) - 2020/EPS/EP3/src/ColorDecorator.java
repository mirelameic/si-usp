import java.text.NumberFormat;

public class ColorDecorator extends BaseDecorator {

	private Produto produtoColorido;
	private final String COLORIDO;
	
	public ColorDecorator(Produto p, String cor) {
		super(p);
		this.produtoColorido = p;
		this.COLORIDO = "<span style=\"color:" + cor + "\">";
	}

	@Override
	public String formataParaImpressao() {
		NumberFormat fmt = NumberFormat.getCurrencyInstance();
        return COLORIDO + produtoColorido.formataParaImpressao() + FECHA_TAG;
        
	}
}
