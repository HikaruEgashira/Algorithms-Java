
// Knapsack.java
import java.util.Random;

public class Knapsack {
    /**
     * ���ȏ��F�\ 6.1�̗� v[1]?v[4]�F���i w[1]?w[4]�F�d��
     */
    static int[] v = { 0, 250, 380, 420, 520 };
    static int[] w = { 0, 1, 2, 4, 3 };

    /**
     * �i�b�v�T�b�N���̍œK����T���i���I�v��@��p���Ȃ��j
     *
     * @param v ���i�̔z��
     * @param w �d���̔z��
     * @param k �ΏۂƂ���ו��̐�
     * @param i �i�b�v�T�b�N�̗e��
     */
    public static int knapsack(int[] v, int[] w, int k, int i) {
        if (k == 0) // ���ׂẲו��̐��ɑ΂��ĒT�����s����
            return 0;
        else {
            if (i < w[k]) // �L���b�V���ƕύX���Ȃ��ꍇ
                return knapsack(v, w, k - 1, i);
            else { // �X�V
                int a = knapsack(v, w, k - 1, i);
                int b = knapsack(v, w, k - 1, i - w[k]) + v[k];
                return Math.max(a, b);
            }
        }
    }

    public static void main(String[] args) {
        if (args.length == 2) { // �������P�ł���
            int k = Integer.parseInt(args[0]);
            int i = Integer.parseInt(args[1]);
            System.out.println(knapsack(v, w, k, i));
        } else if (args.length == 1) { // �������Q�ł���
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
            System.out.println(((t2 - t1) / 1000000) + "�~���b");
        } else { // �������[���ł���
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