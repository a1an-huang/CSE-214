/** 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


 * This class defines an arithmetic expression as a wrapper around Dyck words. It also
 * provides the <code>main</code> method to run the application to evaluate such expressions.
 *
 * @author Ritwik Banerjee

public class ArithmeticExpression {
    
     Provide the full path to the input file. 
    private static final String INPUT_PATH = "";
    
     The Dyck word around which we are wrapping. 
    private DyckWord expression;
    
    
     * The constructor for this class, simply sets the Dyck word based on the given expression.
     *
     * @param  expression  the given expression
     * @throws IllegalArgumentException if the given expression is not a valid Dyck word
     
    public ArithmeticExpression(String expression) throws IllegalArgumentException {
        this.expression = new DyckWord(expression);
    }
    
    
     * @return the string representation of the Dyck word
     
    public String getExpression() {
        return expression.getWord();
    }
    
    
     * The main method to run the application. It reads from the file specified by
     * {@link ArithmeticExpression#INPUT_PATH}, prints out the equivalent postfix expressions,
     * and then prints out the final evaluation as a <code>double</code>.
     
    public static void main(String... args) {
        ArithmeticExpression ae = new ArithmeticExpression("a+b*c+(d*e+f)*g");
        Converter nsi = new ToPostfixConverter();
        System.out.println(nsi.convert(ae));
        ArithmeticExpression num = new ArithmeticExpression("6/(5+(2+3)*8)");
        Converter nums = new ToPostfixConverter();
        Evaluator total = new PostfixEvaluator();
        System.out.println(nums.convert(num));
        System.out.print(total.evaluate(nums.convert(num)));
    }
    
}
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class defines an arithmetic expression as a wrapper around Dyck words. It also
 * provides the <code>main</code> method to run the application to evaluate such expressions.
 *
 * @author Ritwik Banerjee
 */
public class ArithmeticExpression {
    
    /** Provide the full path to the input file. */
    private static final String INPUT_PATH = "";
    
    /** The Dyck word around which we are wrapping. */
    private DyckWord expression;
    
    /**
     * The constructor for this class, simply sets the Dyck word based on the given expression.
     *
     * @param  expression  the given expression
     * @throws IllegalArgumentException if the given expression is not a valid Dyck word
     */
    public ArithmeticExpression(String expression) throws IllegalArgumentException {
        this.expression = new DyckWord(expression);
    }
    
    /**
     * @return the string representation of the Dyck word
     */
    public String getExpression() {
        return expression.getWord();
    }
    
    /**
     * The main method to run the application. It reads from the file specified by
     * {@link ArithmeticExpression#INPUT_PATH}, prints out the equivalent postfix expressions,
     * and then prints out the final evaluation as a <code>double</code>.
     */
    public static void main(String... args) {
        File input = new File(INPUT_PATH);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(input))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.printf("Input: %s%n", line);
                ArithmeticExpression a = new ArithmeticExpression(line.trim());
                
                Converter converter = new ToPostfixConverter();
                String postfix = converter.convert(a);
                System.out.printf("\tPostfix: %s%n", postfix);
                
                Evaluator evaluator = new PostfixEvaluator();
                double result = evaluator.evaluate(postfix);
                System.out.printf("\tValue: %f%n", result);
                
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
