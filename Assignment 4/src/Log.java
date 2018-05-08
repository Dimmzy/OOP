import java.util.List;
import java.util.Map;

public class Log extends BinaryExpression implements Expression {


    public Log(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    public Log (double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Log (String var, double num) {
        super(new Var(var), new Num(num));
    }

    public Log (double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Log (String var1, String var2) { super(new Var(var1), new Var(var2)); }

    public Log (Expression ex, double num) { super(ex, new Num(num)); }

    public Log (double num, Expression ex) { super(new Num(num), ex); }

    public Log (Expression ex, String var) { super(ex, new Var(var)); }

    public Log (String var, Expression ex) { super(new Var(var), ex); }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key: assignment.keySet()) {
            super.exLeft.assign(key, new Num(assignment.get(key)));
            super.exRight.assign(key, new Num(assignment.get(key)));
        }
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        // Calculating log of the base of expression 2 using log rules.
        return Math.log(super.exRight.evaluate()) / Math.log(super.exLeft.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(super.exLeft.assign(var, expression), super.exRight.assign(var, expression));
    }

    public String toString() {
        return "log(" + super.exLeft.toString() + ", " + super.exRight.toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    public Expression differentiate(String var) {
        // if we have a logarithmic expression with the natural base e, differentiate accordingly.
        if (super.exRight.getVariables().toString().equals("e")) {
            return new Div(new Num(1), super.exLeft);
        }
        Expression e = new Var("e");
        return new Div(new Num (1), new Mult(super.exRight, new Log(e,exLeft)));
    }

    @Override
    public Expression simplify() {
        try {
            if (super.getVariables().isEmpty()) {
                return new Num(super.exLeft.evaluate());
            } else if (super.exLeft.toString().equals(super.exRight.toString())) {
                return new Num(1);
            }
            else {
                return this;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
