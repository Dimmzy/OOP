import java.util.List;
import java.util.Map;

/**
 * Public class defining the logartihmic expression. extends Binary Expression and implements the Expression interface.
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Constructor using two expressions. Passes them to the superclas.
     * @param ex1 left expression.
     * @param ex2 right expression.
     */
    public Log(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * Constructor using two numbers. Turns them into expressions and pasess them to the superclass.
     * @param num1 first number (left expression).
     * @param num2 second number (right expression).
     */
    public Log(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructor using a variable and a number. Turns them into expressions and passes them to the superclass.
     * @param var a variable (left expression).
     * @param num a number (right expression).
     */
    public Log(String var, double num) {
        super(new Var(var), new Num(num));
    }


    /**
     * Constructor using a variable and a number. Turns them into expressions and passes them to the superclass.
     * @param var a variable (right expression).
     * @param num a number (left expression).
     */
    public Log(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructor using two variables. Turns them into expressions and passes them to the superclass.
     * @param var1 first variable (left expression).
     * @param var2 second variable (right expression).
     */
    public Log(String var1, String var2) { super(new Var(var1), new Var(var2)); }

    /**
     * Constructor using an expression and a number (turned into expression). Passes them to the superclass.
     * @param ex left expression.
     * @param num a number (right expression).
     */
    public Log(Expression ex, double num) { super(ex, new Num(num)); }

    /**
     * Constructor using an expression and a number (turned into expression). Passes them to the superclass.
     * @param ex right expression.
     * @param num a number (left expression).
     */
    public Log(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructor using an expression and a variable (turned into expression). Passes them to the superclass.
     * @param ex left expression.
     * @param var a variable (right expression).
     */
    public Log(Expression ex, String var) { super(ex, new Var(var)); }


    /**
     * Constructor using an expression and a variable (turned into expression). Passes them to the superclass.
     * @param ex right expression.
     * @param var a variable (left expression).
     */
    public Log(String var, Expression ex) { super(new Var(var), ex); }


    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception when encounters unexpected behavior.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.log(super.getExRight().evaluate(assignment) / Math.log(super.getExLeft().evaluate(assignment))));
    }

    /**
     * Evaluates the expression without assiging anything.
     * Uses logarithmic rules to evaluate logs from different bases. log(b, x) = log(c, x) / log (c, b)
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the logarithm calculation.
     */
    @Override
    public double evaluate() throws Exception {
        if (super.getExLeft().evaluate() <= 0) {
            throw new Exception("Cannot compute logarithm of 0/negative number");
        } else if (super.getExRight().evaluate() <= 0) {
            throw new Exception("Cannot compute logarithm with base 0/negative base");
        }
        return Math.log(super.getExRight().evaluate()) / Math.log(super.getExLeft().evaluate());
    }


    /**
     * Assigns a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(super.getExLeft().assign(var, expression), super.getExRight().assign(var, expression));
    }

    /**
     * @return returns string representation of the expression.
     */
    public String toString() {
        return "log(" + super.getExLeft().toString() + ", " + super.getExRight().toString() + ")";
    }


    /**
     * @return Returns a list of variables the expression contains. (Uses the super-class Binary Experssion's method).
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Differentiates the expression according to differentiation rules.
     * If we have a logarithmic expression with the natural base: ln(f(x)) = f'(x)/ln(fx).
     * Else: 1/xln(a).
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    public Expression differentiate(String var) {
        if (super.getExRight().getVariables().toString().equals("e")) {
            return new Div(new Mult(new Num(1), super.getExLeft().differentiate(var)), super.getExLeft());
        }
        Expression e = new Var("e");
        return new Div(new Num(1), new Mult(super.getExRight(), new Log(e, getExLeft())));
    }

    /**
     * Simplifies the expression to improve readability.
     * 1. If both the base and the value are numbers, calculate the expression and return the result as an expression.
     * 2. if both the base and the value are the same expressions, return one (log(x, x) = 1).
     * @return returns the simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            Expression exLeftSim = super.getExLeft().simplify();
            Expression exRightSim = super.getExRight().simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(this.evaluate());
            } else if (exLeftSim.toString().equals(exRightSim.toString())) {
                return new Num(1);
            } else {
                return this;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
