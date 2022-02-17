/**
 * This class provides the fixed enumerable types of operators to be used in the conversions and
 * evaluations of simple arithmetic expressions. Each operator is also equipped with a rank, which
 * serves to determine the precendence between two operators in case brackets are not enough to
 * resolve ambiguity of operation order.
 *
 * Since the variable and method names are obvious, documentation has not been provided for each
 * object or method individually.
 *
 * @author Ritwik Banerjee
 */
public enum Operator {
    
    MULTIPLICATION('*', 1),
    DIVISION('/', 1),
    ADDITION('+', 2),
    SUBTRACTION('-', 2),
    LEFT_PARENTHESIS('(', 3),
    RIGHT_PARENTHESIS(')', 3);
    
    private final char symbol;
    private final int  rank;
    
    Operator(char c, int rank) {
        this.symbol = c;
        this.rank = rank;
    }
    
    public char getSymbol() { return symbol; }
    
    public int getRank() { return rank; }
    
    public static boolean isOperator(char c) {
        return c == ADDITION.symbol || c == SUBTRACTION.symbol || c == MULTIPLICATION.symbol || c == DIVISION.symbol;
    }
    
    public static boolean isOperator(String c) {
        return isOperator(c.charAt(0));
    }
    
    public static Operator of(char c) {
        if (c == LEFT_PARENTHESIS.symbol)
            return LEFT_PARENTHESIS;
        if (c == RIGHT_PARENTHESIS.symbol)
            return RIGHT_PARENTHESIS;
        if (c == MULTIPLICATION.symbol)
            return MULTIPLICATION;
        if (c == DIVISION.symbol)
            return DIVISION;
        if (c == ADDITION.symbol)
            return ADDITION;
        if (c == SUBTRACTION.symbol)
            return SUBTRACTION;
        throw new IllegalArgumentException(String.format("%c is not a valid character for an operator.", c));
    }
    
    public static Operator of(String c) {
        return Operator.of(c.charAt(0));
    }
}
