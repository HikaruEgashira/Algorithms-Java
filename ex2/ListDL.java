import java.io.*;

// ListDLInt.java
public class ListDL {
    private ListDL next, prev;
    Object val;

    ListDL() {
        // �z����
        initLinks();
    }

    ListDL(Object val) {
        initLinks();
        this.val = val;
    }

    // prev <=> cell <=> next
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        prev.next = cell;       // prev => cell
        next.prev = cell;       // next => cell
        cell.next = next;       // cell => next
        cell.prev = prev;       // cell => prev
    }

    // prev �� next �̌��т�
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // ������
    private void initLinks() {
        this.next = this;
        this.prev = this;
    }

    // this => cell => this.next
    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
    }

    // this.prev => cell => this
    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
    }

    // this.prev => this.next (no this)
    void delete() {
        __Delete(this.prev, this.next);
        initLinks();
    }

    // ���X�g�̃R�s�[�𑖍����āA�f�[�^�l�������Ɠ������̂�Ԃ�
    ListDL search(Object i) {
        for (ListDL j = this.next; j != this; j = j.next) {
            if (i.equals(j.val)) {
                return j;
            }
        }
        return null;
    }

    void display() {
        for (ListDL j = this.next; j != this; j = j.next) {
            if (j.val != null) {
                System.out.print(j.val + " -> ");
            }
        }
        System.out.println();
    }

    // �z�񂩂烊�X�g�̗v�f��ǂݍ��ރ��\�b�h readFromArray()
    ListDL readFromArray(Object[] Array) {
        for (Object val : Array) {
            this.insertPrev(new ListDL(val));
        }
        return this;
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