package listeners;

import collidables.Block;
import gamelogic.GameLevel;
import hitters.Bullet;
import indicators.Counter;

/**
 * BallRemover class is in charge of removed balls when they pass the bottom of the screen using listener/observer
 * logic.
 */
public class BallRemover implements HitListener {

    private Counter removedBalls;
    private GameLevel gameLevel;

    /**
     * BallRemover constructor, sets the number of current balls and the current level we'll be working with.
     * @param gameLevel The current game level.
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * If the BallRemover listener receives an update that the ball hit the "death block", we'll remove the ball from
     * the game by calling the removeFromGame method of the ball.
     * @param beingHit The block that was hit, won't be used since our logic applies only to thee ball itself.
     * @param hitter The hitting ball that will be removed from the game.
     */
    public void hitEvent(Block beingHit, Bullet hitter) {
        hitter.removeFromGame(gameLevel);
    }
}
