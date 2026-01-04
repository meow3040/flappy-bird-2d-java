import java.awt.*;

public class Pipe {
    private int x;
    private int topHeight;
    private int gap;
    private final int width = 80;
    private final int speed = 3;

    public Pipe(int x, int topHeight, int gap) {
        this.x = x;
        this.topHeight = topHeight;
        this.gap = gap;
    }

    public void update() {
        x -= speed;
    }

    public void draw(Graphics2D g) {
        int capHeight = 25;
        int pipeBodyWidth = width - 10;
        int pipeBodyX = x + 5;

        // Draw top pipe body with gradient
        GradientPaint topGradient = new GradientPaint(
            x, 0, new Color(50, 180, 50),
            x + width, 0, new Color(30, 140, 30)
        );
        g.setPaint(topGradient);
        g.fillRect(pipeBodyX, 0, pipeBodyWidth, topHeight - capHeight);

        // Draw top pipe highlights
        g.setColor(new Color(70, 200, 70, 100));
        g.fillRect(pipeBodyX + 5, 0, 8, topHeight - capHeight);

        // Draw top pipe cap
        g.setColor(new Color(40, 160, 40));
        g.fillRect(x, topHeight - capHeight, width, capHeight);

        // Draw cap gradient
        GradientPaint capGradient = new GradientPaint(
            x, topHeight - capHeight, new Color(60, 190, 60),
            x + width, topHeight - capHeight, new Color(30, 140, 30)
        );
        g.setPaint(capGradient);
        g.fillRect(x, topHeight - capHeight, width, capHeight);

        // Draw bottom pipe
        int bottomY = topHeight + gap;
        int bottomHeight = 600 - 100 - bottomY;

        // Draw bottom pipe cap
        g.setPaint(capGradient);
        g.fillRect(x, bottomY, width, capHeight);

        // Draw bottom pipe body with gradient
        g.setPaint(topGradient);
        g.fillRect(pipeBodyX, bottomY + capHeight, pipeBodyWidth, bottomHeight - capHeight);

        // Draw bottom pipe highlights
        g.setColor(new Color(70, 200, 70, 100));
        g.fillRect(pipeBodyX + 5, bottomY + capHeight, 8, bottomHeight - capHeight);

        // Draw borders
        g.setColor(new Color(20, 100, 20));
        g.setStroke(new BasicStroke(2));
        g.drawRect(pipeBodyX, 0, pipeBodyWidth, topHeight - capHeight);
        g.drawRect(x, topHeight - capHeight, width, capHeight);
        g.drawRect(x, bottomY, width, capHeight);
        g.drawRect(pipeBodyX, bottomY + capHeight, pipeBodyWidth, bottomHeight - capHeight);
    }

    public boolean collides(Bird bird) {
        int birdX = (int) bird.getX();
        int birdY = (int) bird.getY();
        int birdSize = bird.getSize();

        // Check if bird is within pipe's x range
        if (birdX + birdSize > x && birdX < x + width) {
            // Check if bird hits top or bottom pipe
            if (birdY < topHeight || birdY + birdSize > topHeight + gap) {
                return true;
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getWidth() {
        return width;
    }
}
