public class ShortestPathFloyd {

    static final int M = Integer.MAX_VALUE; // 到達不能（無限大）
    static final int N = 6; // グラフの頂点数

    static int w[][] = { // グラフの隣接行列
            { 0, M, M, 8, 15, M }, { 10, 0, 24, M, 8, M }, { M, M, 0, M, M, 6 }, { M, M, M, 0, 5, M },
            { M, M, 12, M, 0, 7 }, { M, M, 3, M, M, 0 } };

    static int[][] p = new int[N][N]; // 頂点iからjまでの最短路でたどる最後の辺<k, j>のk
    static int[][] d = new int[N][N]; // 頂点iからjまでの最短距離
    static Stack stack = new Stack(100);

    /**
     * フロイド法で，頂点 p から各頂点への最短路の重みを計算する．
     * 
     * @param なし
     * @return なし
     */
    static void Floyd() {
        // Initialized
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                d[i][j] = w[i][j];
                p[i][j] = i;
            }
        for (int k = 0; k < N; k++)
            for (int i = 0; i < N; i++)
                for (int j = 0; j < N; j++) {
                    int can = M;
                    if (d[i][k] != M && d[k][j] != M)
                        can = d[i][k] + d[k][j];
                    if (can < d[i][j]) {
                        d[i][j] = can;
                        p[i][j] = p[k][j];
                    }
                }
    }

    /**
     * iからの最短経路を出力する．
     * 
     * @param m 開始点のインデックス
     * @param n 開始点の値
     * @return なし
     */
    static void shortestPath(int m, int n) {
        int x;
        if (d[m][n] == M) {
            System.out.println("Cannnot through");
            return;
        }
        x = n;
        stack.push(x);
        while (x != m) {
            x = p[m][x];
            stack.push(x);
        }
        System.out.print(stack.pop());
        while (!stack.isEmpty())
            System.out.print("->" + stack.pop());
        System.out.println();
    }

    /**
     * メイン関数．
     * 
     * @param args コマンドライン引数( 0 - (N-1) )
     * @return なし
     */
    public static void main(String[] args) {
        if (args.length != 1) { // 引数の処理
            System.err.println("Usage: java ShortestPathDijkstra <出発点>");
            System.exit(1);
        }
        if (Integer.parseInt(args[0]) >= N || Integer.parseInt(args[0]) < 0) {
            System.err.println("Argument must be \"0 ~ N-1\"");
            System.exit(1);
        }
        int p = Integer.parseInt(args[0]);

        Floyd();
        System.out.print("Result: ");
        for (int j : d[p])
            System.out.print(j + " ");
        System.out.println();
        for (int j = 0; j < N; j++) {
            System.out.print(p + " -> " + j + ": ");
            shortestPath(p, j);
        }
    }
}
