import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int GROUND_HEIGHT = 100;

    private Timer timer;
    private Bird bird;
    private ArrayList<Pipe> pipes;
    private ArrayList<Cloud> clouds;
    private Random random;
    private int score;
    private boolean gameOver;
    private boolean gameStarted;

    public FlappyBird() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(135, 206, 235));
        setFocusable(true);
        addKeyListener(this);

        bird = new Bird();
        pipes = new ArrayList<>();
        clouds = new ArrayList<>();
        random = new Random();
        score = 0;
        gameOver = false;
        gameStarted = false;

        // Initialize clouds
        for (int i = 0; i < 5; i++) {
            clouds.add(new Cloud(random.nextInt(WIDTH), random.nextInt(HEIGHT - GROUND_HEIGHT - 100)));
        }

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Update clouds
        for (Cloud cloud : clouds) {
            cloud.update();
            if (cloud.getX() < -100) {
                cloud.reset(WIDTH);
            }
        }

        if (gameStarted && !gameOver) {
            bird.update();

            // Add new pipes
            if (pipes.isEmpty() || pipes.get(pipes.size() - 1).getX() < WIDTH - 300) {
                int gap = 150;
                int minHeight = 100;
                int maxHeight = HEIGHT - GROUND_HEIGHT - gap - minHeight;
                int pipeHeight = random.nextInt(maxHeight - minHeight) + minHeight;
                pipes.add(new Pipe(WIDTH, pipeHeight, gap));
            }

            // Update pipes
            for (int i = pipes.size() - 1; i >= 0; i--) {
                Pipe pipe = pipes.get(i);
                pipe.update();

                // Remove off-screen pipes
                if (pipe.getX() + pipe.getWidth() < 0) {
                    pipes.remove(i);
                    score++;
                }

                // Check collision
                if (pipe.collides(bird)) {
                    gameOver = true;
                }
            }

            // Check if bird hits ground or ceiling
            if (bird.getY() > HEIGHT - GROUND_HEIGHT - bird.getSize() || bird.getY() < 0) {
                gameOver = true;
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw sky gradient
        GradientPaint skyGradient = new GradientPaint(
            0, 0, new Color(135, 206, 250),
            0, HEIGHT - GROUND_HEIGHT, new Color(176, 224, 230)
        );
        g2d.setPaint(skyGradient);
        g2d.fillRect(0, 0, WIDTH, HEIGHT - GROUND_HEIGHT);

        // Draw clouds
        for (Cloud cloud : clouds) {
            cloud.draw(g2d);
        }

        // Draw pipes
        for (Pipe pipe : pipes) {
            pipe.draw(g2d);
        }

        // Draw ground with gradient
        GradientPaint groundGradient = new GradientPaint(
            0, HEIGHT - GROUND_HEIGHT, new Color(222, 184, 135),
            0, HEIGHT, new Color(160, 120, 70)
        );
        g2d.setPaint(groundGradient);
        g2d.fillRect(0, HEIGHT - GROUND_HEIGHT, WIDTH, GROUND_HEIGHT);

        // Draw grass on top of ground
        g2d.setColor(new Color(34, 139, 34));
        for (int i = 0; i < WIDTH; i += 10) {
            g2d.fillRect(i, HEIGHT - GROUND_HEIGHT, 8, 8);
        }

        // Draw bird
        bird.draw(g2d);

        // Draw score
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 40));
        g2d.drawString("Score: " + score, 20, 50);

        // Draw start message
        if (!gameStarted) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));
            String msg = "Press SPACE to Start";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            g2d.drawString(msg, x, HEIGHT / 2);
        }

        // Draw game over
        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            String msg = "GAME OVER";
            FontMetrics fm = g2d.getFontMetrics();
            int x = (WIDTH - fm.stringWidth(msg)) / 2;
            g2d.drawString(msg, x, HEIGHT / 2);

            g2d.setFont(new Font("Arial", Font.BOLD, 25));
            String restart = "Press SPACE to Restart";
            fm = g2d.getFontMetrics();
            x = (WIDTH - fm.stringWidth(restart)) / 2;
            g2d.drawString(restart, x, HEIGHT / 2 + 60);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (!gameStarted) {
                gameStarted = true;
            } else if (gameOver) {
                // Restart game
                bird = new Bird();
                pipes.clear();
                score = 0;
                gameOver = false;
                gameStarted = true;
            } else {
                bird.jump();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
