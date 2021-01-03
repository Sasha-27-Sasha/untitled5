import java.awt.*;

public class Bullet {
    private double x;
    private final double y;
    private final int type;

    private static final double speed = 0.025;

    public Rectangle screenPos = new Rectangle(0, 0, 0, 0);

    public Bullet(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public boolean move() {
        x += speed; //TODO: remove fps correlation
        return x < 1.1;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getType() {
        return type;
    }
}
