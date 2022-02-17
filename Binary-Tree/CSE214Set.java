/**
 *  CSE214Set Interface / Class : gets size, checks contains, and adds for redblack tree.
 * 
 * @author Alan Huang 
 *         Ritwik Banerjee
 *         CSE 214-05 Fall 2021 
 *         November 7, 2021
 */
public interface CSE214Set<E>{
    /**
     * returns size of the tee
     * @return
     */
    int size();
    /**
     * checks if the object is contained in the tree
     * returns true if it is contained false if it isnt
     * @param o
     * @return
     */
    boolean contains(Object o);
    /**
     * inserts the element into the tree
     * sorts the tree into a valid red black tree
     * @param e
     * @return
     */
    boolean add(E e);
}
class CSE214TreeSet<E extends Comparable<E>> extends BinaryTree<E> implements CSE214Set<E>{
    private TreeNode<E> root;
    public CSE214TreeSet(){
        root = null;
    }
    public CSE214TreeSet(E... list){
        if(isEmpty()){
            root = new TreeNode<>(list[0]);
            root.setColor("black");
        }
        for(int i = 1; i < list.length; i++){
            if(add(list[i]) == false){
                System.out.println("elem: " + list[i] + " duplicate entry, has not been inserted.");
            }
        }
    }
    public boolean isEmpty(){
        return size() == 0;
    }
    public int size(){
        return size(root);
    }
    public int size(TreeNode<E> root){
        if(root == null){
            return 0;
        }
        return 1 + size(root.getLeft()) + size(root.getRight());
    }
    public boolean contains(Object o){
        if(isEmpty()){
            return false;
        }else{
            return contains((E)o, root);
        }
    }
    public boolean contains(E o, TreeNode<E> root){
        if(root.getValue().compareTo((E)o) == 0){
            return true;
        }
        if(root.getLeft() != null){
            contains(o, root.getLeft());
        }
        if(root.getRight() != null){
            contains(o, root.getRight());
        }
        return false;
    }
   
