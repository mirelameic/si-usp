import java.awt.*;

/**
 * Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
 * instancia um objeto deste tipo quando a execução é iniciada.
 */

public class Ball {
	private double cordx, cordy, largura, altura, velocidadeX, velocidadeY;

	/**
	 * Construtor da classe Ball. Observe que quem invoca o construtor desta classe
	 * define a velocidade da bola (em pixels por millisegundo), mas não define a
	 * direção deste movimento. A direção do movimento é determinada aleatóriamente
	 * pelo construtor.
	 * 
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  velocidade da bola (em pixels por millisegundo).
	 */

	public Ball(double cx, double cy, double width, double height, Color color, double speed) {
		this.cordx = cx;
		this.cordy = cy;
		this.largura = width;
		this.altura = height;
		this.velocidadeX = speed;
		this.velocidadeY = -speed;

		

	}

	/**
	 * Método chamado sempre que a bola precisa ser (re)desenhada.
	 */

	public void draw() {
		// System.out.println("Ball Draw");

		GameLib.setColor(Color.YELLOW);
		GameLib.fillRect(this.cordx, this.cordy, this.largura, this.altura);
	}

	/**
	 * Método chamado quando o estado (posição) da bola precisa ser atualizado.
	 * 
	 * @param delta quantidade de millisegundos que se passou entre o ciclo anterior
	 *              de atualização do jogo e o atual.
	 */

	public void update(long delta) {
		// System.out.println("Ball Update");
		this.cordx += this.velocidadeX;
		this.cordy += this.velocidadeY;

	}

	/**
	 * Método chamado quando detecta-se uma colisão da bola com um jogador.
	 * 
	 * @param playerId uma string cujo conteúdo identifica um dos jogadores.
	 */

	public void onPlayerCollision(String playerId) {
		if (playerId == "Player 1") {
			this.velocidadeX = -this.velocidadeX;
		} else if (playerId == "Player 2") {
			this.velocidadeX = -this.velocidadeX;
		}
		// System.out.println("Ball onPlayerCollision");

	}

	/**
	 * Método chamado quando detecta-se uma colisão da bola com uma parede.
	 * 
	 * @param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	 */

	public void onWallCollision(String wallId) {
		switch (wallId) {
			case "Left":
				this.velocidadeX = -this.velocidadeX;
				break;
			case "Right":
				this.velocidadeX = -this.velocidadeX;
				break;
			case "Top":
				this.velocidadeY = -this.velocidadeY;
				break;
			case "Bottom":
				this.velocidadeY = -this.velocidadeY;
				break;
		}
		// System.out.println(velocidadeX);
	}

	/**
	 * Método que verifica se houve colisão da bola com uma parede.
	 * 
	 * @param wall referência para uma instância de Wall contra a qual será
	 *             verificada a ocorrência de colisão da bola.
	 * @return um valor booleano que indica a ocorrência (true) ou não (false) de
	 *         colisão.
	 */

	public boolean checkCollision(Wall wall) {
		int test = (int) this.cordx;
		int testy = (int) this.cordy;
		switch (wall.getId()) {
			case "Left":
				if (test == wall.getWidth()) {
					return true;
				}
				return false;
			case "Right":
				if (test == (wall.getCx() - (wall.getWidth() / 2))) {
					return true;
				}
				return false;
			case "Top":
				if (testy == (wall.getCy() + (wall.getHeight() / 2))) {
					return true;
				}
				return false;
			case "Bottom":
				if (testy == (wall.getCy() - (wall.getHeight() / 2))) {
					return true;
				}
				return false;
		}
		return false;
	}

	/**
	 * Método que verifica se houve colisão da bola com um jogador.
	 * 
	 * @param player referência para uma instância de Player contra o qual será
	 *               verificada a ocorrência de colisão da bola.
	 * @return um valor booleano que indica a ocorrência (true) ou não (false) de
	 *         colisão.
	 */

	public boolean checkCollision(Player player) {
		int test = (int) this.cordx;
		if (player.getId() == "Player 1"){
			if (test == player.getCx() && ((this.cordy >= player.getCy() - 50)) && ((this.cordy <= player.getCy() + 50))){
				return true;
			}
		} else if (player.getId() == "Player 2") {
			if (test == player.getCx() && ((this.cordy >= player.getCy() - 50)) && ((this.cordy <= player.getCy() + 50))){
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Método que devolve a coordenada x do centro do retângulo que representa a
	 * bola.
	 * 
	 * @return o valor double da coordenada x.
	 */

	public double getCx() {
		return this.cordx;

		// return 400;
	}

	/**
	 * Método que devolve a coordenada y do centro do retângulo que representa a
	 * bola.
	 * 
	 * @return o valor double da coordenada y.
	 */

	public double getCy() {
		return this.cordy;

		// return 300;
	}

	/**
	 * Método que devolve a velocidade da bola.
	 * 
	 * @return o valor double da velocidade.
	 * 
	 */

	public double getSpeed() {
		// System.out.println("Ball getSpeed");
		return this.velocidadeX;

		// return 0;
	}

}
