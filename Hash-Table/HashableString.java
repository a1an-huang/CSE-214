/**
 *  Dictionary Class: provided by Banerjee, COnverts string to hashable object
 * @author Alan Huang 113443530
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 28, 2021
 */
public class HashableString implements Hashable {
    private String s;
    public HashableString(String s){ 
        this.s = s; 
    }
    @Override
    public int key() {
        int h = 0;
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            h = 31 * h + aChar;
        }
        return h;
    }
    public String toString(){
        return this.s;
    }
 }
 
