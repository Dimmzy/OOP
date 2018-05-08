import java.util.List;
import java.util.Map;


public class Minus  extends BinaryExpression implements Expression{

    public Minus (String variable, double number) {
        super(new Var(variable),new Num(number));
    }

    public Minus (Expression e1, Expression e2) {
        super(e1,e2);
    }

    public Minus (double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Minus (double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Minus (String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    public Minus (Expression ex, double num) { super(ex, new Num(num)); }

    public Minus (double num, Expression ex) { super(new Num(num), ex); }

    public Minus (Expression ex, String var) { super(ex, new Var(var)); }

    public Minus (String var, Expression ex) { super(new Var(var), ex); }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key: assignment.keySet()) {
            exLeft = exLeft.assign(key, new Num(assignment.get(key)));
            exRight = exRight.assign(key, new Num(assignment.get(key)));
        }
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return this.exLeft.evaluate() - this.exRight.evaluate();
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    // Returns a new expression in which all occurrences of the variable
    // var are replaced with the provided expression (Does not modify the
    // current expression).
    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(this.exLeft.assign(var, expression), this.exRight.assign(var, expression));
    }

    public String toString() {
        return "(" +  this.exLeft.toString() + " - " + this.exRight.toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Minus(super.exLeft.differentiate(var), super.exRight.differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            Expression exLeftSim = super.exLeft.simplify();
            Expression exRightSim = super.exRight.simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(exLeftSim.evaluate() - exRightSim.evaluate());
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0.0) {
                    return new Neg(exRightSim);
                }
                return new Minus(new Num(exLeftSim.evaluate()), exRightSim);
            } else if (exRightSim.getVariables().isEmpty()) {
                if (exRightSim.evaluate() == 0.0) {
                    return exLeftSim;
                }
                return new Minus(exLeftSim, new Num(exRightSim.evaluate()));
            } else {
                return new Minus(exLeftSim, exRightSim);
            }
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}
