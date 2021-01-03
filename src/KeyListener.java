import java.awt.event.*;

public class KeyListener extends KeyAdapter {

    private final Game game;
    boolean W = false;
    boolean S = false;

    public KeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (W && S)
            return;

        int key = e.getKeyCode();

        //TODO: shoot

        if (key == KeyEvent.VK_SHIFT)
            game.cannon.jump = true;

        if (key == KeyEvent.VK_W) {
            game.cannon.up = true;
            W = true;
        }

        if (key == KeyEvent.VK_S) {
            game.cannon.down = true;
            S = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SHIFT)
            game.cannon.jump = false;

        if (key == KeyEvent.VK_W) {
            game.cannon.up = false;
            W = false;
        }

        if (key == KeyEvent.VK_S) {
            game.cannon.down = false;
            S = false;
        }
    }
}
