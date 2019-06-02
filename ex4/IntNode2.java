// IntNode2.java
public class IntNode2 {
    int val; 
    IntNode2 left, right;  
    int height;

    // q‹Ÿ‚Ì–Ø‚Ì‚‚³‚Ìî•ñ‚©‚ç‚±‚Ìß‚Ì‚‚³‚ğÄİ’è
    public void reset_height () {
        this.height = Math.max(height(left), height(right))+ 1;
    }

    // ‚‚³‚Ìî•ñ‚ğ•Ô‚·Bn‚ªnull‚Ì‚Í0‚ğ•Ô‚·B
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