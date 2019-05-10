import java.io.*;

// ListDLInt.java
public class ListDL {
    private ListDL next, prev;
    Object val;

    ListDL() {
        // 循環する
        initLinks();
    }

    ListDL(Object val) {
        initLinks();
        this.val = val;
    }

    // prev <=> cell <=> next
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        prev.next = cell;       // prev => cell
        next.prev = cell;       // next => cell
        cell.next = next;       // cell => next
        cell.prev = prev;       // cell => prev
    }

    // prev と next の結びつけ
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // 初期化
    private void initLinks() {
        this.next = this;
        this.prev = this;
    }

    // this => cell => this.next
    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
    }

    // this.prev => cell => this
    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
    }

    // this.prev => this.next (no this)
    void delete() {
        __Delete(this.prev, this.next);
        initLinks();
    }

    // リストのコピーを走査して、データ値が引数と同じものを返す
    ListDL search(Object i) {
        for (ListDL j = this.next; j != this; j = j.next) {
            if (i.equals(j.val)) {
                return j;
            }
        }
        return null;
    }

    void display() {
        for (ListDL j = this.next; j != this; j = j.next) {
            if (j.val != null) {
                System.out.print(j.val + " -> ");
            }
        }
        System.out.println();
    }

    // 配列からリストの要素を読み込むメソッド readFromArray()
    ListDL readFromArray(Object[] Array) {
        for (Object val : Array) {
            this.insertPrev(new ListDL(val));
        }
        return this;
    }

    // リストの要素数を返す
    int length() {
        int Count = 0;
        for (ListDL i = this.next; i != this; i = i.next) {
            Count++;
        }
        return Count;
    }

    // リストの要素を書き出した配列を返すメソッド writeToArray()
    int[] writeToArray() {
        // リストと同じ長さの配列を作成してリストの値を代入する。
        int[] res = new int[this.length()];
        int Count = 0;
        for (ListDL i = this.next; i != this; i = i.next) {
            res[Count] = (int) i.val;
            Count++;
        }
        return res;
    }

    // ファイルからリストの要素を読み込むメソッド readFromFile()
    void readFromFile(String readfile) {
        try {
            // 引数のパスにあるファイルを読み込んで一行ずつリストに追加
            File file = new File(readfile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    insertPrev(new ListDL(str));
                }
            } finally {
                // ファイルを閉じる
                br.close();
            }
        } catch (FileNotFoundException e) {
            // ファイルが存在しない
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // ファイルにリストの要素を書き出すメソッド writeToFile() を追加しなさい。
    void writeToFile(String fileName) {
        try {
            //リストをループするごとに 引数のパスにあるファイルにデータを書き込む
            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            try {
                for (ListDL i = this.next; i != this; i = i.next) {
                    pw.println(i.val);
                }
            } finally {
                // ファイルを閉じる
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}