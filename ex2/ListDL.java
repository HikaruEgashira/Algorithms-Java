import java.io.*;

// ListDLInt.java
public class ListDL {
    private ListDL next, prev;
    Object val;

    ListDL() {
        // �z����(
        initLinks();
    }

    ListDL(Object val) {
        initLinks();
        this.val = val;
    }

    // prev <=> cell <=> next
    private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
        // prev => cell
        if (prev != null) {
            prev.next = cell;
        }
        // next => cell
        if (next != null) {
            next.prev = cell;
        }
        // cell => next
        cell.next = next;
        // cell => prev
        cell.prev = prev;
    }

    // prev �� next �̌��т�
    private static void __Delete(ListDL prev, ListDL next) {
        prev.next = next;
        next.prev = prev;
    }

    // ���т����Ȃ���
    private void initLinks() {
        this.next = this;
        this.prev = this;
    }

    // this => cell => this.next
    void insertNext(ListDL cell) {
        __Insert(cell, this, this.next);
        this.next = cell;
    }

    // this.prev => cell => this
    void insertPrev(ListDL cell) {
        __Insert(cell, this.prev, this);
        this.prev = cell;
    }

    // this.prev => this.next (no this)
    void delete() {
        if (this != null) {
            __Delete(this.prev, this.next);
            this.prev = null;
            this.next = null;
        }
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
    ListDL readFromArray(int[] Array) {
        for (Object val : Array) {
            this.insertPrev(new ListDL(val));
        }
        return this;
    }

    int length() {
        int Count = 0;
        for (ListDL i = this.next; i != this; i = i.next) {
            Count++;
        }
        return Count;
    }

    // ���X�g�̗v�f�������o�����z���Ԃ����\�b�h writeToArray()
    int[] writeToArray() {
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
            File file = new File(readfile);
            BufferedReader br = new BufferedReader(new FileReader(file));
            try {
                String str;
                while ((str = br.readLine()) != null) {
                    insertPrev(new ListDL(str));
                }
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // �t�@�C���Ƀ��X�g�̗v�f�������o�����\�b�h writeToFile() ��ǉ����Ȃ����B
    void writeToFile(String fileName) {
        try {
            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            try {
                for (ListDL i = this.next; i != this; i = i.next) {
                    pw.println(i.val);
                }
            } finally {
                pw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}