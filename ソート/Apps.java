// package track;
import java.util.Scanner;
import java.util.ArrayList;

public class Apps {
  
  public static void main(String[] args) {
    // ���̃R�[�h�͕W�����͂ƕW���o�͂�p�����T���v���R�[�h�ł��B
    // ���̃R�[�h�͍D���Ȃ悤�ɕҏW�E�폜���Ă�����č\���܂���B
    // ---
    // This is a sample code to use stdin and stdout.
    // Edit and remove this code as you like.

    String[] lines = getStdin();
    for (int i = 0, l = lines.length; i < l; i++) {
      String output = String.format("line[%s]: %s", i, lines[i]);
      
      System.out.println(output);
    }
  }

  private static String[] getStdin() {
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> lines = new ArrayList<>();
    while (scanner.hasNext()) {
      lines.add(scanner.nextLine());
    }
    return lines.toArray(new String[lines.size()]);
  }
}
