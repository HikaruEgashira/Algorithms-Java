// ListDL.java
public class ListDL23 {
    private ListDL prev, next;
    int val;

    ListDL() {
        // -1�̓_�~�[�f�[�^�Ȃ̂œǂݎ��Ȃ�
        this(-1);
    }
    
    ListDL(int val) {
        this.val = val;
        // �z���邽�߂̃R�[�h
        this.next = this;
        this.prev = this;
    }

    // prev <=> cell <=> next
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        // prev => cell
        if (prev != null) {
            prev.next = cell;
        }
        // next => cell
        if (next != null) {
            next.prev = cell;
        }
        if (cell != null) {
            // cell => next
            cell.next = next;
            // cell => prev
            cell.prev = prev;
        }
    }

    // prev �� next �̌��т�
    private static void __Delete(ListDL prev, ListDL next) {
        if (prev != null) {
            // �����𖞂����Ƃ���next��null�ł͂Ȃ�
            prev.next = next;
            next.prev = prev;
        }
    }

    // ���т����Ȃ���
    private void initLinks() {
        this.prev = null;
        this.next = null;
    }

    // this => cell => this.next
    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
        this.next = cell;
    }

    // this.prev => cell => this
    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
        this.prev = cell;
    }

    // this.prev => this.next (no this)
    void delete() {
        __Delete(this.prev, this.next);
        initLinks();
    }

    // ���X�g�̃R�s�[�𑖍����āA�f�[�^�l�������Ɠ������̂�Ԃ�
    ListDL search(int i) {
        ListDL tmp = this;
        while (tmp != null) {
            tmp = tmp.next;
            if (tmp.val == i) {
                return tmp;
            }
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