// BinarySearchTree2

public class BinarySearchTree2 {

    // 木機構の表示：２分木の実装のメソッド displayを再利用
    public static void display(IntNode2 n) {
        if (n == null) {
            System.out.print("null");
            return;
        };
        System.out.print(n.val + "#" + n.height);
        System.out.print("(");
        display(n.left);
        System.out.print(", ");
        display(n.right);
        System.out.print(")");
    }

    // 最小値の探索
    public static int min_bst(IntNode2 p) {
        if (p == null)
            return -1;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }

    // 値の探索
    public static boolean search_bst(IntNode2 p, int d) {
        if (p == null)
            return false;
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

    // 値の挿入
    public static IntNode2 insert_bst(IntNode2 p, int d) {
        IntNode2 data = new IntNode2(d, null, null);
        if (p == null)
            return data;
        IntNode2 _p = p;
        while (true) {
            if (_p.val == d)
                // 重複
                return p;
            else if (_p.val > d) {
                if (_p.left == null) {
                    _p.left = data;
                    return p;
                } else
                    _p = _p.left;
            } else {
                if (_p.right == null) {
                    _p.right = data;
                    return p;
                } else
                    _p = _p.right;
            }
        }
    }

    // 最小値の削除
    public static IntNode2 delete_min_bst(IntNode2 p) {
        if (p == null)
            return null;
        IntNode2 _p = p;
        while (_p.left.left != null) {
            _p = _p.left;
        }
        IntNode2 min = _p.left;
        _p.left = null;
        return min;
    }

    // 値の削除
    public static IntNode2 delete_bst(IntNode2 p, int d) {
        if (p == null)
            return p;

        IntNode2 _p = p;
        IntNode2 parent = null;
        Lr lr = null;

        while (_p != null) {
            if (_p.val > d) {
                parent = _p;
                _p = _p.left;
                lr = Lr.L;
            } else if (_p.val < d) {
                parent = _p;
                _p = _p.right;
                lr = Lr.R;
            } else {
                // 見つけた！
                // p: 削除対象ノード, parent: 親, parent.lr == p
                if (_p.left == null || _p.right == null) {
                    __delete(_p, parent, lr);
                    return _p;
                } else {
                    // 一重回転: 両方が埋まってる
                    IntNode2 r = _p.right; // r == 右部木の最小ノード になるように探索
                    parent = _p;
                    lr = Lr.R;

                    while (r.left != null) {
                        parent = r;
                        r = r.left;
                        lr = Lr.L;
                    }

                    _p.val = r.val;
                    r.val = d;
                    __delete(r, parent, lr);
                }
            }
        }
        return p;
    }

    public static void __delete(IntNode2 p, IntNode2 parent, Lr lr) {
        if (p == null)
            return;
        if (p.left == null && p.right == null) { // p == 葉
            // 親との繋がりを遮断
            if (lr == Lr.L)
                parent.left = null;
            else
                parent.right = null;
        } else if (p.left == null) { // pの右に子
            if (lr == Lr.L)
                parent.left = p.right;
            else
                parent.right = p.right;
        } else if (p.right == null) { // pの左に子
            if (lr == Lr.L)
                parent.left = p.left;
            else
                parent.right = p.left;
        } else { // 両方に子
            System.err.println("This should １重回転");
        }
    }

    public static void main(String[] args) {
        // 値の挿入
        // 空の２分探索木に対して、値 10, 15, 18, 6, 12, 20, 19,
        // 9をこの順で挿入したときの作られる２分探索木の各節点が正しい高さの情報を保持していることを確認せよ。
        IntNode2 t = null;
        t = insert_bst(t, 10);
        t = insert_bst(t, 15);
        t = insert_bst(t, 18);
        t = insert_bst(t, 6);
        t = insert_bst(t, 12);
        t = insert_bst(t, 20);
        t = insert_bst(t, 19);
        display(t);
        System.out.println();
        // 1で出来た２分探索木から1５を削除する。２分探索木の各節点が正しい高さの情報を保持していることを確認せよ。
        t = delete_bst(t, 15);
        display(t);
        System.out.println();
        // 2で出来た２分探索木から19を削除する。２分探索木の各節点が正しい高さの情報を保持していることを確認せよ。
        t = delete_bst(t, 19);
        display(t);
        System.out.println();
    }
}