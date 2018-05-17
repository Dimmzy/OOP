/**
 * BallRemover class is in charge of removed balls when they pass the bottom of the screen.
 */
public class BallRemover implements HitListener{
    private Counter removedBalls;
    private Game game;

    public BallRemover(Game game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        removedBalls.decrease(1);
    }
}
