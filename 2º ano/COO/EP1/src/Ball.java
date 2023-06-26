import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {
	private double cx;
    private double cy;
    private double width;
    private double height;
    private Color color;
    private double speed;
    private double speedX;
    private double speedY;

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
        this.cy = cy;
        this.width = width;
        this.height = height;
        this.color = color;
        this.speed = speed;
        this.speedX = Math.abs(speed);
        this.speedY = Math.abs(speed);
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){
		GameLib.setColor(Color.YELLOW);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		this.cx += (this.speedX*delta)/2;
        this.cy += (this.speedY*delta)/2;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		if(playerId=="Player 1"){
            this.speedX = -this.speedX;
        }
        else if(playerId=="Player 2"){
            this.speedX = -this.speedX;
        }
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		switch(wallId){
            case "Left" :
                this.speedX = -this.speedX;
                break;
            case "Right" :
                this.speedX = -this.speedX;
                break;
            case "Top" :
                this.speedY =- speedY;
                break;
            case "Bottom" :
                this.speedY =- speedY;
                break;                
        }
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		int aux = (int) this.cx;
        if(wall.getId()=="Left"){
            if(aux<=wall.getCx() + (wall.getWidth()/2)){
             return true;
           	}
             return false;     
            }
        if(wall.getId()=="Right"){
            if(aux>=wall.getCx() - (wall.getWidth()/2)){
             return true;
            } 
             return false;
            }
        if(wall.getId()=="Top"){
            if(this.cy<=(wall.getCy()) + (wall.getHeight()/2)){
             return true;
            }
             return false;
            }
        if(wall.getId()=="Bottom"){
            if(this.cy>=(wall.getCy()) - (wall.getHeight()/2)){
             return true;
            }
             return false;
            }
           return false;
	}

	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		int test = (int) this.cx;
        if(player.getId()=="Player 1"){
            if(test==player.getCx()&&((this.cy>=player.getCy()-50))&&((this.cy<=player.getCy()+50))){
                return true;
            }
        }else if(player.getId()=="Player 2"){
            if(test==player.getCx()&&((this.cy>=player.getCy()-50))&&((this.cy<=player.getCy()+50))){
                return true;
            }
        }
        return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){
		return this.cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){
		return this.cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){
		return this.speed;
	}

}
