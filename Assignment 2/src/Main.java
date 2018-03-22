/**
 * Just a simple driver class.
 */
public class Main {

    /**
     * Driver Program.
     * @param args whatever.
     */
    public static void main(String[] args) {
        Line l1 = new Line(-7, -5, 4, 17);
        Line l2 = new Line(-4, -5, 3, 18);
        System.out.println("X Intersection" + l2.xIntersect(l1));
        System.out.println("Y Intersection" + l2.yIntersect(l2.xIntersect(l1)));
    }
}
