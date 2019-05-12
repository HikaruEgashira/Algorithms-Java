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