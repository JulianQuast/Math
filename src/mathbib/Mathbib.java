/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 *
 * @author Julian
 */
public class Mathbib {

    public static ExtDecimal[] testwerte = {new ExtDecimal(-1), new ExtDecimal(0), new ExtDecimal(0.5), new ExtDecimal(1), new ExtDecimal(2)};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int[] frac = ToFractionConverter.toFraction((double) 2.718281828, 20);
//        ToFractionConverter.fractionOutput(frac);
////        System.out.println(sqrt(BigInteger.valueOf(120)));
        //ExtDecimal a = new ExtDecimal(-50);
        //System.out.println(a.remainder(new ExtDecimal(7)));
        //System.out.println(ExtDecimal.HALF.scale());
//        System.out.println(ExtDecimal.PI.multiply(ExtDecimal.PI));
//        BigDecimal b;
//        Polynomial p = new Polynomial(0,0,1);
//        Polynomial q = new Polynomial(0,1,-1,1,0);
//        System.out.println(q);
//        PolynomialGroovy obj = new PolynomialGroovy();
//        System.out.println(obj);
//        long time = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++){
//            p = p.multiply(q);
//        }
//        System.out.println(System.currentTimeMillis() -time);
//        time = System.currentTimeMillis();
//        System.out.println(p);
//        System.out.println(System.currentTimeMillis());
//        System.out.println(ExtDecimal.PI.negate().sqrt(1000));
        //System.out.println(longxor(100000000));
//        ExtDecimal a = new ExtDecimal(1);
//        ExtDecimal b = new ExtDecimal(-2);
//        System.out.println(ExtDecimal.alternatingFunction(1));
//        System.out.println(ExtDecimal.alternatingFunction(8));
//        int[] a = SmallFunctions.primeList2(3);
//        int[] b = SmallFunctions.primeList3(3);
//        echo(b);
//        System.out.println(Arrays.equals(a,b));
        //System.out.println(SmallFunctions.li(1.4513692348));

        ExtDecimal a = ExtDecimal.ZERO;
        ExtDecimal b = ExtDecimal.TWO;
        ExtDecimal c = ExtDecimal.E;

//        System.out.println(a.ln(10, RoundingMode.UP));
        System.out.println(b.ln(1000, RoundingMode.UP));
        //System.out.println(c.ln(10, RoundingMode.UP));

    }
//    public static ArrayList<BigInteger> getFactors(BigInteger number) {
//        BigInteger testvar = BigInteger.valueOf(2);
//        ArrayList<BigInteger> listoffactors = new ArrayList();
//        BigInteger root = number.
//        while () {
//            BigInteger[] res = number.divideAndRemainder(testvar);
//            if (res[1].compareTo(BigInteger.ZERO) == 0) {
//                listoffactors.add(testvar);
//            }
//        }
//    }
//    public static BigInteger sqrt(BigInteger r) {
//        BigInteger a = r.divide(BigInteger.valueOf(2));
//        BigInteger last = a.abs().add(BigInteger.ONE);
//        while (a.compareTo(last) != 0) {
//            last = a.abs();
//            a = a.add(r.divide(a)).divide(BigInteger.valueOf(2));
//        }
//        return a;
//    }

    public static long longxor(int numberof) {
        long c = 982734325976294279L;
        for (int i = 0; i < numberof; i++) {
            long a = 27867323425339348L;
            c = c ^ a + 982734325976294279L;
        }
        return c;
    }

    public static void echo(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
