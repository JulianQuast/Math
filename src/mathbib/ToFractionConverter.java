/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

/**
 *
 * @author Julian
 */
public class ToFractionConverter {

    public static int[] toFraction(double number, double limit) {
        int vorkommastelle = (int) Math.floor(number);
        double rest = number - vorkommastelle;
        System.out.println(rest);
        if (rest == 0) {
            int[] ret = {vorkommastelle, 1};
            return ret;
        } else {
            double rec = 1 / rest;
            if (Math.abs(rec) > limit) {
                int[] ret = {vorkommastelle, 1};
                return ret;
            } else {
                int[] underfraction = toFraction(rec, limit);
                int[] thefraction = {vorkommastelle * underfraction[0] + underfraction[1], underfraction[0]};
                return thefraction;
            }
        }
    }

    public static int[] toFraction(double number) {
        return toFraction(number, 300);
    }
    
    public static void fractionOutput(int[] fraction) {
        System.out.println(fraction[0] + "/" + fraction[1]);
    }
}
