import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Canvas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.Font;

public class Tablet extends JPanel implements KeyListener, Runnable {

    public static int WIDTH = DrawIt.WIDTH;
    public static int HEIGHT = DrawIt.HEIGHT;

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int SPACE = 4;

    private boolean[] keys;

    private int x;
    private int y;

    private BufferedImage buffer;

    public Tablet(JFrame par) {
        // Indices are the values in the enum KEYS
        keys = new boolean[5];

        setBackground(Color.BLACK);

        // (x, y) is the position of the pen. Initialize it to the center of the screen.
        x = WIDTH / 2;
        y = HEIGHT / 2;

        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);
    }

    @Override
    public void update(Graphics window) {
        paint(window);
    }

    @Override
    public void paint(Graphics window) {

        /* Make a canvas from the BufferedImage to allow persistence */
        Graphics2D canvas = buffer.createGraphics();

        /* If the space is pressed, center the brush and clear the screen */
        if (keys[SPACE]) { //space
            x = WIDTH / 2;
            y = HEIGHT / 2;

            canvas.setColor(Color.BLACK);
            canvas.fillRect(0, 0, WIDTH, HEIGHT);
        }

        /* Move brush according to key press */
        if (keys[UP]) {
            y -= 1;
        }
        if (keys[DOWN]) {
            y += 1;
        }
        if (keys[LEFT]) {
            x -= 1;
        }
        if (keys[RIGHT]) {
            x += 1;
        }

        /* Draw a 2x2 square at the location of the paintbrush to paint the path */
        canvas.setColor(Color.WHITE);
        canvas.fillRect(x, y, 2, 2);

        /* Draw the instructions last to prevent flickering on clear. */
        canvas.setColor(Color.WHITE);
        canvas.setFont(new Font("TAHOMA", Font.BOLD, 18));
        canvas.drawString("A+ Draw a Shape", 20, 20);
        canvas.drawString("Use the arrow keys to draw.", 20, 40);
        canvas.drawString("Use the space bar to clear the screen.", 20, 60);

        /* Finally, draw the buffered canvas onto the screen. */
        window.drawImage(buffer, 0, 0, null);
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[LEFT] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[RIGHT] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[UP] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[DOWN] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[SPACE] = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[LEFT] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[RIGHT] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[UP] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[DOWN] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[SPACE] = false;
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void run() {
        try {
            while (true) {
                Thread.currentThread().sleep(5);
                repaint();
            }
        } catch (Exception e) {
        }
    }
}

