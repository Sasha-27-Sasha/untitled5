import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    private static final int timerDelay = 10;

    Cannon cannon = new Cannon();

    public Panel() {
        Timer timer = new Timer(timerDelay, this);
        timer.start();
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
        g.fillRect(cannonX, (int) (cannon.getPos() * panelSize.height - cannonSize / 2), cannonSize, cannonSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
