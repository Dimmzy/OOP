import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;


/**
 * Test class.
 */
public class Main {

    private static final int WIDTH = 800, HEIGHT = 600;
    /**
     * Main testing method.
     * @param args argggggggggs.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Woo", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Point center = new Point(15, 15);
        Point res = new Point(800, 600);
        GameEnvironment ge = new GameEnvironment();
        Ball ball = new Ball(center, 5, Color.BLACK, ge, res);
        Velocity v = Velocity.fromAngleAndSpeed(150, 5);
        ball.setVelocity(v);
        Random rand = new Random();
        for (int i = 0; i < 20; i++) {
            int randomX = rand.nextInt(770);
            int randomY = rand.nextInt(575);
            Point upLeft = new Point(randomX, randomY);
            Block blockObama = new Block(upLeft, 30, 25);
            ge.addCollidable(blockObama);
        }
        ge.addCollidable(new Block(new Point(0, 0), 800, 0));
        ge.addCollidable(new Block(new Point(0, 0), 0, 600));
        ge.addCollidable(new Block(new Point(800, 0), 0, 600));
        ge.addCollidable(new Block(new Point(0, 600), 800, 0));
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep();
            List blockList = ge.getObjectList();
            for (int i = 0; i < blockList.size(); i++) {
                Block drawBlock = (Block) blockList.get(i);
                drawBlock.drawOn(d);
            }
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}
