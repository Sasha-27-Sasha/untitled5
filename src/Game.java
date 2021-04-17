import java.util.LinkedList;
import java.util.Random;
import java.io.*;

public class Game {

    private static final int enemyRate = 90;
    private static final int enemyRateMaxDiff = 70;
    private int enemyReload = 0;
    private int enemyRateDiff = 0;
    private int rowScore = 0, score = 0, bonusReload = 0, bestScore;
    private boolean isBonus = false;

    private boolean is_run = true;

    private static final Random rnd = new Random();

    LinkedList<Bullet> bullets = new LinkedList<>();
    LinkedList<Enemy> enemies = new LinkedList<>();
    Cannon cannon;

    public Game() {
        enemies.add(new Enemy());
        cannon = new Cannon(enemies.getFirst().getType());
        File file = new File("score.txt");
        try {
            if (file.createNewFile()) {
                    FileWriter fw = new FileWriter(file, false);
                    PrintWriter pw = new PrintWriter(fw, true);
                    pw.println(0);
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            bestScore = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                File file = new File("score.txt");
                FileWriter fw = null;
                try {
                    fw = new FileWriter(file, false);
                } catch (IOException e) {
                    e.printStackTrace();
                    break;
                }
                PrintWriter pw = new PrintWriter(fw, true);
                pw.println(bestScore);
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
                    if (score > bestScore)
                        bestScore = score;
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
                return true;
            }
        }
        return false;
    }

    public int getScore() {
        return score;
    }

    public int getBestScore() {
        return bestScore;
    }

    public boolean isBonus() {
        return isBonus;
    }

    public boolean Is_run() {
        return is_run;
    }
}
