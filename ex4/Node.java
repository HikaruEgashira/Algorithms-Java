// 2分木のノード
/**
 * Node.java
 */
public class Node {
    String val;
    Node left, right;

    public Node (String val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}