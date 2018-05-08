import java.rmi.server.ExportException;
import java.util.List;
import java.util.Map;

public class Mult extends BinaryExpression implements Expression{


    public Mult(Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    public Mult (double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Mult (String var, double num) {
        super(new Var(var), new Num(num));
    }

    public Mult (double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Mult (String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    public Mult (Expression ex, double num) { super(ex, new Num(num)); }

    public Mult (double num, Expression ex) { super(new Num(num), ex); }

    public Mult (Expression ex, String var) { super(ex, new Var(var)); }

    public Mult (String var, Expression ex) { super(new Var(var), ex); }


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
        return super.exLeft.evaluate() * super.exRight.evaluate();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(super.exLeft.assign(var, expression), super.exRight.assign(var, expression));
    }

    public String toString() {
        return "(" + super.exLeft.toString() + " * " + super.exRight.toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression differentiate(String var) {
            return new Plus(new Mult(super.exLeft.differentiate(var), super.exRight),
                    new Mult(super.exLeft, super.exRight.differentiate(var)));

    }

    @Override
    public Expression simplify() {
        try {
        Expression exLeftSim = super.exLeft.simplify();
        Expression exRightSim = super.exRight.simplify();
            if (exLeftSim.getVariables().isEmpty() && exRightSim.getVariables().isEmpty()) {
                return new Num(exLeftSim.evaluate() * exRightSim.evaluate());
            } else if (exLeftSim.getVariables().isEmpty()) {
                if (exLeftSim.evaluate() == 0.0 ) {
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
