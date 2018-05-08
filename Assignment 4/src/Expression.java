import java.util.Map;
import java.util.List;
/**
 * The expression interface.
 * Provides methods to it's implementing classes for managing their expressions.
 */
public interface Expression {

    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception for unexpected behavior.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluates the expression without assigning anything.
     * @return returns the evaluation of the expression.
     * @throws Exception throws an exception in case we received some undefined behavior.
     */
    double evaluate() throws Exception;

    /**
     * @return Creates and returns a list of variables inside the expression.
     */
    List<String> getVariables();

    /**
     * @return returns the string representation of the expression.
     */
    String toString();

    /**
     * Assigns a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */
    Expression assign(String var, Expression expression);

    /**
     * Differentiates the expression according to differentiation rules.
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    Expression differentiate(String var);

    /**
     * Simplifies the expression to improve readability.
     * @return returns the simplified expression, according to our simplification rules in each class.
     */
    Expression simplify();

}
