public class ShortestPathDijkstra {

    static final int M = Integer.MAX_VALUE; // 到達不能（無限大）
    static final int N = 6; // グラフの頂点数

    static int w[][] = { // グラフの隣接行列
            { 0, M, M, 8, 15, M }, { 10, 0, 24, M, 8, M }, { M, M, 0, M, M, 6 }, { M, M, M, 0, 5, M },
            { M, M, 12, M, 0, 7 }, { M, M, 3, M, M, 0 } };

    static boolean S[] = new boolean[N]; // 頂点集合Sを表す配列
    static int Scount = 0; // 頂点集合Sの要素数
    static int d[] = new int[N]; // 重みの累積値を表す配列
    static int parent[] = new int[N]; // 直前の頂点を格納する配列

    /**
     * 頂点集合 S に頂点 u を追加する．
     * 
     * @param u 追加する頂点
     * @param S 頂点集合
     * @return なし
     */
    static void add(int u, boolean[] S) {
        // 関数を完成させなさい．
        S[u] = true;
        Scount++;
    }

    /**
     * 頂点集合のうち S に追加されていない頂点があるかどうか確認する．
     * 
     * @return S に追加されていない頂点が存在すれば true，それ以外は false
     */
    static boolean remain() {
        // 関数を完成させなさい．
        return Scount != S.length;
    }

    /**
     * 3 * p からの最短距離が確定していない頂点のうち，d[] が最小の頂点， 適切な頂点がない場合は -1 を返す．
     * 
     * @param なし
     * @return 未確定の d[] が最小の頂点，適切な頂点がない場合 -1
     */
    static int select_min() {
        // 関数を完成させなさい．
        // int配列dの最小値のインデックスを返す
        int min = 0;
        boolean flag = false;

        // 最小値の選択
        for (int i = 0; i < N; i++) {
            if (S[i])
                // すでに走査したのでスルー
                continue;

            if (!flag) {
                min = i;
                flag = true;
            } else if (d[min] > d[i]) {
                min = i;
            }
        }

        // 見つかった場合
        if (flag)
            return min;
        return -1;
    }

    /**
     * 頂点 u から頂点 x に接続する辺が存在すれば true, それ以外は false を返す.
     * 
     * @param u 頂点
     * @param x 頂点
     * @return 辺が存在すれば true, それ以外は false
     */
    static boolean member(int u, int x) {
        // 関数を完成させなさい．
        // 指定の配列の値がMでなければ辺が存在する
        return w[u][x] != M;
    }

    /**
     * ダイクストラ法で，頂点 p から各頂点への最短路の重みを計算する．
     * 
     * @param p 開始点
     * @return なし
     */
    static void dijkstra(int p) {
        add(p, S);
        // dの初期化
        for (int i = 0; i < N; i++) {
            d[i] = w[p][i];
            // (A)
            parent[i] = p;
        }

        while (remain()) {
            int u = select_min();
            // 見つからなかった場合
            if (u == -1)
                return;
            add(u, S);

            for (int x = 0; x < N; x++) {
                // dの更新
                if (!member(u, x))
                    continue;
                int k = d[u] + w[u][x];
                if (k < d[x] && M != d[u]) {
                    d[x] = k;
                    // (B)
                    parent[x] = u;
                }
            }
        }
    }

    /**
     * 配列の中身を標準出力に表示．結果出力およびデバッグ用．
     * 
     * @param label ラベル（変数名など）
     * @param ary   配列
     * @return なし
     */
    static void display(String label, int[] ary) {
        System.out.print(label + ":");
        for (int i = 0; i < ary.length; i++) {
            if (ary[i] == M)
                System.out.print(" M");
            else
                System.out.print(" " + ary[i]);
        }
        System.out.println();
    }

    /**
     * グラフの経路を表示する関数．
     * 
     * @param p 始点の値
     * @param q 始点のインデックス
     * @return nothing
     */
    static void printPath(int p, int q) {
        if (p == q)
            return;
        printPath(p, parent[q]);
        System.out.print("->" + q);
    }

    /**
     * 最短路を求める関数．
     * 
     * @param p 始点
     * @return nothing
     */
    static void printShortestPath(int p) {
        for (int i = 0; i < N; i++) {
            System.out.print(p + " -> " + i + ": ");
            // 辺がない場合、通れないことを知らせる
            if (d[i] == M) {
                System.out.println("Cannnot through");
                continue;
            }
            System.out.print(p);
            printPath(p, i);
            System.out.println();
        }
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

        for (int i = 0; i < N; i++) // 初期化
            S[i] = false;

        dijkstra(p); // ダイクストラ法による最短路の計算
        display("Result", d); // 結果表示
        printShortestPath(p);
    }
}
