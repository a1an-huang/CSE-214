/**
 *  TreeNode : Node class that contains values for the Red Black Tree
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 7, 2021
 */
public class TreeNode<E>{
    private E value;
    private String color = "red";
    public TreeNode<E> left;
    public TreeNode<E> right;
    public TreeNode<E> parent;
    public TreeNode(){

    }
    public TreeNode(E value){
        this.value = value;
    }
    public String getColor(){
        return this.color;
    }
    public E getValue(){
        return this.value;
    }

    public TreeNode<E> getLeft(){
        return this.left;
    }
    public TreeNode<E> getRight(){
        return this.right;
    }

    public void setColor(String color){
        this.color = color;
    }
    public void setValue(E value){
        this.value = value;
    }

    public void setLeft(TreeNode<E> left){
        this.left = left;
    }
    public void setRight(TreeNode<E> right){
        this.right = right;
    }

    public void setParent(TreeNode<E> p){
        this.parent = p;
    }
    public TreeNode<E> getParent(){
        return parent;
    }
}
