import java.util.LinkedList;
import java.util.List;

/**
 * Binary Expression that consists of two expression.
 */
public abstract class BinaryExpression extends BaseExpression {

    protected Expression leftEx;
    protected Expression rightEx;

    /**
     * Constructor for the object, using two passed exprssions from it's children expressions.
     * @param leftEx the left expression
     * @param rightEx the right expression.
     */
    public BinaryExpression(Expression leftEx, Expression rightEx) {
        super(leftEx,rightEx);
        this.leftEx = leftEx;
        this.rightEx = rightEx;
    }


    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(this.leftEx.getVariables());
        varList.addAll(this.rightEx.getVariables());
        // Removes any multiple occurrences of the variables.
        for(int i = 0; i < varList.size() - 1; i++) {
            for(int j = i + 1; j < varList.size(); j++) {
                if (varList.get(i) == varList.get(j)) {
                    varList.remove(j);
                }
            }
        }
        return varList;
    }

}
