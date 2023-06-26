import java.awt.*;

/**
 * Esta classe representa os jogadores (players) do jogo. A classe princial do
 * jogo (Pong) instancia dois objetos deste tipo quando a execução é iniciada.
 */

public class Player {
	private double cordx, cordy, larguraPlayer, alturaPlayer, velocidade;
	private String idDoPlayer;
	private Color cor;
	private double [] limite;

	/**
	 * Construtor da classe Player.
	 * 
	 * @param cx      coordenada x da posição inicial do player (centro do retangulo
	 *                que o representa).
	 * @param cy      coordenada y da posição inicial do player (centro do retangulo
	 *                que o representa).
	 * @param width   largura do retangulo que representa o player.
	 * @param height  altura do retangulo que representa o player.
	 * @param color   cor do player.
	 * @param id      uma string que identifica o player
	 * @param v_limit um array de double contendo dois valores (em pixels) que
	 *                determinam os limites verticais da área útil da quadra.
	 * @param speed   velocidade do movimento vertical do player (em pixels por
	 *                millisegundo).
	 */

	public Player(double cx, double cy, double width, double height, Color color, String id, double[] v_limit,
			double speed) {
		this.cordx = cx;
		this.cordy = cy;
		this.larguraPlayer = width;
		this.alturaPlayer = height;
		this.velocidade = speed;
		// this.limite = v_limit;
		this.idDoPlayer = id;
		this.cor = color;
		this.limite = v_limit;

		// System.out.println("Id: " + idDoPlayer);
		// System.out.println("cordx Player: " + cordx);
		// System.out.println("cordy Player: " + cordy);
		// System.out.println("largura Player: " + larguraPlayer);
		// System.out.println("altura Player: " + alturaPlayer);
		// System.out.println("limite: " + this.limite[0]);
		// System.out.println("limite: " + this.limite[1]);

	}

	/**
	 * Método chamado sempre que o player precisa ser (re)desenhado.
	 */

	public void draw() {

		GameLib.setColor(this.cor);
		GameLib.fillRect(this.cordx, this.cordy, this.larguraPlayer, this.alturaPlayer);
	}

	/**
	 * Método chamado quando se deseja mover o player para cima. Este método é
	 * chamado sempre que a tecla associada à ação de mover o player para cima
	 * estiver pressionada.
	 * 
	 * @param delta quantidade de millisegundos que se passou entre o ciclo anterior
	 *              de atualização do jogo e o atual.
	 */

	public void moveUp(long delta) {
		double topo = this.limite[0];
		double chao = this.limite[1];
		
		if (getCy() - (getHeight()/2)  < topo){
			this.cordy += 0;
		} else {
			this.cordy += -1;
		}

		// System.out.println("topo "+ topo);
		// System.out.println("chao "+ chao);
		// System.out.println(cordy);

	}

	/**
	 * Método chamado quando se deseja mover o player para baixo. Este método é
	 * chamado sempre que a tecla associada à ação de mover o player para baixo
	 * estiver pressionada.
	 * 
	 * @param delta quantidade de millisegundos que se passou entre o ciclo anterior
	 *              de atualização do jogo e o atual.
	 */

	public void moveDown(long delta) {
		double topo = this.limite[0];
		double chao = this.limite[1];
		
		if (getCy() + (getHeight()/2)  > chao){
			this.cordy += 0;
		} else {
			this.cordy += 1;
		}
			
		// System.out.println("topo "+ topo);
		// System.out.println("chao "+ chao);
		// System.out.println(cordy);
	}

	/**
	 * Método que devolve a string de identificação do player.
	 * 
	 * @return a String de indentificação.
	 */

	public String getId() {

		return this.idDoPlayer;
	}

	/**
	 * Método que devolve a largura do retangulo que representa o player.
	 * 
	 * @return um double com o valor da largura.
	 */

	public double getWidth() {

		return this.larguraPlayer;
	}

	/**
	 * Método que devolve a algura do retangulo que representa o player.
	 * 
	 * @return um double com o valor da altura.
	 */

	public double getHeight() {

		return this.alturaPlayer;
	}

	/**
	 * Método que devolve a coordenada x do centro do retangulo que representa o
	 * player.
	 * 
	 * @return o valor double da coordenada x.
	 */

	public double getCx() {

		return this.cordx;
	}

	/**
	 * Método que devolve a coordenada y do centro do retangulo que representa o
	 * player.
	 * 
	 * @return o valor double da coordenada y.
	 */

	public double getCy() {

		return this.cordy;
	}
}
