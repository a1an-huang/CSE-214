/**
 *  Linked List Class : Methods for Linked List
 * 
 * @author Alan Huang 1
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         October 10, 2021
 */
public interface CSE214List<T> {
    /**
    * @return the number of elements in this list.
    */
    int size();
    /**
    * Adds the specified element at the specified index, such that after the addition
    * operation, the previous elements in that index and beyond (if any) will occupy
    * indices incremented by one.
    *
    * @param elem the specified element
    * @param index the specified index
    */
    void add(T elem, int index);
    /**
    * Adds the given element at the start of this list.
    *
    * @param elem the given element
    */
    void add(T elem);
    /**
    * Removes the element from the specified index, such that after the removal
    * operation, the previous elements beyond that index (if any) will occupy indices
    * decremented by one.
    *
    * @param index the specified index
    * @return the removed element
    */
    T remove(int index);
    /**
    * Removes the first occurrence of the given element from this list.
    * Throws {@link java.util.NoSuchElementException} if such an element is not present
    * in this list.
    * @param elem the given element
    */
    void remove(T elem);
    /**
    * Searches for the specified element in this list, and returns <code>true</code> if
    * and only if it was found.
    * @param elem the specified element
    * @return <code>true</code> if the element was found, <code>false</code> otherwise
    */
    boolean find(T elem);
    /**
     * displays the linked list
     */
    void display();
}
class SinglyLinkedList<T> implements CSE214List<T>{
    Node<T> head;
    /**
     * Empty constructor for SinglyLinked List
     */
    public SinglyLinkedList(){
    }
    /**
     * Constructor for linked List that accepts a variable number of arguments
     * @param list
     * Use vargs to accepts a variable number of arguments and stores it into an array.
     * use add method to create link list.
     * loop through the array in reverse and use add method to keep adding to the front.
     */
    public SinglyLinkedList(T... list){
        for(int i = list.length - 1; i > - 1; i--){
            add(list[i]);
        }
    }
    /**
     * Returns size of the linked list
     * @return size
     * traverse through the linked list until cursor is equal to null.
     * if the cursor is not null increase size by 1.
     * returns the value of size after the while loop.
     */
    public int size(){
        int size = 0;
        Node<T> cursor = head;
        while(cursor != null){
            cursor = cursor.getNext();
            size++;
        }
        return size;
    }
    /**
     * Adds an element to a certain index
     * @param elem
     * @param index
     * @throws IndexOutOfBoundsException
     * checks if index is greater than size or if index is less than zero for out of bounds
     */
    public void add(T elem, int index){
        Node<T> cursor = head;
        Node<T> newNode = new Node<T>(elem);
        if(index > this.size()  || index < 0){
            throw new IndexOutOfBoundsException();
        }
        if(index == 0){
            add(elem);
        }else{
            int i = 0;
            while(cursor.getNext() != null && i < index - 1){
                cursor = cursor.getNext();
                i++;
            }
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
        }
    }
    /**
     * Adds element to the front of the Linked List
     * @param elem
     * if list is empty make the newNode head
     * else set the newNodes next to head and make newNode the head
     */
    public void add(T elem){
        Node<T> newNode = new Node<T>(elem);
        if(isEmpty()){
            head = newNode;
        }else{
            newNode.setNext(head);
            head = newNode;
        }
    }
    /**
     * Removes element from a certain index and returns it
     * @throws IndexOutOfBoundsException
     * @throws IllegalArgumentException
     * @param index
     * CHecks if index is larger than the list size 
     * checks if the list is empty
     * if the index is the first node store the head and make head the next, then return data
     * else traverse through the tree to the index and remove the index & return the data.
     */
    public T remove(int index){
        Node<T> cursor = head;
        Node<T> temp = new Node<T>();
        if(index > this.size() - 1|| index < 0){
            throw new IndexOutOfBoundsException("The Index Entered is Out of Bounds!");
        }
        if(isEmpty()){
            throw new IllegalArgumentException("The list is Empty!");
        }
        if(index == 0){
            temp.setData(head.getData());
            head = head.getNext();
            return temp.getData();
        }else{
            int i = 0;
            while(cursor != null && i < index - 1){
                cursor = cursor.getNext();
                i++;
            }
            temp = cursor.getNext();
            cursor.setNext(cursor.getNext().getNext());
        }
        return temp.getData();
    }
    /**
     * remove the element T elem
     * @throws IllegalArgumentException
     * @param elem
     * if list is empty throw exception
     * checks if elem is in first node, remove elem by setting head to next node if target matches
     * if conditions above is not met traverse through the list to find elem
     * return when remove is complete, else throw exception if elem can not be found in the list
     */
    public void remove(T elem){
        Node<T> cursor = head;
        if(isEmpty()){
            throw new IllegalArgumentException("The List is Empty!");
        }else if(head.getData() == elem || head.getData().equals(elem)){
            head = head.getNext();
        }else{
            while(cursor.getNext() != null){
                if(cursor.getNext().getData() == elem || cursor.getNext().getData().equals(elem)){
                    cursor.setNext(cursor.getNext().getNext());
                    return;
                }
                cursor = cursor.getNext();
            }
            if(cursor.getNext() == null){
                throw new IllegalArgumentException("Element can not be found!");
            }
        }      
    }
    /**
     * finds the elemen T elem, returns true if found else return false
     * @param elem
     * checks if list is empty, return false if it is
     * checks if the elem is in the first node return true if it is
     * if all condition above the while loop is not met traverse through the list to find the elem
     * return true if elem is found
     * else return false if all conditions are not met
     */
    public boolean find(T elem){
        Node<T> cursor = head;
        if(isEmpty()){
            return false;
        }else if(head.getData() == elem || head.getData().equals(elem)){
            return true;
        }else{
            while(cursor.getNext() != null){
                if(cursor.getNext().getData() == elem || cursor.getNext().getData() == elem){
                    return true;
                }
                cursor = cursor.getNext();
            }
        }
        return false;
    }
    /**
     * checks if list is empty
     * @return
     * return true if its empty, else return false
     */
    public boolean isEmpty(){
        return(head == null);
    }
    /**
     * displays the linked List
     * Checks if list is empty
     * traverses through the linked list and prints the data of each node
     */
    public void display(){
        Node<T> cursor = head;
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
                System.out.print( cursor.getData() + ", ");
                cursor = cursor.getNext();
            }
        }
    }
}
