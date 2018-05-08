import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Number class. Implements the Expression interface.  Represents double numbers.
 */
public class Num implements Expression {

    private double number;

    /**
     * Constructs the object from a given double number.
     * @param number the number to be turned into an expression.
     */
    public Num(double number) {
        this.number = number;
    }

    /**
     * Evaluates the expression after assigning expressions to it's variables.
     * In this case simply returns the value of the number.
     * @param assignment Doesn't do anything in this case.
     * @return returns the value of the number.
     * @throws Exception throws exception if it encounters an undefined behavior.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return this.number;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Evaluates the expression. In our case simply returns the value of the number.
     * @return returns the value of the number field.
     * @throws Exception throws exception if it encounters an undefined behavior.
     */
    public double evaluate() throws Exception {
        try {
            return this.number;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return returns an empty list (Num isn't a variable)
     */
    public List<String> getVariables() {
        return new LinkedList<>();
    }

    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return Double.toString(number);
    }


    /**
     * Assigns all occurrences of a variable to the expression and returns the new assigned expression.
     * In our case simply returns the same object since we have no variables to assign.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns the same unchanged expression object.
     */
    public Expression assign(String var, Expression expression) { return this; }

    /**
     * Differentiates the expression according to differentiation rules.
     * n` = 0
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Simplifies the expression to improve readability.
     * In our case simply returns the same object (nothing to simplify).
     * @return returns the same object.
     */
    @Override
    public Expression simplify() {
        return this;
    }
}
