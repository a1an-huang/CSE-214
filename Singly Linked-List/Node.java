/**
 * Node Class : Stores the data and refrence to the next Node.
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         Octorber 10, 2021
 */
public class Node<T>{ 
    Node<T> next;
    T data;
    /**
     * Empty Node constructor
     * Creates a Node with default value data of type T 
     * Creates a Node refrence to next equal to null
    */
    public Node(){

    }
    /**
     * Node constructor with T data param
     * Creates a Node with data of type T passed through the parameter 
     * Creates a Node refrence to next equal to null
    */
    public Node(T data){
        this.next = null;
        this.data = data;
    }
    /**
     * returns the next node refrence
     * @return next
     */
    public Node<T> getNext(){
        return this.next;
    }
    /**
     * returns data of type T
     * @return data
     */
    public T getData(){
        return this.data;
    }
    /**
     * sets next to Node<T> next
     * @param next
     */
    public void setNext(Node<T> next){
        this.next = next;
    }
    /**
     * sets data to T data
     * @param data
     */
    public void setData(T data){
        this.data = data;
    }
}
