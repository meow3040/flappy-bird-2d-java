import java.awt.*;

public class Bird {
    private double x;
    private double y;
    private double velocity;
    private final double gravity = 0.6;
    private final double jumpStrength = -8;
    private final int size = 30;

    public Bird() {
        x = 100;
        y = 250;
        velocity = 0;
    }

    public void update() {
        velocity += gravity;
        y += velocity;
    }

    public void jump() {
        velocity = jumpStrength;
    }

    public void draw(Graphics2D g) {
        // Draw wing
        g.setColor(new Color(255, 200, 0));
        g.fillOval((int) x + 5, (int) y + 15, 12, 8);

        // Draw body with gradient
        GradientPaint bodyGradient = new GradientPaint(
            (int) x, (int) y, new Color(255, 220, 0),
            (int) x + size, (int) y + size, new Color(255, 180, 0)
        );
        g.setPaint(bodyGradient);
        g.fillOval((int) x, (int) y, size, size);

        // Draw belly
        g.setColor(new Color(255, 240, 200));
        g.fillOval((int) x + 8, (int) y + 12, 14, 14);

        // Draw eye white
        g.setColor(Color.WHITE);
        g.fillOval((int) x + 18, (int) y + 6, 10, 10);

        // Draw eye pupil
        g.setColor(Color.BLACK);
        g.fillOval((int) x + 21, (int) y + 9, 5, 5);

        // Draw eye shine
        g.setColor(Color.WHITE);
        g.fillOval((int) x + 22, (int) y + 9, 2, 2);

        // Draw beak
        g.setColor(new Color(255, 140, 0));
        int[] xPoints = {(int) x + size, (int) x + size + 12, (int) x + size};
        int[] yPoints = {(int) y + 12, (int) y + 15, (int) y + 18};
        g.fillPolygon(xPoints, yPoints, 3);

        // Draw beak outline
        g.setColor(new Color(200, 100, 0));
        g.setStroke(new BasicStroke(1));
        g.drawPolygon(xPoints, yPoints, 3);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
