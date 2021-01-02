import javax.swing.*;

public class Frame extends JFrame {

    public Frame(Game game) {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(new Panel(game));
        this.setVisible(true);
    }
}
