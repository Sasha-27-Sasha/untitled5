import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    private static final int timerDelay = 16;
    private final Game game;

    private static final Random rnd = new Random();

    private final Image[] Balls = { new ImageIcon("res/redball.png").getImage(), new ImageIcon("res/greenball.png").getImage(), new ImageIcon("res/blueball.png").getImage()};
//    private final Image[] Enemies = {
//            new ImageIcon("res/enemy_r1.png").getImage(), new ImageIcon("res/enemy_r2.png").getImage(), new ImageIcon("res/enemy_r3.png").getImage(), new ImageIcon("res/enemy_r4.png").getImage(), new ImageIcon("res/enemy_r5.png").getImage(),
//            new ImageIcon("res/enemy_g1.png").getImage(), new ImageIcon("res/enemy_g2.png").getImage(), new ImageIcon("res/enemy_g3.png").getImage(), new ImageIcon("res/enemy_g4.png").getImage(), new ImageIcon("res/enemy_g5.png").getImage(),
//            new ImageIcon("res/enemy_b1.png").getImage(), new ImageIcon("res/enemy_b2.png").getImage(), new ImageIcon("res/enemy_b3.png").getImage(), new ImageIcon("res/enemy_b4.png").getImage(), new ImageIcon("res/enemy_b5.png").getImage()
//    };
    private final Image[] Enemies = { new ImageIcon("res/red.png").getImage(), new ImageIcon("res/green.png").getImage(), new ImageIcon("res/blue.png").getImage()};
    private final Image bg = new ImageIcon("res/bg.png").getImage();
    private final Image cannonImage = new ImageIcon("res/cannon.png").getImage();
    private final Image gameOver = new ImageIcon("res/gg.png").getImage();
    private final Image menuBg = new ImageIcon("res/menubg.jpeg").getImage();

    private final JButton playB, exitB, menuB;
    private boolean isMenu = true;

    public Panel(Game game) {
        this.game = game;
        addKeyListener(new KeyListener(game));
        setFocusable(true);
        setLayout(null);
        playB = new JButton("Play");
        playB.setBackground(new Color(30, 240, 60));
        playB.setBounds(150, 400, 400, 150);
        playB.setFocusable(false);
        playB.setActionCommand("Play");
        playB.setFont(new Font(null, Font.BOLD, 40));
        playB.addActionListener(this);
        add(playB);
        menuB = new JButton("Menu");
        menuB.setBackground(Color.CYAN);
        menuB.setBounds(400, 550, 400, 150);
        menuB.setFocusable(false);
        menuB.setActionCommand("Menu");
        menuB.setFont(new Font(null, Font.BOLD, 40));
        menuB.addActionListener(this);
        menuB.setVisible(false);
        add(menuB);
        exitB = new JButton("Exit");
        exitB.setBackground(new Color(200, 30, 50));
        exitB.setBounds(650, 400, 400, 150);
        exitB.setFocusable(false);
        exitB.setFont(new Font(null, Font.BOLD, 40));
        exitB.setActionCommand("Exit");
        exitB.addActionListener(this);
        add(exitB);
        Timer timer = new Timer(timerDelay, this);
        timer.start();
        //TODO: window decoration
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        this.setBackground(Color.BLACK);

        Graphics2D g = (Graphics2D) graphics;
        Rectangle panelSize = g.getClip().getBounds();

        if (isMenu) {
            g.drawImage(menuBg, 0, 0, null);
            return;
        }

        //Cannon
        Color cannonColor = Color.CYAN;
        final int cannonX = 10;
        final int cannonSize = 100;
        //Bullets
        final int bulletSize = 40;
        //Enemy
        final int enemySize = 60;

        g.drawImage(bg, 0, 0, null);

        //Enemy
        for (Enemy enemy : game.enemies) {
            enemy.screenPos = new Rectangle((int) (enemy.getX() * (panelSize.width + enemySize)), (int) (enemy.getY() * (panelSize.height - enemySize)), enemySize, enemySize);
            g.drawImage(Enemies[enemy.getType() + enemy.getSubType()], enemy.screenPos.x, enemy.screenPos.y, null);
        }

        //Bullets
        for (Bullet bullet : game.bullets) {
            bullet.screenPos = new Rectangle((int) (bullet.getX() * panelSize.width) + cannonX + 50, (int) (bullet.getY() * (panelSize.height - bulletSize - 55)) + 25, bulletSize, bulletSize);
            g.drawImage(Balls[bullet.getType()], bullet.screenPos.x, bullet.screenPos.y, null);
        }
        //Cannon
        g.setColor(cannonColor);
        g.drawImage(Balls[game.cannon.getBulletType()], cannonX + 78, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)) + 28, null);
        g.drawImage(cannonImage, cannonX, (int) (game.cannon.getPos() * (panelSize.height - cannonSize)), null);

        //GameOver
        if (!game.isRun())
            g.drawImage(gameOver, 300, 400, null);

        //Score
        g.setFont(new Font(null, Font.BOLD, 40));
        g.setColor(Color.black);
        g.drawString("Score: " + game.getScore(), 900, 50);
        g.drawString("Best score: " + game.getBestScore(), 800, 950);

        if (game.isBonus()) {
            bonusFrames++;
            if (bonusFrames > 80)
                return;
            g.setColor(new Color(bonusFrames * 3, bonusFrames, bonusFrames * 2));
            g.drawString("You got bonus!", 400, 400);
        }
        else {
            bonusFrames = 0;
        }
    }

    private int bonusFrames = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Play".equals(e.getActionCommand())) {
            game.restart();
            playB.setVisible(false);
            exitB.setVisible(false);
            isMenu = false;
        }
        if ("Exit".equals(e.getActionCommand()))
            System.exit(0);
        if ("Menu".equals(e.getActionCommand())) {
            menuB.setVisible(false);
            isMenu = true;
            playB.setVisible(true);
            exitB.setVisible(true);
        }
        if (!isMenu)
            game.update();
        if (!game.isRun() && !isMenu) {
            menuB.setVisible(true);
        }
        repaint();
    }
}
