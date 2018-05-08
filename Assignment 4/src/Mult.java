import java.util.List;
import java.util.Map;

/**
 * The Multiplication Expression class. Extends Binary Expression and implements the Expression interface.
 */
public class Mult extends BinaryExpression implements Expression {


    /**
     * Constructor using two expressions. Passes them to the superclass.
     * @param ex1 left expression.
     * @param ex2 right expression.
     */
    public Mult(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * Constructor using two numbers. Creating expressions from the numbers and passes them to the superclass.
     * @param num1 first number (left expression).
     * @param num2 second number (right expression).
     */
    public Mult(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructor using a variable and a number. Creates expressions from them and passes them to the superclass.
     * @param var a variable (left expression).
     * @param num a number (right expression).
     */
    public Mult(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * Constructor using a variable and a number. Creates expressions from them and passes them to the superclass.
     * @param var a variable (right expression).
     * @param num a number (left expression).
     */
    public Mult(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructor using two variables. Creates expressions from them and then passes them to the superclass.
     * @param var1 first variable (left expression).
     * @param var2 second variable (right expression).
     */
    public Mult(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * Constructor using an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex the left expression.
     * @param num a number (right expression).
     */
    public Mult(Expression ex, double num) { super(ex, new Num(num)); }

    /**
     * Constructor using an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex the right expression.
     * @param num a number (left expression).
     */
    public Mult(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructor using an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex the left expression.
     * @param var a variable (right expression).
     */
    public Mult(Expression ex, String var) { super(ex, new Var(var)); }

    /**
     * Constructor using an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex the right expression.
     * @param var a variable (left expression).
     */
    public Mult(String var, Expression ex) { super(new Var(var), ex); }


    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExLeft().evaluate(assignment) * super.getExRight().evaluate(assignment));
    }

    /**
     * Evaluates the expression without assigning anything.
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate() throws Exception {
        try {
            return super.getExLeft().evaluate() * super.getExRight().evaluate();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Assigns all occurrences of a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(super.getExLeft().assign(var, expression), super.getExRight().assign(var, expression));
    }

    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return "(" + super.getExLeft().toString() + " * " + super.getExRight().toString() + ")";
    }

    /**
     * @return Returns a list of variables the expression contains. (Uses the super-class Binary Expression's method).
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Differentiates the expression according to differentiation rules.
     *  (f(x) * g(x))` = f`(x) * g(x) + f(x) * g`(x)
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
            return new Plus(new Mult(super.getExLeft().differentiate(var),
                    super.getExRight()), new Mult(super.getExLeft(), super.getExRight().differentiate(var)));

    }

    /**
     * Simplifies the expression to improve readability.
     * 1. If both expressions are numbers, calculate their product and return it as an expression. (5*5=25)
     * 2. If one of the sides equates to the number 0 returns new expression of the number 0. (0*x = 0)
     * 3. If one of the sides equates to the number 1 returns the other side of the expression. (1*x = x)
     * @return returns the simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
        Expression exLeftSim = super.getExLeft().simplify();
        Expression exRightSim = super.getExRight().simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(exLeftSim.evaluate() * exRightSim.evaluate());
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0.0) {
                    return new Num(0);
                } else if (exLeftSim.evaluate() == 1.0) {
                    return exRightSim;
                }
                return new Mult(new Num(exLeftSim.evaluate()), exRightSim);
            } else if (exRightSim.getVariables().isEmpty()) {
                if (exRightSim.evaluate() == 0.0) {
                    return new Num(0);
                } else if (exRightSim.evaluate() == 1.0) {
                    return exLeftSim.simplify();
                }
                return new Mult(exLeftSim, new Num(exRightSim.evaluate()));
            } else { return new Mult(exLeftSim, exRightSim); }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