    public void insert(TreeNode<E> node){
        insert(root, node);
    
    }
    public TreeNode<E> insert(TreeNode<E> root, TreeNode<E> node){
        if(root == null){
            node.setColor("red");
            root = node;
        }else if(node.getValue().compareTo(root.getValue()) < 0){
            root.setLeft(insert(root.getLeft(), node));
            root.getLeft().setParent(root);
        }else if(node.getValue().compareTo(root.getValue()) > 0){
            root.setRight(insert(root.getRight(), node));   
            root.getRight().setParent(root);
        }
        return root; 
    }
    /**
     public TreeNode<E> getParentNode(TreeNode<E> node){
        if(root == node){
            return null;
        }
        return parentNode(root, node);
    }
    public TreeNode<E> parentNode(TreeNode<E> root, TreeNode<E> node){
        if(root.getLeft().getValue().compareTo(node.getValue()) == 0 || root.getRight().getValue().compareTo(node.getValue()) == 0){
            return root;
        }
        if(root.getLeft() != null && root.getLeft().getValue().compareTo(node.getValue()) < 0){
            parentNode(root.getLeft(), node);
        }
        if(root.getRight() != null && root.getRight().getValue().compareTo(node.getValue()) > 0){
            parentNode(root.getRight(), node);
        }
        return null;
    }
    public void rotateRight(TreeNode<E> node){
        TreeNode<E> grandParent = getParentNode(getParentNode(node));
        TreeNode<E> parent = getParentNode(node);
        TreeNode<E> temp = new TreeNode();
        temp.setRight(node.getRight());
        node.setRight(parent);
        parent.setLeft(null);
        parent.setLeft(temp.getRight());
        if(grandParent.getValue().compareTo(node.getValue()) < 0){
            grandParent.setRight(node);
        }else{
            grandParent.setLeft(node);
        }
    }
    public void rotateLeft(TreeNode<E> node){
        TreeNode<E> grandParent = getParentNode(getParentNode(node));
        TreeNode<E> parent = getParentNode(node);
        TreeNode<E> temp = new TreeNode();
        temp.setLeft(node.getLeft());
        node.setLeft(parent);
        parent.setRight(null);
        parent.setRight(temp.getLeft());
        if(grandParent.getValue().compareTo(node.getValue()) < 0){
            grandParent.setRight(node);
        }else{
            grandParent.setLeft(node);
        }
    }

    */
    public void sort(TreeNode<E> node){
        TreeNode<E> cursor = node;
        while(cursor.getParent() != null && cursor.getParent().getParent() != null && cursor.getColor() == "red" && cursor.getParent().getColor() == "red"){
            TreeNode<E> grandparent = cursor.getParent().getParent();
            TreeNode<E> parent = cursor.getParent();
            if(grandparent.getRight() != null && grandparent.getLeft() == parent){
                if(grandparent.getRight().getColor().equals("red")){
                    grandparent.getRight().setColor("black");
                    parent.setColor("black");
                    grandparent.setColor("red");
                }else{
                    if(parent.getRight() == cursor){
                        rotateLeft(parent);
                        cursor = cursor.getLeft();
                    }
                    grandparent.setColor("red");
                    parent.setColor("black");
                    rotateRight(grandparent);
                }
            }else{
                if(grandparent.getLeft() != null && grandparent.getLeft().getColor().equals("red")){
                    grandparent.getLeft().setColor("black");
                    parent.setColor("black");
                    grandparent.setColor("red");
                }else{
                    if(parent.getLeft() == cursor){
                        rotateRight(parent);
                        cursor = cursor.getRight();
                    }
                    grandparent.setColor("red");
                    parent.setColor("black");
                    rotateLeft(grandparent);
                }
            }
            if(parent != null && grandparent != null && grandparent.getParent() != null && grandparent.getColor().equals("red")){
                cursor = grandparent;
            }
        }
        root.setColor("black");
    }
    public void rotateLeft(TreeNode<E> node){
        TreeNode<E> temp = node.getRight();
        TreeNode<E> parent = node.getParent();
        node.setRight(temp.getLeft());
        if(temp.getLeft() != null){
            temp.getLeft().setParent(node);
        }
        temp.setParent(node.getParent());
        if(parent == null){
            root = temp;
        }else{
            if(parent.getRight() == node){
                parent.setRight(temp);
            }else{
                parent.setLeft(temp);
            }
        }
        temp.setLeft(node);
        node.setParent(temp);
    }
    public void rotateRight(TreeNode<E> node){
        TreeNode<E> temp = node.getLeft();
        TreeNode<E> parent = node.getParent();
        node.setLeft(temp.getRight());

        if(temp.getRight() != null){
            temp.getRight().setParent(node);
        }
        temp.setParent(node.getParent());
        if(parent == null){
            root = temp;
        }else{
            if(parent.getRight() == node){
                parent.setRight(temp);
            }else{
                parent.setLeft(temp);
            }
        }
        temp.setRight(node);
        node.setParent(temp);
    }
    public boolean add(E e){
        if(contains(e)){
            return false;
        }else{
            TreeNode<E> newNode = new TreeNode<>(e);
            insert(newNode);
            sort(newNode);
        }
        //System.out.println(newNode.getValue() + " " + newNode.getParent().getValue());
        return true;
    }
    public int compareTo(Object o) {
        return this.getValue().compareTo((E)o);
    }
    private static <E> String traversePreOrder(TreeNode<E> root) {
        
        if (root == null) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(root.getValue());
        
        String pointerRight = "└──";
        String pointerLeft  = (root.getRight() != null) ? "├──" : "└──";
        
        traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        traverseNodes(sb, "", pointerRight, root.getRight(), false);
        
        return sb.toString();
    }
    
    private static <E> void traverseNodes(StringBuilder sb, String padding, String pointer, TreeNode<E> node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getValue());
            sb.append(" [").append(node.getColor()).append("]");
            
            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }
            
            String paddingForBoth = paddingBuilder.toString();
            String pointerRight   = "└──";
            String pointerLeft    = (node.getRight() != null) ? "├──" : "└──";
            
            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
    
    @Override
    public String toString() {
        return traversePreOrder(root);
    }
    
    public static void main(String[] args) {
        CSE214Set<Integer> empty = new CSE214TreeSet<Integer>();
        System.out.println(empty.size());
        System.out.println(empty.contains(null));
        CSE214Set<Integer> singleton = new CSE214TreeSet<Integer>(0,1,2,3,4,5,6,7);
        System.out.println(singleton.size());
        System.out.println(singleton.contains(4));
        System.out.println(singleton.contains(11));
        System.out.println(singleton);
        CSE214Set<String> str = new CSE214TreeSet<String>("aeeee","ab", "bfwefw", "c", " d", "e", "f", "g" );
        System.out.println(str.size());
        System.out.println(str.contains("a"));
        System.out.println(str);
    } 
    
    
    
}
