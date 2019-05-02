// ListDL.java
public class ListDL {
    private ListDL prev, next;
    int val;

    ListDL() {
        this.val = 0;
    }

    ListDL(int val) {
        this.val = val;
    }

    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        cell.next = next;
        cell.prev = prev;
    }

    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    private void initLinks() {
        this.prev = null;
        this.next = null;
    }

    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
        if (this.prev != null) {
            this.prev.next = this;
        }
        if (this.next != null) {
            this.next.prev = this;
        }
    }

    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
        if (this.prev != null) {
            this.prev.next = cell;
        }
        if (this.next != null) {
            this.next.prev = cell;
        }
    }

    void delete() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
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
        while (tmp != null) {
            System.out.print(tmp.val);
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListDL head = new ListDL();
        ListDL elem;

        head.insertNext(new ListDL(2));
        head.insertNext(new ListDL(1));
        head.insertPrev(new ListDL(5));
        head.display();

        elem = head.search(2);
        elem.insertNext(new ListDL(3));
        head.display();

        elem = head.search(5);
        elem.delete();
        head.display();
    }
}