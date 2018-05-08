import java.util.LinkedList;
import java.util.List;

/**
 * Binary Expression that consists of two expression.
 */
public abstract class BinaryExpression extends BaseExpression {

    private Expression exRight;

    /**
     * Constructor for the object and its super, using two passed exprssions from it's children expressions.
     * @param exLeft the left expression
     * @param exRight the right expression.
     */
    public BinaryExpression(Expression exLeft, Expression exRight) {
        super(exLeft);
        this.exRight = exRight;
    }

    /**
     * Gets a list of variables from the super and creates a unified list with both expressions' variables.
     * @return returns a list of the variables from the left expression (this) and the right one (super).
     */
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(this.getExRight().getVariables());
        varList.addAll(super.getVariables());
        // Checks for duplicates and removes them
        for (int i = 0; i < varList.size() - 1; i++) {
            for (int j = i + 1; j < varList.size(); j++) {
                if (varList.get(i).equals(varList.get(j))) {
                    varList.remove(j);
                }
            }
        }
        return varList;
    }

    /**
     * @return right expression getter.
     */
    public Expression getExRight() {
        return this.exRight;
    }

    /**
     * @param exR right expression setter.
     */
    public void setExRight(Expression exR) { this.exRight = exR; }

}
