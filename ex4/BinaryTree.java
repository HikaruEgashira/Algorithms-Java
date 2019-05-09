/**
 * BinaryTree.java
 */
public class BinaryTree {
    // 行きがけ順のなぞり
    public static void preorder(Node n) {

    }

    // 通りがけ順のなぞり
    public static void inorder(Node n) {

    }

    // 帰りがけ順のなぞり
    public static void postorder(Node n) {

    }

    // 木構造の表示
    public static void display(Node n) {

    }

    // 幅優先探索
    public static void breadth_first_search(Node n) {
        QueueArray queue = new QueueArray(100); // 課題２で実装した待ち行列

    }

    // 木の高さ：ただし、教科書の定義の高さ + 1とする。nullの高さが0, 根のみの木が高さ１
    public static int height(Node n) {

    }

    public static void main(String[] args) {
        // 課題の要件を満たすテストを行うためには、main関数で行うテストは自分で書く必要があります。
        // 木構造の構築
        Node f = new Node("F", null, null);
        Node i = new Node("I", null, null);
        Node d = new Node("D", f, null);
        Node g = new Node("G", null, null);
        Node a = new Node("A", i, d);
        Node l = new Node("L", null, g);
        Node c = new Node("C", a, l);

        // 各メソッドのテスト
        preorder(c);
        System.out.println();
        postorder(c);
        System.out.println();
        inorder(c);
        System.out.println();
        breadth_first_search(c);
        System.out.println();
        display(c);
        System.out.println();
    }
}