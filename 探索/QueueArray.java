public class QueueArray {
    int length, front, rear;
    Node[] queue;

    // 指定された長さの配列を生成するコンストラクタ
    QueueArray(int len) {
        queue = new Node[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // queue の後ろに引数を代入する
    void enqueue(Node val) {
        if (rear > length-1) {
            System.err.println("Queue Overflow !!");
            System.exit(1);
        }
        queue[rear] = val;
        rear++;
    }

    // queue の先頭を返し、番号を一つずらす
    Node dequeue() {
        if (front == rear) {
            System.err.println("Queue Underflow !!");
            System.exit(1);
        }
        Node x = queue[front];
        front++;
        return x;
    }

    // queue の先頭から末尾までを出力
    void display() {
        for (int i = front; i < rear; i++) {
            System.out.print(queue[i].val);
        }
        System.out.println();
    }

    boolean is_empty() {
        return (rear == front) ? true : false;
    }

    boolean is_null() {
        for (int i = front; i < rear; i++) {
            if (queue[i].val != " ") return false;
        }
        return true;
    }
}