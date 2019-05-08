// ListDLmain.java
// リストの実装をテストするためのクラス
public class ListDLmain {
    public static void main(String[] args) {
        ListDLInt head = new ListDLInt();    // セルの生成
        ListDL elem;

        head.insertNext(new ListDL(1));      // セルの先頭への追加
        head.insertNext(new ListDL(5));
        head.insertPrev(new ListDL(2));      // セルの末尾への追加
        head.display();                      // 5 -> 1 -> 2

        elem = head.search(5);               // セルを探す
        elem.insertNext(new ListDL(3));      // 探したセルの直後にセルを追加
        head.display();                      // 5 -> 3 -> 1 -> 2

        elem = head.search(5);               // セルを探す
        elem.delete();                       // 探したセルを削除
        head.display();                      // 3 -> 1-> 2

        // readFromArray
        ListDL readedList = new ListDLInt();
        int[] array = {1, 2, 3, 4, 5};
        readedList = readedList.readFromArray(array);
        readedList.display();

        // writeToArray
        for (int i : readedList.writeToArray()) {
            System.out.print(i);
        }
        System.out.println();

        // readFromFile
        ListDLInt fileList = new ListDLInt();
        fileList.readFromFile("ex2/array.txt");
        fileList.display();

        // writeToFile
        head.writeToFile("ex2/out.txt");
    }
}