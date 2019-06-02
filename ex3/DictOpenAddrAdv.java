
// DictOpenAddr.java
import java.util.Random;

public class DictOpenAddrAdv {
    DictData[] H; // �����̔z��
    int B; // �z��̑傫��

    // �R���X�g���N�^
    DictOpenAddrAdv(int len) {
        H = new DictData[len];
        B = len;
        for (int i = 0; i < len; i++) {
            H[i] = new DictData();
        }
    }

    // �n�b�V���֐�
    int h(int d, int count) {
        return (d + count * 757) % B;
    }

    // �f�[�^ d �������ɑ}��
    void insert_hash(int d) {
        if (search_hash(d) != -1) return;
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.EMPTY || H[hash].state == State.DELETED) {
                H[hash].name = d;
                H[hash].state = State.OCCUPIED;
                return;
            }
        }
        System.out.println("Overflow!");
    }

    // �f�[�^ d ���������Ɋ܂܂�邩��T���i�߂�l��boolean�^�ł��j
    // ���������ꍇ�͂��̈ʒu���A������Ȃ����-1��Ԃ�
    int search_hash(int d) {
        int hash;
        for (int count = 0; count != B; count++) {
            hash = h(d, count);
            if (H[hash].state == State.OCCUPIED && H[hash].name == d) {
                // found!
                return hash;
            } else if (H[hash].state == State.EMPTY) {
                return -1;
            }
        }
        return -1;
    }

    // �f�[�^ d ����������폜
    void delete_hash(int d) {
        int hash = search_hash(d);
        if (hash == -1) {
        } else H[hash].state = State.DELETED;
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
        DictOpenAddrAdv dict = new DictOpenAddrAdv(10000);
        int length = 100; // ���s��
        Random rnd = new Random();

        long[] time_insert = new long[length];
        long[] time_search = new long[length];
        long[] time_delete = new long[length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 1000; j++) {

                // insert
                long t_insert1 = System.nanoTime();
                dict.search_hash(rnd.nextInt(1000));
                long t_insert2 = System.nanoTime();

                // search
                long t_search1 = System.nanoTime();
                dict.search_hash(rnd.nextInt(1000));
                long t_search2 = System.nanoTime();

                // delete
                long t_delete1 = System.nanoTime();
                dict.delete_hash(rnd.nextInt(1000));
                long t_delete2 = System.nanoTime();

                time_insert[i] += t_insert2 - t_insert1;
                time_search[i] += t_search2 - t_search1;
                time_delete[i] += t_delete2 - t_delete1;
            }
        }
        long sum_insert = 0;
        long sum_search = 0;
        long sum_delete = 0;
        for (int i = 0; i < length; i++) {
            sum_insert += time_insert[i];
            sum_search += time_search[i];
            sum_delete += time_delete[i];
        }
        System.out.println("insert:" + sum_insert / length); // insert:1134990
        System.out.println("search:" + sum_search / length); // search:1115530
        System.out.println("delete:" + sum_delete / length); // delete:1134428
    }
}
