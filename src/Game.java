import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Game {

    Cannon cannon = new Cannon();
    LinkedList<Bullet> bullets = new LinkedList<>();

    public void update() {
        cannon.update();
        bullets.removeIf(bullet -> !bullet.move());
    }

    public void run() {
        Frame frame = new Frame(this);
    }
}
