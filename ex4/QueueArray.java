public class QueueArray {
    int length, front, rear;
    Node[] queue;

    // �w�肳�ꂽ�����̔z��𐶐�����R���X�g���N�^
    QueueArray(int len) {
        queue = new Node[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // queue �̌��Ɉ�����������
    void enqueue(Node val) {
        if (rear > length-1) {
            System.err.println("Queue Overflow !!");
            System.exit(1);
        }
        queue[rear] = val;
        rear++;
    }

    // queue �̐擪��Ԃ��A�ԍ�������炷
    Node dequeue() {
        if (front == rear) {
            System.err.println("Queue Underflow !!");
            System.exit(1);
        }
        Node x = queue[front];
        front++;
        return x;
    }

    // queue �̐擪���疖���܂ł��o��
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