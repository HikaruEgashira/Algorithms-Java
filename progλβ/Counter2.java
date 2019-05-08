/*
 * �ۑ�̖ړI�F
 * * �p��
 * * �R���X�g���N�^
 */
class AnonymousCounter {
    static int nCounter = 0;

    int value;

    AnonymousCounter() {
        this(0);
    }

    AnonymousCounter(int value) {
        this.value = value;     // 3�s�ڂŌĂ΂��`value`
        nCounter++;
    }

    AnonymousCounter add(AnonymousCounter x) {
        return new AnonymousCounter(this.value + x.value);
    }

    @Override
    public String toString() {
        return "<value=" + value + ">";
    }
}

class NamedCounter extends AnonymousCounter {
    // �ۑ�F���L�̗��𖄂߂ăv���O����������������B
    String name;

    NamedCounter(String name, int value) {
        super(value);
        this.name = name;       // 1, 2�s�ڂŌĂ΂��`name`
        this.value = value;     // 1, 2�s�ڂŌĂ΂��`value`
    }
    // ...


    @Override
    public String toString() {
        return "<name=" + name + ", value=" + value + ">";
    }

    public static void main(String[] args) {
        NamedCounter a = new NamedCounter("A", 1);
        assert a.name == "A";
        assert a.value == 1;
        assert nCounter == 1;

        NamedCounter b = new NamedCounter("B", 2);
        assert b.name == "B";
        assert b.value == 2;
        assert nCounter == 2;

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.add(b));
    }
}
