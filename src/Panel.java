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

        g.setColor(cannonColor);
        g.fillRect(cannonX, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)), cannonSize, cannonSize);

        //Bullets
        final int bulletSize = 80;
        //TODO: bullets color

        for (Bullet bullet : game.bullets) {
            g.fillOval((int) (bullet.x * panelSize.width), (int) (bullet.y * panelSize.height), bulletSize, bulletSize);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
        repaint();
    }
}
