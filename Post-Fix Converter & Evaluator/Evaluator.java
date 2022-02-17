/**
 *  Evaluator CLass : evaluates postfix expression
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 7, 2021
 */
import java.util.Stack;
public interface Evaluator {
    double evaluate(String expressionString);
    /**
     * This class handles the parsing of tokens from a string. This is helpful in situations where a
     * single token may take up more than one character in the string.
     */
    class TokenBuilder {
        
        /**
         * The {@link StringBuilder} object used internally. This is used because {@link String}s in
         * Java are immutable, while we may want to build a token as we parse from left to right one
         * character at a time.
         */
        private StringBuilder tokenBuilder = new StringBuilder();
        
        /**
         * @see StringBuilder#append(char)
         */
        public void append(char c) {
            tokenBuilder.append(c);
        }
        
        /**
         * @return the final string object that represents a single token
         * @see StringBuilder#toString()
         */
        public String build() {
            return tokenBuilder.toString();
        }
        
    }

    /**
     * Determines whether or not a string is a valid operand.
     *
     * @param s the given string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    boolean isOperand(String s);
}
class PostfixEvaluator implements Evaluator {
    public double evaluate(String ExpressionString) {
        Stack<Double> stack = new Stack<Double>();
        for(int i = 0; i < ExpressionString.length(); i++){
            if(isOperand(ExpressionString.substring(i, i + 1))){
                    double num = Double.parseDouble(nextToken(ExpressionString, i));
                    stack.push(num);
                    i += nextToken(ExpressionString, i).length();
            }else if(Operator.isOperator(ExpressionString.charAt(i))){
                switch(ExpressionString.charAt(i)){
                    case '+':
                        double sum = stack.pop() + stack.pop();
                        stack.push(sum);
                        i++;
                        break; 
                    case '-':
                        double difference = stack.pop() - stack.pop();
                        stack.push(difference);
                        i++;
                        break;
                    case '*':
                        double product = stack.pop() * stack.pop();
                        stack.push(product);
                        i++;
                        break;
                    case '/':
                        double dividend = stack.pop();
                        double quotient = stack.pop() / dividend;
                        stack.push(quotient);
                        i++;
                        break;
                    default: 
                        break;
                }
            }
        }
        return stack.pop();
    }
    public String nextToken(String s, int start){
        String str = "";
        for(int i = start; i < s.length() && !Operator.isOperator(s.charAt(i)) && s.charAt(i) != ' '; i++){
            str += s.charAt(i);
            
        }
        return str;
    }
    public boolean isOperand(String s){
        if(Operator.isOperator(s)){
            return false;
        }
        return true;
    }
}
