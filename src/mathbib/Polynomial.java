/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

/**
 *
 * @author Julian C. Quast (c) 2013
 */
public class Polynomial extends VectorSpaceElement {

    private ExtDecimal[] coefficients;
    private int degree = -2;
    public static Polynomial ZERO = new Polynomial(0);

    public Polynomial() {
        coefficients = new ExtDecimal[1];
        coefficients[0] = ExtDecimal.ZERO;
    }

    /**
     * Constructs the polynomial using the set of coefficients.
     *
     * @param coeff
     */
    public Polynomial(ExtDecimal[] coeff) {
        coefficients = new ExtDecimal[coeff.length];
        for (int i = 0; i < coeff.length; i++) {
            coefficients[i] = coeff[i].plus();
        }
    }

    /**
     * Constructs the polynomial using the set of coefficients.
     *
     * @param coeff
     */
    Polynomial(double... coeff) {
        coefficients = new ExtDecimal[coeff.length];
        for (int i = 0; i < coeff.length; i++) {
            coefficients[i] = new ExtDecimal(coeff[i]);
        }
    }

    /**
     * Returns an array of the coefficients of the polynomial
     *
     * @return {@code this as array}
     */
    public ExtDecimal[] getCoefficients() {
        return coefficients;
    }

    /**
     * Returns the degree of the polynomial. If the Polynomial is zero and
     * therefore the degree is -infinity, the function returns -1. The degree is
     * lazy calculated.
     *
     * @return {@code deg(this)}
     */
    public int getDegree() {
        if (degree == -2) {
            for (int i = coefficients.length - 1; i >= 0; i--) {
                if (coefficients[i].compareTo(ExtDecimal.ZERO) != 0) {
                    return i;
                }
            }
            return -1;
        } else {
            return degree;
        }
    }

    /**
     * Returns the value of the polynomial for a defined value of x using
     * Horner's method.
     *
     * @param x
     * @return {@code this(x)}
     */
    public ExtDecimal valueFor(ExtDecimal x) {
        ExtDecimal y = ExtDecimal.ZERO;
        for (int i = getDegree(); i >= 0; i--) {
            y = y.multiply(x);
            y = y.add(coefficients[i]);
        }
        return y;
    }

    /**
     * Returns the value of the polynomial for a defined value of x using
     * Horner's method.
     *
     * @param x
     * @return {@code this(x)}
     */
    public ExtDecimal valueFor(double x) {
        return valueFor(new ExtDecimal(x));
    }

    /**
     * Returns the derivation of the polynomial
     *
     * @return {@code Deriv(this)}
     */
    public Polynomial getDerivation() {
        if (getDegree() > 0) {
            ExtDecimal c[] = new ExtDecimal[getDegree()];
            for (int i = 1; i <= getDegree(); i++) {
                c[i - 1] = coefficients[i].multiply(new ExtDecimal(i));
            }
            return new Polynomial(c);
        } else {
            return Polynomial.ZERO;
        }
    }

    @Override
    public VectorSpaceElement add(VectorSpaceElement vse) {
        return add(vse);
    }

    /**
     * Returns a {@code Polynomial} whose value is {@code (this + augend)}.
     *
     *
     * @param augend value to be added to this {@code Polynomial}.
     * @return {@code this + augend}.
     */
    public Polynomial add(Polynomial augend) {
        ExtDecimal[] c = new ExtDecimal[Math.max(Math.max(getDegree(), augend.getDegree()), 0) + 1];
        for (int i = 0; i < c.length; i++) {
            if (i <= getDegree() && i <= augend.getDegree()) {
                c[i] = coefficients[i].add(augend.getCoefficients()[i]);
            } else if (i <= getDegree() && i > augend.getDegree()) {
                c[i] = coefficients[i];
            } else if (i > getDegree() && i <= augend.getDegree()) {
                c[i] = augend.getCoefficients()[i];
            } else {
                c[i] = ExtDecimal.ZERO;
            }
        }
        return new Polynomial(c);
    }

    @Override
    public VectorSpaceElement subtract(VectorSpaceElement vse) {
        return subtract(vse);
    }

    /**
     * Returns a {@code Polynomial} whose value is {@code (this - subtrahend)}.
     *
     *
     * @param subtrahend value to be subtracted from this {@code Polynomial}.
     * @return {@code this - augend}.
     */
    public Polynomial subtract(Polynomial subtrahend) {
        ExtDecimal[] c = new ExtDecimal[Math.max(Math.max(getDegree(), subtrahend.getDegree()), 0) + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = coefficients[i].subtract(subtrahend.getCoefficients()[i]);
        }
        return new Polynomial(c);
    }

    @Override
    public VectorSpaceElement multiply(VectorSpaceElement vse) {
        return multiply(vse);
    }

    /**
     * Returns a {@code Polynomial} whose value is {@code (this * multiplicand)}.
     * Verbesserbar, vielleicht mit Karazuba
     *
     * @param multiplicand value to be multiplied with this {@code Polynomial}.
     * @return {@code this * multiplicand}.
     */
    public Polynomial multiply(Polynomial multiplicand) {
        int carraylength = Math.max(getDegree() + multiplicand.getDegree(), 0) + 1;
        ExtDecimal[] c = new ExtDecimal[carraylength];
        ExtDecimal[] mcoefficients = multiplicand.getCoefficients();

        if (getDegree() >= 0 && multiplicand.getDegree() >= 0) {
            for (int n = 0; n <= getDegree() + multiplicand.getDegree(); n++) {
                c[n] = ExtDecimal.ZERO;
                for (int i = 0; i <= n; i++) {
                    if (coefficients.length > i && mcoefficients.length > n - i) {
                        c[n] = c[n].add(coefficients[i].multiply(mcoefficients[n - i]));
                    }
                }
            }
            return new Polynomial(c);
        } else {
            return Polynomial.ZERO;
        }
    }

    public String toString(String var) {
        String erg = "";
        boolean show = false;
        for (int i = 0; i <= getDegree(); i++) {
            if (coefficients[i].compareTo(ExtDecimal.ZERO) != 0) {
                if (coefficients[i].compareTo(ExtDecimal.ZERO) == 1) {
                    if (i != 0 && show) {
                        erg += " + ";
                    }
                } else {
                    erg += " - ";
                }
                if (coefficients[i].abs().compareTo(ExtDecimal.ONE) != 0 && i >= 1 || coefficients[i].abs().compareTo(ExtDecimal.ZERO) != 0 && i == 0) {
                    erg += coefficients[i].abs();
                }
                if (i >= 1) {
                    erg += var;
                }
                if (i >= 2) {
                    erg += "^" + i;
                }
            }
            show = true;
        }
        return erg;
    }

    public Polynomial plus(Polynomial other) {
        return add(other);
    }

    public Polynomial minus(Polynomial other) {
        return minus(other);
    }

    @Override
    public String toString() {
        return toString("x");
    }
}
