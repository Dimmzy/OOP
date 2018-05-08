import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class UnaryExpression extends BaseExpression {

    private Expression unoexpressiono;
    public UnaryExpression (Expression e1) {
        super(e1);
    }

    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key : assignment.keySet()) {
            super.exLeft = super.exLeft.assign(key, new Num(assignment.get(key)));
        }
        return super.exLeft.evaluate();
    }


    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(this.exLeft.getVariables());
        return varList;
    }

    public Expression simplify() {
        try {
            if (this.getVariables().isEmpty()) {
                return new Num(super.exLeft.evaluate());
            }
            else {
                return this.unoexpressiono;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
