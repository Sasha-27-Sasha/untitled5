import java.util.LinkedList;
import static java.lang.Math.*;

public class Cannon {

    private double y = 0.5;
    boolean up = false, down = false, jump = false;

    public void update() {
        int direction = (up ? 1 : 0) - (down ? 1 : 0);
        if (jump) {
            if (direction != 0)
                y = 0.5 - direction / 2.0;
            return;
        }
        double v = 0.01;
        y -= v * direction; //TODO: remove fps correlation
        y = max(0.0, min(y, 1.0));
    }

    public void shoot(LinkedList<Bullet> bullets, int type) {
        bullets.add(new Bullet(0, y, type));
    }

    public double getPos() {
        return y;
    }
}
