import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Unary Expression superclass, extends BaseExpression.
 */
public abstract class UnaryExpression extends BaseExpression {



    /**
     * Constructs a new UnaryExpression and passes it to Base Expression from a passed expression from it's children.
     * @param e1 the expression passed from it's children.
     */
    public UnaryExpression(Expression e1) {
        super(e1);
    }

    /**
     * Evaluates the expression after mapping each variable to it's value from the given assignment parameter.
     * @param assignment a Map that maps each variable it's corresponding value.
     * @return returns the evaluation of the expression after the assignment.
     * @throws Exception throws an exception if there was a mismatch between the map's variables and the expression's.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        for (String key : assignment.keySet()) {
           super.setExLeft(getExLeft().assign(key, new Num(assignment.get(key))));
        }
        return super.getExLeft().evaluate();
    }


    /**
     * @return Returns a list of the variables from the superclass's children.
     */
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(super.getVariables());
        return varList;
    }

}
