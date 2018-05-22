
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameLevel Class. Creates the objects and sets the game in motion.
 */

public class GameLevel implements Animation {

    // Constant values we'll use to define the the window, blocks size and ball location.
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;
    private static final int STARTING_LIVES = 4;
    private static final int DEATH_ZONE = 650;
    private static final int BORDER_HEIGHT = 25, BORDER_WIDTH = 25;
    private static final int PADDLE_START_X = 375, PADDLE_START_Y = 555;
    private LevelInformation levelInfo;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blockCounter, ballCounter, scoreCounter, livesCounter;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;

    /**
     * Constructs the GameLevel object using passed level information.
     * @param levelInfo The information about the level design.
     */
    public GameLevel(LevelInformation levelInfo) {
        this.levelInfo = levelInfo;
    }

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

    /**
     * Calls the game environment to remove the collidable the method receives.
     * @param c The collidable to remove from the environment.
     */
    public void removeCollidable(Collidable c) {
        try {
            this.environment.removeCollidalbe(c);
        } catch (Exception e) {
            System.out.println("Collidable to remove not found");
        }
    }

    /**
     * Calls the sprite list to remove the sprite the method receives.
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Initializes all the needed variables for a new game: user interface, sprites, game environment, blocks, balls
     * and a paddle.
     */
    public void initialize() {
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = new AnimationRunner(gui);
        this.keyboard = this.gui.getKeyboardSensor();
        this.initCounters();
        HitListener blockRemover = new BlockRemover(this, this.blockCounter);
        HitListener ballRemover = new BallRemover(this, this.ballCounter);
        HitListener scoreTracker = new ScoreTrackingListener(this.scoreCounter);
        this.sprites.addSprite(levelInfo.getBackground());
        this.createBorders();
        this.createBlocks(blockRemover, scoreTracker);
        this.addSprite(new ScoreIndicator(this.scoreCounter));
        this.addSprite(new LivesIndicator(this.livesCounter));
        this.addSprite(new LevelIndicator(levelInfo.levelName()));
        Block deathBlock = new Block(new Point (0,DEATH_ZONE), SCREEN_WIDTH, 0, Color.LIGHT_GRAY, -1);
        deathBlock.addHitListener(ballRemover);
        deathBlock.addToGame(this);
    }


    /**
     * Initializes the counter fields we'll be using in the game.
     */
    public void initCounters() {
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.scoreCounter = new Counter();
        this.livesCounter = new Counter();
        this.livesCounter.increase(STARTING_LIVES);
    }

    /**
     * Creates blocks in the borders of the game window.
     */
    public void createBorders() {
        Block top = new Block(new Point(0, 0), SCREEN_WIDTH, BORDER_HEIGHT * 1.5, Color.LIGHT_GRAY, -1);
        Block left = new Block(new Point(0, 0), BORDER_WIDTH, SCREEN_HEIGHT, Color.LIGHT_GRAY, -1);
        Block right = new Block(new Point(SCREEN_WIDTH - BORDER_WIDTH, 0), BORDER_WIDTH, SCREEN_HEIGHT, Color
                .LIGHT_GRAY, -1);
        Block gameInfo = new Block(new Point(0,0), SCREEN_WIDTH, BORDER_HEIGHT / 1.5, Color.WHITE, -1);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        gameInfo.addToGame(this);
    }

    /**
     * Creates the blocks we'll be hitting and destroying. Uses the block list retrieved from the levelInfo and adds
     * the required listeners to each block and then adds them to the game.
     */
    public void createBlocks(HitListener blockRemover, HitListener scoreTracker) {
        for (Block block : levelInfo.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTracker);
            block.addToGame(this);
            blockCounter.increase(1);
        }
    }

    /**
     * Creates a paddle if it doesn't exist, if it does, it resets the position of the paddle to the middle of the
     * screen. (Happens when the player loses a life)
     */
    public void createPaddle() {
        // Checks if we already have a paddle, if we do, reset it's position.
        for (Collidable collidable : this.environment.getObjectList()) {
            if (collidable instanceof Paddle) {
                collidable.getCollisionRectangle().setNewLocation(
                        (new Point(SCREEN_WIDTH / 2 - levelInfo.paddleWidth() / 2, PADDLE_START_Y)));
                return;
            }
        }
        // If we don't, create it.
        Paddle paddle = new Paddle(keyboard, new Point(SCREEN_WIDTH / 2 - levelInfo.paddleWidth() / 2,
                PADDLE_START_Y), levelInfo.paddleWidth(), levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Adds the balls created in the levelinformation class to our game.
     */
    public void createBalls() {
        for (Ball ball : levelInfo.balls()) {
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            ballCounter.increase(1);
        }
    }
    /**
     * Runs the game. Draws all the objects and then tells them time passed.
     */
    public void playOneTurn() {
        this.createBalls();
        this.createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }


    public void run() {
        while(livesCounter.getValue() >= 0) {
            this.playOneTurn();
            this.livesCounter.decrease(1);
        }
        gui.close();
    }

    public boolean shouldStop() {
        return !this.running;
    }

    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.blockCounter.getValue() == 0 || this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }
}