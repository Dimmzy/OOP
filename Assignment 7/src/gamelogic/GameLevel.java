package gamelogic;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidables.Block;
import collidables.Collidable;
import collidables.Ship;
import geometry.Point;
import hitters.Bullet;
import hitters.Velocity;
import indicators.Counter;
import indicators.LivesIndicator;
import indicators.ScoreIndicator;
import levels.LevelIndicator;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;
import sprites.AlienGroup;
import sprites.BackgroundColor;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The GameLevel Class. Creates the objects and sets the game in motion.
 */

public class GameLevel implements Animation {

    // Constant values we'll use to define certain constant sizes we'll be using throughout the class.
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600; // Window resolution.
    private static final int SHIP_START_Y = 555, SHIP_WIDTH = 50, SHIP_SPEED = 500; // The Y coordinate the


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCounter, scoreCounter, livesCounter;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private List<Image> alienImages;
    private Velocity alienSpeed;
    private AlienGroup aliensGroup;


    /**
     * Constructs the GameLevel object using passed level information.
     *
     * @param keyboardSensor  The keyboard sensor we'll use to scan for keystrokes.
     * @param animationRunner The animation runner object that runs the animation of this level.
     * @param lives           The player's lives counter.
     * @param score           The player's score counter.
     * @param alienImages the images of the aliens different phases extracted from files.
     * @param alienSpeed the speed of the aliens.
     */
    public GameLevel(KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter lives, Counter score, Velocity alienSpeed, List<Image> alienImages) {
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;
        this.scoreCounter = score;
        this.livesCounter = lives;
        this.alienSpeed = alienSpeed;
        this.alienImages = alienImages;
    }

    /**
     * Adds the collidable to the environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds the sprite to the environment.
     *
     * @param s the sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Calls the game environment to remove the collidable the method receives.
     *
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
     *
     * @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initializes the game, creates the borders, shields, player's ship and aliens in formation.
     */
    public void initialize() {

        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCounter = new Counter();
        this.addSprite(new BackgroundColor(Color.BLACK));
        HitListener blockRemover = new BlockRemover(this, this.blockCounter);
        HitListener ballRemover = new BallRemover(this);
        HitListener scoreTracker = new ScoreTrackingListener(this.scoreCounter);
        this.aliensGroup = new AlienGroup(this.alienSpeed, this.alienImages, environment, this);
        this.aliensGroup.createAliens(blockRemover, scoreTracker, ballRemover);
        this.addSprite(aliensGroup);
        this.createShip();
        this.createBorders(ballRemover);
        this.blockCounter.increase(aliensGroup.getNumOfAliens());
        this.addSprite(new ScoreIndicator(this.scoreCounter));
        this.addSprite(new LivesIndicator(this.livesCounter));
        this.addSprite(new LevelIndicator("Space Invaders"));
        // Creates three shields, each in a different quarter of the screen
        this.createShields(blockRemover, ballRemover, 1);
        this.createShields(blockRemover, ballRemover, 2);
        this.createShields(blockRemover, ballRemover, 3);
    }

    /**
     * In charge of creating the borders to remove bullets (so they won't get saved in memory for no reason once
     * they're out of bounds).
     * @param ballRemover the ball remover listener we use to remove the bullets.
     */
    private void createBorders(HitListener ballRemover) {
        Block upperMenu = new Block(new Point(0, 0), SCREEN_WIDTH, SCREEN_HEIGHT / 30, Color.WHITE);
        upperMenu.addHitListener(ballRemover);
        Block bottomBallRemover = new Block(new Point(0, 600), SCREEN_WIDTH, 0, Color.WHITE);
        bottomBallRemover.addHitListener(ballRemover);
        Block leftBallRemover = new Block(new Point(0, 0), 0, SCREEN_HEIGHT, Color.WHITE);
        leftBallRemover.addHitListener(ballRemover);
        Block rightBallRemover = new Block(new Point(800, 0), 0, SCREEN_HEIGHT, Color.WHITE);
        rightBallRemover.addHitListener(ballRemover);
        this.addSprite(upperMenu);
        this.addCollidable(upperMenu);
        this.addSprite(bottomBallRemover);
        this.addCollidable(bottomBallRemover);
        this.addSprite(leftBallRemover);
        this.addCollidable(leftBallRemover);
        this.addSprite(rightBallRemover);
        this.addCollidable(rightBallRemover);
    }




