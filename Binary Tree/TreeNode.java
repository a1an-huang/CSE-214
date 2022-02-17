/**
 * 
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         Octorber 10, 2021
 */
public class TreeNode<T>{
    TreeNode<T> right;
    TreeNode<T> left;
    T data;

    public TreeNode(){   
    }
    public TreeNode(T data){
        this.right = null;
        this.left = null;
        this.data = data;
    }
    /**
     * returns the left node refrence
     * @return left
     */
    public TreeNode<T> getLeft(){
        return this.left;
    }
    /**
     * returns the right node refrence
     * @return right
     */
    public TreeNode<T> getRight(){
        return this.right;
    }
    /**
     * returns data of type T
     * @return data
     */
    public T getData(){
        return this.data;
    }
    /**
     * sets left to TreeNode<T> left
     * @param left
     */
    public void setLeft(TreeNode<T> left){
        this.left = left;
    }
    /**
     * sets right to TreeNode<T> right
     * @param right
     */
    public void setRight(TreeNode<T> right){
        this.right = right;
    }
    /**
     * sets data to T data
     * @param data
     */
    public void setData(T data){
        this.data = data;
    }
}
