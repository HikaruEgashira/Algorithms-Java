public class GCDEuclid {
    public static void main(final String[] args) {
        assert(euclid(13673, 31640) == 113);
        
        if (args.length != 2) {
            System.err.println("Usage: java GCDEuclid <int1> <int2>");
            System.exit(1);
        }
        // Process arguments.
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        // Find the greatest common divisor.
        int gcd = euclid(n, m);
        System.out.println("The GCD of " + m + " and " + n + " is " + gcd + ".") ;
    }

    // Find the greatest common divisor of the two integers, n and m.
    static int euclid(int n, int m) {
        int r = 0;
        // Let n be the smaller number.
        if (n > m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
        //     n % m = x ... r
        // ==> m % r = x' ... r'
        while (m != 0) {
            r = n % m;
            n = m;
            m = r;
        }
        return n;
    }
}