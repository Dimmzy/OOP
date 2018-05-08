import java.util.Map;

public abstract class BaseExpression {

    protected Expression exLeft;
    protected Expression exRight;

    public BaseExpression(Expression exLeft, Expression exRight) {
        this.exLeft = exLeft;
        this.exRight = exRight;
    }

    public BaseExpression(Expression unoExpression) {
        this.exLeft = unoExpression;
    }

    abstract double evaluate(Map<String, Double> assignment) throws Exception;


}
