import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;


public class Game {

    private static final int WIDTH = 800, HEIGHT = 600, BLOCK_WIDTH = 50, BLOCK_HEIGHT = 30;
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
        Ball ballOne = new Ball(new Point(215, 515), 5, Color.BLUE,
                this.environment);
        ballOne.setVelocity(Velocity.fromAngleAndSpeed(321, 3));
        ballOne.addToGame(this);
        Ball ballTwo = new Ball(new Point(215, 515), 5, Color.RED,
                this.environment);
        ballTwo.setVelocity(Velocity.fromAngleAndSpeed(123, 3));
        ballTwo.addToGame(this);
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(keyboard, new Point(BALL_START_X, BALL_START_Y));
        paddle.addToGame(this);
    }


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

    public void createBlocks() {
        int yPos = 100;
        int xPos = 122;
        int xOffset = 51;
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