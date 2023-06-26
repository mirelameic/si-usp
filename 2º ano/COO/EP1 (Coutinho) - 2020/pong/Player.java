import java.awt.*;

public class Player{
	private Player player;
	private Player player2;
	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private String id;
	private double[] v_limit;
	private double speed;

	public Player(double cx, double cy, double width, double height, Color color, String id, double [] v_limit, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.id = id;
		this.v_limit = v_limit;
		this.speed = speed;
	}

	public void draw(){
		GameLib.setColor(color);
		GameLib.fillRect(getCx(), getCy(), getWidth(), getHeight());
	}

	public void moveUp(long delta){
		if(((this.cy - getHeight())/2)>=35){
			this.cy -= (this.speed)*delta;
		}	
	}

	public void moveDown(long delta){
		if(((this.cy - getHeight())/2)<=213){
			this.cy += (this.speed)*delta;
		}
	}

	public String getId(){
		return this.id; 
	}

	public double getWidth(){
		return this.width; 
	}

	public double getHeight(){
		return this.height;
	}

	public double getCx(){
		return this.cx;
	}

	public double getCy(){
		return this.cy;
	}
}

