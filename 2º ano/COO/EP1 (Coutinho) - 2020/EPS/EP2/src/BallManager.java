import java.awt.Color;
import java.lang.reflect.*;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que gerencia uma ou mais listaBolas presentes em uma partida. Esta
 * classe é a responsável por instanciar e gerenciar a bola principal do jogo
 * (aquela que existe desde o ínicio de uma partida), assim como eventuais
 * listaBolas extras que apareçam no decorrer da partida. Esta classe também
 * deve gerenciar a interação da(s) bola(s) com os alvos, bem como a aplicação
 * dos efeitos produzidos para cada tipo de alvo atingido.
 */

public class BallManager {
	private List<IBall> listaBolas = new ArrayList<>();
	private List<Long> newExtraBall = new ArrayList<>();
	private int lastBoost = 0;
	private List<Long> lastFade = new ArrayList<>();
	private List<Long> newBoost = new ArrayList<>();
	private List<Integer> indexBallBoost = new ArrayList<>();
	private Class<?> ballClass = null;
	private double ballSpeed;

	public BallManager(String className) {
		try {
			ballClass = Class.forName(className);
		} catch (Exception e) {

			System.out.println("Classe '" + className + "' não reconhecida... Usando 'Ball' como classe padrão.");
			ballClass = Ball.class;
		}
	}

	/**
	 * Recebe as componetes x e y de um vetor, e devolve as componentes x e y do
	 * vetor normalizado (isto é, com comprimento igual a 1.0).
	 * 
	 * @param x componente x de um vetor que representa uma direção.
	 * @param y componente y de um vetor que represetna uma direção.
	 * 
	 * @return array contendo dois valores double que representam as componentes x
	 *         (índice 0) e y (índice 1) do vetor normalizado (unitário).
	 */
	private double[] normalize(double x, double y) {

		double length = Math.sqrt(x * x + y * y);

		return new double[] { x / length, y / length };
	}

	/**
	 * Cria uma instancia de bola, a partir do tipo (classe) cujo nome foi passado
	 * ao construtor desta classe. O vetor direção definido por (vx, vy) não precisa
	 * estar normalizado. A implemntação do método se encarrega de fazer a
	 * normalização.
	 * 
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  ballSpeed da bola (em pixels por millisegundo).
	 * @param vx     componente x do vetor (não precisa ser unitário) que representa
	 *               a direção da bola.
	 * @param vy     componente y do vetor (não precisa ser unitário) que representa
	 *               a direção da bola.
	 */

	private IBall createBallInstance(double cx, double cy, double width, double height, Color color, double speed,
			double vx, double vy) {

		IBall ball = null;
		double[] v = normalize(vx, vy);

		try {
			Constructor<?> constructor = ballClass.getConstructors()[0];
			ball = (IBall) constructor.newInstance(cx, cy, width, height, color, speed, v[0], v[1]);
		} catch (Exception e) {

			System.out.println("Falha na instanciação da bola do tipo '" + ballClass.getName()
					+ "' ... Instanciando bola do tipo 'Ball'");
			ball = new Ball(cx, cy, width, height, color, speed, v[0], v[1]);
		}

		return ball;
	}

	/**
	 * Cria a bola principal do jogo. Este método é chamado pela classe Pong, que
	 * contem uma instância de BallManager.
	 * 
	 * @param cx     coordenada x da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param cy     coordenada y da posição inicial da bola (centro do retangulo
	 *               que a representa).
	 * @param width  largura do retangulo que representa a bola.
	 * @param height altura do retangulo que representa a bola.
	 * @param color  cor da bola.
	 * @param speed  ballSpeed da bola (em pixels por millisegundo).
	 * @param vx     componente x do vetor (não precisa ser unitário) que representa
	 *               a direção da bola.
	 * @param vy     componente y do vetor (não precisa ser unitário) que representa
	 *               a direção da bola.
	 */

	public void initMainBall(double cx, double cy, double width, double height, Color color, double speed, double vx,
			double vy) {

		listaBolas.add(createBallInstance(cx, cy, width, height, color, speed, vx, vy));
		ballSpeed = speed;
	}

	/**
	 * Método que desenha todas as listaBolas gerenciadas pela instância de
	 * BallManager. Chamado sempre que a(s) bola(s) precisa ser (re)desenhada(s).
	 */

