/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

/**
 *
 * @author Julian Quast (c) 2013
 */
public class SmallFunctions {

    /**
     * This function returns a prime list using the sieve of erathosthenes.
     *
     * Profil: 10^4 -> 2.12 ms 10^5 -> 11.6 ms 10^6 -> 21.8 ms 10^7 -> 223 ms
     * 10^8 -> 2922 ms
     *
     * @param maximum
     * @return
     */
    public static int[] primeList(int maximum) {
        boolean[] list = new boolean[maximum + 1];
        int primes = 0;
        // O(n)
        for (int i = 0; i <= maximum; i++) {
            list[i] = true;
        }
        list[0] = false;
        list[1] = false;
        for (int i = 0; i <= maximum; i++) {
            if (list[i] != false) {
                primes++;
                for (int j = i * 2; j <= maximum; j += i) {
                    list[j] = false;
                }
            }
        }
        int[] primelist = new int[primes];
        int c = 0;
        // O(n)
        for (int i = 0; i <= maximum; i++) {
            if (list[i]) {
                primelist[c] = i;
                c++;
            }
        }
        return primelist;
    }

    /**
     * This function returns a prime list using the sieve of erathosthenes.
     *
     * Profil: 10^4 -> 1.41 ms
     *
     * @param maximum
     * @return
     */
    public static int[] primeList2(int maximum) {
        boolean[] list = new boolean[maximum + 1];
        int primes = 0;
        for (int i = 2; i <= maximum; i++) {
            list[i] = true;
        }
        list[0] = false;
        list[1] = false;
        double wurzel = Math.sqrt(maximum);
        for (int i = 0; i <= maximum; i++) {
            if (list[i] != false) {
                primes++;
                if (i <= wurzel) {
                    for (int j = i * 2; j <= maximum; j += i) {
                        list[j] = false;
                    }
                }
            }
        }
        int[] primelist = new int[primes];
        int c = 0;
        for (int i = 0; i <= maximum; i++) {
            if (list[i]) {
                primelist[c] = i;
                c++;
            }
        }
        return primelist;
    }

    /**
     * This function returns a prime list using the sieve of erathosthenes.
     *
     * @param maximum
     * @return
     */
    public static int[] primeList3(int maximum) {
        if (maximum == 0) {
            return new int[0];
        } else {
            // Aufbauen des booleschen Arrays
            // O(n)
            boolean[] list = new boolean[maximum + 1];
            int primes = 0;
            for (int i = 2; i <= maximum; i++) {
                list[i] = true;
            }
            list[0] = false;
            list[1] = false;
            double wurzel = Math.sqrt(maximum);
            // Hauptschleife
            // > O(n)
            for (int i = 0; i <= maximum; i++) {
                if (list[i] != false) {
                    primes++;
                    if (i <= wurzel) {
                        if (i >= 3) {
                            // Schnelle Variante ohne die durch 2 teilbaren Zahlen
                            for (int j = i * 3; j <= maximum; j += 2 * i) {
                                list[j] = false;
                            }
                        } else {
                            // Langsame Variante für die 2
                            for (int j = i * 2; j <= maximum; j += i) {
                                list[j] = false;
                            }
                        }
                    }
                }
            }
            // Zurückspielen des booleschen Arrays
            // O(n)
            int[] primelist = new int[primes];
            int c = 0;
            for (int i = 0; i <= maximum; i++) {
                if (list[i]) {
                    primelist[c] = i;
                    c++;
                }
            }
            return primelist;
        }
    }

    public static double li(double arg) {
        int fragments = 10000000;
        double val = 0;
        double dx = arg / fragments;
        for (int i = 0; i < fragments; i++) {
            val += dx / Math.log(arg / i);
        }
        return val;
    }

    /**
     *
     * The recursive implemented Ackermann function ack(n,m) .
     *
     * @param n
     * @param m
     * @return
     */
    public static int ackermann(int n, int m) {
        if (n == 0) {
            return m + 1;
        } else if (m == 0) {
            return ackermann(n - 1, 1);
        } else {
            return ackermann(n - 1, ackermann(n, m - 1));
        }
    }
}
