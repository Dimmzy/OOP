import java.util.List;
import java.util.Map;

public class Sin extends UnaryExpression implements Expression{

    public Sin(Expression expression){
        super(expression);
    }

    public Sin(double num) {
        super(new Num(num));
    }

    public Sin(String var) {
        super(new Var(var));
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return super.evaluate(assignment);
    }

    @Override
    public double evaluate() throws Exception {
        return Math.sin(Math.toRadians(this.exLeft.evaluate()));
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(super.exLeft.assign(var, expression));
    }

    public String toString() {
        return "sin(" + super.exLeft.toString() + ")";
    }

    @Override
    public Expression differentiate(String var) {
        return new Mult(new Cos(super.exLeft), super.exLeft.differentiate(var));
    }

    @Override
    public Expression simplify() {
        return super.simplify();
    }
}
