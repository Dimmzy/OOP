import java.util.List;
import java.util.Map;


/**
 * Division, expression 1 is the dividend  and expression 2 is the divisor
 */
public class Div extends BinaryExpression implements Expression{


    public Div (Expression ex1, Expression ex2) {
        super(ex1, ex2);
    }

    public Div (double num1, double num2) {
        super(new Num(num1), new Num(num2));
    }

    public Div (String var, double num) {
        super(new Var(var), new Num(num));
    }

    public Div (double num, String var) {
        super(new Num(num), new Var(var));
    }

    public Div (String var1, String var2) { super(new Var(var1), new Var(var2)); }

    public Div (Expression ex, double num) { super(ex, new Num(num)); }

    public Div (double num, Expression ex) { super(new Num(num), ex); }

    public Div (Expression ex, String var) { super(ex, new Var(var)); }

    public Div (String var, Expression ex) { super(new Var(var), ex); }


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
        if (super.exRight.toString().equals("0.0")) {
            throw new Exception ("Error: Division by Zero");
        } else {
            return super.exLeft.evaluate() / super.exRight.evaluate();
        }
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(super.exLeft.assign(var, expression), super.exRight.assign(var, expression));
    }

    public String toString() {
        return "(" +  super.exLeft.toString() + " / " + super.exRight.toString() + ")";
    }

    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    // Differentiates using the (f`g - g`f)/g^2 formula.
    public Expression differentiate(String var){
        return new Div(new Minus(new Mult(super.leftEx.differentiate(var),super.rightEx),
                new Mult(super.leftEx, super.rightEx.differentiate(var))),
                new Pow(super.rightEx, new Num(2)));
    }

    @Override
    public Expression simplify() {
        try {
            if (super.exLeft.getVariables().isEmpty() && super.exRight.getVariables().isEmpty()) {
                return new Num(super.exLeft.evaluate() / super.exRight.evaluate());
            } else if (super.exRight.getVariables().isEmpty()) {
                if (super.exRight.evaluate() == 1 ) {
                    return super.exLeft.simplify();
                } else {
                    return new Div(super.exLeft, new Num(1));
                }
            } else if (super.exRight.toString().equals(super.exLeft.toString())) {
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
