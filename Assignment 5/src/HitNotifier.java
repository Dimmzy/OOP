/**
 * Interface for HitNotifiers.
 */
public interface HitNotifier {

    /**
     * Adds a hit listener that the hit notifier notifies to when an event occurs.
     * @param h1 The hit listener we'll add.
     */
    void addHitListener(HitListener h1);

    /**
     * Removes the hit listener for the listeners list of the current notifier.
     * @param h1 The hit listener to remove.
     */
    void removeHitListener(HitListener h1);
}
