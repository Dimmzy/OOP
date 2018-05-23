/**
 * The Counter class provides us with the the ability to keep track of various variables in our game.
 */

public class Counter {

    private int currentCount;

    /**
     * The counter constructs, each of our counters will start at 0.
     */
    public Counter() {
        this.currentCount = 0;
    }

    /**
     * Increments the counter by number.
     * @param number The number which we'll increment the counter by.
     */
    public void increase(int number) {
        this.currentCount += number;
    }

    /**
     * Decrement the counter by number.
     * @param number The number which we'll decrement the counter by.
     */
    public void decrease(int number) {
        this.currentCount -= number;
    }

    /**
     * @return returns the current value of the counter.
     */
    public int getValue() {
        return this.currentCount;
    }
}