public class Counter {

    private int currentCount;

    public Counter() {
        this.currentCount = 0;
    }
    // add number to current count.
    public void increase(int number) {
        this.currentCount += number;
    }
    // subtract number from current count.
    public void decrease(int number) {
        this.currentCount -= number;
    }
    // get current count.
    public int getValue() {
        return this.currentCount;
    }
}