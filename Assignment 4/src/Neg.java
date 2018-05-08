import java.util.List;
import java.util.Map;

/**
 * Class of the Negate expression. Extends teh Unary Expression abstract class and implements the Expression interface.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor from an expression. Passes the expression to the superclass.
     * @param expression the unary expression.
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Constructor from a number. Creates an expression from the number and passes it to the superclass.
     * @param num a number (becomes an unary expression)
     */
    public Neg(double num) {
        super(new Num(num));
    }

    /**
     * Constructor from a variable. Creates an expression from the variable and passes it to the superclass.
     * @param var a variable (becomes an unary expression).
     */
    public Neg(String var) {
        super(new Var(var));
    }

    /**
     * Assigns the passed variables and evaluates the expression. (Uses the superclass evaluate method0
     * @param assignment a Map containing the variables and their assigned values (key and value).
     * @return returns the evaluation of the expression.
     * @throws Exception if the evaluation failed (arithmetic issues).
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return -1 * super.getExLeft().evaluate(assignment);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Evaluates the expression without assigning variables. Converts the value to radians and performs cosine function.
     * @return returns the evaluation of the expression.
     * @throws Exception Failed to evaluate the expression.
     */
    @Override
    public double evaluate() throws Exception {
        try {
            return super.getExLeft().evaluate() * -1;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return Returns a list of variables the expression contains. (Uses the super-class Unary Experssion's method).
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Assigns all occurrences of a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.getExLeft().assign(var, expression));
    }

    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return "(" + "-" + super.getExLeft().toString() + ")";
    }

    /**
     * Differentiates the expression according to differentiation rules.
     * -f(x) = -f`(x)
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Mult(new Num(-1), super.getExLeft().differentiate(var));
    }

    /**
     * Simplifies our expression for better readability.
     * If we don't have any variables, we'll simply calculate the expression and return the result (as an Expression).
     * @return returns the simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            Expression exSim = super.getExLeft().simplify();
            if (exSim.getVariables().isEmpty()) {
                return new Num(exSim.evaluate());
            } else {
                return new Neg(exSim);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
