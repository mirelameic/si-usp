import java.awt.*;

public class Ball{
	private double cx;
    private double cy;
    private double width;
    private double height;
    private Color color;
    private double speed;
    private double speedX;
    private double speedY;

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

	public void draw(){
		GameLib.setColor(Color.YELLOW);
        GameLib.fillRect(this.cx, this.cy, this.width, this.height);
	}

	public void update(long delta){
		this.cx += (this.speedX*delta)/2;
        this.cy += (this.speedY*delta)/2;
	}

	public void onPlayerCollision(String playerId){
		if(playerId=="Player 1"){
            this.speedX = -this.speedX;
        }
        else if(playerId=="Player 2"){
            this.speedX = -this.speedX;
        }
	}

	public void onWallCollision(String wallId){
		switch(wallId){
            case "Left" :
                this.speedX = (0.5);
                this.speedY =+ speedY;
                break;
            case "Right" :
                this.speedX = (-0.5);
                this.speedY =+ speedY;
                break;
            case "Top" :
                this.speedY =- speedY;
                break;
            case "Bottom" :
                this.speedY =- speedY;
                break;                
        }
	}
	
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
	
	public double getCx(){
		return cx;
	}

	public double getCy(){
		return cy;
	}

	public double getSpeed(){
		return speed;
	}

}
