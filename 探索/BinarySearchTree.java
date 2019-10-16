enum Lr {
    L, R
};

public class BinarySearchTree {
    static IntNode root;

    // �؍\���̕\��
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

    // �ŏ��l�̒T��: �؂̈�ԍ��̍��̒l
    public static int min_bst() {
        if (root == null)
            return -1;
        IntNode p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p.val;
    }

    // �l�̒T��: ���Ɣ�ׂđ傫����΍��ɁA��������ΉE�ɍs���A
    // �������l��������܂Ń��[�v���ĒT������
    public static boolean search_bst(int d) {
        if (root == null)
            return false;
        IntNode p = root;
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

    // �l�̑}��: ���߂ĂȂ�΁Anew����B
    // ���Ɣ�ׂāA�傫����ΉE�ɁA��������΍��ɂ��J��Ԃ�
    // �|�C���^��null�ɂȂ����ꏊ���}������ꏊ�ł���
    public static void insert_bst(int d) {
        IntNode data = new IntNode(d, null, null);
        if (root == null) {
            root = data;
            return;
        }
        IntNode p = root;
        while (true) {
            if (p.val == d)
                // �d��
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

    // �l�̍폜: �e�ƌ��݂Ƃ̑��Έʒu�iLr�j��ێ����Ȃ���T��
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
                // �������I
                // p: �폜�Ώۃm�[�h, parent: �e, parent.lr == p
                if (p.left == null || p.right == null) {
                    __delete(p, parent, lr);
                    return;
                } else {
                    // ��d��]: ���������܂��Ă�
                    IntNode r = p.right; // r == �E���؂̍ŏ��m�[�h �ɂȂ�悤�ɒT��
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
        if (p.left == null && p.right == null) { // p == �t
            if (parent == null) // p == ��
                root = null;
            else {
                // �e�Ƃ̌q������Ւf
                if (lr == Lr.L)
                    parent.left = null;
                else
                    parent.right = null;
            }
        } else if (p.left == null) { // p�̉E�Ɏq
            if (parent == null)
                root = p.left;
            else {
                if (lr == Lr.L)
                    parent.left = p.right;
                else
                    parent.right = p.right;
            }
        } else if (p.right == null) { // p�̍��Ɏq
            if (parent == null)
                root = p.right;
            else {
                if (lr == Lr.L)
                    parent.left = p.left;
                else
                    parent.right = p.left;
            }
        } else { // �����Ɏq
            System.out.println("This should �P�d��]");
        }
    }

    public static void main(String[] args) {
        // ��̂Q���T���؂ɑ΂��āA�l 10, 15, 18, 6, 12, 20, 19, 9�����̏���
        // ��������Ƃ��̍����Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����
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

        // 1�ŏo�����Q���T���؂���15���폜����B�Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����B
        delete_bst(15); // 10(6(null, 9), 18(12(null, null), 20(19(null, null), null)))
        display(root);
        System.out.println();
        // 2�ŏo�����Q���T���؂���19���폜����B�Q���T���؂̊e�ߓ_�������������̏���ێ����Ă��邱�Ƃ��m�F����B
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

        // ���W�ۑ�̃e�X�g
        delete_bst(10);
        display(root);
        System.out.println();
    }
}
