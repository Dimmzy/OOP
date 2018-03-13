/**
 * Just a simple driver class.
 */
public class Main {

    /**
     * Driver Program.
     * @param args whatever.
     */
    public static void main(String[] args) {
        Point pointOne = new Point(13, 22);
        Point pointTwo = new Point(124123, 222.121);
        Point pointThree = new Point(13, 22);
        System.out.println("Does p1 equal p2?");
        System.out.println(pointOne.equals(pointTwo));
        System.out.println("What are the x and y values of p1?");
        System.out.println(pointOne.getX() + " " + pointOne.getY());
        System.out.println("Does p1 equal p3?");
        System.out.println(pointOne.equals(pointThree));
        System.out.println("What is the distance between p1 and p2?");
        System.out.println(pointOne.distance(pointTwo));
    }
}
