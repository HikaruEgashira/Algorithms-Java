/*
 * 課題の目的
 * * クラスフィールド，インスタンスフィールド
 * * クラスメソッド，インスタンスメソッド
 */
class Counter {

    // 課題：下記の欄を埋めてプログラムを完成させよ。
    String name;
    int value;
    static int nCounter = 0;

    Counter(String name, int value) {
        this.name = name;
        this.value = value;
        nCounter++;
    }

    static int getCounter() {
        return nCounter;
    }

    Counter add(Counter x) {
        return new Counter(this.name + x.name, this.value + x.value);
    }

    @Override
    public String toString() {
        return "<name=" + name + ", value=" + value + ">";
    }

    public static void main(String[] args) {
        Counter a = new Counter("A", 10);
        assert a.name == "A";
        assert a.value == 10;
        assert nCounter == 1;
        assert Counter.getCounter() == 1;

        Counter b = new Counter("B", 20);
        assert b.name == "B";
        assert b.value == 20;
        assert Counter.getCounter() == 2;

        Counter c = a.add(b);
        assert c.name.equals("AB");
        assert c.value == 30;
        assert Counter.getCounter() == 3;

        System.out.println(a);
        System.out.println(b);
        System.out.println(a.add(b));
    }

}
