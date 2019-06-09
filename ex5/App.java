import java.util.regex.Pattern;

// src/main/java/track
public class App {
  public static int Levenshtein(String a, String b) {
    // aが大きい文字列になるように交換
    if (a.length() < b.length()) {
      String tmp = a;
      a = b;
      b = tmp;
    }
    int count = a.length() - b.length();
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) != b.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  // 仕様に合う文字列かどうかの判定
  public static boolean isApplicable(String str) {
    return Pattern.matches("^[0-9a-zA-Z]*$", str);
  }

  public static void main(String[] args) {
    switch (args.length) {
    case 0:
      System.out.println(0);
      break;
    case 1:
      if (!isApplicable(args[0])) {
        System.exit(1);
      }
      System.out.println(args[0].length());
      break;
    case 2:
      for (int i = 0, l = args.length; i < l; i++) {
        if (!isApplicable(args[i])) {
          System.exit(1);
        }
      }
      int res = Levenshtein(args[0], args[1]);
      System.out.println(res);
      break;
    default:
      System.exit(1);
      break;
    }
  }
}
