package listeners;

import collidables.Block;
import hitters.Ball;
import indicators.Counter;

/**
 * ScoreTrackingListener is a class that's in charge of updating the counter whenever he gets notified by the
 * notifier. Using the observer/listener logic.
 */
public class ScoreTrackingListener implements HitListener {

    private Counter currentScore;

    /**
     * Constructs the ScoreTrackingListener using the scoreCounter passed on.
     * @param scoreCounter The scoreCounter we'll update through the listener.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * If the ScoreTrackingListener receives an update that it should increases the score, it increases the score by
     * 15 if a block has also been destroyed (hp == 0), otherwise it increases it by 5.
     * @param beingHit The block that has been hit.
     * @param hitter The ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            currentScore.increase(15);
        } else {
            currentScore.increase(5);
        }
    }
}