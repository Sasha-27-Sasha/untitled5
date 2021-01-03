import java.awt.geom.Point2D;
import java.util.LinkedList;

public class Game {

    private static final int enemyRate = 70;
    private int enemyReload = enemyRate;

    Cannon cannon = new Cannon();
    LinkedList<Bullet> bullets = new LinkedList<>();
    LinkedList<Enemy> enemies = new LinkedList<>();

    public void update() {
        if (enemyReload < enemyRate)
            enemyReload++;
        else {
            enemies.add(new Enemy());
            enemyReload = 0;
        }
        for (Enemy enemy : enemies)
            enemy.move();
        //TODO: collision
        cannon.update();
        bullets.removeIf(bullet -> !bullet.move());
    }

    public void run() {
        Frame frame = new Frame(this);
    }
}
