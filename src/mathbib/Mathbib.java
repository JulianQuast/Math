/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

import java.math.BigDecimal;

/**
 *
 * @author Julian
 */
public class Mathbib {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        int[] frac = ToFractionConverter.toFraction((double) 2.718281828, 20);
//        ToFractionConverter.fractionOutput(frac);
////        System.out.println(sqrt(BigInteger.valueOf(120)));
        ExtDecimal a = new ExtDecimal(2);
        System.out.println(ExtDecimal.PI.multiply(ExtDecimal.PI));
        BigDecimal b;
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
}
