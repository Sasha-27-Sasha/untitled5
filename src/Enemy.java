import java.util.Random;
import java.awt.*;

public class Enemy {
    private double x = 1;
    private final double y;
    private final int type, subType;
    private double speed = 0.001;
    private static final double acceleration = 0.002;
    private static final Random rnd = new Random();

    public Rectangle screenPos = new Rectangle(0, 0, 0, 0);

    public Enemy() {
        y = rnd.nextDouble();
        type = rnd.nextInt(3);
        subType = rnd.nextInt(5);
    }

    public int getSubType() {
        return subType;
    }

    public void speedUp() {
        speed += acceleration;
    }

    public boolean move() {
        x -= speed;//TODO: remove fps correlation
        return x > 0;
    }

    public int getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
