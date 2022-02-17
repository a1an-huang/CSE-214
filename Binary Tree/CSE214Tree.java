/**
 * Tree CLass : Methods For a binary tree
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         October 10, 2021
 */
public interface CSE214Tree<T>{
    String preorder();
    String postorder();
    String inorder();
    int numNodes();
    int depth(); // a tree with only the root node returns 0; empty tree returns -1
    int numNodesCounter(TreeNode<T> root); // a tree with only the root node returns 0; empty tree returns -1
}
class CSE214BinaryTree<T> implements CSE214Tree<T>{
    TreeNode<T> root;
    /**
     * Empty Constructor
     */
    public CSE214BinaryTree(){
    }
    /**
     * adds into the tree breadth first
     * @param list
     * checks if tree is empty print tree is empty if it is
     * else add elements to the lsit with the method
     */
    public CSE214BinaryTree(T... list){
        if(list.length == 0){
            System.out.println("Tree is Empty");
        }else{
            add(list, 0);
        }
    }
    /**
     * adds to tree breadth first
     * @param list
     * @param i
     * @return
     * checks if i is greater than list size return null if it is
     * check if it is the first index of the tree set root if it is
     * recursively add into the tree breadth first
     * return newNode
     */
    public TreeNode<T> add(T[] list, int i){
        if(i >= list.length){
            return null;
        }
        TreeNode<T> newNode = new TreeNode<>(list[i]);
        if(i == 0){
            root = newNode;
        }
        newNode.left = add(list, 1 + 2 * i);
        newNode.right = add(list, 2 + 2 * i);
        return newNode;
    }
    /**
     * Returns number of Nodes in the Tree
     * @return
     * Recursively checks right and left adds 1 if there is a node else returns 0
     */
    public int numNodesCounter(TreeNode<T> root){
        if(root == null){
            return 0;
        }
        return 1 + numNodesCounter(root.left) + numNodesCounter(root.right);
    }
    /**
     * returns 0 if tree is empty
     * @return
     * calls numnodescounter method
     */
    public int numNodes(){
        if(isEmpty()){
            return 0;
        }
        return numNodesCounter(root);
    }
    /**
     * Returns the depth of the tree
     * @return depth of tree
     * Returns -1 if the tree is empty
     * Returns 0 if there is only one element in the tree
     * Use numNode() in formula to calculate depth
     * 
     */
    public int depth(){
        if(isEmpty()){
            return -1;
        }else if(root.left == null && root.right == null){
            return 0;
        }else{
            return (int)(Math.log(numNodes()) / Math.log(2));
        }
    }
    /**
     * Prints out tree in preorder
     * @return pre
     * calls preorder method 
     * return pre minus last 2 index to removed the commas at the end
     */
    private String pre = "";
    public String preorder(){
        if(isEmpty()){
            return "Tree is Empty!";
        }
        preorder(root);
        return pre.substring(0, pre.length() - 2);
    }
    /**
     * Stores preorder into a string called pre
     * @param root
     * If the tree is empty return nothing. (cSould throw exception)
     * Checks if its last node in the tree to decide if the comma should be printed or not.
     * Recursively calls the method and prints the tree in preorder.
     */
    public void preorder(TreeNode<T> root){
        pre += root.data + ", ";
        if(root.left != null){
            preorder(root.left);
        }
        if(root.right!= null){
            preorder(root.right);
        }
    }
    /**
     * Prints out tree in inorder
     * @return in
     * calls inorder method from treenode class
     * return in minus last 2 index to removed the commas at the end
     */
    private String in = "";
    public String inorder(){
        if(isEmpty()){
            return "Tree is Empty!";
        }
        inorder(root);
        return in.substring(0, in.length() - 2);
    }
    /**
     * Stores inorder into a String called in
     * @param root
     * If the tree is empty return nothing.(could throw exception)
     * Recursively calls the method and prints the tree in inorder.
     */
    public void inorder(TreeNode<T> root){
        if(root.left != null){
            inorder(root.left);
        }
        in += root.data + ", ";
        if(root.right!= null){
            inorder(root.right);
        }
    }
    /**
     * Prints out tree in post
     * @return post
     * calls postorder method from treenode class
     * return in minus last 2 index to removed the commas at the end
     */
    private String post = "";
    public String postorder(){
        if(isEmpty()){
            return "Tree is Empty!";
        }
        postorder(root);
        return post.substring(0, post.length() - 2);
    }
    /**
     * Stores post into a String called post
     * @param root
     * If the tree is empty return nothing.(could throw exception)
     * Recursively calls the method and prints the tree in postorder.
     */
    public void postorder(TreeNode<T> root){
        if(root.left != null){
            postorder(root.left);
        }
        if(root.right!= null){
            postorder(root.right);
        }
        post += root.data + ", ";
    }
    /**
     * Checks if the tree is empty
     * @return
     * Returns true if the tree is empty, by checking if root is null.
     * Returns false is the root node has an element.
     */
    public boolean isEmpty(){
        return (root == null);
    }
}
