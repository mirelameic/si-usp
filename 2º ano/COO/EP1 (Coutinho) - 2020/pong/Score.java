import java.awt.*;

public class Score{
	private String playerId;
	private int score;

	public Score(String playerId){
		this.playerId = playerId;
		score = 0;
	}

	public void draw(){
		if(playerId.equals("Player 1")){
            GameLib.setColor(Color.GREEN);
            GameLib.drawText(playerId + ": " + score, 70, GameLib.ALIGN_LEFT);
        }else if(playerId.equals("Player 2")){
            GameLib.setColor(Color.BLUE);
            GameLib.drawText(playerId + ": " + score, 70, GameLib.ALIGN_RIGHT);
        }				
	}

	public void inc(){
		score++;
	}

	public int getScore(){
		return this.score;
	}
}
