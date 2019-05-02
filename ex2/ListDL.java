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

    // cell, prev, next間の関係を定義
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

    // prevとnextの結びつけ
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // 結びつきをなくす
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

    // リストの実装をテストするためのクラス
    public static void main(String[] args) {
          ListDL head = new ListDL();          // ダミーセルの生成
          ListDL elem;

          head.insertNext(new ListDL(2));      // セルの先頭への追加
          head.insertNext(new ListDL(1));
          head.insertPrev(new ListDL(5));      // セルの末尾への追加
          head.display();                      // リストの表示

          elem = head.search(2);               // セルを探す elem.val == 5
          elem.insertNext(new ListDL(3));      // 探したセルの直後にセルを追加
          head.display();

          elem = head.search(5);               // セルを探す elem.val == 5
          elem.delete();                       // 探したセルを削除
          head.display();
    }
}