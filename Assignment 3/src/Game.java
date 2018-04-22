import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Random;


public class Game {

    private static final int WIDTH = 800, HEIGHT = 600, BLOCK_WIDTH = 90, BLOCK_HEIGHT = 30;
    private static final int BALL_START_X = 400, BALL_START_Y = 500;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("Arkanoid", 800, 600);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.createBorders();
        this.createBlocks();
        Ball ball = new Ball(new Point(BALL_START_X, BALL_START_Y), 5, RandomColor.generateRandomColor(),
                this.environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(123, 2));
        ball.addToGame(this);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Point(BALL_START_X, BALL_START_Y));
        paddle.addToGame(this);

    }

    public void createBorders() {
        Block top = new Block(new Point(0, 0), WIDTH, 0);
        Block left = new Block(new Point(0, 0), 0, HEIGHT);
        Block right = new Block(new Point(WIDTH, 0), 0, HEIGHT);
        Block bottom = new Block(new Point(0, HEIGHT), WIDTH, 0);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
    }

    public void createBlocks() {
        for (int i = 100; i < 700; i += 100) {
            for (int j = 35; j < 245; j += 35) {
                Block newBlock = new Block(new Point(i, j), BLOCK_WIDTH, BLOCK_HEIGHT);
                newBlock.addToGame(this);
            }
        }
    }

    // Run the game -- start the animation loop.
    public void run() {
        int framesPerSecond = 60;
        int milisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long miliSecondLeftToSleep = milisecondsPerFrame - usedTime;
            if (miliSecondLeftToSleep > 0) {
                sleeper.sleepFor(miliSecondLeftToSleep);
            }
        }
    }
}