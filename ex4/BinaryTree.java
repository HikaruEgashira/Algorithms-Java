/**
 * BinaryTree.java
 */
public class BinaryTree {
    // �s���������̂Ȃ���
    // node�̍��̎q���o�͂��Ă����Anull���o�͂��ꂽ��A�������獶�̎q�����ɏo�͂��Ă����B
    public static void preorder(Node n) {
        if (n == null)
            return;
        System.out.print(n.val);
        preorder(n.left);
        preorder(n.right);
    }

    // �ʂ肪�����̂Ȃ���
    // ���̎q��null�t���O���o�͂��āA���̎q�𑀍삵�����O�ɏo��
    public static void inorder(Node n) {
        if (n == null)
            return;
        inorder(n.left);
        System.out.print(n.val);
        inorder(n.right);
    }

    // �A�肪�����̂Ȃ���
    // ���E�̎q��nullnull�t���O��ʂ���node���o�͂���
    public static void postorder(Node n) {
        if (n == null)
            return;
        postorder(n.left);
        postorder(n.right);
        System.out.print(n.val);
    }

    // �؍\���̕\��
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

    // �K�w�Ɛ�Έʒu���킩��悤�ɕ\��
    public static void adv_display(Node n) {
        QueueArray queue = new QueueArray(100);
        queue.enqueue(n);
        // �S��null�ɂȂ�����I��
        while (!queue.is_null()) {
            Node node = queue.dequeue();
            // ���̏I���Ȃ���s
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
    
    // ���D��T��
    public static void breadth_first_search(Node n) {
        QueueArray queue = new QueueArray(100); // �ۑ�Q�Ŏ��������҂��s��
        queue.enqueue(n);
        while (!queue.is_empty()) {
            Node node = queue.dequeue();
            System.out.print(node.val);
            // �q�̃m�[�h���L���[�ɉ�����
            if (node.left != null) {
                queue.enqueue(node.left);
            }
            if (node.right != null) {
                queue.enqueue(node.right);
            }
        }
    }

    // �������Q�̗ݏ� => true
    public static boolean is_power_of_2(int n) {
        if (n == 1) {
            return true;
        } else if (n%2==0) {
            return false;
        }
        return is_power_of_2(n/2);
    }

    // �؂̍����F�������A���ȏ��̒�`�̍��� + 1�Ƃ���Bnull�̍�����0, ���݂̖̂؂������P�B
    // ��ԉ��̗t���[���Ƃ��āA�v���X�P��Ԃ��B���ƉE�̓��傫�������o�͂��čő�l�����߂�B
    public static int height(Node n) {
        if (n == null) return 0;
        int l = height(n.left);
        int r = height(n.right);
        return (l > r) ? l + 1 : r + 1;
    }

    public static void main(String[] args) {
        // �ۑ�̗v���𖞂����e�X�g���s�����߂ɂ́Amain�֐��ōs���e�X�g�͎����ŏ����K�v������܂��B
        // �؍\���̍\�z
        Node f = new Node("F", null, null);
        Node i = new Node("I", null, null);
        Node d = new Node("D", f, null);
        Node g = new Node("G", null, null);
        Node a = new Node("A", i, d);
        Node l = new Node("L", null, g);
        Node c = new Node("C", a, l);

        // �e���\�b�h�̃e�X�g
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