import java.util.List;
import java.util.Map;

public class Cos extends UnaryExpression implements Expression{

    public Cos(Expression expression){
        super(expression);
    }

    public Cos(double num) {
        super(new Num(num));
    }

    public Cos(String var) {
        super(new Var(var));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.cos(Math.toRadians(this.exLeft.evaluate()));
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(super.exLeft.assign(var, expression));
    }

    @Override
    public String toString() {
        return "cos(" + super.exLeft.toString() + ")";
    }

    public Expression differentiate(String var) {
            return new Neg(new Mult(new Sin(super.exLeft), super.exLeft.differentiate(var)));
    }

    public Expression simplify() {
        return super.simplify();
    }
}
