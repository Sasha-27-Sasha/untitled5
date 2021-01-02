import java.awt.event.*;

public class KeyListener extends KeyAdapter {

    private final Game game;

    public KeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {
            game.cannon.move(-1, 0);
        }

        if (key == KeyEvent.VK_S) {
            game.cannon.move(1, 0);
        }
    }
}
