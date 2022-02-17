/**
 *  HashableV Class: converts number datatypes to hashableV object
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 28, 2021
 */
public class HashableV implements Hashable {
    private int i;
    public HashableV(int i){ 
        this.i = i; 
    }
    @Override
    public int key(){
        return (int)i;
    }
    public String toString(){
        return "" + i;
    }
}