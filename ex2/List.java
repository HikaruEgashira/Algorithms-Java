public class List {
    static List head;
    List next;
    int data;

    static void insert_cell(List p, int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = p.next;
        p.next = new_cell;
    }

    static void insert_cell_top(int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = head;
        head = new_cell;
    }

    static void delete_cell(List p) {
        List q = p.next;
        p.next = q.next;
    }

    static void delete_cell_top() {
        List q = head;
        head = q.next;
    }

    static void display() {
        List tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            tmp = tmp.next;
        }
        System.out.println();
    }

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