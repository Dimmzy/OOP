package sprites;

import biuoop.DrawSurface;
import collidables.Block;
import collidables.Collidable;
import gamelogic.GameEnvironment;
import gamelogic.GameLevel;
import geometry.Point;
import geometry.Rectangle;
import hitters.Bullet;
import hitters.Velocity;
import listeners.HitListener;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * AlienGroup class is in charge of keeping the aliens in formation and provides the movement and shooting logic.
 */
public class AlienGroup implements Sprite, Collidable {

    // Several constants for the starting locations and offsets, and also a value for the shield location.
    private static final int ALIEN_START_X = 100, ALIEN_OFFSET_X = 50, ALIEN_START_Y = 50, ALIEN_OFFSET_Y = 40;
    private static final int DEATH_ZONE = 500;

    private int numOfAliens;
    private List<Image> alienImages;
    private Velocity alienSpeed;
    private List<List<Block>> alienColumns;
    private double lastShot;
    private GameEnvironment ge;
    private GameLevel level;
    private Velocity startingSpeed;

    /**
     * Constructs the alien group formation.
     * @param alienSpeed the starting speed of the aliens.
     * @param alienImages the images we'll draw of the aliens.
     * @param ge the game environment the level is a part of.
     * @param level the level the enemies are a part of.
     */
    public AlienGroup(Velocity alienSpeed, List<Image> alienImages, GameEnvironment ge, GameLevel level) {
        this.alienColumns = new ArrayList<List<Block>>();
        this.startingSpeed = alienSpeed;
        this.alienSpeed = alienSpeed;
        this.alienImages = alienImages;
        this.lastShot = System.currentTimeMillis();
        this.ge = ge;
        this.level = level;
    }

    /**
     * Creates the aliens in formation.
     * @param blockRemover a listener for removing the alien when he's hit.
     * @param scoreTracker a listener to increase the score by 100 when an alien is hit,
     * @param ballRemover a listener to remove the bullet that hit the alien.
     */
    public void createAliens(HitListener blockRemover, HitListener scoreTracker, HitListener ballRemover) {
        int currColumn = 0;
        for (int i = ALIEN_START_X; i <= ALIEN_START_X * 6.5; i += ALIEN_OFFSET_X + 10) {
            alienColumns.add(currColumn, new ArrayList<Block>());
            for (int j = ALIEN_START_Y; j <= ALIEN_START_Y * 5; j += ALIEN_OFFSET_Y + 10) {
                Block newBlock = new Block(new Point(i, j), 40, 30, this.alienSpeed);
                newBlock.setAlienImage(this.alienImages);
                newBlock.addHitListener(blockRemover);
                newBlock.addHitListener(scoreTracker);
                newBlock.addHitListener(ballRemover);
                newBlock.addToGame(level);
                newBlock.setColumn(this.alienColumns.get(currColumn));
                this.numOfAliens++;
                this.alienColumns.get(currColumn).add(newBlock);
            }
            currColumn++;
        }
    }

    /**
     * @return returns the number of aliens (the number we need to kill to finish the level)
     */
    public int getNumOfAliens() {
        return this.numOfAliens;
    }

    /**
     * calls the ResetLocation method of the rectangles of the aliens to reset them to their starting positions when
     * the player loses a life.
     */
    public void resetAliens() {
        for (List<Block> blockColumn : this.alienColumns) {
            for (Block block : blockColumn) {
                 block.resetPosition();
            }
        }
    }

