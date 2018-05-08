import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Var implements Expression{

    private String variable;

    public Var (String variable) {
        this.variable = variable;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (variable.equals("e")) {
            return Math.E;
        } else if (variable.equals("pi")) {
            return Math.PI;
        }
        return 0;
    }

    @Override
    public double evaluate() throws Exception {
        if (variable.equals("e")) {
            return Math.E;
        } else if (variable.equals("pi")) {
            return Math.PI;
        }
        return 0;
    }

    @Override
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.add(this.variable);
        return varList;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        }
        return this;
    }

    public String toString() {
        return this.variable;
    }

    @Override
    public Expression differentiate(String var) {
        if(var.equals(this.variable)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    @Override
    public Expression simplify() {
        return this;
    }
}
