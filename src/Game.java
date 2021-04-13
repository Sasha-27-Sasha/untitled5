import java.util.LinkedList;
import java.util.Random;

public class Game {

    private static final int enemyRate = 90;
    private static final int enemyRateMaxDiff = 70;
    private int enemyReload = 0;
    private int enemyRateDiff = 0;
    private int rowScore = 0, score = 0, bonusReload = 0;
    private boolean isBonus = false;

    private boolean is_run = true;

    private static final Random rnd = new Random();

    LinkedList<Bullet> bullets = new LinkedList<>();
    LinkedList<Enemy> enemies = new LinkedList<>();
    Cannon cannon;

    public Game() {
        enemies.add(new Enemy());
        cannon = new Cannon(enemies.getFirst().getType());
    }

    public LinkedList<Enemy> getEnemies() {
        return enemies;
    }

    public void update() {
        if (!is_run)
            return;
        if (isBonus)
        {
            bonusReload++;
            if (bonusReload == 1000) {
                bonusReload = 0;
                isBonus = false;
            }
        }
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
        cannon.isBonus = isBonus;
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
                if (enemy.getType() == bullet.getType() || isBonus) {
                    enemies.remove(enemy);
                    rowScore++;
                    score++;
                    if (!isBonus) {
                        bonusReload++;
                        if (bonusReload == 5) {//TODO: remove number
                            bonusReload = 0;
                            isBonus = true;
                        }
                    }
                } else {
                    rowScore = 0;
                }
                System.out.print("Score in a row: ");
                System.out.println(rowScore);
                System.out.print("Score: ");
                System.out.println(score);
                clearConsole();

                return true;
            }
        }
        return false;
    }

    public boolean Is_run() {
        return is_run;
    }

    private static void clearConsole() //TODO: Don't work on linux from Intellij
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
                Runtime.getRuntime().exec("cls");
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (final Exception e) {
            System.out.println("Use normal OS");
        }
    }
}
