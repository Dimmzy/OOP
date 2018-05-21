/**
 * BlockRemover class is in charge of removing blocks from the gameLevel as well as keeping track of the number of blocks
 * left in the gameLevel.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }

    }
}