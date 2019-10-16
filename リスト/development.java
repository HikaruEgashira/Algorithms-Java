// ListDLInt.java
class ListDL {
    private ListDL next, prev;
    
    ListDL() {
        // èzä¬Ç∑ÇÈÇΩÇﬂÇÃÉRÅ[Éh
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

    // prev Ç∆ next ÇÃåãÇ—Ç¬ÇØ
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // åãÇ—Ç¬Ç´ÇÇ»Ç≠Ç∑
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