
// DictOpenAddr.java
public class DictOpenAddr {
    DictData[] H;       // �����̔z��
    int B;              // �z��̑傫��

    // �R���X�g���N�^
    DictOpenAddr(int len) {
        H = new DictData[len];
        B = len;
        for (int i = 0; i < B; i++) {
            H[i] = new DictData();
        }
    }
    
// �n�b�V���֐�
    int h(int d, int count) {
        return (d + count) % B;
    }
    // �f�[�^ d �������ɑ}��
    void insert_hash(int d) {
        if(search_hash(d)) return;
        int count = 0;
        int hash;
        while (count != B) {
            hash = h(d, count);
            if (H[hash].state == State.EMPTY || H[hash].state == State.DELETED) {
                H[hash].name = d;
                H[hash].state = State.OCCUPIED;
                return;
            }
            count++;
        }
        System.out.println("Overflow!");
    }
    // �f�[�^ d ���������Ɋ܂܂�邩��T���i�߂�l��boolean�^�ł��j
    boolean search_hash(int d) {
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED && H[hash].name == d) {
                return true;
            } else if (H[hash].state == State.EMPTY) {
                return false;
            }
        }
        return false;
    }
    // �f�[�^ d ����������폜
    void delete_hash(int d) {
        if (!search_hash(d)) {
            System.out.println(d + "is not found");
            return;
        }
        int count = 0;
        int hash;
        while (count != B)  {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED) {
                if (H[hash].name == d) {
                    H[hash].state = State.DELETED;
                    return;
                }
            }
            count++;
        }
    }
    // �z��v�f�̕\��
    void display() {
        for (int i = 0; i < B; i++) {
            switch (H[i].state) {
                case OCCUPIED:
                    System.out.print(H[i].name + " ");
                    break;
                case EMPTY:
                    System.out.print("e ");
                    break;
                case DELETED:
                    System.out.print("d ");
                    break;
                default:
                    break;
            }
        }
        System.out.println();
    }

    // main���\�b�h
       public static void main(String[] args) {
               DictOpenAddr dict = new DictOpenAddr(10);

               dict.insert_hash(1);
               dict.insert_hash(2);
               dict.insert_hash(3);
               dict.insert_hash(11);
               dict.insert_hash(12);
               dict.insert_hash(21);
               dict.display();

               System.out.println("Search 1 ...\t" + dict.search_hash(1));
               System.out.println("Search 2 ...\t" + dict.search_hash(2));
               System.out.println("Search 21 ..\t" + dict.search_hash(21));
               System.out.println("Search 5 ...\t" + dict.search_hash(5));

               dict.delete_hash(3);
               dict.display();

               dict.delete_hash(11);
               dict.display();
       }
}
