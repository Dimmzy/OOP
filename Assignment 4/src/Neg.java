import java.util.List;
import java.util.Map;

public class Neg extends UnaryExpression implements Expression{

    public Neg(Expression expression) {
        super(expression);
    }

    public Neg(double num) {
        super(new Num(num));
    }

    public Neg(String var) {
        super(new Var(var));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        try {
            return this.exLeft.evaluate() * -1;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(super.exLeft.assign(var, expression));
    }

    public String toString() {
        return "(" + "-" + super.exLeft.toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Num(-1), super.exLeft.differentiate(var));
    }

    /**
     * asd.
     * @return
     */
    @Override
    public Expression simplify() {

        try {
            Expression exSim = super.exLeft.simplify();
            if (exSim.getVariables().isEmpty()) {
                return new Num(exSim.evaluate());
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
