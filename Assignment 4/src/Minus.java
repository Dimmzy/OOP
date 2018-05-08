import java.util.List;
import java.util.Map;

/**
 * Public class defining the Minus expression. extends Binary Expression and implements the Expression interface.
 */
public class Minus  extends BinaryExpression implements Expression {

    /**
     * Constructor using a variable and an number. Creates expressions from them and passes them to the superclass.
     * @param variable a variable (left expression).
     * @param number a number (right expression).
     */
    public Minus(String variable, double number) {
        super(new Var(variable), new Num(number));
    }

    /**
     * Constructor using two expressions. Passes them to the superclass.
     * @param e1 the left expression.
     * @param e2 the right expression.
     */
    public Minus(Expression e1, Expression e2) {
        super(e1, e2);
    }

    /**
     * Constructor using two numbers. Creates expressions from them and passes them to the superclass.
     * @param num1 first number (left expression).
     * @param num2 second number (right expression).
     */
    public Minus(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructuor using a number and a variable. Creates expressions from them and passes them to the superclass.
     * @param num a number (left expression).
     * @param var a variable (right expression).
     */
    public Minus(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructuor using two strings. Creates expressions from them and passes them to the superclass.
     * @param var1 first variable (left expression).
     * @param var2 second variable (right expression).
     */
    public Minus(String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    /**
     * Constructor using an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex left expression.
     * @param num a number (right expression).
     */
    public Minus(Expression ex, double num) { super(ex, new Num(num)); }


    /**
     * Constructor using an expression and a number. Creates an expression from the number and passes them to the
     * superclass.
     * @param ex right expression.
     * @param num a number (left expression).
     */
    public Minus(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructor using an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex left expression.
     * @param var a variable (right expression)/
     */
    public Minus(Expression ex, String var) { super(ex, new Var(var)); }


    /**
     * Constructor using an expression and a variable. Creates an expression from the variable and passes them to the
     * superclass.
     * @param ex right expression.
     * @param var a variable (left expression)/
     */
    public Minus(String var, Expression ex) { super(new Var(var), ex); }


    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception exception if unexpected behavior occurs.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExLeft().evaluate(assignment) - super.getExRight().evaluate(assignment));
    }


    /**
     * Evaluates the expression without assiging anything.
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate() throws Exception {
        try {
            return this.getExLeft().evaluate() - this.getExRight().evaluate();
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
        return new Minus(this.getExLeft().assign(var, expression), this.getExRight().assign(var, expression));
    }


    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return "(" +  this.getExLeft().toString() + " - " + this.getExRight().toString() + ")";
    }

    /**
     * Differentiates the expression according to differentiation rules.
     * f(x) - g(x) = f`(x) - g`(x)
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(super.getExLeft().differentiate(var), super.getExRight().differentiate(var));
    }

    /**
     * Simplifies the expression to improve readability.
     * 1. If both expressions are numbers, calculate the difference and return it as a single expression.
     * 2. If the left expression is the number 0, return the negation of the right expression.
     * 3. Vice versa to 3.
     * 4. If both expressions are equal, return zero (x-x=0).
     * @return returns a simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            Expression exLeftSim = super.getExLeft().simplify();
            Expression exRightSim = super.getExRight().simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(exLeftSim.evaluate() - exRightSim.evaluate());
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0.0) {
                    return new Neg(exRightSim);
                } else { return new Minus(new Num(exLeftSim.evaluate()), exRightSim); }
            } else if (exRightSim.getVariables().isEmpty()) {
                if (exRightSim.evaluate() == 0.0) {
                    return exLeftSim;
                } else { return new Minus(exLeftSim, new Num(exRightSim.evaluate())); }
            } else if (exLeftSim.toString().equals(exRightSim.toString())) {
                return new Num(0);
            } else {
                return this;
            }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
