import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

class StringMatch {
	static boolean isVerbose = false; // 比較回数表示スイッチ
	static int Ncmp = 0; // 比較回数のカウンタ
	static char[] text; // テキスト
	static char[] pat; // 検索パターン

	/**
	 * ファイルを読み込み，内容を文字列として返す．
	 * 
	 * @param path 読み込むファイルのパス
	 * @return ファイルの内容の文字列
	 */
	public static String readFile(String path) {
		StringBuffer sb = new StringBuffer();
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str;
			while ((str = br.readLine()) != null)
				sb.append(str);
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return new String(sb);
	}

	/**
	 * 文字比較関数．比較回数をカウントする．
	 * 
	 * @param a 比較文字
	 * @param b 比較文字
	 * @return a と b が等しければ true, 等しくなければ false.
	 */
	public static boolean cmp(char a, char b) {
		if (isVerbose)
			System.out.println("cmp(" + a + ", " + b + ")");
		Ncmp++;
		return (a == b);
	}

	/**
	 * next配列．
	 * 
	 * @param pat pattern文字配列
	 * @return patのnext配列
	 */
	public static int[] compnext(char[] pat) {
		int len = pat.length;
		int next[] = new int[len];

		// 初期化
		for (int i = 0; i < len; i++)
			next[i] = 0;
		if (len <= 1)
			return next;

		for (int i = 1; i < len; i++) {
			int ofs = 0;
			// 一つずつすすむ
			while (cmp(pat[i + ofs], pat[ofs]) && i + ofs < len - 1)
				ofs++;
			// nextの決定
			next[i + ofs] = Math.max(next[i + ofs], ofs + 1);
		}

		return next;
	}

	/**
	 * 単純照合法による文字列照合．
	 * 
	 * @param text テキスト
	 * @param pat  検索パターン
	 * @return 照合に成功した位置．失敗した場合は -1．
	 */
	public static int naive(char[] text, char[] pat) {
		// 関数を完成させなさい．
		int i = 0;
		int j = 0;
		int n = text.length;
		int m = pat.length;

		while (j < n) {
			if (!cmp(pat[i], text[j])) {
				i = 0;
				j -= i - 1;
			} else {
				if (i != m - 1) {
					i++;
					j++;
				} else
					return j - m + 1; // 照合成功
			}
		}
		return -1; // 照合失敗
	}

	public static int kmp(char[] text, char[] pat) {
		int i = 0;
		int j = 0;
		int n = text.length;
		int m = pat.length;

		int[] next = compnext(pat);
		for (int k = 0; k < next.length; k++) {
			System.out.print(next[k] + " ");
		}
		while (j < n) {
			while (i >= 0 && !cmp(pat[i], text[j]))
				i = next[i] - 1; // 失敗した場合のシフト量
			if (i != m - 1) {
				i++;
				j++;
			} else
				return (j - m + 1); // 照合成功
		}
		return -1; // 照合失敗
	}

	public static void main(String[] args) {
		// 引数処理
		if (args.length < 2) {
			System.err.println("Usage: java StringMatch [-v] <text file> <pattern file>");
			System.exit(1);
		}
		int i = 0;
		if (args[i].equals("-v")) {
			isVerbose = true;
			i++;
		}

		// ファイルを読み込んで，char型の配列に格納．
		text = readFile(args[i++]).toCharArray();
		pat = readFile(args[i]).toCharArray();

		if (isVerbose) {
			System.out.println("text    size: " + text.length);
			System.out.println(text);
			System.out.println("pattern size: " + pat.length);
			System.out.println(pat);
			// int[] next = compnext(pat);
			// for (int j = 0; j < next.length; j++)
			// System.out.println(next[j]);
		}

		System.out.println("naive: Pattern found at " + naive(text, pat) + ".");
		if (isVerbose) {
			System.out.println("# of comparisons: " + Ncmp + ".");
			Ncmp = 0;
		}
		// naive: Pattern found at -1.
		// # of comparisons: 15.

		int offset = kmp(text, pat);
		System.out.println("kmp  : Pattern found at " + offset + ".");
		if (isVerbose)
			System.out.println("# of comparisons: " + Ncmp + ".");
		// kmp : Pattern found at 10.
		// # of comparisons: 24.

		System.out.println(text);
		for (int j = 0; j < offset; j++)
			System.out.print(" ");
		System.out.println(pat);

	}
}
