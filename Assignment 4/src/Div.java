import java.util.List;
import java.util.Map;


/**
 * The Division expression class, a child of Binray Expression and implements Interface.
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Constructor using two expressions, left and right. Passes onto the superclass.
     * @param ex1 the left expression.
     * @param ex2 the right expression.
     */
    public Div(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    /**
     * Constructor using two number variables. Creates two expressions from them and passes them to the superclass.
     * @param num1 the first number (creates left expression).
     * @param num2 the second number (creates right expression).
     */

    public Div(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    /**
     * Constructor using a variable and a number. Creates two expressions from them and passes them to the superclass.
     * @param var the variable (creates left expression).
     * @param num the number (creates right expression).
     */

    public Div(String var, double num) {
        super(new Var(var), new Num(num));
    }


    /**
     * Constructor using a variable and a number. Creates two expressions from them and passes them to the superclass.
     * @param var the variable (creates right expression).
     * @param num the number (creates left expression).
     */

    public Div(double num, String var) {
        super(new Num(num), new Var(var));
    }

    /**
     * Constructor using two variables. Creates two expressions from them and passes them to the superclass.
     * @param var1 the first variable (creates left expression).
     * @param var2 the right variable (creates right expression).
     */

    public Div(String var1, String var2) { super(new Var(var1), new Var(var2)); }

    /**
     * Constructor using an expression and a number.
     * Passes the first expression as the right one and creates a new expression from the number.
     * @param ex the left expression.
     * @param num the number (creates right expression).
     */

    public Div(Expression ex, double num) { super(ex, new Num(num)); }

    /**
     * Constructor using an expression and a number.
     * Passes the first expression as the left one and creates a new expression from the number.
     * @param ex the right expression.
     * @param num the number (creates left expression).
     */

    public Div(double num, Expression ex) { super(new Num(num), ex); }

    /**
     * Constructor using an expression and a variable.
     * Passes the first expression as the left one  and creates a new expression from the variable.
     * @param ex the left expression.
     * @param var the number (creates right experssion).
     */

    public Div(Expression ex, String var) { super(ex, new Var(var)); }

    /**
     * Constructor using an expression and a variable.
     * Passes the first expression as the right one  and creates a new expression from the variable.
     * @param ex the right expression.
     * @param var the number (creates left experssion).
     */

    public Div(String var, Expression ex) { super(new Var(var), ex); }

    /**
     * Assigns the variable using from the map and evaluates the expression.
     * @param assignment Maps each value to it's corresponding variable.
     * @return returns the result of the evaluation of the expression.
     * @throws Exception throws exception if for unexpected behavior.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (super.getExLeft().evaluate(assignment) / super.getExRight().evaluate(assignment));
    }

    /**
     * Evaluates the expression without assigning anything.
     * If any of the expressions are null, throws new exception -> undefined behavior.
     * If the right expression (the divisor) is equal to zero or , throws exception -> division by zero.
     * @return returns the evaluation of the expression.
     * @throws Exception throws exception in case of division by zero or undefined expressions.
     */
    @Override
    public double evaluate() throws Exception {
        if (super.getExLeft() == null || super.getExRight() == null) {
            throw new Exception("Error: Undefined Behavior");
        }
        if (super.getExLeft().toString().equals("0.0")) {
            throw new Exception("Error: Division by Zero");
        } else {
            return super.getExLeft().evaluate() / super.getExRight().evaluate();
        }
    }

    /**
     * Assigns all occurences of a variable to the expression and returns the new assigned expression.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns a new expression with the variable assigned.
     */

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(super.getExLeft().assign(var, expression), super.getExRight().assign(var, expression));
    }


    /**
     * @return Returns a string representation of the expression.
     */
    public String toString() {
        return "(" +  super.getExLeft().toString() + " / " + super.getExRight().toString() + ")";
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
     * (f`g - g`f)/g^2
     * @param var the variable we differentiate according to.
     * @return returns the differentiated expression.
     */
    public Expression differentiate(String var) {
        return new Div(new Minus(new Mult(super.getExLeft().differentiate(var), super.getExRight()),
                new Mult(super.getExLeft(), super.getExRight().differentiate(var))),
                new Pow(super.getExRight(), new Num(2)));
    }

    /**
     * Simplifies our expression for better readability.
     * 1. if both the divisor and dividend are numbers, simply return the evaluation of the expression.
     * 2. if the divisor isn't a variable and it's equal to 1, return the left expression (x/1 = x).
     * 3. if both the divisor and dividend are the same expression, return 1 (x/x = 1).
     * @return returns the simplified expression.
     */
    @Override
    public Expression simplify() {
        try {
            if (super.getExLeft().getVariables().isEmpty() && super.getExRight().getVariables().isEmpty()) {
                return new Num(super.getExLeft().evaluate() / super.getExRight().evaluate());
            } else if (super.getExRight().getVariables().isEmpty()) {
                if (super.getExRight().evaluate() == 1) {
                    return super.getExLeft().simplify();
                } else {
                    return this;
                }
            } else if (super.getExRight().toString().equals(super.getExLeft().toString())) {
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
