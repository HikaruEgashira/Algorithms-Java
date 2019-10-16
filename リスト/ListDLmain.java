// ListDLmain.java
// ���X�g�̎������e�X�g���邽�߂̃N���X
public class ListDLmain {
    public static void main(String[] args) {
        ListDLInt head = new ListDLInt();    // �Z���̐���
        ListDL elem;

        head.insertNext(new ListDL(1));      // �Z���̐擪�ւ̒ǉ�
        head.insertNext(new ListDL(5));
        head.insertPrev(new ListDL(2));      // �Z���̖����ւ̒ǉ�
        head.display();                      // 5 -> 1 -> 2

        elem = head.search(5);               // �Z����T��
        elem.insertNext(new ListDL(3));      // �T�����Z���̒���ɃZ����ǉ�
        head.display();                      // 5 -> 3 -> 1 -> 2

        elem = head.search(5);               // �Z����T��
        elem.delete();                       // �T�����Z�����폜
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