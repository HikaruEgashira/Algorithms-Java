// ListDLmain.java
// ���X�g�̎������e�X�g���邽�߂̃N���X
public class ListDLmain {
    public static void main(String[] args) {
          ListDL head = new ListDL();          // �_�~�[�Z���̐���
          ListDL elem;

          head.insertNext(new ListDL(2));      // �Z���̐擪�ւ̒ǉ�
          head.insertNext(new ListDL(1));
          head.insertPrev(new ListDL(5));      // �Z���̖����ւ̒ǉ�
          head.display();                      // ���X�g�̕\��

          elem = head.search(2);               // �Z����T��
          elem.insertNext(new ListDL(3));      // �T�����Z���̒���ɃZ����ǉ�
          head.display();

          elem = head.search(5);               // �Z����T��
          elem.delete();                       // �T�����Z�����폜
          head.display();
    }
}