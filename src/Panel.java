import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    private static final int timerDelay = 16;
    private final Game game;

    private static final Random rnd = new Random();

    private final Image[] Balls = { new ImageIcon("res/redball.png").getImage(), new ImageIcon("res/greenball.png").getImage(), new ImageIcon("res/blueball.png").getImage()};
    private final Image[] Enemies = {
            new ImageIcon("res/enemy_r1.png").getImage(), new ImageIcon("res/enemy_r2.png").getImage(), new ImageIcon("res/enemy_r3.png").getImage(), new ImageIcon("res/enemy_r4.png").getImage(), new ImageIcon("res/enemy_r5.png").getImage(),
            new ImageIcon("res/enemy_g1.png").getImage(), new ImageIcon("res/enemy_g2.png").getImage(), new ImageIcon("res/enemy_g3.png").getImage(), new ImageIcon("res/enemy_g4.png").getImage(), new ImageIcon("res/enemy_g5.png").getImage(),
            new ImageIcon("res/enemy_b1.png").getImage(), new ImageIcon("res/enemy_b2.png").getImage(), new ImageIcon("res/enemy_b3.png").getImage(), new ImageIcon("res/enemy_b4.png").getImage(), new ImageIcon("res/enemy_b5.png").getImage()
    };
    private final Image bg = new ImageIcon("res/bg.png").getImage();
    private final Image cannonImage = new ImageIcon("res/cannon.png").getImage();
    private final Image gameOver = new ImageIcon("res/gg.png").getImage();

    public Panel(Game game) {
        this.game = game;
        addKeyListener(new KeyListener(game));
        setFocusable(true);
        Timer timer = new Timer(timerDelay, this);
        timer.start();
        //TODO: window decoration
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.setBackground(Color.BLACK);

        Graphics2D g = (Graphics2D) graphics;
        Rectangle panelSize = g.getClip().getBounds();

        //Cannon
        Color cannonColor = Color.CYAN;
        final int cannonX = 10;
        final int cannonSize = 100;
        //Bullets
        final int bulletSize = 40;
        //Enemy
        final int enemySize = 60;

        g.drawImage(bg, 0, 0, null);

        //Enemy
        for (Enemy enemy : game.enemies) {
            enemy.screenPos = new Rectangle((int) (enemy.getX() * (panelSize.width + enemySize)), (int) (enemy.getY() * (panelSize.height - enemySize)), enemySize, enemySize);
            g.drawImage(Enemies[enemy.getType() * 5 + enemy.getSubType()], enemy.screenPos.x, enemy.screenPos.y, null);
        }

        //Bullets
        for (Bullet bullet : game.bullets) {
            bullet.screenPos = new Rectangle((int) (bullet.getX() * panelSize.width) + cannonX + 50, (int) (bullet.getY() * (panelSize.height - bulletSize - 55)) + 25, bulletSize, bulletSize);
            g.drawImage(Balls[bullet.getType()], bullet.screenPos.x, bullet.screenPos.y, null);
        }
        //Cannon
        g.setColor(cannonColor);
        g.drawImage(Balls[game.cannon.getBulletType()], cannonX + 78, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)) + 28, null);
        g.drawImage(cannonImage, cannonX, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)), null);

        //GameOver
        if (!game.Is_run())
            g.drawImage(gameOver, 300, 400, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
    }
}
