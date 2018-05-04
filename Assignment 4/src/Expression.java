import java.util.Map;
import java.util.List;
/**
 *
 */
public interface Expression {

    double evaluate(Map<String, Double> assignment) throws Exception;

    double evaluate() throws Exception;

    List<String> getVariables();

    String toString();

    Expression assign(String var, Expression expression);
}
