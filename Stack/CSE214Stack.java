/**
 * Stacked Class : methods for Stack data structure using Linked List
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         October 10, 2021
 */
import java.util.NoSuchElementException;
public interface CSE214Stack<T> {
    int size();
    /**
    * Returns the top element of this stack, without removing it from this stack.
    * Throws {@link java.util.NoSuchElementException} if this stack is empty.
    *
    * @return the top element of this stack, if it exists
    */
    T peek();
    /**
    * Pops and returns the top element of this stack. If the stack is empty, throws
    * {@link java.util.NoSuchElementException}.
    *
    * @return the top element of this stack, if it exists
    */
    T pop();
    /**
    * Pushes the given element onto the top of this stack.
    *
    * @param elem the given element to be pushed onto the top of this stack
    */
    void push(T elem);
    /**
    * @return <code>true</code> if this stack is empty, and <code>false</code> otherwise
    */
    boolean isEmpty();
    /** 
     * displays the stack
     */
    void display();
}
class SinglyLinkedStack<T> implements CSE214Stack<T>{
    SinglyLinkedList<T> newStack = new SinglyLinkedList<T>();
    /**
     * Empty Constructor
     */
    public SinglyLinkedStack(){
    }
    /**
     * constructor with list[]
     * @param list
     * adds the list with add method from Linked List which only adds to the front
     */
    public SinglyLinkedStack(T... list){
        for(int i = 0; i < list.length; i++){
            newStack.add(list[i]);
        }
    }
    /**
     * returns size of the stack
     * calls size method from linked list and returns it
     */
    public int size(){
        return newStack.size();
    }
    /**
     * peeks at the top of the stack
     * @throws NoSuchElementException
     * if stack is empty throw Exception
     * gets the data for the head of the stack
     */
    public T peek(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return newStack.head.getData();
    }
    /**
     * Removes and return top of the Stack
     * @throws NoSuchElementException
     * Stores data of the head
     * set head to next node
     * return the data
     */
    public T pop(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        T temp = newStack.head.getData();
        newStack.head = newStack.head.getNext();
        return temp;
    }
    /**
     * adds the element to top of the stack
     * calls the add to head method from LinkList class
     */
    public void push(T elem){
        newStack.add(elem);
    }
    /**
     * checks if stack is empty by checking if the size is 0
     */
    public boolean isEmpty(){
        return (newStack.size() == 0);
    }   
    /**
     * Displays the Stack From head to tail */ 
    public void display(){
        Node<T> cursor = newStack.head;
        if(isEmpty()){
            System.out.print("List is Empty");
        }else{
            System.out.print("List: ");
        }
        while(cursor != null){
            if(cursor.getNext() == null){
                System.out.print(cursor.getData());
                System.out.println();
                cursor = cursor.getNext();
            }else{
                System.out.print(cursor.getData() + ", ");
                cursor = cursor.getNext();
            }
        }
    }
}
