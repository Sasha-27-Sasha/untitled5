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

        //Cannon
        Color cannonColor = Color.GREEN;
        final int cannonX = 10;
        final int cannonSize = 80;
        //Bullets
        final int bulletSize = 60;
        Color[] bulletColors = {Color.RED, Color.GREEN, Color.BLUE}; //TODO: remove count correlation

        //Bullets
        for (Bullet bullet : game.bullets) {
            g.setColor(bulletColors[bullet.type]);
            g.fillOval((int) (bullet.x * panelSize.width) + cannonX, (int) (bullet.y * (panelSize.height - bulletSize)), bulletSize, bulletSize);
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
