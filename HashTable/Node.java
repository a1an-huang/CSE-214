/**
 *  NodeV class: node that stores data for the hashtable
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 28, 2021
 */
public class Node<V extends Hashable> {
    private int key;
    private Hashable value = null;

    public Node(){
    }
    public Node(Hashable value, int key){
        this.value = value;
        this.key = key;
    }
    public int getKey(){
        return this.key;
    }
    public Hashable getValue(){
        return this.value;
    }
    public void setKey(int key){
        this.key = key;
    }
    public void setValue(Hashable value){
        this.value = value;
    }
}
