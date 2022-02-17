/**
 *  DyckWOrd Class : Checks if it is a valid Dyckword
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 7, 2021
 */
/**
 * @author Ritwik Banerjee
 */
public class DyckWord {
    
    private final String word;
    
    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }
    
    private static boolean isDyckWord(String word) {
        int left = 0;
        int right = 0;
        if(word.length() < 2){
            return false;
        }
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) == '('){
                ++left;
            }
            if(word.charAt(i) == ')'){
                ++right;
                if(left < right){
                    return false;
                }
            }
        }
        if(left == right){
            return true;
        }else{
            return false;
        }
    }
    
    public String getWord() {
        return word;
    }
    public static void main(String[] args) {
        System.out.println(isDyckWord("()"));
        System.out.println(isDyckWord("(3)"));
        System.out.println(isDyckWord("2*((5+3)/2)"));
        System.out.println(isDyckWord("1-4("));
        System.out.println(isDyckWord("((4+2)"));
        System.out.println(isDyckWord(")("));
    }
}
