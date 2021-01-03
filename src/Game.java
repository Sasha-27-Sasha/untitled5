import java.util.LinkedList;
import static java.lang.Math.sqrt;

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
        //TODO: speedup
        cannon.update();
        bullets.removeIf(this::collisionCheck);
        bullets.removeIf(bullet -> !bullet.move());
    }

    public void run() {
        Frame frame = new Frame(this);
    }

    private boolean collisionCheck(Bullet bullet) {
        for (Enemy enemy : enemies) {
            if (enemy.screenPos.intersects(bullet.screenPos)) {
                enemies.remove(enemy);
                return true;
            }
        }
        return false;
    }
}
