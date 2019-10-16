// List.java
public class List {
    static List head;
    List next;
    int data;

    // �V���ȃ��X�g�𐶐� p => new_cell => p.next
    static void insert_cell(List p, int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = p.next;
        p.next = new_cell;
    }

    // head = new_cell(�f�[�^��d) => old_head
    static void insert_cell_top(int d) {
        List new_cell = new List();
        new_cell.data = d;
        new_cell.next = head;
        head = new_cell;
    }

    // p.next �� p.next.next �ŏ㏑������p.next���폜
    static void delete_cell(List p) {
        List q = p.next;
        p.next = q.next;
    }

    // head �� head.next �ŏ㏑��
    static void delete_cell_top() {
        List q = head;
        head = q.next;
    }

    // head �̃R�s�[��.next�ŏI���܂Ń��[�v���Ă��ꂼ��̃f�[�^��\��
    static void display() {
        List tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data);
            tmp = tmp.next;
        }
        // ���s
        System.out.println();
    }

    // List �N���X�̓���m�F
    public static void main(String[] args) {
        insert_cell_top(1);
        insert_cell(head, 3);
        insert_cell(head, 2);
        display();

        delete_cell(head);
        display();

        delete_cell_top();
        display();
    }
}

    // ���X�g�̗v�f����Ԃ�
    int length() {
        int Count = 0;
        for (ListDL i = this.next; i != this; i = i.next) {
            Count++;
        }
        return Count;
    }

    // ���X�g�̗v�f�������o�����z���Ԃ����\�b�h writeToArray()
    int[] writeToArray() {
        // ���X�g�Ɠ��������̔z����쐬���ă��X�g�̒l��������B
        int[] res = new int[this.length()];
        int Count = 0;
        for (ListDL i = this.next; i != this; i = i.next) {
            res[Count] = (int) i.val;
            Count++;
        }
        return res;
    }

    // �t�@�C�����烊�X�g�̗v�f��ǂݍ��ރ��\�b�h readFromFile()
    void readFromFile(String readfile) {
        try {
            // �����̃p�X�ɂ���t�@�C����ǂݍ���ň�s�����X�g�ɒǉ�
            File file = new File(readfile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    insertPrev(new ListDL(str));
                }
            } finally {
                // �t�@�C�������
                br.close();
            }
        } catch (FileNotFoundException e) {
            // �t�@�C�������݂��Ȃ�
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // �t�@�C���Ƀ��X�g�̗v�f�������o�����\�b�h writeToFile() ��ǉ����Ȃ����B
    void writeToFile(String fileName) {
        try {
            //���X�g�����[�v���邲�Ƃ� �����̃p�X�ɂ���t�@�C���Ƀf�[�^����������
            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            try {
                for (ListDL i = this.next; i != this; i = i.next) {
                    pw.println(i.val);
                }
            } finally {
                // �t�@�C�������
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}