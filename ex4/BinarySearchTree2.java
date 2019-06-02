// BinarySearchTree2

public class BinarySearchTree2 {

    // �؋@�\�̕\���F�Q���؂̎����̃��\�b�h display���ė��p
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

    // �ŏ��l�̒T��
    public static int min_bst(IntNode2 p) {
        if (p == null)
            return -1;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }

    // �l�̒T��
    public static boolean search_bst(IntNode2 p, int d) {
        if (p == null)
            return false;
        while (p != null) {
            if (p.val == d)
                return true;
            else
                // �T�����Ă�f�[�^ > ���߂��� => ����ɍ���T��
                // �T�����Ă�f�[�^ < ���߂��� => �E��T��
                p = p.val > d ? p.left : p.right;
        }
        return false;
    }

    // �l�̑}��
    public static IntNode2 insert_bst(IntNode2 p, int d) {
        IntNode2 data = new IntNode2(d, null, null);
        if (p == null)
            return data;
        IntNode2 _p = p;
        while (true) {
            if (_p.val == d)
                // �d��
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

    // �ŏ��l�̍폜
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

    // �l�̍폜
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
                // �������I
                // p: �폜�Ώۃm�[�h, parent: �e, parent.lr == p
                if (_p.left == null || _p.right == null) {
                    __delete(_p, parent, lr);
                    return _p;
                } else {
                    // ��d��]: ���������܂��Ă�
                    IntNode2 r = _p.right; // r == �E���؂̍ŏ��m�[�h �ɂȂ�悤�ɒT��
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
        if (p.left == null && p.right == null) { // p == �t
            // �e�Ƃ̌q������Ւf
            if (lr == Lr.L)
                parent.left = null;
            else
                parent.right = null;
        } else if (p.left == null) { // p�̉E�Ɏq
            if (lr == Lr.L)
                parent.left = p.right;
            else
                parent.right = p.right;
        } else if (p.right == null) { // p�̍��Ɏq
            if (lr == Lr.L)
                parent.left = p.left;
            else
                parent.right = p.left;
        } else { // �����Ɏq
            System.err.println("This should �P�d��]");
        }
    }

    public static void main(String[] args) {
        // �l�̑}��
        // ��̂Q���T���؂ɑ΂��āA�l 10, 15, 18, 6, 12, 20, 19,
        // 9�����̏��ő}�������Ƃ��̍����Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����B
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
        // 1�ŏo�����Q���T���؂���1�T���폜����B�Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����B
        t = delete_bst(t, 15);
        display(t);
        System.out.println();
        // 2�ŏo�����Q���T���؂���19���폜����B�Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����B
        t = delete_bst(t, 19);
        display(t);
        System.out.println();
    }
}