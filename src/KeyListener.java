import java.awt.event.*;

public class KeyListener extends KeyAdapter {

    private final Game game;
    boolean W = false, S = false;
    boolean[] shoot = {false, false, false}; //TODO: remove count correlation

    public KeyListener(Game game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        int key = e.getKeyCode();

        if (KeyEvent.VK_7 <= key && key <= KeyEvent.VK_9) {
            boolean pressed = false;
            for (boolean i : shoot)
                pressed = pressed || i;

            if (!pressed)
                game.cannon.shoot(game.bullets, key - KeyEvent.VK_7);
            shoot[key - KeyEvent.VK_7] = true;
        }

        if (key == KeyEvent.VK_SHIFT)
            game.cannon.jump = true;

        if (W && S)
            return;

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

        if (KeyEvent.VK_7 <= key && key <= KeyEvent.VK_9)
            shoot[key - KeyEvent.VK_7] = false;

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
