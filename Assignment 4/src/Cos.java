import java.util.List;
import java.util.Map;

/**
 * The Consine expression class, a child of Unary Expression and implements the Expression interface.
 */
public class Cos extends UnaryExpression implements Expression{

    /**
     * Constrcutor using expression, passses the expression to the super class.
     * @param expression expression to construct with.
     */
    public Cos(Expression expression){ super(expression); }

    /**
     * Constructor using a double number, we'll create a new Expression and then pass it to the super class.
     * @param num the num we'll use to construct a new object with.
     */
    public Cos(double num) { super(new Num(num)); }

    /**
     * Constructor using a String variable, we'll create a new Expression and then pass it to the super class.
     * @param var the variable we'll use to construct a new object with.
     */
    public Cos(String var) { super(new Var(var)); }

    /**
     * Assigns the passed variables and evaluates the expression. (Uses the superclass evaluate method0
     * @param assignment a Map containing the variables and their assigned values (key and value).
     * @return returns the evaluation of the expression.
     * @throws Exception if the evaluation failed (arithmetic issues).
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        try {
            return super.evaluate(assignment);
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
            return Math.cos(Math.toRadians(this.exLeft.evaluate()));
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
     * Assigns all occurences of a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.exLeft.assign(var, expression));
    }

    /**
     * @return Returns a string representation of the expression.
     */
    @Override
    public String toString() {
        return "cos(" + super.exLeft.toString() + ")";
    }

    /**
     * Differentiates the expression according to differentiation rules.
     * (-(sin(fx)*f`(x))
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    public Expression differentiate(String var) {
            return new Neg(new Mult(new Sin(super.exLeft), super.exLeft.differentiate(var)));
    }

    /**
     * Simplifies our expression for better readability.
     * If we don't have any variables, we'll simple calculate the expression and return the result (as an Expression).
     * @return returns the simplified expression.
     */
    public Expression simplify() {
        try {
            Expression exSim = super.exLeft.simplify();
            if (exSim.getVariables().isEmpty()) {
                return new Num(exSim.evaluate());
            }
            else {
                return new Cos(exSim);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
