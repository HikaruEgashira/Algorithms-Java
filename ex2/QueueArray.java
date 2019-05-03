public class QueueArray {
    int length, front, rear;
    int[] queue;

    // �w�肳�ꂽ�����̔z��𐶐�����R���X�g���N�^
    QueueArray(int len) {
        queue = new int[len];
        length = len;
        front = 0;
        rear = 0;
    }

    // queue �̌��Ɉ�����������
    void enqueue(int val) {
        if (rear > length-1) {
            System.err.println("Queue Overflow !!");
            System.exit(1);
        }
        queue[rear] = val;
        rear += 1;
    }

    // queue �̐擪��Ԃ��A�ԍ�������炷
    int dequeue() {
        if (front == rear) {
            System.err.println("Queue Underflow !!");
            System.exit(1);
        }
        int x = queue[front];
        front += 1;
        return x;
    }

    // queue �̐擪���疖���܂ł��o��
    void display() {
        for (int i = front; i < rear; i++) {
            System.out.print(queue[i]);
        }
        System.out.println();
    }

    // QueueArray �N���X�̓���m�F
    public static void main(String[] args) {
          QueueArray queue = new QueueArray(10);

          queue.enqueue(1);
          queue.enqueue(2);
          queue.display();                        // 12

          System.out.println(queue.dequeue());    // 1
          System.out.println(queue.dequeue());    // 2
    }
}