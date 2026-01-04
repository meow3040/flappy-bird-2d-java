import java.awt.*;
import java.util.Random;

public class Cloud {
    private int x;
    private int y;
    private int width;
    private int height;
    private float speed;
    private Random random;

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
        this.random = new Random();
        this.width = 60 + random.nextInt(40);
        this.height = 30 + random.nextInt(20);
        this.speed = 0.3f + random.nextFloat() * 0.3f;
    }

    public void update() {
        x -= speed;
    }

    public void reset(int newX) {
        x = newX;
        y = random.nextInt(300);
        width = 60 + random.nextInt(40);
        height = 30 + random.nextInt(20);
        speed = 0.3f + random.nextFloat() * 0.3f;
    }

    public void draw(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 180));
        
        // Draw cloud circles
        g.fillOval(x, y, width / 2, height);
        g.fillOval(x + width / 4, y - height / 3, width / 2, height);
        g.fillOval(x + width / 2, y, width / 2, height);
    }

    public int getX() {
        return x;
    }
}
