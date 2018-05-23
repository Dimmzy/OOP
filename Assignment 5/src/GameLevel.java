
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * The GameLevel Class. Creates the objects and sets the game in motion.
 */

public class GameLevel implements Animation {

    // Constant values we'll use to define certain constant sizes we'll be using throughout the class.
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600; // Window resolution.
    private static final int STARTING_LIVES = 7; // Amount of lives the player starts with.
    private static final int DEATH_ZONE = 650; // The Y coordinate of the ball removal block (under the screen).
    private static final int PADDLE_START_Y = 555; // The Y coordinate the paddle moves on.
    private static final int BORDER_HEIGHT = 25, BORDER_WIDTH = 25; // Size of the border edges.


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
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner animationRunner) {
        this.levelInfo = levelInfo;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
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
     * Initializes the needed variables for the game, the listeners,borders,blocks (through levelinformation),
     * deathblock and sprites.
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
     * Initializes the counter fields we'll be using in the game, and sets the number of starting lives.
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
     * We'll be setting the health points of the border blocks to -1 so they won't ever be removed when hit.
     */
    public void createBorders() {
        Block top = new Block(new Point(0, 0), SCREEN_WIDTH, BORDER_HEIGHT * 1.5, Color.LIGHT_GRAY, -1);
        Block left = new Block(new Point(0, 0), BORDER_WIDTH, SCREEN_HEIGHT, Color.LIGHT_GRAY, -1);
        Block right = new Block(new Point(SCREEN_WIDTH - BORDER_WIDTH, 0), BORDER_WIDTH, SCREEN_HEIGHT, Color
                .LIGHT_GRAY, -1);
        // The game information panel that will host the number of lives, score, level name.
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
     * Creates a paddle if it doesn't exist, if it does (new round has begun), it resets the position of the paddle to
     * the middle of the screen.
     */
    public void createPaddle() {
        // Checks if we already have a paddle, if we do, reset the paddle's position and return.
        for (Collidable collidable : this.environment.getObjectList()) {
            if (collidable instanceof Paddle) {
                collidable.getCollisionRectangle().setNewLocation(
                        (new Point(SCREEN_WIDTH / 2 - levelInfo.paddleWidth() / 2, PADDLE_START_Y)));
                return;
            }
        }
        // If we don't have a paddle, we'll create one with the center of the paddle in the center of the screen.
        Paddle paddle = new Paddle(keyboard, new Point(SCREEN_WIDTH / 2 - levelInfo.paddleWidth() / 2,
                PADDLE_START_Y), levelInfo.paddleWidth(), levelInfo.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Adds the balls created in the levelInformation class to our game.
     */
    public void createBalls() {
        for (Ball ball : levelInfo.balls()) {
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            ballCounter.increase(1);
        }
    }

    /**
     * Plays one turn of the game. Creates the balls and the paddle, runs the countdown animation and afterwards starts
     * running the game animation.
     */
    public void playOneTurn() {
        this.createBalls();
        this.createPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }


    /**
     * Plays new turns so long as we have lives left.
     */
    public void run() {
        while(livesCounter.getValue() >= 0) {
            this.playOneTurn();
            this.livesCounter.decrease(1);
        }
        gui.close();
    }

    /**
     * @return Method that returns if we should stop running the animation.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Each frame of the game we'll draw all the sprites we have to draw and tell them that time has passed.
     * Also allows for pausing of the game by scanning for the 'p' keystroke, if it does runs the PauseScreen.
     * If no more blocks or balls are left, we'll stop running the current level's animation.
     * @param d The drawsurface we'll draw the sprites on.
     */
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