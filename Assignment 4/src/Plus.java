import java.util.List;
import java.util.Map;

/**
 * Plus expression class. Extends Binary Expression abstract class and implements the Expression interface.
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Constructs using a variable and a number. Creates two expressions from them and passes them to the superclass.
     * @param variable a variable (left expression).
     * @param number a number (right expression).
     */
    public Plus(String variable, double number) {
        super(new Var(variable), new Num(number));
    }

    /**
     * Constructs from two given expressions. Passes them to the superclass.
     * @param e1 left expression.
     * @param e2 right expression.
     */
    public Plus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructs from two given numbers. Creates two expressions from them and passes them to the superclass.
     * @param num1 first number (left expression).
     * @param num2 second number (right expression).
     */
    public Plus(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructs from a number and a variable. Creates two expressions from them and passes them to the superclass.
     * @param num a number (left expression).
     * @param var a variable (right expression).
     */
    public Plus(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructs from two variables. Creates two expressions from them and passes them to the superclass.
     * @param var1 first variable (left expression).
     * @param var2 second variable (right expression).
     */
    public Plus(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * Constructs from an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex left expression.
     * @param num a number (right expression).
     */
    public Plus(Expression ex, double num) { super(ex, new Num(num)); }

    /**
     * Constructs from an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex right expression.
     * @param num a number (left expression).
     */
    public Plus(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructs from an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex left expression.
     * @param var a variable (right expression).
     */
    public Plus(Expression ex, String var) { super(ex, new Var(var)); }

    /**
     * Constructs from an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex right expression.
     * @param var a variable (left expression).
     */
    public Plus(String var, Expression ex) { super(new Var(var), ex); }


    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExLeft().evaluate(assignment) + super.getExRight().evaluate(assignment));
    }

    /**
     * Evaluates the expression without assigning anything.
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate() throws Exception {
        try {
            return super.getExLeft().evaluate() + super.getExRight().evaluate();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * @return Returns a list of variables the expression contains. (Uses the super-class Binary Expression's method).
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
        return new Plus(super.getExLeft().assign(var, expression), super.getExRight().assign(var, expression));
    }

    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() { return "(" + super.getExLeft().toString()
            + " + " +  super.getExRight().toString() + ")"; }

    /**
     * Differentiates the expression according to differentiation rules.
     *  (f(x) + g(x))` = f`(x) + g`(x)
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Plus(super.getExLeft().differentiate(var), super.getExRight().differentiate(var));
    }

    /**
     * Simplifies the expression to improve readability.
     * 1. If both of the expressions are numbers, simply return their sum as an expression.
     * 2. If one of the expression sides is equal to 0, return the opposite side expression.
     * @return returns the simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            Expression exLeftSim = super.getExLeft().simplify();
            Expression exRightSim = super.getExRight().simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(exLeftSim.evaluate() + exRightSim.evaluate());
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0.0) {
                    return exRightSim;
                }
                return new Plus(new Num(exLeftSim.evaluate()), exRightSim);
            } else if (exRightSim.getVariables().isEmpty()) {
                if (exRightSim.evaluate() == 0.0) {
                    return exLeftSim;
                }
                return new Plus(exLeftSim, new Num(exRightSim.evaluate()));
            } else {
                return new Plus(exLeftSim, exRightSim);
            }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
