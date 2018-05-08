

/**
 * Base Expression abstract class, gets extended by Unary and Binary Expressions.
 */
public abstract class BaseExpression {

    protected Expression exLeft;
    protected Expression exRight;

    /**
     * Object constructor, using two expressions. (Used by binary expression).
     * @param exLeft left expression.
     * @param exRight right expression.
     */
    public BaseExpression(Expression exLeft, Expression exRight) {
        this.exLeft = exLeft;
        this.exRight = exRight;
    }

    /**
     * Constructs the object using only one expression (used by Unary Expression).
     * @param singleExp the only expression passed from unary expression.
     */
    public BaseExpression(Expression singleExp) {
        this.exLeft = singleExp;
    }


}
