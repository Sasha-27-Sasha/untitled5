import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    private static final int timerDelay = 16;
    private final Game game;

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

        Color[] typeToColors = {Color.RED, Color.GREEN, Color.BLUE}; //TODO: remove count correlation
        //Cannon
        Color cannonColor = Color.GREEN;
        final int cannonX = 10;
        final int cannonSize = 80;
        //Bullets
        final int bulletSize = 60;
        //Enemy
        final int enemySize = 60;

        //Enemy
        for (Enemy enemy : game.enemies) {
            g.setColor(typeToColors[enemy.getType()]);
            enemy.screenPos = new Rectangle((int) (enemy.getX() * (panelSize.width + enemySize)), (int) (enemy.getY() * (panelSize.height - enemySize)), enemySize, enemySize);
            g.fillRoundRect(enemy.screenPos.x, enemy.screenPos.y, enemy.screenPos.width, enemy.screenPos.height, 10, 10);
        }

        //Bullets
        for (Bullet bullet : game.bullets) {
            g.setColor(typeToColors[bullet.getType()]);
            bullet.screenPos = new Rectangle((int) (bullet.getX() * panelSize.width) + cannonX, (int) (bullet.getY() * (panelSize.height - bulletSize)), bulletSize, bulletSize);
            g.fillOval(bullet.screenPos.x, bullet.screenPos.y, bullet.screenPos.width, bullet.screenPos.height);
        }

        //Cannon
        g.setColor(cannonColor);
        g.fillRect(cannonX, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)), cannonSize, cannonSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
    }
}
