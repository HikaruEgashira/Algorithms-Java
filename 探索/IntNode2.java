// IntNode2.java
public class IntNode2 {
    int val; 
    IntNode2 left, right;  
    int height;

    // �q���̖؂̍����̏�񂩂炱�̐߂̍������Đݒ�
    public void reset_height () {
        this.height = Math.max(height(left), height(right))+ 1;
    }

    // �����̏���Ԃ��Bn��null�̎���0��Ԃ��B
    public static int height(IntNode2 n) {
        if (n == null) return 0; else return n.height;
    }
    
    public IntNode2 (int val, IntNode2 left,  IntNode2 right) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.height = Math.max(height(left), height(right))+ 1;
    }
}