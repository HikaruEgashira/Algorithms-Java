
// Knapsack.java
import java.util.Random;

public class DPKnapsack {
    /**
     * ���ȏ��F�\ 6.1�̗� v[1]?v[4]�F���i w[1]?w[4]�F�d��
     */
    static int[] v = { 0, 250, 380, 420, 520, 720, 980, 990, 820, 480 };
    static int[] w = { 0, 1, 2, 4, 3, 7, 9, 10, 8, 5 };

    /**
     * �i�b�v�T�b�N���̍œK����T���i���I�v��@��p���Ȃ��j
     *
     * @param v ���i�̔z��
     * @param w �d���̔z��
     * @param k �ΏۂƂ���ו��̐�
     * @param i �i�b�v�T�b�N�̗e��
     */
    public static int knapsack(int v[], int w[], int k, int i) {
        int G[][] = new int[k + 1][i + 1];
        // initialize G
        for (int l = 0; l <= k; l++) {
            for (int j = 0; j <= i; j++) {
                G[l][j] = 0;
            }
        }
        for (int l = 1; l <= k; l++)
        for (int j = 0; j <= i; j++) {
            // G���L���b�V���ƕύX���Ȃ��ꍇ
            if (j < w[l])
                G[l][j] = G[l - 1][j];

            // G�̍X�V
            else {
                int _v = G[l - 1][j - w[l]] + v[l];
                if (G[l - 1][j] < _v)
                    G[l][j] = _v;
                else
                    G[l][j] = G[l - 1][j];
            }
        }
        return G[k][i];
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int k = Integer.parseInt(args[0]);
            int i = Integer.parseInt(args[1]);
            System.out.println(knapsack(v, w, k, i));
        } else if (args.length == 1) {
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
            System.out.println(((t2 - t1) / 1000) + "�~���~���b");
        } else {
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