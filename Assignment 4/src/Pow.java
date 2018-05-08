import java.util.List;
import java.util.Map;

public class Pow extends BinaryExpression implements Expression {


    public Pow(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    public Pow(double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Pow(String var, double num) {
        super(new Var(var), new Num(num));
    }

    public Pow(double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Pow(String var1, String var2) { super(new Var(var1), new Var(var2)); }

    public Pow (Expression ex, double num) { super(ex, new Num(num)); }

    public Pow (double num, Expression ex) { super(new Num(num), ex); }

    public Pow (Expression ex, String var) { super(ex, new Var(var)); }

    public Pow (String var, Expression ex) { super(new Var(var), ex); }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key : assignment.keySet()) {
            super.exLeft = super.exLeft.assign(key, new Num(assignment.get(key)));
            super.exRight = super.exRight.assign(key, new Num(assignment.get(key)));
        }
        return this.evaluate();
    }

    @Override
    public double evaluate() throws Exception {
        return Math.pow(super.exLeft.evaluate(), super.exRight.evaluate());
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(super.exLeft.assign(var, expression), super.exRight.assign(var, expression));
    }

    public String toString() {
        return "(" + super.exLeft.toString() + "^" + super.exRight.toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression differentiate(String var) {
            // We'll use the mathematical e constant here so we'll create it
            Expression e = new Var("e");
            return new Mult(new Pow(super.exLeft, super.exRight), new Plus(new Mult(super.exLeft.differentiate(var),
                    new Div(super.exRight, super.exLeft)), new Mult(super.exRight.differentiate(var),
                    new Log(e, super.exLeft))));
    }

    public Expression simplify() {
        try {
            Expression exLeftSim = super.exLeft.simplify();
            Expression exRightSim = super.exRight.simplify();
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









