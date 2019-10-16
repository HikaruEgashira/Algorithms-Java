
// Knapsack.java
import java.util.Random;

public class Knapsack {
    /**
     * 教科書：表 6.1の例 v[1]?v[4]：価格 w[1]?w[4]：重さ
     */
    static int[] v = { 0, 250, 380, 420, 520 };
    static int[] w = { 0, 1, 2, 4, 3 };

    /**
     * ナップサック問題の最適解を探索（動的計画法を用いない）
     *
     * @param v 価格の配列
     * @param w 重さの配列
     * @param k 対象とする荷物の数
     * @param i ナップサックの容量
     */
    public static int knapsack(int[] v, int[] w, int k, int i) {
        if (k == 0) // すべての荷物の数に対して探索を行った
            return 0;
        else {
            if (i < w[k]) // キャッシュと変更がない場合
                return knapsack(v, w, k - 1, i);
            else { // 更新
                int a = knapsack(v, w, k - 1, i);
                int b = knapsack(v, w, k - 1, i - w[k]) + v[k];
                return Math.max(a, b);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 2) { // 引数が１である
            int k = Integer.parseInt(args[0]);
            int i = Integer.parseInt(args[1]);
            System.out.println(knapsack(v, w, k, i));
        } else if (args.length == 1) { // 引数が２である
            int n = Integer.parseInt(args[0]);
            int[] v = new int[n + 1];
            int[] w = new int[n + 1];
            Random rnd = new Random();
            for (int i = 1; i <= n; i++) {
                v[i] = rnd.nextInt(100);
                w[i] = rnd.nextInt(10) + 1;
            }
            long t1 = System.nanoTime();
            int i = knapsack(v, w, n, n * 5);
            long t2 = System.nanoTime();
            System.out.println(i);
            System.out.println();
            System.out.println(((t2 - t1) / 1000000) + "ミリ秒");
        } else { // 引数がゼロである
            for (int n = 25; n <= 30; n++) {
                int[] v = new int[n + 1];
                int[] w = new int[n + 1];
                Random rnd = new Random();
                for (int i = 1; i <= n; i++) {
                    v[i] = rnd.nextInt(100);
                    w[i] = rnd.nextInt(10) + 1;
                }
                long t1 = System.nanoTime();
                int i = knapsack(v, w, n, n * 5);
                long t2 = System.nanoTime();
                System.out.print(n + " ");
                System.out.println(((t2 - t1) / 1000));
            }
        }
    }

}