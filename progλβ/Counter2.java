/*
 * 課題の目的：
 * * 継承
 * * コンストラクタ
 */
class AnonymousCounter {
    static int nCounter = 0;

    int value;

    AnonymousCounter() {
        this(0);
    }

    AnonymousCounter(int value) {
        this.value = value;     // 3行目で呼ばれる`value`
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
    // 課題：下記の欄を埋めてプログラムを完成させよ。
    String name;

    NamedCounter(String name, int value) {
        super(value);
        this.name = name;       // 1, 2行目で呼ばれる`name`
        this.value = value;     // 1, 2行目で呼ばれる`value`
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
