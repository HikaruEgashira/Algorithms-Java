public class GCDRecursive {
    public static void main(final String[] args) {
        // Process arguments.
        int n = 13673;
        int m = 31640;
        if (args.length != 2) {
            System.err.println("Usage: java GCDEuclid <int1> <int2>");
            // System.exit(1);
        } else {
            n = Integer.parseInt(args[0]);
            m = Integer.parseInt(args[1]);
        }
        int gcd = recursive(n, m);
        // gcd = 113
        System.out.println("The GCD of " + m + " and " + n + " is " + gcd + ".") ;
    }

    // Find the greatest common divisor of the two integers, n and m.
    static int recursive(int n, int m) {
        if (m == 0) {
            return n;
        }
        // Let n be the smaller number.
        if (n < m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        int r = n % m;
        return recursive(m, r);
    }
}