import java.util.List;
import java.util.Map;

/**
 *
 */
public class Num implements Expression{

    double number;

    public Num (double number) {
        this.number = number;
    }

    public double evaluate(Map<String, Double> assignment) throws Exception {
        throw new Exception ("Num holds no variables");
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public double evaluate() throws Exception{
        return this.number;
    }

    /**
     * @return Returns null since number isn't a variable. (?)
     */
    public List<String> getVariables() {
        return null;
    }

    /**
     * @return returns the number saved in the field as a string
     */
    public String toString() {
        return Double.toString(number);
    }


    /**
     *
     * @param var
     * @param expression
     * @return
     */
    Expression assign(String var, Expression expression) {

    }
}
