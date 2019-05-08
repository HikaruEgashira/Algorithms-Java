// ListDL.java
public class ListDL23 {
    private ListDL prev, next;
    int val;

    ListDL() {
        // -1はダミーデータなので読み取らない
        this(-1);
    }
    
    ListDL(int val) {
        this.val = val;
        // 循環するためのコード
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

    // prev と next の結びつけ
    private static void __Delete(ListDL prev, ListDL next) {
        if (prev != null) {
            // 条件を満たすときはnextもnullではない
            prev.next = next;
            next.prev = prev;
        }
    }

    // 結びつきをなくす
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

    // リストのコピーを走査して、データ値が引数と同じものを返す
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