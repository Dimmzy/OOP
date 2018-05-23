
/**
 * BlockRemover class is in charge of removing blocks from the gameLevel as well as keeping track of the number of blocks
 * left in the gameLevel,  using listener/observer logic.
 */
public class BlockRemover implements HitListener {

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * The BlockRemover constructor. receives a counter of the *current* remaining blocks (same reason as ball
     * remover) and the game level we're operating on.
     * @param gameLevel The game level the blocks are part of.
     * @param removedBlocks The number of current blocks still present in game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * If the BlockRemover receives an update that the ball has been hit, it checks if its "dead" (hp==0), and if so
     * it removes the block's listener and removes it from game.
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block (won't be used since we're only applying operations on the block
     *               itself)
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }

    }
}