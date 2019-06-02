enum Lr {
    L, R
};

public class BinarySearchTree {
    static IntNode root;

    // 木構造の表示
    public static void display(IntNode n) {
        if (n == null) {
            System.out.print("null");
            return;
        }
        ;
        System.out.print(n.val);
        System.out.print("(");
        display(n.left);
        System.out.print(", ");
        display(n.right);
        System.out.print(")");
    }

    // 最小値の探索: 木の一番左の根の値
    public static int min_bst() {
        if (root == null)
            return -1;
        IntNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }

    // 値の探索: 根と比べて大きければ左に、小さければ右に行き、
    // 等しい値が見つかるまでループして探索する
    public static boolean search_bst(int d) {
        if (root == null)
            return false;
        IntNode p = root;
        while (p != null) {
            if (p.val == d)
                return true;
            else
                // 探索してるデータ > 求めたい => さらに左を探す
                // 探索してるデータ < 求めたい => 右を探す
                p = p.val > d ? p.left : p.right;
        }
        return false;
    }

    // 値の挿入: 初めてならば、newする。
    // 根と比べて、大きければ右に、小さければ左にを繰り返す
    // ポインタがnullになった場所が挿入する場所である
    public static void insert_bst(int d) {
        IntNode data = new IntNode(d, null, null);
        if (root == null) {
            root = data;
            return;
        }
        IntNode p = root;
        while (true) {
            if (p.val == d)
                // 重複
                return;
            else if (p.val > d) {
                if (p.left == null) {
                    p.left = data;
                    return;
                } else
                    p = p.left;
            } else {
                if (p.right == null) {
                    p.right = data;
                    return;
                } else
                    p = p.right;
            }
        }
    }

    // 値の削除: 親と現在との相対位置（Lr）を保持しながら探索
    public static void delete_bst(int d) {
        if (root == null)
            return;

        IntNode p = root;
        IntNode parent = null;5
        Lr lr = null;

        while (p != null) {
            if (p.val > d) {
                parent = p;
                p = p.left;
                lr = Lr.L;
            } else if (p.val < d) {
                parent = p;
                p = p.right;
                lr = Lr.R;
            } else {
                // 見つけた！
                // p: 削除対象ノード, parent: 親, parent.lr == p
                if (p.left == null || p.right == null) {
                    __delete(p, parent, lr);
                    return;
                } else {
                    // 一重回転: 両方が埋まってる
                    IntNode r = p.right; // r == 右部木の最小ノード になるように探索
                    parent = p;
                    lr = Lr.R;

                    while (r.left != null) {
                        parent = r;
                        r = r.left;
                        lr = Lr.L;
                    }
                    
                    p.val = r.val;
                    r.val = d;
                    __delete(r, parent, lr);
                }
            }
        }
    }

    public static void __delete(IntNode p, IntNode parent, Lr lr) {
        if (p.left == null && p.right == null) { // p == 葉
            if (parent == null) // p == 根
                root = null;
            else {
                // 親との繋がりを遮断
                if (lr == Lr.L)
                    parent.left = null;
                else
                    parent.right = null;
            }
        } else if (p.left == null) { // pの右に子
            if (parent == null)
                root = p.left;
            else {
                if (lr == Lr.L)
                    parent.left = p.right;
                else
                    parent.right = p.right;
            }
        } else if (p.right == null) { // pの左に子
            if (parent == null)
                root = p.right;
            else {
                if (lr == Lr.L)
                    parent.left = p.left;
                else
                    parent.right = p.left;
            }
        } else { // 両方に子
            System.out.println("This should １重回転");
        }
    }

    public static void main(String[] args) {
        // 空の２分探索木に対して、値 10, 15, 18, 6, 12, 20, 19, 9をこの順で
        // 代入したときの作られる２分探索木の各節点が正しい高さの情報を保持していることを確認せよ
        insert_bst(10);
        insert_bst(15);
        insert_bst(18);
        insert_bst(6);
        insert_bst(12);
        insert_bst(20);
        insert_bst(19);
        insert_bst(9);
        display(root);
        System.out.println();

        // 1で出来た２分探索木から15を削除する。２分探索木の各節点が正しい高さの情報を保持していることを確認せよ。
        delete_bst(15); // 10(6(null, 9), 18(12(null, null), 20(19(null, null), null)))
        display(root);
        System.out.println();
        // 2で出来た２分探索木から19を削除する。２分探索木の各節点が正しい高さの情報を保持していることを確認せよ。
        delete_bst(19); // 10(6(null, 9), 18(12(null, null), 20(null, null)))
        display(root);
        System.out.println();
        if (search_bst(14))
            System.out.println("Found!");
        else
            System.out.println("Not found!");
        if (search_bst(7))
            System.out.println("Found!");
        else
            System.out.println("Not found!");

        System.out.println("min: " + min_bst());

        // 発展課題のテスト
        delete_bst(10);
        display(root);
        System.out.println();
    }
}
