import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * The Game Class. Creates the objects and sets the game in motion.
 */

public class Game {

    // Constant values we'll use to define the the window, blocks size and ball location.
    private static final int WIDTH = 800, HEIGHT = 600, BLOCK_WIDTH = 50, BLOCK_HEIGHT = 30;
    private static final int PADDLE_START_X = 400, PADDLE_START_Y = 555;
    private static final int BALL_ONE_START_X = 400, BALL_ONE_START_Y = 550, BALL_ONE_START_ANGLE = 123;
    private static final int BALL_TWO_START_X = 380, BALL_TWO_START_Y = 550, BALL_TWO_START_ANGLE = 321;
    private static final int BALL_SPEED = 7;
    private static final int BALL_RADIUS = 5;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;

    /**
     * Adds the collidable to the environment.
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds the sprite to the environment.
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: creates the Blocks, Balls and the Paddle.
    public void initialize() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.sleeper = new Sleeper();
        this.createBorders();
        this.createBlocks();
        Ball ballOne = new Ball(new Point(BALL_ONE_START_X, BALL_ONE_START_Y), BALL_RADIUS, Color.BLUE,
                this.environment);
        ballOne.setVelocity(Velocity.fromAngleAndSpeed(BALL_ONE_START_ANGLE, BALL_SPEED));
        ballOne.addToGame(this);
        Ball ballTwo = new Ball(new Point(BALL_TWO_START_X, BALL_TWO_START_Y), BALL_RADIUS, Color.RED,
                this.environment);
        ballTwo.setVelocity(Velocity.fromAngleAndSpeed(BALL_TWO_START_ANGLE, BALL_SPEED));
        ballTwo.addToGame(this);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Point(PADDLE_START_X, PADDLE_START_Y), BALL_SPEED);
        paddle.addToGame(this);
    }


    /**
     * Creates blocks in the borders of the game window.
     */
    public void createBorders() {
        Block top = new Block(new Point(0, 0), WIDTH, 15, Color.LIGHT_GRAY, 1);
        Block left = new Block(new Point(0, 0), 15, HEIGHT, Color.LIGHT_GRAY, 1);
        Block right = new Block(new Point(WIDTH-15, 0), 15, HEIGHT, Color.LIGHT_GRAY, 1);
        Block bottom = new Block(new Point(0, HEIGHT-15), WIDTH, 15, Color.LIGHT_GRAY, 1);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
    }

    /**
     * Creates the blocks we'll be hitting and destryoing. Four rows, starting at 12 block and decrementing by one
     * each row. The distance between each block is defined by the X and Y offsets.
     */
    public void createBlocks() {
        int yPos = 100;
        int xPos = 122;
        int xOffset = 50;
        int yOffset = 30;
        int edgeLimit = WIDTH - xOffset;
        for (int i = xPos; i < edgeLimit; i+= xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.CYAN, 2);
            newBlock.addToGame(this);
        }
        xPos += xOffset;
        yPos += yOffset;
        for (int i = xPos; i < edgeLimit; i += xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.RED, 1);
            newBlock.addToGame(this);
        }
        xPos += xOffset;
        yPos += yOffset;
        for (int i = xPos; i < edgeLimit; i += xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.YELLOW, 1);
            newBlock.addToGame(this);
        }
        xPos += xOffset;
        yPos += yOffset;
        for (int i = xPos; i < edgeLimit; i += xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.MAGENTA, 1);
            newBlock.addToGame(this);
        }
        xPos += xOffset;
        yPos += yOffset;
        for (int i = xPos; i < edgeLimit; i += xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.PINK, 1);
            newBlock.addToGame(this);
        }
        xPos += xOffset;
        yPos += yOffset;
        for (int i = xPos; i < edgeLimit; i += xOffset) {
            Block newBlock = new Block(new Point(i, yPos), BLOCK_WIDTH, BLOCK_HEIGHT, Color.GREEN, 1);
            newBlock.addToGame(this);
        }
    }

    /**
     * Runs the game. Draws all the objects and then tells them time passed.
     */
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