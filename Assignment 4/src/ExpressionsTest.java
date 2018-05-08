import java.util.Map;
import java.util.TreeMap;

/**
 * Test class for assignment 4.
 */
public class ExpressionsTest {
    /**
     * Driver function.
     * @param args not used.
     * @throws Exception throws exceptions on unexpected behavior.
     */
    public static void main(String[] args) throws Exception {
        Expression e = new Plus(new Plus(new Mult(2, "x"),
                new Sin(new Mult(4, "y"))), new Pow("e", "x"));
        System.out.println(e.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", (double) 2);
        assignment.put("y", 0.25);
        assignment.put("e", Math.E);
        double value = e.evaluate(assignment);
        System.out.println(value);
        Expression de = e.differentiate("x");
        System.out.println(de);
        System.out.println(de.evaluate(assignment));
        System.out.println(de.simplify());
    }
}
