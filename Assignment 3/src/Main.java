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

    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
