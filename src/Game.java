import java.util.LinkedList;
import java.util.Random;

public class Game {

    private static final int enemyRate = 90;
    private static final int enemyRateMaxDiff = 70;
    private int enemyReload = enemyRate;
    private int enemyRateDiff = 0;

    private boolean is_run = true;

    private static final Random rnd = new Random();

    Cannon cannon = new Cannon();
    LinkedList<Bullet> bullets = new LinkedList<>();
    LinkedList<Enemy> enemies = new LinkedList<>();

    public void update() {
        if (!is_run)
            return;

        if (enemyReload + enemyRateDiff < enemyRate)
            enemyReload++;
        else {
            enemies.add(new Enemy());
            enemyReload = 0;
            enemyRateDiff = rnd.nextInt(enemyRateMaxDiff) - enemyRateMaxDiff / 2;
        }
        for (Enemy enemy : enemies) {
            if (!enemy.move()) {
                is_run = false;
                break;
            }
        }
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
                if (enemy.getType() == bullet.getType())
                    enemies.remove(enemy);
                //TODO: power up
                return true;
            }
        }
        return false;
    }
}
