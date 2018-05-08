import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression {


    public UnaryExpression (Expression e1) {
        super(e1);

    }

    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key : assignment.keySet()) {
            if (!(exLeft.getVariables().contains(key))) {
                throw new Exception ("Mapped Variable does not exist in Expression");
            }
            super.exLeft = super.exLeft.assign(key, new Num(assignment.get(key)));
        }
        return exLeft.evaluate();
    }


    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(this.exLeft.getVariables());
        return varList;
    }
/*
    public Expression simplify() {
        try {
            if (this.getVariables().isEmpty()) {
                return new Num(super.exLeft.evaluate());
            }
            else {
                return super.exLeft.simplify();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    */

}
