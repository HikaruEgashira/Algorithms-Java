// ListDL.java
public class ListDL {
    private ListDL prev, next;
    int val;

    ListDL() {
        this.val = -1;
        this.next = this;
        this.prev = this;
    }

    ListDL(int val) {
        this.val = val;
        this.next = this;
        this.prev = this;
    }

    // cell, prev, next�Ԃ̊֌W���`
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        // next => cell
        if (prev != null) {
            prev.next = cell;
        }
        // prev => cell
        if (next != null) {
            next.prev = cell;
        }
        // cell => next
        cell.next = next;
        // cell => prev
        cell.prev = prev;
    }

    // prev��next�̌��т�
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // ���т����Ȃ���
    private void initLinks() {
        this.prev = null;
        this.next = null;
    }

    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
        this.next = cell;
    }

    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
        this.prev = cell;
    }

    void delete() {
        __Delete(this.prev, this.next);
        initLinks();
    }

    ListDL search(int i) {
        ListDL tmp = this;
        while (tmp != null) {
            if (tmp.val == i) {
                return tmp;
            }
            tmp = tmp.next;
        }
        return null;
    }

    void display() {
        ListDL tmp = this.next;
        while (tmp.val != -1) {
            System.out.print(tmp.val);
            tmp = tmp.next;
        }
        System.out.println();
    }

    // ���X�g�̎������e�X�g���邽�߂̃N���X
    public static void main(String[] args) {
          ListDL head = new ListDL();          // �_�~�[�Z���̐���
          ListDL elem;

          head.insertNext(new ListDL(2));      // �Z���̐擪�ւ̒ǉ�
          head.insertNext(new ListDL(1));
          head.insertPrev(new ListDL(5));      // �Z���̖����ւ̒ǉ�
          head.display();                      // ���X�g�̕\��

          elem = head.search(2);               // �Z����T�� elem.val == 5
          elem.insertNext(new ListDL(3));      // �T�����Z���̒���ɃZ����ǉ�
          head.display();

          elem = head.search(5);               // �Z����T�� elem.val == 5
          elem.delete();                       // �T�����Z�����폜
          head.display();
    }
}