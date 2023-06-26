import java.awt.*;
import java.util.*;

public class FxBall extends Ball implements IBall {
    private double[] rastroX;
    private double[] rastroY;
    int comeco;
    int end;
    int count;

    public FxBall(double cx, double cy, double width, double height, Color color, double speed, double vx, double vy) {
        super(cx, cy, width, height, color, speed, vx, vy);
        criaArray();
        comeco = 0;
        end = 99;
    }

    void criaArray() {
        this.rastroX = new double[100];
        this.rastroY = new double[100];
    }

    public void draw() {
        end++;
        switch (end) {
            case 100:
                end = 0;
        }
        comeco++;
        switch (comeco) {
            case 100:
                comeco = 0;
        }
        rastroX[end] = getCx();
        rastroY[end] = getCy();
        count = end;
        int redutor = 1;
        while (count != comeco) {
            GameLib.setColor(getColor().darker());
            GameLib.fillRect(rastroX[count], rastroY[count], (getWidth() - redutor * 0.25),
                    (getHeight() - redutor * 0.25));
            redutor++;
            count--;
            if (count < 0)
                count = 99;
        }
        GameLib.setColor(getColor());
        GameLib.fillRect(getCx(), getCy(), getWidth(), getHeight());
    }

}