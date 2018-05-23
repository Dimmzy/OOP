/**
 * Interface for the HitListeners.
 */
public interface HitListener {

    /**
     * The actions to perform whenever the block has been hit.
     * @param beingHit The block that has been hit.
     * @param hitter The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
