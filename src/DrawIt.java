import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
public class DrawIt extends JFrame
{
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    public DrawIt()
    {
        super("DrawIt");
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        Tablet tablet = new Tablet(this);
        tablet.setFocusable(true);
        getContentPane().add(tablet);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main( String args[] )
    {
        DrawIt run = new DrawIt();
    }
}