import javax.swing.*;

public class Frame extends JFrame {

    Panel panel = new Panel();

    public Frame() {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.setVisible(true);
    }
}
