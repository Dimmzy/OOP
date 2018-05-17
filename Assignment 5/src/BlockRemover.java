/**
 * BlockRemover class is in charge of removing blocks from the game as well as keeping track of the number of blocks
 * left in the game.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    public BlockRemover(Game game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }

    }
}