    /**
     * Draws the aliens on the screen.
     * @param d The surface tod raw the object on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (List<Block> aColumn : this.alienColumns) {
            for (Block block : aColumn) {
                block.drawOn(d);
            }
        }
    }

    /**
     * Tells the aliens to move (and keep in formation, moving down when hitting the screen border).
     * @param dt let's use normalize the speed of the moving objects to be pixels per second rather than fps.
     */
    @Override
    public void timePassed(double dt) {
        if (this.wallCollision(dt)) {
            this.alienSpeed = new Velocity(this.alienSpeed.getDx() * -1.1, 0);
            for (List<Block> blockList : this.alienColumns) {
                for (Block block : blockList) {
                    block.getCollisionRectangle().setNewLocation(new Point(
                            block.getCollisionRectangle().getUpperLeft().getX() + this.alienSpeed.getDx() * dt,
                            block.getCollisionRectangle().getUpperLeft().getY() + 10));
                    // Kills the player if the aliens reached the shields y location (DEATH ZONE)
                    if (block.getCollisionRectangle().getUpperLeft().getY()
                            + block.getCollisionRectangle().getHeight() + 10 >= DEATH_ZONE) {
                        this.level.getLivesCounter().decrease(1);
                        this.level.stopAnimation();
                    }
                }
            }
        }
        for (List<Block> blockList : this.alienColumns) {
            for (Block block : blockList) {
                block.getCollisionRectangle().setNewLocation(new Point(
                        block.getCollisionRectangle().getUpperLeft().getX() + this.alienSpeed.getDx() * dt,
                        block.getCollisionRectangle().getUpperLeft().getY()));
            }
        }
        if (System.currentTimeMillis() - this.lastShot > 500) {
            this.lastShot = System.currentTimeMillis();
            Random rand = new Random();
            int column;
            // Checks that the next shooting column isn't empty
            while (true) {
                column = rand.nextInt(10);
                if (!(this.alienColumns.get(column).isEmpty())) {
                    break;
                }
            }
            this.shoot(column);
        }
    }

    /**
     * Checks if the aliens are colliding with the screen border from the side.
     * @param dt used for measuring their movement.
     * @return returns true if they hit a wall, false otherwise.
     */
    private boolean wallCollision(double dt) {
        if (this.alienSpeed.getDx() > 0) {
            for (int i = this.alienColumns.size() - 1; i >= 0; i--) {
                for (Block block : this.alienColumns.get(i)) {
                    Rectangle colRect = block.getCollisionRectangle();
                    if (colRect.getUpperLeft().getX() + this.alienSpeed.getDx() * dt + colRect.getWidth() >= 800) {
                        return true;
                    }
                }
            }
        } else {
            for (int i = 0; i <= this.alienColumns.size() - 1; i++) {
                for (Block block : this.alienColumns.get(i)) {
                    Rectangle colRect = block.getCollisionRectangle();
                    if (colRect.getUpperLeft().getX() + this.alienSpeed.getDx() * dt <= 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Makes the bottom-most alien in a random column that's passed shoot (timed every 0.5 seconds).
     * @param column the column we'll shoot form.
     */
    private void shoot(int column) {
        int bulletY = 0;
        int bulletX = 0;
        for (Block block : alienColumns.get(column)) {
            if (block.getCollisionRectangle().getUpperLeft().getY() > bulletY) {
                bulletY = (int) block.getCollisionRectangle().getUpperLeft().getY() + 40;
                bulletX = (int) block.getCollisionRectangle().getUpperLeft().getX();
            }
        }
        Bullet shot = new Bullet(new Point(bulletX, bulletY), 3, this.ge, Color.RED);
        shot.setVelocity(Velocity.fromAngleAndSpeed(180, 250));
        shot.addToGame(level);
    }

    /**
     * Isn't used.
     * @return null;
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return null;
    }

    /**
     * Resets the speed of the aliens to their starting speed from the beginning of the level (when the player dies).
     */
    public void resetSpeed() {
        this.alienSpeed = new Velocity(this.startingSpeed.getDx(), 0);
    }

    /**
     * Isn't used.
     * @param hitter not used.
     * @param collisionRectangle not used.
     * @param currentVelocity not used.
     * @return not used,
     */
    @Override
    public Velocity hit(Bullet hitter, Rectangle collisionRectangle, Velocity currentVelocity) {
        return null;
    }

}