    /**
     * Creates a ship if it doesn't exist, if it does (new round has begun), it resets the position of the paddle to
     * the middle of the screen.
     */
    private void createShip() {
        // Checks if we already have a paddle, if we do, reset the paddle's position and return.
        for (Collidable collidable : this.environment.getObjectList()) {
            if (collidable instanceof Ship) {
                collidable.getCollisionRectangle().setNewLocation(
                        (new Point(SCREEN_WIDTH / 2 - SHIP_WIDTH / 2, SHIP_START_Y)));
                return;
            }
        }
        // If we don't have a ship, we'll create one with the center of the ship in the center of the screen.
        Ship ship = new Ship(keyboard, new Point(SCREEN_WIDTH / 2 - 50 / 2,
                SHIP_START_Y), SHIP_WIDTH, SHIP_SPEED, this.environment, this);
        ship.addToGame(this);
    }

    /**
     * Plays one turn of the game. Creates the balls and the paddle, runs the countdown animation and afterwards starts
     * running the game animation.
     */
    public void playOneTurn() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
        this.aliensGroup.resetAliens();
        this.aliensGroup.resetSpeed();
        this.removeBullets();
    }

    /**
     * @return Returns the lives counter.
     */
    public Counter getLivesCounter() {
        return this.livesCounter;
    }

    /**
     * Creates shields at the specified part of the game screen, and adds the needed listeners to it.
     * The shield is made up of several small blocks.
     * @param blockRemover the listener is in charge of removing a small shield block when it's hit.
     * @param ballRemover the listener is in charge of removing the bullet that hit the shield block.
     * @param location a specific quarter of the screen.
     */
    public void createShields(HitListener blockRemover, HitListener ballRemover, int location) {
        for (int i = (SCREEN_WIDTH - 100) * location / 4; i <= (SCREEN_WIDTH - 100) * location / 4 + 120; i += 12) {
            for (int j = SCREEN_HEIGHT - 100; j <= SCREEN_HEIGHT - 85; j += 5) {
                Block newBlock = new Block(new Point(i, j), 12, 5, Color.BLUE);
                newBlock.addHitListener(blockRemover);
                newBlock.addHitListener(ballRemover);
                newBlock.addToGame(this);
            }
        }

    }

    /**
     * @return Returns the blockCounter
     */
    public Counter getBlockCounter() {
        return this.blockCounter;
    }

    /**
     * Removes bullets when a new round begins (player loses a life).
     */
    public void removeBullets() {
        List<Collidable> colList = new ArrayList<Collidable>(this.environment.getObjectList());
        for (Collidable collidable : colList) {
            if (collidable instanceof Bullet) {
                this.removeCollidable(collidable);
            }
        }
        List<Sprite> spriteList = new ArrayList<Sprite>(this.sprites.getSpriteList());
        for (Sprite sprite : spriteList) {
            if (sprite instanceof Bullet) {
                this.removeSprite(sprite);
            }
        }
    }

    /**
     * @return Returns if we should stop running the animation after this frame.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Stops the animation (when the ship is hit or the aliens reached the shields).
     */
    public void stopAnimation() {
        this.running = false;
    }

    /**
     * Each frame of the game we'll draw all the sprites we have to draw and tell them that time has passed.
     * Also allows for pausing of the game by scanning for the 'p' keystroke, if it does runs the PauseScreen.
     * If no more blocks or balls are left, we'll stop running the current level's animation.
     *
     * @param d The DrawSurface we'll draw the sprites on.
     * @param dt isn't used.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new PauseScreen(this.keyboard));
        }
        if (this.blockCounter.getValue() == 0) {
            this.running = false;
        }
    }
}