import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    private static final int timerDelay = 16;
    private final Game game;

    private Image[] Balls = { new ImageIcon("res/redball.png").getImage(), new ImageIcon("res/greenball.png").getImage(), new ImageIcon("res/blueball.png").getImage()};

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
        final int cannonSize = 80;
        //Bullets
        final int bulletSize = 60;
        //Enemy
        final int enemySize = 60;

        //Enemy
        for (Enemy enemy : game.enemies) {
            enemy.screenPos = new Rectangle((int) (enemy.getX() * (panelSize.width + enemySize)), (int) (enemy.getY() * (panelSize.height - enemySize)), enemySize, enemySize);
            g.fillRoundRect(enemy.screenPos.x, enemy.screenPos.y, enemy.screenPos.width, enemy.screenPos.height, 10, 10);
        }

        //Bullets
        for (Bullet bullet : game.bullets) {
            bullet.screenPos = new Rectangle((int) (bullet.getX() * panelSize.width) + cannonX, (int) (bullet.getY() * (panelSize.height - bulletSize)), bulletSize, bulletSize);
            g.drawImage(Balls[game.cannon.getBulletType()], bullet.screenPos.x, bullet.screenPos.y, null);
        }

        //Cannon
        g.setColor(cannonColor);
        g.fillRect(cannonX, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)), cannonSize, cannonSize);
        g.drawImage(Balls[game.cannon.getBulletType()], cannonX + 50, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)) + 10, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
    }
}
