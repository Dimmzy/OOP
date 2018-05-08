import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The Variable Expression class. implements the Expression interface.
 */
public class Var implements Expression {

    private String variable;

    /**
     * Constructs from a passed variable.
     * @param variable the variable that becomes the var expression.
     */
    public Var(String variable) {
        this.variable = variable;
    }

    /**
     * Evaluates the expression. Only returns something in case the variable is e or pi.
     * @param assignment not used.
     * @return returns the evaluation (if available).
     * @throws Exception throws exception if we try to evaluate an empty variable.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (variable.equals("e")) {
            return Math.E;
        } else if (variable.equals("pi")) {
            return Math.PI;
        }
        return assignment.get(this.variable);
    }

    /**
     * Evaluates the expression. Only returns something in case the variable is e or pi.
     * @return returns the evaluation (if available).
     * @throws Exception throws exception if we try to evaluate an empty variable.
     */
    @Override
    public double evaluate() throws Exception {
        if (variable.equals("e")) {
            return Math.E;
        } else if (variable.equals("pi")) {
            return Math.PI;
        }
        throw new Exception("Can't evaluate empty variable");
    }

    /**
     * @return returns a list of variables in the given expression (only this in our case).
     */
    @Override
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.add(this.variable);
        return varList;
    }

    /**
     * Assigns an expression to the variable.
     * @param var the variable to assign to.
     * @param expression the expression to assign the variable to.
     * @return returns the assigned variable expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else { return this; }
    }

    /**
     * @return returns a string representation of the variable.
     */
    public String toString() {
        return this.variable;
    }

    /**
     * Differentiates the expression. If the var we differentiate according to is this var we'll return 1, otherwise 0.
     * @param var the variable we differentiate according to.
     * @return returns the result of the differentiation as an expression.
     */
    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.variable)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * Simplifies the expression to improve readability. In this case it doesn't do anything
     * @return simply returns the same expression.
     */
    @Override
    public Expression simplify() { return this; }
}
