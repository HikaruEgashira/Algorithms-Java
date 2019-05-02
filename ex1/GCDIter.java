public class GCDIter {
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
        // Find the greatest common divisor.
        int gcd = gcd(n, m);
        System.out.println("The GCD of " + m + " and " + n + " is " + gcd + ".") ;
    }

    // Find the greatest common divisor of the two integers, n and m.
    static int gcd(int n, int m) {
        // Let n be the smaller number.
        if (n > m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        int gcd = 1;
        int i = 1;
        while (i <= n) {
            if (n % i == 0 && m % i == 0)
                gcd = i;
            i++;
        }
        return gcd;
    }
}