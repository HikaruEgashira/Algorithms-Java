
// DictOpenAddr.java
public class DictOpenAddr {
    DictData[] H;       // 辞書の配列
    int B;              // 配列の大きさ

    // コンストラクタ
    DictOpenAddr(int len) {
        H = new DictData[len];
        B = len;
        for (int i = 0; i < B; i++) {
            H[i] = new DictData();
        }
    }
    
// ハッシュ関数
    int h(int d, int count) {
        return (d + count) % B;
    }
    // データ d を辞書に挿入
    void insert_hash(int d) {
        if(search_hash(d)) return;
        int count = 0;
        int hash;
        while (count != B) {
            hash = h(d, count);
            if (H[hash].state == State.EMPTY || H[hash].state == State.DELETED) {
                H[hash].name = d;
                H[hash].state = State.OCCUPIED;
                return;
            }
            count++;
        }
        System.out.println("Overflow!");
    }
    // データ d が辞書内に含まれるかを探索（戻り値はboolean型でも可）
    boolean search_hash(int d) {
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED && H[hash].name == d) {
                return true;
            } else if (H[hash].state == State.EMPTY) {
                return false;
            }
        }
        return false;
    }
    // データ d を辞書から削除
    void delete_hash(int d) {
        if (!search_hash(d)) {
            System.out.println(d + "is not found");
            return;
        }
        int count = 0;
        int hash;
        while (count != B)  {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED) {
                if (H[hash].name == d) {
                    H[hash].state = State.DELETED;
                    return;
                }
            }
            count++;
        }
    }
    // 配列要素の表示
    void display() {
        for (int i = 0; i < B; i++) {
            switch (H[i].state) {
                case OCCUPIED:
                    System.out.print(H[i].name + " ");
                    break;
                case EMPTY:
                    System.out.print("e ");
                    break;
                case DELETED:
                    System.out.print("d ");
                    break;
                default:
                    break;
            }
        }
        System.out.println();
    }

    // mainメソッド
       public static void main(String[] args) {
               DictOpenAddr dict = new DictOpenAddr(10);

               dict.insert_hash(1);
               dict.insert_hash(2);
               dict.insert_hash(3);
               dict.insert_hash(11);
               dict.insert_hash(12);
               dict.insert_hash(21);
               dict.display();

               System.out.println("Search 1 ...\t" + dict.search_hash(1));
               System.out.println("Search 2 ...\t" + dict.search_hash(2));
               System.out.println("Search 21 ..\t" + dict.search_hash(21));
               System.out.println("Search 5 ...\t" + dict.search_hash(5));

               dict.delete_hash(3);
               dict.display();

               dict.delete_hash(11);
               dict.display();
       }
}
