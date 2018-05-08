import java.util.List;
import java.util.Map;

/**
 * Power expression class. extends BinaryExpression abstract class and implements the Expression interface.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Constructs from two expressions. Passes them to the superclass.
     * @param ex1 left expression.
     * @param ex2 right expression.
     */
    public Pow(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * Constructs from two numbers. Creates two expressions from them and then passes them to the superclass.
     * @param num1 first number (left expression)
     * @param num2 second number (right expression)
     */
    public Pow(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructs from a variable and a number. Creates two expressions from them and passes them to the superclass.
     * @param var a variable (left expression).
     * @param num a number (right expression).
     */
    public Pow(String var, double num) {
        super(new Var(var), new Num(num));
    }

    /**
     * Constructs from a variable and a number. Creates two expressions from them and passes them to the superclass.
     * @param var a variable (right expression).
     * @param num a number (left expression).
     */
    public Pow(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructs from two variables. Creates two expressions from them and passes them to the superclass.
     * @param var1 first variable (left expression).
     * @param var2 second variable (right expression).
     */
    public Pow(String var1, String var2) { super(new Var(var1), new Var(var2)); }

    /**
     * Constructs from an expression and a number. Creates an expression from the number and passes them both to the
     * superclass.
     * @param ex left expression.
     * @param num a number (becomes right expression).
     */
    public Pow(Expression ex, double num) { super(ex, new Num(num)); }

    /**
     * Constructs from an expression and a number. Creates an expression from the number and passes them both to the
     * superclass.
     * @param ex right expression.
     * @param num a number (becomes left expression).
     */
    public Pow(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructs from an expression and a variable. Creates an expression from the variable and passes them both to
     * the superclass.
     * @param ex left expression.
     * @param var a variable (becomes right expression).
     */
    public Pow(Expression ex, String var) { super(ex, new Var(var)); }

    /**
     * Constructs from an expression and a variable. Creates an expression from the variable and passes them both to
     * the superclass.
     * @param ex right expression.
     * @param var a variable (becomes left expression).
     */
    public Pow(String var, Expression ex) { super(new Var(var), ex); }


    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (Math.pow(super.getExLeft().evaluate(assignment), super.getExRight().evaluate(assignment)));
    }

    /**
     * Evaluates the expression without assigning anything.
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of undefined behavior in the difference calculation.
     */
    @Override
    public double evaluate() throws Exception {
        try {
            if (super.getExLeft().evaluate() == 0) {
                if (super.getExRight().evaluate() == 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
            if (super.getExRight().evaluate() == 0) {
                return 1;
            }
            return Math.pow(super.getExLeft().evaluate(), super.getExRight().evaluate());
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
        return new Pow(super.getExLeft().assign(var, expression), super.getExRight().assign(var, expression));
    }

    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return "(" + super.getExLeft().toString() + "^" + super.getExRight().toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Differentiates the expression according to differentiation rules.
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    @Override
    public Expression differentiate(String var) {
        // We'll use the mathematical e constant here so we'll create it
        Expression e = new Var("e");
        return new Mult(new Pow(super.getExLeft(), super.getExRight()),
                new Plus(new Mult(super.getExLeft().differentiate(var),
                new Div(super.getExRight(), super.getExLeft())), new Mult(super.getExRight().differentiate(var),
                new Log(e, super.getExLeft()))));
    }

    /**
     * Simplifies the expression to improve readability.
     * 1. If both the base and exponent are numbers, calculate the value and return as an expression. (2^2 = 4).
     * 2. if the exponent is zero, return 1 as expression (x^0 = 1).
     * 3. if the exponent is 1, return the left expression as is (x^1 = x).
     * 4. if the base is 0, return 0. (0^x = 0).
     * @return returns the simplified expression.
     */
    public Expression simplify() {
        try {
            Expression exLeftSim = super.getExLeft().simplify();
            Expression exRightSim = super.getExRight().simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(this.evaluate());
            } else if (exRightSim.getVariables().isEmpty()) {
                if (exRightSim.evaluate() == 0) {
                    return new Num(1);
                } else if (exRightSim.evaluate() == 1) {
                    return exLeftSim;
                } else {
                    return this;
                }
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0) {
                    return new Num(0);
                } else {
                    return this;
                }
            } else {
                return this;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}









