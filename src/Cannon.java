import java.util.LinkedList;
import java.util.Random;

import static java.lang.Math.*;

public class Cannon {

    private double y = 0.5;
    private static final double speed = 0.015;
    private final int reloadTime = 10;
    private int reload = reloadTime;

    private static final Random rnd = new Random();
    private int bulletType;

    public boolean isBonus = false; //TODO: public to private

    boolean up = false, down = false, jump = false;

    public Cannon(int type) {
        bulletType = type;
    }

    public void restart(int type) {
        up = down = jump = false;
        bulletType = type;
        y = 0.5;
        reload = reloadTime;
    }

    public void update() {
        if (reload < reloadTime)//TODO: remove fps correlation
            reload++;
        int direction = (up ? 1 : 0) - (down ? 1 : 0);
        if (jump) {
            if (direction != 0)
                y = 0.5 - direction / 2.0;
            return;
        }

        y -= speed * direction; //TODO: remove fps correlation
        y = max(0.0, min(y, 1.0));
    }

    public void shoot(LinkedList<Bullet> bullets, int type, LinkedList<Enemy> enemies) {
        if (reload == reloadTime && bulletType == type || isBonus) {
                bullets.add(new Bullet(0, y, bulletType));
                bulletType = rnd.nextInt(3);
                while (!checkNewBullet(enemies) && enemies.size() != 0)
                    bulletType = (bulletType + 1) % 3;
                reload = 0;
        }
    }

    private boolean checkNewBullet(LinkedList<Enemy> enemies) {
        boolean res = false;
        for (Enemy enemy : enemies) {
            if (enemy.getType() == bulletType) {
                res = true;
                break;
            }
        }
        return res;
    }

    public double getPos() {
        return y;
    }

    public int getBulletType() {
        return bulletType;
    }
}
