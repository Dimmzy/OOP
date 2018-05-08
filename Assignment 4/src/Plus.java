import java.util.List;
import java.util.Map;

public class Plus extends BinaryExpression implements Expression{

    public Plus (String variable, double number) {
        super(new Var(variable),new Num(number));
    }

    public Plus (Expression e1, Expression e2) {
        super(e1,e2);
    }

    public Plus (double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Plus (double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Plus (String var1, String var2) {
        super(new Var(var1), new Var(var2));
    }

    public Plus (Expression ex, double num) { super(ex, new Num(num)); }

    public Plus (double num, Expression ex) { super(new Num(num), ex); }

    public Plus (Expression ex, String var) { super(ex, new Var(var)); }

    public Plus (String var, Expression ex) { super(new Var(var), ex); }


    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key: assignment.keySet()) {
            exLeft.assign(key, new Num(assignment.get(key)));
            exRight.assign(key, new Num(assignment.get(key)));
        }
        return this.evaluate();
    }

    // Evaluates without assignment (
    @Override
    public double evaluate() throws Exception {
        return super.exLeft.evaluate() + super.exRight.evaluate();
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(super.exLeft.assign(var, expression), super.exRight.assign(var, expression));
    }

    public String toString() { return "(" + super.exLeft.toString() + " + " +  super.exRight.toString() + ")"; }

    @Override
    public Expression differentiate(String var) {
        return new Plus(super.exLeft.differentiate(var), super.exRight.differentiate(var));
    }

    @Override
    public Expression simplify() {
        try {
            Expression exLeftSim = super.exLeft.simplify();
            Expression exRightSim = super.exRight.simplify();
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
