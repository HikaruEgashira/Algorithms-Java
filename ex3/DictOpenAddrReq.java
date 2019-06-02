
// DictOpenAddr.java
import java.util.Random;

public class DictOpenAddrReq {
    DictData[] H; // 辞書の配列
    int B; // 配列の大きさ

    // コンストラクタ
    DictOpenAddrReq(int len) {
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
        if (search_hash(d)) return;
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.EMPTY || H[hash].state == State.DELETED) {
                H[hash].name = d;
                H[hash].state = State.OCCUPIED;
                return;
            }
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
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED) {
                if (H[hash].name == d) {
                    H[hash].state = State.DELETED;
                    return;
                }
            }
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

    public static void main(String[] args) {
        DictOpenAddrAdv dict = new DictOpenAddrAdv(10000);
        int length = 100; // 試行回数
        Random rnd = new Random();

        long[] time_insert = new long[length];
        long[] time_search = new long[length];
        long[] time_delete = new long[length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 1000; j++) {

                // insert
                long t_insert1 = System.nanoTime();
                dict.search_hash(rnd.nextInt(1000));
                long t_insert2 = System.nanoTime();

                // search
                long t_search1 = System.nanoTime();
                dict.search_hash(rnd.nextInt(1000));
                long t_search2 = System.nanoTime();

                // delete
                long t_delete1 = System.nanoTime();
                dict.delete_hash(rnd.nextInt(1000));
                long t_delete2 = System.nanoTime();

                time_insert[i] += t_insert2 - t_insert1;
                time_search[i] += t_search2 - t_search1;
                time_delete[i] += t_delete2 - t_delete1;
            }
        }
        long sum_insert = 0;
        long sum_search = 0;
        long sum_delete = 0;
        for (int i = 0; i < length; i++) {
            sum_insert += time_insert[i];
            sum_search += time_search[i];
            sum_delete += time_delete[i];
        }
        System.out.println("insert:" + sum_insert / length); // insert:1103917
        System.out.println("search:" + sum_search / length); // search:1103999
        System.out.println("delete:" + sum_delete / length); // delete:1113875
    }
}
