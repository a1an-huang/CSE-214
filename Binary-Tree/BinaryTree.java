public class BinaryTree<E> {
    
    private E             value;
    private String        color;
    private BinaryTree<E> left;
    private BinaryTree<E> right;
    public BinaryTree(){
    }
    public BinaryTree(E e) {
        this.value = e;
        this.color = "red";
    }
    
    public E getValue()                       { return value; }
    
    public void setValue(E value)             { this.value = value; }
    
    public BinaryTree<E> getLeft()            { return left; }
    
    public void setLeft(BinaryTree<E> left)   { this.left = left; }
    
    public BinaryTree<E> getRight()           { return right; }
    
    public void setRight(BinaryTree<E> right) { this.right = right; }
    
    public String getColor()                  { return this.color; }
    
    private static <E> String traversePreOrder(BinaryTree<E> root) {
        
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
    
    private static <E> void traverseNodes(StringBuilder sb, String padding, String pointer, BinaryTree<E> node, boolean hasRightSibling) {
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
        return traversePreOrder(this);
    }
    
    
    public static void main(String... args) {
        BinaryTree<String> root = new BinaryTree<>("root");
        
        BinaryTree<String> node1 = new BinaryTree<>("node1");
        BinaryTree<String> node2 = new BinaryTree<>("node2");
        root.setLeft(node1);
        root.setRight(node2);
        
        BinaryTree<String> node3 = new BinaryTree<>("node3");
        BinaryTree<String> node4 = new BinaryTree<>("node4");
        node1.setLeft(node3);
        node1.setRight(node4);
        
        node2.setLeft(new BinaryTree<>("node5"));
        node2.setRight(new BinaryTree<>("node6"));
        
        BinaryTree<String> node7 = new BinaryTree<>("node7");
        node3.setLeft(node7);
        node7.setLeft(new BinaryTree<>("node8"));
        node7.setRight(new BinaryTree<>("node9"));
        
        System.out.println(root.toString());
    }
}
