/**
 * Announces when a block is being hit and at which location.
 */
public class PrintingHitListener implements HitListener{
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
