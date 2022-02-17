/**
 *  Converter Class : Converts the expersion into postfix
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 7, 2021
 */
/**
 * This interface defines the structure of any converter to be used for conversion of arithmetic
 * expressions between infix, prefix, and postfix types.
 *
 * @author Ritwik Banerjee
 */
import java.util.Stack;
public interface Converter {
    
    /**
     * The fundamental method of any class implementing this interface. It converts the given
     * arithmetic expression to another type, depending on the implementation.
     *
     * @param expression  the given arithmetic expression
     */
    String convert(ArithmeticExpression expression);
    
    /**
     * Given a string and a specific index, this method returns the next token starting at that index.
     *
     * @param s     the given string
     * @param start the given index
     * @return the next token starting at the given index in the given string
     */
    String nextToken(String s, int start);
    
    /**
     * Determines whether or not a string is a valid operand.
     *
     * @param s the given string
     * @return <code>true</code> if the given string is a valid operand, and <code>false</code> otherwise
     */
    boolean isOperand(String s);
    
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
    
}
class ToPostfixConverter implements Converter{   
    public String convert(ArithmeticExpression expression){
        Stack<Operator> stack = new Stack<Operator>();
        String s = expression.getExpression();
        String output = "";
        for(int i = 0; i < s.length(); i++){
            if(isOperand(s.substring(i, i + 1))){
                output += nextToken(s, i) + " ";
                i += nextToken(s, i).length() - 1;
            }else if(s.charAt(i) == '('){
                stack.push(Operator.of('('));
            }else if(s.charAt(i) == ')'){
                while(stack.peek() != Operator.LEFT_PARENTHESIS){
                    output += stack.pop().getSymbol() + " ";
                }
                stack.pop();
            }else if(Operator.isOperator(s.charAt(i))){
                if(stack.isEmpty() || stack.peek() == Operator.LEFT_PARENTHESIS){
                    stack.push(Operator.of(s.charAt(i)));
                }else if(Operator.of(s.charAt(i)).getRank() < stack.peek().getRank()){
                    stack.push(Operator.of(s.charAt(i)));
                }else if(Operator.of(s.charAt(i)).getRank() == stack.peek().getRank()){
                    output += stack.pop().getSymbol() + " ";
                    stack.push(Operator.of(s.charAt(i)));
                }else if(Operator.of(s.charAt(i)).getRank() > stack.peek().getRank()){
                    output += stack.pop().getSymbol() + " ";
                    if(!stack.isEmpty() && Operator.of(s.charAt(i)).getRank() >= stack.peek().getRank()){
                        output += stack.pop().getSymbol() + " "; 
                    }
                    stack.push(Operator.of(s.charAt(i)));
                }
            }
        }
        while(!stack.isEmpty()){
            output += stack.pop().getSymbol() + " ";
        }
        return output;
    }
    public String nextToken(String s, int start){
        TokenBuilder output = new TokenBuilder();
        for(int i = start;i < s.length() && !Operator.isOperator(s.charAt(i)) && !isParen(s.charAt(i)); i++){
            output.append(s.charAt(i));
        }
        return output.build();
    }
    public Boolean isParen(char ch){
        if(ch == Operator.LEFT_PARENTHESIS.getSymbol() || ch == Operator.RIGHT_PARENTHESIS.getSymbol()){
            return true;
        }
        return false;
    }
    public boolean isOperand(String s){
        if(Operator.isOperator(s) || s.equals("(") || s.equals(")")){
            return false;
        }
        return true;
    }
}
