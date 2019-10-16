/**
 * BinaryTree.java
 */
public class BinaryTree {
    // 行きがけ順のなぞり
    // nodeの左の子を出力していき、nullが出力されたら、そこから左の子を順に出力していく。
    public static void preorder(Node n) {
        if (n == null)
            return;
        System.out.print(n.val);
        preorder(n.left);
        preorder(n.right);
    }

    // 通りがけ順のなぞり
    // 左の子がnullフラグを出力して、左の子を操作しだす前に出力
    public static void inorder(Node n) {
        if (n == null)
            return;
        inorder(n.left);
        System.out.print(n.val);
        inorder(n.right);
    }

    // 帰りがけ順のなぞり
    // 左右の子がnullnullフラグを通ったnodeを出力する
    public static void postorder(Node n) {
        if (n == null)
            return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.val);
    }

    // 木構造の表示
    public static void display(Node n) {
        if (n == null) {
            System.out.print("null");
            return;
        };
        System.out.print(n.val);
        System.out.print("(");
        display(n.left);
        System.out.print(",");
        display(n.right);
        System.out.print(")");
    }

    // 階層と絶対位置がわかるように表示
    public static void adv_display(Node n) {
        QueueArray queue = new QueueArray(100);
        queue.enqueue(n);
        // 全てnullになったら終了
        while (!queue.is_null()) {
            Node node = queue.dequeue();
            // 幅の終わりなら改行
            if (is_power_of_2(queue.rear - queue.front)) {
                System.out.println("\n");
            }
            
            System.out.print(node.val);
            
            if (node.left != null) {
                queue.enqueue(node.left);
            } else {
                queue.enqueue(new Node(" ", null, null));
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            } else {
                queue.enqueue(new Node(" ", null, null));
            }
        }
    }
    
    // 幅優先探索
    public static void breadth_first_search(Node n) {
        QueueArray queue = new QueueArray(100); // 課題２で実装した待ち行列
        queue.enqueue(n);
        while (!queue.is_empty()) {
            Node node = queue.dequeue();
            System.out.print(node.val);
            // 子のノードをキューに加える
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    // 引数が２の累乗 => true
    public static boolean is_power_of_2(int n) {
        if (n == 1) {
            return true;
        } else if (n%2==0) {
            return false;
        }
        return is_power_of_2(n/2);
    }

    // 木の高さ：ただし、教科書の定義の高さ + 1とする。nullの高さが0, 根のみの木が高さ１。
    // 一番下の葉をゼロとして、プラス１を返す。左と右の内大きい方を出力して最大値を求める。
    public static int height(Node n) {
        if (n == null) return 0;
        int l = height(n.left);
        int r = height(n.right);
        return (l > r) ? l + 1 : r + 1;
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
        preorder(c);                    // CAIDFLG
        System.out.println();
        postorder(c);                   // IFDAGLC
        System.out.println();
        inorder(c);                     // IAFDCLG
        System.out.println();
        breadth_first_search(c);
        System.out.println();
        System.out.println(height(c));  // 4
        display(c);
        System.out.println();
        System.out.println("advance================");
        adv_display(c);
        /** ==> C(A(I(null,null),D(F(null,null),null)),L(null,G(null,null))) */
        System.out.println();
    }
}