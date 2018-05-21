/**
 * BallRemover class is in charge of removed balls when they pass the bottom of the screen.
 */
public class BallRemover implements HitListener{
    private Counter removedBalls;
    private GameLevel gameLevel;

    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.removedBalls = removedBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        removedBalls.decrease(1);
    }
}
