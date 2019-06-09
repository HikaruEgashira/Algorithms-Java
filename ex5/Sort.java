import java.util.Random;
import java.util.Scanner; // テキスト入力を簡単に扱うためのライブラリ

public class Sort {
    static long compare_count = 0; // 比較回数を計測するためのクラス変数

    // 比較回数をリセット
    static void reset() {
        compare_count = 0;
    }

    // i と j を比較
    // i > j => 1
    static int compare(int i, int j) {
        compare_count++;
        if (i < j)
            return -1;
        else if (i == j)
            return 0;
        else
            return 1;
    }

    // 配列の要素の交換
    static void swap(int[] a, int i, int j) {
        // System.out.println(i + "と" + j + "を交換");
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // 配列の要素を表示
    static void display(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
            System.out.println();
        }
    }

    // 選択ソート
    static void selection_sort(int[] a, int n) {
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (compare(a[j], a[min]) == -1)
                    min = j;
            }
            swap(a, i, min);
        }
    }

    // 挿入ソート
    static void insertion_sort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int target = a[i];
            int j = i - 1;
            while (j >= 0 && target < a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = target;
        }
    }

    // ヒープソート
    static void sift_down(int[] a, int i, int n) {
        int snode, svalue;
        while (2 * i + 1 <= n - 1) { // iに(左の)子がある
            if (2 * i + 1 == n - 1) { // 左のみ
                snode = 2 * i + 1;
                svalue = a[2 * i + 1];
            } else { // 両方の子
                if (compare(a[2 * i + 1], a[2 * i + 2]) == 1) {
                    snode = 2 * i + 1;
                    svalue = a[2 * i + 1];
                } else {
                    snode = 2 * i + 2;
                    svalue = a[2 * i + 2];
                }
            }
            if (compare(a[i], svalue) == -1) { // 親が子の値より大きい
                // a[snode] <-> a[i]
                swap(a, i, snode);
                i = snode;
            } else
                return;
        }
        return;
    }

    // 要素数nの配列aをもとにヒープを構築
    static void build_heap(int[] a, int n) {
        // i = 右下の親, その左の親, その左の親, ...
        for (int b = n / 2 - 1; b >= 0; b--) {
            sift_down(a, b, n);
        }
    }

    // 要素数nの配列aをヒープソートにより整列
    static void heap_sort(int[] a, int n) {
        build_heap(a, n);
        for (int m = n - 1; m > 0; m--) {
            // a[0] <-> a[m]
            swap(a, 0, m);
            sift_down(a, 0, m);
        }
    }

    // クイックソート
    // partition におけるピボットは, 最後の要素(a[right])とする.
    static int partition(int[] a, int pivot, int left, int right) {
        swap(a, right, left);
        int l = left;
        int r = right - 1;
        while (true) {
            while (compare(a[l], a[right]) == -1)
                l++;
            while (l <= r && compare(a[r], a[right]) >= 0)
                r--;
            if (l < r) {
                swap(a, l, r);
            } else {
                break;
            }
        }
        swap(a, l, right);
        return l;
    }

    static void quick_sort(int[] a, int left, int right) {
        if (left < right) {
            int pivot = (left + right) / 2; // leftとrightの間の値
            int p = partition(a, pivot, left, right);
            quick_sort(a, left, p - 1);
            quick_sort(a, p + 1, right);
        }
    }

    static void qsort(int[] a, int n) {
        quick_sort(a, 0, n - 1);
    }

    // 待ち行列の配列を表示
    static void display_QueueArray(QueueArray[] b, int n) {
        for (int i = 0; i < n; i++) {
            b[i].display();
        }
    }

    // 基数ソート
    static void radix_sort(int[] a, int n, int k) {
        QueueArray[] b = new QueueArray[10]; // 課題2で実装した待ち行列QueueArrayを用いる
        int digit = 1;

        for (int i = 0; i < 10; i++)
            b[i] = new QueueArray(n + 1); // 大きさnの配列で実装したQueueArrayは, n-1個しか要素を格納できない
        // h: 桁, i: 並び替える数, j: 基数0~9
        for (int h = 0; h <= k - 1; h++) {


            /** 桁ごとの処理 */
            for (int i = 0; i < n; i++) {
                /** 探索元の配列の処理 */
                int num = a[i] / digit % 10; // num: h桁目の値
                b[num].enqueue(a[i]);
            }
            digit *= 10;

            
            int index = 0; // index: 並び替える数の順番
            for (int j = 0; j < 10; j++) {
                /** 基数の処理 */
                while (compare(b[j].rear, b[j].front) != 0) {
                    a[index] = b[j].dequeue();
                    index++;
                }
            }
        }

    }

    public static void main(String[] args) {
        if (args.length > 0) {
            Scanner scan = new Scanner(System.in);
            int n = Integer.parseInt(args[0]);
            int[] a = new int[n];
            System.out.println(n + "個の整数を入力してください．");
            for (int i = 0; i < n; i++) {
                a[i] = scan.nextInt(); // 整数を入力する
            }
            scan.close();
            radix_sort(a, n, 2);
            System.out.println("整列結果");
            display(a, n);
        } else {
            for (int j = 1; j <= 10; j++) { // 大きさn (n=1000, 2000, ..., 10000) の配列に対してテスト
                int n = 1000 * j;
                int[] a = new int[n];
                Random rnd = new Random();
                for (int i = 0; i < n; i++) {
                    a[i] = rnd.nextInt(n * 10); // 0〜配列の大きさ*10 -1 のランダムな整数を要素とする
                }
                System.out.println("=TIME=");
                long t1, t2;
                t1 = System.nanoTime();
                radix_sort(a, n, 4);
                t2 = System.nanoTime();
                System.out.println("radix_sort: " + (t2 - t1) / 1000);
                t1 = System.nanoTime();
                qsort(a, n);
                t2 = System.nanoTime();
                System.out.println("quick_sort: " + (t2 - t1) / 1000);
                t1 = System.nanoTime();
                heap_sort(a, n);
                t2 = System.nanoTime();
                System.out.println("heap_sort : " + (t2 - t1) / 1000);
                System.out.print(n);
                System.out.println(": count: " + compare_count); // 比較回数を表示
                reset(); // 比較回数をリセット
            }
        }
    }
}