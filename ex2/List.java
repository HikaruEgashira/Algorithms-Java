// List.java
public class List {
    static List head;
    List next;
    int data;

    // 新たなリストを生成 p => new_cell => p.next
    static void insert_cell(List p, int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = p.next;
        p.next = new_cell;
    }

    // head = new_cell(データはd) => old_head
    static void insert_cell_top(int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = head;
        head = new_cell;
    }

    // p.next を p.next.next で上書きしてp.nextを削除
    static void delete_cell(List p) {
        List q = p.next;
        p.next = q.next;
    }

    // head を head.next で上書き
    static void delete_cell_top() {
        List q = head;
        head = q.next;
    }

    // head のコピーを.nextで終わりまでループしてそれぞれのデータを表示
    static void display() {
        List tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            tmp = tmp.next;
        }
        // 改行
        System.out.println();
    }

    // List クラスの動作確認
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