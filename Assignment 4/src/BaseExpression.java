import java.util.LinkedList;
import java.util.List;

/**
 * Base Expression abstract class, gets extended by Unary and Binary Expressions.
 */
public abstract class BaseExpression {

    private Expression exLeft;

    /**
     * Object constructor, using two expressions. (Used by binary expression).
     * @param exLeft left expression.
     */
    public BaseExpression(Expression exLeft) {
        this.exLeft = exLeft;
    }

    /**
     * @return left expression getter.
     */
    public Expression getExLeft() {
        return this.exLeft;
    }

    /**
     * @return Returns a list of the variables from the superclass's children.
     */
    public List<String> getVariables() {
        List<String> varList = new LinkedList<String>();
        varList.addAll(this.exLeft.getVariables());
        return varList;
    }


    /**
     * @param exL left expression setter.
     */
    public void setExLeft(Expression exL) { this.exLeft = exL; }

}
