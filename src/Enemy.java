import java.util.Random;

public class Enemy {
    private double y = 1;
    private final double x;
    private double speed = 0.01;
    private static final double acceleration = 0.001;
    private static final Random rnd = new Random();

    public Enemy() {
        x = rnd.nextDouble();
    }

    public void speedUp() {
        speed += acceleration;
    }

    public boolean move() {
        y -= speed;//TODO: remove fps correlation
        return true; //TODO: disapear
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
