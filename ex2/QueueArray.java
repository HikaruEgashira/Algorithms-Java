import java.lang.reflect.Array;

public class QueueArray {
    int length, front, rear;
    int[] queue;

    // 指定された長さの配列を生成するコンストラクタ
    QueueArray(int len) {
        queue = new int[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // データのエンキュー
    void enqueue(int val) {
        if (rear > length-1) {
            System.err.println("Queue Overflow !!");
            System.exit(1);
        }
        queue[rear] = val;
        rear += 1;
    }

    // データのデキュー
    int dequeue() {
        if (front == rear) {
            System.err.println("Queue Underflow !!");
            System.exit(1);
        }
        int x = queue[front];
        front += 1;
        return x;
    }

    // キューの要素の表示
    void display() {
        for (int i = front; i < rear; i++) {
            System.out.print(queue[i]);
        }
        System.out.println();
    }

    // main メソッド
    public static void main(String[] args) {
          QueueArray queue = new QueueArray(10);

          queue.enqueue(1);
          queue.enqueue(2);
          queue.display();

          System.out.println(queue.dequeue());
          System.out.println(queue.dequeue());
    }
}