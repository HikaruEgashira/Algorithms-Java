// List.java
public class List {
    static List head;
    List next;
    int data;

    // �V���ȃ��X�g�𐶐� p => new_cell => p.next
    static void insert_cell(List p, int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = p.next;
        p.next = new_cell;
    }

    // head = new_cell(�f�[�^��d) => old_head
    static void insert_cell_top(int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = head;
        head = new_cell;
    }

    // p.next �� p.next.next �ŏ㏑������p.next���폜
    static void delete_cell(List p) {
        List q = p.next;
        p.next = q.next;
    }

    // head �� head.next �ŏ㏑��
    static void delete_cell_top() {
        List q = head;
        head = q.next;
    }

    // head �̃R�s�[��.next�ŏI���܂Ń��[�v���Ă��ꂼ��̃f�[�^��\��
    static void display() {
        List tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            tmp = tmp.next;
        }
        // ���s
        System.out.println();
    }

    // List �N���X�̓���m�F
    public static void main(String[] args) {
        insert_cell_top(1);
        insert_cell(head, 3);
        insert_cell(head, 2);
        display();

        delete_cell(head);
        display();

        delete_cell_top();
        display();
    }
}