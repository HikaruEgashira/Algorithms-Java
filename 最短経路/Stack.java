public class Stack {
    int length;
    int ptr;
    int stack[];

    public Stack(int len) {
        ptr = 0;
        length = len;
        stack = new int[length];
    }

    public int pop() {
        if (ptr >= length)
            System.out.println("stack overflow");
        return stack[--ptr];
    }

    public void push(int n) {
        if (ptr < 0)
            System.out.println("stack underflow");
        stack[ptr++] = n;
    }

    public boolean isEmpty() {
        return ptr <= 0;
    }

    public void display() {
        if (isEmpty())
            System.out.println("Stack is Empty");
        else {
            for (int i = 0; i < ptr; i++)
                System.out.print(stack[i] + " ");
            System.out.println();
        }
    }
}