	public void draw() {
		for (IBall ball : listaBolas) {
			ball.draw();
		}
	}

	/**
	 * Método que atualiza todas as listaBolas gerenciadas pela instância de
	 * BallManager, em decorrência da passagem do tempo.
	 * 
	 * @param delta quantidade de millisegundos que se passou entre o ciclo anterior
	 *              de atualização do jogo e o atual.
	 */

	public void update(long delta) {
		for (IBall ball : listaBolas)
			ball.update(delta);
	}

	/**
	 * Método que processa as colisões entre as listaBolas gerenciadas pela
	 * instância de BallManager com uma parede.
	 * 
	 * @param wall referência para uma instância de Wall para a qual será verificada
	 *             a ocorrência de colisões.
	 * @return um valor int que indica quantas listaBolas colidiram com a parede
	 *         (uma vez que é possível que mais de uma bola tenha entrado em contato
	 *         com a parede ao mesmo tempo).
	 */

	public int checkCollision(Wall wall) {

		int hits = 0;
		for (IBall ball : listaBolas)
			if (ball.checkCollision(wall)) {
				hits++;
			}

		return hits;
	}

	/**
	 * Método que processa as colisões entre as listaBolas gerenciadas pela
	 * instância de BallManager com um player.
	 * 
	 * @param player referência para uma instância de Player para a qual será
	 *               verificada a ocorrência de colisões.
	 */

	public void checkCollision(Player player) {
		for (IBall ball : listaBolas)
			ball.checkCollision(player);
	}

	/**
	 * Método que processa as colisões entre as listaBolas gerenciadas pela
	 * instância de BallManager com um alvo.
	 * 
	 * @param target referência para uma instância de Target para a qual será
	 *               verificada a ocorrência de colisões.
	 */

	// Cahama a todo instante

	public void checkCollision(Target target) {
		IBall novaBola = null;

		long tempoMilis = System.currentTimeMillis();

		for (IBall ball : listaBolas) {
			double velocidadeBall = ball.getSpeed();
			if (ball.checkCollision(target) == true) {
				if (target instanceof BoostTarget && velocidadeBall == ballSpeed) {
					newBoost.add(tempoMilis);
					indexBallBoost.add(listaBolas.indexOf(ball));
					ball.setSpeed((velocidadeBall + (BoostTarget.BOOST_FACTOR)));
				} else if (target instanceof BoostTarget && velocidadeBall != ballSpeed) {
					newBoost.add(tempoMilis);
					indexBallBoost.add(listaBolas.indexOf(ball));
				} else {
					newExtraBall.add(tempoMilis);
					lastFade.add(2500L);
					novaBola = createBallInstance(listaBolas.get(listaBolas.indexOf(ball)).getCx(),
							listaBolas.get(listaBolas.indexOf(ball)).getCy(),
							listaBolas.get(listaBolas.indexOf(ball)).getWidth(),
							listaBolas.get(listaBolas.indexOf(ball)).getHeight(), Color.RED, ballSpeed,
							listaBolas.get(listaBolas.indexOf(ball)).getVx(),
							(listaBolas.get(listaBolas.indexOf(ball)).getVy() + (Math.random())));
				}
			}
		}

		if (novaBola != null)
			listaBolas.add(novaBola);

		for (int count = 0; count < newExtraBall.size(); count++) {
			long novaBolaTempo = (tempoMilis - newExtraBall.get(count));
			if (novaBolaTempo > (lastFade.get(count)) && novaBolaTempo < (lastFade.get(count) + 100)) {
				(listaBolas.get(count + 1)).setColor(((listaBolas.get(count + 1)).getColor()).darker());
				lastFade.set(count, (lastFade.get(count) + 100));
			}
			if (novaBolaTempo >= DuplicatorTarget.EXTRA_BALL_DURATION && count < listaBolas.size()) {
				listaBolas.remove(count + 1);
				newExtraBall.remove(count);
				lastFade.remove(count);
			}
		}

		for (int count = lastBoost; count < newBoost.size(); count++) {
			if ((tempoMilis - newBoost.get(count)) >= BoostTarget.BOOST_DURATION
					&& indexBallBoost.get(count) < listaBolas.size()) {
				listaBolas.get(indexBallBoost.get(count)).setSpeed(ballSpeed);
				lastBoost = lastBoost + 1;
			}
		}
	}
}
