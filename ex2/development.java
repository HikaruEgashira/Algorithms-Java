// ListDLInt.java
class ListDL {
    private ListDL next, prev;
    
    ListDL() {
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
        // cell => next
        cell.next = next;
        // cell => prev
        cell.prev = prev;
    }

    // prev �� next �̌��т�
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
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
}