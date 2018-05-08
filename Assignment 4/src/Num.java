import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Num implements Expression{

    private double number;

    public Num (double number) {
        this.number = number;
    }

    public double evaluate(Map<String, Double> assignment) throws Exception { return this.number; }

    /**
     *
     * @return
     * @throws Exception
     */
    public double evaluate() throws Exception{ return this.number; }

    /**
     * @return returns an empty list (Num isn't a variable)
     */
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        return varList;
    }

    /**
     * @return returns the number saved in the field as a string
     */
    public String toString() {
        return Double.toString(number);
    }


    /**
     *
     * @param var
     * @param expression
     * @return
     */
    public Expression assign(String var, Expression expression) { return this; }

    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
