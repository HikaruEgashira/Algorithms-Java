/**
 * BinaryTree.java
 */
public class BinaryTree {
    // �s���������̂Ȃ���
    public static void preorder(Node n) {

    }

    // �ʂ肪�����̂Ȃ���
    public static void inorder(Node n) {

    }

    // �A�肪�����̂Ȃ���
    public static void postorder(Node n) {

    }

    // �؍\���̕\��
    public static void display(Node n) {

    }

    // ���D��T��
    public static void breadth_first_search(Node n) {
        QueueArray queue = new QueueArray(100); // �ۑ�Q�Ŏ��������҂��s��

    }

    // �؂̍����F�������A���ȏ��̒�`�̍��� + 1�Ƃ���Bnull�̍�����0, ���݂̖̂؂������P
    public static int height(Node n) {

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