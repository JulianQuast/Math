/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mathbib;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * This class is a hull of the class BigDecimal and contains more math
 * functions. ExtDecimal stands for "extended BigDecimal". A part of the
 * comments is copied from the class BigDecimal.
 *
 * To control: mod = modulo() div = divide() shift = shiftDecimalExponent(),
 * shiftBinaryExponent() round = setScale()
 *
 * @author Julian C. Quast (c) 2013
 */
public class ExtDecimal extends VectorSpaceElement {

    // Constants
    /**
     * The value 0, with a scale of 0.
     *
     */
    public static final ExtDecimal ZERO = new ExtDecimal(BigDecimal.ZERO);
    /**
     * The value of a number 0+ almost equal to 0 but positive.
     *
     */
    public static final ExtDecimal POSITIVEZERO = new ExtDecimal(Type.POSITIVEZERO);
    /**
     * The value of a number 0- almost equal to 0 but negative.
     *
     */
    public static final ExtDecimal NEGATIVEZERO = new ExtDecimal(Type.POSITIVEZERO);
    /**
     * The value 1, with a scale of 0.
     *
     */
    public static final ExtDecimal ONE = new ExtDecimal(BigDecimal.ONE);
    /**
     * The value -1, with a scale of 0.
     *
     */
    public static final ExtDecimal MINUSONE = new ExtDecimal(BigDecimal.ONE.negate());
    /**
     * The value 2, with a scale of 0.
     *
     */
    public static final ExtDecimal TWO = new ExtDecimal(2);
    /**
     * The value 10, with a scale of 0.
     *
     */
    public static final ExtDecimal TEN = new ExtDecimal(BigDecimal.TEN);
    /**
     * The value 0.5, with a scale of 1.
     *
     */
    public static final ExtDecimal HALF = new ExtDecimal(0.5);
    /**
     * The value of pi, with a scale of 200.
     *
     */
    public static final ExtDecimal PI = new ExtDecimal("3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706798214808651328230664709384460955058223172535940812848111745028410270193852110555964462294895493038196");
    /**
     * The value of e, with a scale of 1105.
     *
     */
    public static final ExtDecimal E = new ExtDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342952605956307381323286279434907632338298807531952510190115738341879307021540891499348841675092447614606680822648001684774118537423454424371075390777449920695517027618386062613313845830007520449338265602976067371132007093287091274437470472306969772093101416928368190255151086574637721112523897844250569536967707854499699679468644549059879316368892300987931277361782154249992295763514822082698951936680331825288693984964651058209392398294887933203625094431173012381970684161403970198376793206832823764648042953118023287825098194558153017567173613320698112509961818815930416903515988885193458072738667385894228792284998920868058257492796104841984443634632449684875602336248270419786232090021609902353043699418491463140934317381436405462531520961836908887070167683964243781405927145635490613031072085103837505101157477041718986106873969655212671546889570350354021234078498193343210681701210056278802351930332247450158539047304199577770935036604169973297250886876966");
    /**
     * A number INF bigger than every real number
     *
     */
    public static final ExtDecimal INFINITY = new ExtDecimal(Type.INFINITY);
    /**
     * A number -INF smaller than every real number
     *
     */
    public static final ExtDecimal NEGATIVEINFINITY = new ExtDecimal(Type.NEGATIVEINFINITY);
    /**
     * Contains the number in form of a BigDecimal object.
     *
     */
    private BigDecimal content;

    public enum Type {

        NUMBER, POSITIVEZERO, NEGATIVEZERO, INFINITY, NEGATIVEINFINITY
    };
    private Type type = Type.NUMBER;

    // Constructors
    /**
     * Translates a {@code double} into a {@code ExtDecimal} which is the exact
     * decimal representation of the {@code double}'s binary floating-point
     * value. The scale of the returned
     * {@code ExtDecimal} is the smallest value such that
     * <tt>(10<sup>scale</sup> &times; val)</tt> is an integer. <p>
     * <b>Notes:</b> <ol> <li> The results of this constructor can be somewhat
     * unpredictable. One might assume that writing {@code new ExtDecimal(0.1)}
     * in Java creates a {@code ExtDecimal} which is exactly equal to 0.1 (an
     * unscaled value of 1, with a scale of 1), but it is actually equal to
     * 0.1000000000000000055511151231257827021181583404541015625. This is
     * because 0.1 cannot be represented exactly as a
     * {@code double} (or, for that matter, as a binary fraction of any finite
     * length). Thus, the value that is being passed <i>in</i> to the
     * constructor is not exactly equal to 0.1, appearances notwithstanding.
     *
     * <li> The {@code String} constructor, on the other hand, is perfectly
     * predictable: writing {@code new ExtDecimal("0.1")} creates a {@code ExtDecimal}
     * which is <i>exactly</i> equal to 0.1, as one would expect. Therefore, it
     * is generally recommended that the {@linkplain #ExtDecimal(String)
     * <tt>String</tt> constructor} be used in preference to this one.
     *
     * <li> When a {@code double} must be used as a source for a
     * {@code ExtDecimal}, note that this constructor provides an exact
     * conversion; it does not give the same result as converting the {@code double}
     * to a {@code String} using the
     * {@link Double#toString(double)} method and then using the
     * {@link #ExtDecimal(String)} constructor. To get that result, use the {@code static} {@link #valueOf(double)}
     * method. </ol>
     *
     * @param val {@code double} value to be converted to
     *        {@code ExtDecimal}.
     * @throws NumberFormatException if {@code val} is infinite or NaN.
     */
    public ExtDecimal(double val) {
        content = new BigDecimal(val);
    }

    /**
     * The BigDecimal constructor
     *
     * @param val
     */
    public ExtDecimal(BigDecimal val) {
        content = val;
    }

    /**
     *
     * @param val
     */
    public ExtDecimal(Type specialtype) {
        content = null;
        type = specialtype;
    }

    /**
     * Translates the string representation of a {@code ExtDecimal} into a {@code ExtDecimal}.
     * The string representation consists of an optional sign, {@code '+'} (<tt>
     * '&#92;u002B'</tt>) or
     * {@code '-'} (<tt>'&#92;u002D'</tt>), followed by a sequence of zero or
     * more decimal digits ("the integer"), optionally followed by a fraction,
     * optionally followed by an exponent.
     *
     * <p>The fraction consists of a decimal point followed by zero or more
     * decimal digits. The string must contain at least one digit in either the
     * integer or the fraction. The number formed by the sign, the integer and
     * the fraction is referred to as the <i>significand</i>.
     *
     * <p>The exponent consists of the character {@code 'e'}
     * (<tt>'&#92;u0065'</tt>) or {@code 'E'} (<tt>'&#92;u0045'</tt>) followed
     * by one or more decimal digits. The value of the exponent must lie between -{@link Integer#MAX_VALUE} ({@link
     * Integer#MIN_VALUE}+1) and {@link Integer#MAX_VALUE}, inclusive.
     *
     * <p>More formally, the strings this constructor accepts are described by
     * the following grammar: <blockquote> <dl> <dt><i>BigDecimalString:</i>
     * <dd><i>Sign<sub>opt</sub> Significand Exponent<sub>opt</sub></i> <p>
     * <dt><i>Sign:</i> <dd>{@code +} <dd>{@code -} <p> <dt><i>Significand:</i>
     * <dd><i>IntegerPart</i> {@code .} <i>FractionPart<sub>opt</sub></i> <dd>{@code .}
     * <i>FractionPart</i> <dd><i>IntegerPart</i> <p> <dt><i>IntegerPart:</i>
     * <dd><i>Digits</i> <p> <dt><i>FractionPart:</i> <dd><i>Digits</i> <p>
     * <dt><i>Exponent:</i> <dd><i>ExponentIndicator SignedInteger</i> <p>
     * <dt><i>ExponentIndicator:</i> <dd>{@code e} <dd>{@code E} <p>
     * <dt><i>SignedInteger:</i> <dd><i>Sign<sub>opt</sub> Digits</i> <p>
     * <dt><i>Digits:</i> <dd><i>Digit</i> <dd><i>Digits Digit</i> <p>
     * <dt><i>Digit:</i> <dd>any character for which {@link Character#isDigit}
     * returns {@code true}, including 0, 1, 2 ... </dl> </blockquote>
     *
     * <p>The scale of the returned {@code BigDecimal} will be the number of
     * digits in the fraction, or zero if the string contains no decimal point,
     * subject to adjustment for any exponent; if the string contains an
     * exponent, the exponent is subtracted from the scale. The value of the
     * resulting scale must lie between {@code Integer.MIN_VALUE} and
     * {@code Integer.MAX_VALUE}, inclusive.
     *
     * <p>The character-to-digit mapping is provided by {@link
     * java.lang.Character#digit} set to convert to radix 10. The String may not
     * contain any extraneous characters (whitespace, for example).
     *
     * <p><b>Examples:</b><br> The value of the returned {@code ExtDecimal} is
     * equal to <i>significand</i> &times; 10<sup>&nbsp;<i>exponent</i></sup>.
     * For each string on the left, the resulting representation
     * [{@code ExtDecimal}, {@code scale}] is shown on the right.
     * <pre>
     * "0"            [0,0]
     * "0.00"         [0,2]
     * "123"          [123,0]
     * "-123"         [-123,0]
     * "1.23E3"       [123,-1]
     * "1.23E+3"      [123,-1]
     * "12.3E+7"      [123,-6]
     * "12.0"         [120,1]
     * "12.3"         [123,1]
     * "0.00123"      [123,5]
     * "-1.23E-12"    [-123,14]
     * "1234.5E-4"    [12345,5]
     * "0E+7"         [0,-7]
     * "-0"           [0,0]
     * </pre>
     *
     * <p>Note: For values other than {@code float} and
     * {@code double} NaN and &plusmn;Infinity, this constructor is compatible
     * with the values returned by {@link Float#toString} and {@link Double#toString}.
     * This is generally the preferred way to convert a {@code float} or {@code double}
     * into a ExtDecimal, as it doesn't suffer from the unpredictability of the {@link #ExtDecimal(double)}
     * constructor.
     *
     * @param val String representation of {@code ExtDecimal}.
     *
     * @throws NumberFormatException if {@code val} is not a valid
     * representation of a {@code ExtDecimal}.
     */
    public ExtDecimal(String val) {
        content = new BigDecimal(val);
    }

    // Static Factory Methods
    /**
     * Translates a {@code double} into a {@code ExtDecimal}, using the {@code double}'s
     * canonical string representation provided by the {@link Double#toString(double)}
     * method.
     *
     * <p><b>Note:</b> This is generally the preferred way to convert a {@code double}
     * (or {@code float}) into a
     * {@code ExtDecimal}, as the value returned is equal to that resulting from
     * constructing a {@code ExtDecimal} from the result of using {@link Double#toString(double)}.
     *
     * @param val {@code double} to convert to a {@code ExtDecimal}.
     * @return a {@code ExtDecimal} whose value is equal to or approximately
     * equal to the value of {@code val}.
     * @throws NumberFormatException if {@code val} is infinite or NaN.
     */
    public static ExtDecimal valueOf(double val) {
        return new ExtDecimal(BigDecimal.valueOf(val));
    }

    // Arithmetic Operations
    /**
     * Returns a {@code ExtDecimal} whose scale is the specified value, and
     * whose value is numerically equal to this
     * {@code ExtDecimal}'s. Throws an {@code ArithmeticException} if this is
     * not possible.
     *
     * <p>This call is typically used to increase the scale, in which case it is
     * guaranteed that there exists a {@code ExtDecimal} of the specified scale
     * and the correct value. The call can also be used to reduce the scale if
     * the caller knows that the
     * {@code ExtDecimal} has sufficiently many zeros at the end of its
     * fractional part (i.e., factors of ten in its integer value) to allow for
     * the rescaling without changing its value.
     *
     * <p>This method returns the same result as the two-argument versions of {@code setScale},
     * but saves the caller the trouble of specifying a rounding mode in cases
     * where it is irrelevant.
     *
     * <p>Note that since {@code ExtDecimal} objects are immutable, calls of
     * this method do <i>not</i> result in the original object being modified,
     * contrary to the usual convention of having methods named
     * <tt>set<i>X</i></tt> mutate field <i>{@code X}</i>. Instead, {@code setScale}
     * returns an object with the proper scale; the returned object may or may
     * not be newly allocated.
     *
     * @param newScale scale of the {@code ExtDecimal} value to be returned.
     * @return a {@code ExtDecimal} whose scale is the specified value, and
     * whose unscaled value is determined by multiplying or dividing this {@code ExtDecimal}'s
     * unscaled value by the appropriate power of ten to maintain its overall
     * value.
     * @throws ArithmeticException if the specified scaling operation would
     * require rounding.
     * @see #setScale(int, int)
     * @see #setScale(int, RoundingMode)
     */
    public ExtDecimal setScale(int newScale) {
        return new ExtDecimal(content.setScale(newScale));
    }

    /**
     * Returns a {@code ExtDecimal} whose scale is the specified value, and
     * whose unscaled value is determined by multiplying or dividing this {@code ExtDecimal}'s
     * unscaled value by the appropriate power of ten to maintain its overall
     * value. If the scale is reduced by the operation, the unscaled value must
     * be divided (rather than multiplied), and the value may be changed; in
     * this case, the specified rounding mode is applied to the division.
     *
     * <p>Note that since ExtDecimal objects are immutable, calls of this method
     * do <i>not</i> result in the original object being modified, contrary to
     * the usual convention of having methods named <tt>set<i>X</i></tt> mutate
     * field <i>{@code X}</i>. Instead, {@code setScale} returns an object with
     * the proper scale; the returned object may or may not be newly allocated.
     *
     * @param newScale scale of the {@code ExtDecimal} value to be returned.
     * @param roundingMode The rounding mode to apply.
     * @return a {@code ExtDecimal} whose scale is the specified value, and
     * whose unscaled value is determined by multiplying or dividing this {@code ExtDecimal}'s
     * unscaled value by the appropriate power of ten to maintain its overall
     * value.
     * @throws ArithmeticException if {@code roundingMode==UNNECESSARY} and the
     * specified scaling operation would require rounding.
     * @see RoundingMode
     */
    public ExtDecimal setScale(int newScale, RoundingMode roundingMode) {
        return new ExtDecimal(content.setScale(newScale, roundingMode));
    }

    /**
     * Returns a {@code ExtDecimal} whose scale is the specified value, and
     * whose unscaled value is determined by multiplying or dividing this {@code ExtDecimal}'s
     * unscaled value by the appropriate power of ten to maintain its overall
     * value. If the scale is reduced by the operation, the unscaled value must
     * be divided (rather than multiplied), and the value may be changed; in
     * this case, the specified rounding mode is applied to the division.
     *
     * <p>Note that since ExtDecimal objects are immutable, calls of this method
     * do <i>not</i> result in the original object being modified, contrary to
     * the usual convention of having methods named <tt>set<i>X</i></tt> mutate
     * field <i>{@code X}</i>. Instead, {@code setScale} returns an object with
     * the proper scale; the returned object may or may not be newly allocated.
     *
     * <p>The new {@link #setScale(int, RoundingMode)} method should be used in
     * preference to this legacy method.
     *
     * @param newScale scale of the {@code ExtDecimal} value to be returned.
     * @param roundingMode The rounding mode to apply.
     * @return a {@code ExtDecimal} whose scale is the specified value, and
     * whose unscaled value is determined by multiplying or dividing this {@code ExtDecimal}'s
     * unscaled value by the appropriate power of ten to maintain its overall
     * value.
     * @throws ArithmeticException if {@code roundingMode==ROUND_UNNECESSARY}
     * and the specified scaling operation would require rounding.
     * @throws IllegalArgumentException if {@code roundingMode} does not
     * represent a valid rounding mode.
     * @see #ROUND_UP
     * @see #ROUND_DOWN
     * @see #ROUND_CEILING
     * @see #ROUND_FLOOR
     * @see #ROUND_HALF_UP
     * @see #ROUND_HALF_DOWN
     * @see #ROUND_HALF_EVEN
     * @see #ROUND_UNNECESSARY
     */
    public ExtDecimal setScale(int newScale, int roundingMode) {
        return new ExtDecimal(content.setScale(newScale, roundingMode));
    }

    /**
     * Returns the value of the specified number as a
     * <code>byte</code>. This may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion to
     * type
     * <code>byte</code>.
     */
    public byte byteValue() {
        return content.byteValue();
    }

    /**
     * Converts this {@code ExtDecimal} to a {@code byte}, checking for lost
     * information. If this {@code ExtDecimal} has a nonzero fractional part or
     * is out of the possible range for a
     * {@code byte} result then an {@code ArithmeticException} is thrown.
     *
     * @return this {@code ExtDecimal} converted to a {@code byte}.
     * @throws ArithmeticException if {@code this} has a nonzero fractional
     * part, or will not fit in a {@code byte}.
     */
    public byte byteValueExact() {
        return content.byteValueExact();
    }

    /**
     * Converts this {@code ExtDecimal} to an {@code int}. This conversion is
     * analogous to the <i>narrowing primitive conversion</i> from {@code double}
     * to
     * {@code short} as defined in section 5.1.3 of <cite>The Java&trade;
     * Language Specification</cite>: any fractional part of this
     * {@code ExtDecimal} will be discarded, and if the resulting
     * "{@code BigInteger}" is too big to fit in an
     * {@code int}, only the low-order 32 bits are returned. Note that this
     * conversion can lose information about the overall magnitude and precision
     * of this {@code ExtDecimal} value as well as return a result with the
     * opposite sign.
     *
     * @return this {@code BigDecimal} converted to an {@code int}.
     */
    public int intValue() {
        return content.intValue();
    }

    /**
     * Converts this {@code ExtDecimal} to an {@code int}, checking for lost
     * information. If this {@code ExtDecimal} has a nonzero fractional part or
     * is out of the possible range for an
     * {@code int} result then an {@code ArithmeticException} is thrown.
     *
     * @return this {@code ExtDecimal} converted to an {@code int}.
     * @throws ArithmeticException if {@code this} has a nonzero fractional
     * part, or will not fit in an {@code int}.
     */
    public int intValueExact() {
        return content.intValueExact();
    }

    /**
     * Converts this {@code ExtDecimal} to a {@code double}. This conversion is
     * similar to the <i>narrowing primitive conversion</i> from {@code double}
     * to
     * {@code float} as defined in section 5.1.3 of <cite>The Java&trade;
     * Language Specification</cite>: if this {@code ExtDecimal} has too great a
     * magnitude represent as a {@code double}, it will be converted to {@link Double#NEGATIVE_INFINITY}
     * or {@link
     * Double#POSITIVE_INFINITY} as appropriate. Note that even when the return
     * value is finite, this conversion can lose information about the precision
     * of the {@code ExtDecimal} value.
     *
     * @return this {@code ExtDecimal} converted to a {@code double}.
     */
    public double doubleValue() {
        return content.doubleValue();
    }

    /**
     * Converts this {@code ExtDecimal} to a {@code BigDecimal}.
     *
     * @return this {@code ExtDecimal} converted to a {@code BigDecimal}.
     */
    public BigDecimal toBigDecimal() {
        return content;
    }

    /**
     * Returns a {@code ExtDecimal} whose value is the absolute value of this {@code ExtDecimal},
     * and whose scale is
     * {@code this.scale()}.
     *
     * 2013-06-21: OK
     *
     * @return {@code abs(this)}
     */
    public ExtDecimal abs() {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.abs());
        } else if (type == Type.POSITIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.NEGATIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.INFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else if (type == Type.NEGATIVEINFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    /**
     * This function returns 1 if n is even and -1 if n is odd. This function is
     * the alternating function (-1)^x and is used in many series
     * representations.
     *
     * @return {@code (-1)^n}
     */
    public static ExtDecimal alternatingFunction(int n) {
        if (n % 2 == 0) {
            return ONE;
        } else {
            return ONE.negate();
        }
    }

    /**
     * Returns a {@code ExtDecimal} whose value is the absolute value of this {@code ExtDecimal},
     * with rounding according to the context settings.
     *
     * @param mc the context to use.
     * @return {@code abs(this)}, rounded as necessary.
     * @throws ArithmeticException if the result is inexact but the rounding
     * mode is {@code UNNECESSARY}.
     */
    public ExtDecimal abs(MathContext mc) {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.abs(mc));
        } else if (type == Type.POSITIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.NEGATIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.INFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else if (type == Type.NEGATIVEINFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (+this)}, and whose
     * scale is {@code this.scale()}.
     *
     * <p>This method, which simply returns this {@code ExtDecimal} is included
     * for symmetry with the unary minus method {@link
     * #negate()}.
     *
     * @return {@code this}.
     * @see #negate()
     */
    public ExtDecimal plus() {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.plus());
        } else if (type == Type.POSITIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.NEGATIVEZERO) {
            return new ExtDecimal(Type.NEGATIVEZERO);
        } else if (type == Type.INFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else if (type == Type.NEGATIVEINFINITY) {
            return new ExtDecimal(Type.NEGATIVEINFINITY);
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (-this)}, and whose
     * scale is {@code this.scale()}.
     *
     * 2013-06-21: OK
     *
     * @return {@code -this}.
     */
    public ExtDecimal negate() {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.negate());
        } else if (type == Type.POSITIVEZERO) {
            return new ExtDecimal(Type.NEGATIVEZERO);
        } else if (type == Type.NEGATIVEZERO) {
            return new ExtDecimal(Type.POSITIVEZERO);
        } else if (type == Type.INFINITY) {
            return new ExtDecimal(Type.NEGATIVEINFINITY);
        } else if (type == Type.NEGATIVEINFINITY) {
            return new ExtDecimal(Type.INFINITY);
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public VectorSpaceElement add(VectorSpaceElement vse) {
        return add(vse);
    }

    /**
     * Compares this {@code ExtDecimal} with the specified
     * {@code ExtDecimal}. Two {@code ExtDecimal} objects that are equal in
     * value but have a different scale (like 2.0 and 2.00) are considered equal
     * by this method. This method is provided in preference to individual
     * methods for each of the six boolean comparison operators ({@literal <}, ==,
     * {@literal >}, {@literal >=}, !=, {@literal <=}).  The
     * suggested idiom for performing these comparisons is:
     * {@code (x.compareTo(y)} &lt;<i>op</i>&gt; {@code 0)}, where
     * &lt;<i>op</i>&gt; is one of the six comparison operators.
     *
     * @param val {@code ExtDecimal} to which this {@code ExtDecimal} is to be
     * compared.
     * @return -1, 0, or 1 as this {@code ExtDecimal} is numerically less than,
     * equal to, or greater than {@code val}.
     */
    public int compareTo(ExtDecimal val) {
        if (type == Type.NUMBER) {
            return content.compareTo(val.toBigDecimal());
        } else if (type == Type.POSITIVEZERO) {
            if (val.type == Type.NEGATIVEZERO || val.type == Type.NEGATIVEINFINITY) {
                // 0+ > 0- > -INF
                return 1;
            } else if (val.type == Type.INFINITY) {
                // 0+ < INF
                return -1;
            } else if (val.type == Type.POSITIVEZERO) {
                // 0+ == 0+
                return 0;
            } else {
                // Restvergleich
                return compareTo(ZERO);
            }
        } else if (type == Type.NEGATIVEZERO) {
            if (val.type == Type.NEGATIVEINFINITY) {
                // 0- > -INF
                return 1;
            } else if (val.type == Type.POSITIVEZERO || val.type == Type.INFINITY) {
                // 0- < 0+ < INF
                return -1;
            } else if (val.type == Type.NEGATIVEZERO) {
                // 0- == 0-
                return 0;
            } else {
                // Restvergleich
                return compareTo(ZERO);
            }
        } else if (type == Type.INFINITY) {
            if (val.type == Type.INFINITY) {
                // INF ==? INF
                return 0;
            } else {
                // x < INF
                return -1;
            }
        } else if (type == Type.NEGATIVEINFINITY) {
            if (val.type == Type.NEGATIVEINFINITY) {
                // -INF ==? -INF
                return 0;
            } else {
                // -INF < x
                return 1;
            }
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    /**
     * Returns a {@code BigDecimal} whose value is {@code (this +
     * augend)}, and whose scale is {@code max(this.scale(),
     * augend.scale())}.
     *
     * @param augend value to be added to this {@code BigDecimal}.
     * @return {@code this + augend}
     */
    public ExtDecimal add(ExtDecimal augend) {
        if (type == Type.NUMBER) {
            if (augend.type == Type.NUMBER) {
                return new ExtDecimal(content.add(augend.toBigDecimal()));
            } else if (augend.type == Type.POSITIVEZERO) {
                return augend;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return augend;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.POSITIVEZERO) {
            if (augend.type == Type.NUMBER) {
                return augend;
            } else if (augend.type == Type.POSITIVEZERO) {
                return POSITIVEZERO;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return ZERO;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.NEGATIVEZERO) {
            if (augend.type == Type.NUMBER) {
                return augend;
            } else if (augend.type == Type.POSITIVEZERO) {
                return ZERO;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return NEGATIVEZERO;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.INFINITY) {
            if (augend.type == Type.NEGATIVEINFINITY) {
                throw new UnsupportedOperationException("Unknown number");
            } else {
                return INFINITY;
            }
        } else if (type == Type.NEGATIVEINFINITY) {
            if (augend.type == Type.INFINITY) {
                throw new UnsupportedOperationException("Unknown number");
            } else {
                return INFINITY;
            }
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this + augend)}, with
     * rounding according to the context settings.
     *
     * If either number is zero and the precision setting is nonzero then the
     * other number, rounded if necessary, is used as the result.
     *
     * @param augend value to be added to this {@code ExtDecimal}.
     * @param mc the context to use.
     * @return {@code this + augend}, rounded as necessary.
     * @throws ArithmeticException if the result is inexact but the rounding
     * mode is {@code UNNECESSARY}.
     */
    public ExtDecimal add(ExtDecimal augend, MathContext mc) {
        if (type == Type.NUMBER) {
            if (augend.type == Type.NUMBER) {
                return new ExtDecimal(content.add(augend.toBigDecimal(), mc));
            } else if (augend.type == Type.POSITIVEZERO) {
                return augend;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return augend;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.POSITIVEZERO) {
            if (augend.type == Type.NUMBER) {
                return augend;
            } else if (augend.type == Type.POSITIVEZERO) {
                return POSITIVEZERO;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return ZERO;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.NEGATIVEZERO) {
            if (augend.type == Type.NUMBER) {
                return augend;
            } else if (augend.type == Type.POSITIVEZERO) {
                return ZERO;
            } else if (augend.type == Type.NEGATIVEZERO) {
                return NEGATIVEZERO;
            } else if (augend.type == Type.INFINITY) {
                return INFINITY;
            } else if (augend.type == Type.NEGATIVEINFINITY) {
                return NEGATIVEINFINITY;
            } else {
                throw new UnsupportedOperationException("Unknown type");
            }
        } else if (type == Type.INFINITY) {
            if (augend.type == Type.NEGATIVEINFINITY) {
                throw new UnsupportedOperationException("Unknown number");
            } else {
                return INFINITY;
            }
        } else if (type == Type.NEGATIVEINFINITY) {
            if (augend.type == Type.INFINITY) {
                throw new UnsupportedOperationException("Unknown number");
            } else {
                return INFINITY;
            }
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    public ExtDecimal inc() {
        if (type == Type.NUMBER) {
            return add(ONE);
        } else if (type == Type.POSITIVEZERO) {
            return ONE;
        } else if (type == Type.NEGATIVEZERO) {
            return ONE;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else if (type == Type.NEGATIVEINFINITY) {
            return NEGATIVEINFINITY;
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    public ExtDecimal dec() {
        if (type == Type.NUMBER) {
            return add(new ExtDecimal(-1));
        } else if (type == Type.POSITIVEZERO) {
            return MINUSONE;
        } else if (type == Type.NEGATIVEZERO) {
            return MINUSONE;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else if (type == Type.NEGATIVEINFINITY) {
            return NEGATIVEINFINITY;
        } else {
            throw new UnsupportedOperationException("Unknown type");
        }
    }

    @Override
    public VectorSpaceElement subtract(VectorSpaceElement vse) {
        return subtract(vse);
    }

    /**
     * Returns a {@code BigDecimal} whose value is {@code (this -
     * subtrahend)}, and whose scale is {@code max(this.scale(),
     * subtrahend.scale())}.
     *
     * @param subtrahend value to be subtracted from this {@code BigDecimal}.
     * @return {@code this - subtrahend}
     */
    public ExtDecimal subtract(ExtDecimal subtrahend) {
        // Not yet customized
        return new ExtDecimal(content.subtract(subtrahend.toBigDecimal()));
    }

    @Override
    public VectorSpaceElement multiply(VectorSpaceElement vse) {
        return multiply(vse);
    }

    /**
     * Returns a {@code ExtDecimal} whose value is <tt>(this &times;
     * multiplicand)</tt>, and whose scale is {@code (this.scale() +
     * multiplicand.scale())}.
     *
     * @param multiplicand value to be multiplied by this {@code ExtDecimal}.
     * @return {@code this * multiplicand}
     */
    public ExtDecimal multiply(ExtDecimal multiplicand) {
        // Not yet customized
        return new ExtDecimal(content.multiply(multiplicand.toBigDecimal()));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is <tt>(this &times;
     * multiplicand)</tt>, with rounding according to the context settings.
     *
     * @param multiplicand value to be multiplied by this {@code ExtDecimal}.
     * @param mc the context to use.
     * @return {@code this * multiplicand}, rounded as necessary.
     * @throws ArithmeticException if the result is inexact but the rounding
     * mode is {@code UNNECESSARY}.
     */
    public ExtDecimal multiply(ExtDecimal multiplicand, MathContext mc) {
        // Not yet customized
        return new ExtDecimal(content.multiply(multiplicand.toBigDecimal(), mc));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this *
     * multiplicand)}, and whose scale is as specified. If rounding must be
     * performed to generate a result with the specified scale, the specified
     * rounding mode is applied.
     *
     * @param multiplicand value to be multiplied by this {@code ExtDecimal}.
     * @param precision precision of the {@code ExtDecimal} product to be
     * returned.
     * @param roundingMode rounding mode to apply.
     * @return {@code this * multiplicand}
     * @throws ArithmeticException if {@code multiplicand} is zero,
     *         {@code roundingMode==RoundingMode.UNNECESSARY} and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    public ExtDecimal multiply(ExtDecimal multiplicand, int precision, RoundingMode roundingMode) {
        // Not yet customized
        return new ExtDecimal(content.multiply(multiplicand.toBigDecimal(), new MathContext(precision, roundingMode)));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, and whose preferred scale is {@code (this.scale() -
     * divisor.scale())}; if the exact quotient cannot be represented (because
     * it has a non-terminating decimal expansion) an {@code ArithmeticException}
     * is thrown.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @throws ArithmeticException if the exact quotient does not have a
     * terminating decimal expansion
     * @return {@code this / divisor}
     */
    public ExtDecimal divide(ExtDecimal divisor) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal()));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, with rounding according to the context settings.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @param mc the context to use.
     * @return {@code this / divisor}, rounded as necessary.
     * @throws ArithmeticException if the result is inexact but the rounding
     * mode is {@code UNNECESSARY} or
     *         {@code mc.precision == 0} and the quotient has a non-terminating decimal
     * expansion.
     */
    public ExtDecimal divide(ExtDecimal divisor, MathContext mc) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal(), mc));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, and whose scale is {@code this.scale()}. If rounding must be
     * performed to generate a result with the given scale, the specified
     * rounding mode is applied.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @param roundingMode rounding mode to apply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor==0}, or
     *         {@code roundingMode==RoundingMode.UNNECESSARY} and
     *         {@code this.scale()} is insufficient to represent the result of the
     * division exactly.
     */
    public ExtDecimal divide(ExtDecimal divisor, RoundingMode roundingMode) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal(), roundingMode));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, and whose scale is {@code this.scale()}. If rounding must be
     * performed to generate a result with the given scale, the specified
     * rounding mode is applied.
     *
     * <p>The new {@link #divide(ExtDecimal, RoundingMode)} method should be
     * used in preference to this legacy method.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @param roundingMode rounding mode to apply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor==0}, or
     *         {@code roundingMode==ROUND_UNNECESSARY} and
     *         {@code this.scale()} is insufficient to represent the result of the
     * division exactly.
     * @throws IllegalArgumentException if {@code roundingMode} does not
     * represent a valid rounding mode.
     * @see #ROUND_UP
     * @see #ROUND_DOWN
     * @see #ROUND_CEILING
     * @see #ROUND_FLOOR
     * @see #ROUND_HALF_UP
     * @see #ROUND_HALF_DOWN
     * @see #ROUND_HALF_EVEN
     * @see #ROUND_UNNECESSARY
     */
    public ExtDecimal divide(ExtDecimal divisor, int roundingMode) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal(), roundingMode));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, and whose scale is as specified. If rounding must be performed
     * to generate a result with the specified scale, the specified rounding
     * mode is applied.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @param scale scale of the {@code ExtDecimal} quotient to be returned.
     * @param roundingMode rounding mode to apply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor} is zero,
     *         {@code roundingMode==RoundingMode.UNNECESSARY} and the specified scale is
     * insufficient to represent the result of the division exactly.
     */
    public ExtDecimal divide(ExtDecimal divisor, int scale, RoundingMode roundingMode) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal(), scale, roundingMode));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this /
     * divisor)}, and whose scale is as specified. If rounding must be performed
     * to generate a result with the specified scale, the specified rounding
     * mode is applied.
     *
     * <p>The new {@link #divide(ExtDecimal, int, RoundingMode)} method should
     * be used in preference to this legacy method.
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @param scale scale of the {@code ExtDecimal} quotient to be returned.
     * @param roundingMode rounding mode to apply.
     * @return {@code this / divisor}
     * @throws ArithmeticException if {@code divisor} is zero,
     *         {@code roundingMode==ROUND_UNNECESSARY} and the specified scale is
     * insufficient to represent the result of the division exactly.
     * @throws IllegalArgumentException if {@code roundingMode} does not
     * represent a valid rounding mode.
     * @see #ROUND_UP
     * @see #ROUND_DOWN
     * @see #ROUND_CEILING
     * @see #ROUND_FLOOR
     * @see #ROUND_HALF_UP
     * @see #ROUND_HALF_DOWN
     * @see #ROUND_HALF_EVEN
     * @see #ROUND_UNNECESSARY
     */
    public ExtDecimal divide(ExtDecimal divisor, int scale, int roundingMode) {
        // Not yet customized
        return new ExtDecimal(content.divide(divisor.toBigDecimal(), scale, roundingMode));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is <tt>(this<sup>n</sup>)</tt>,
     * The power is computed exactly, to unlimited precision.
     *
     * <p>The parameter {@code n} must be in the range 0 through 999999999,
     * inclusive.  {@code ZERO.pow(0)} returns {@link
     * #ONE}.
     *
     * Note that future releases may expand the allowable exponent range of this
     * method.
     *
     * @param n power to raise this {@code ExtDecimal} to.
     * @return <tt>this<sup>n</sup></tt>
     * @throws ArithmeticException if {@code n} is out of range.
     */
    public ExtDecimal pow(int n) {
        return new ExtDecimal(content.pow(n));
    }

    /**
     * Returns a {@code ExtDecimal} which is numerically equal to this one but
     * with any trailing zeros removed from the representation. For example,
     * stripping the trailing zeros from the {@code ExtDecimal} value {@code 600.0},
     * which has
     * [{@code BigInteger}, {@code scale}] components equals to [6000, 1],
     * yields {@code 6E2} with [{@code BigInteger},
     * {@code scale}] components equals to [6, -2]
     *
     * @return a numerically equal {@code ExtDecimal} with any trailing zeros
     * removed.
     */
    public ExtDecimal stripTrailingZeros() {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.stripTrailingZeros());
        } else {
            return this;
        }
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this % divisor)}.
     *
     * !!!!Very Bad performance!!!!
     *
     * <p>The remainder is given by
     * {@code this.subtract(this.divideToIntegralValue(divisor).multiply(divisor))}.
     * Note that this is not the modulo operation (the result can be negative).
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @return {@code this % divisor}.
     * @throws ArithmeticException if {@code divisor==0}
     */
    public ExtDecimal remainder(ExtDecimal divisor) {
        // Not yet customized
        return new ExtDecimal(content.remainder(divisor.toBigDecimal()));
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (this modulo divisor)}.
     * Note that this is the modulo operation (the result can not be negative).
     *
     * @param divisor value by which this {@code ExtDecimal} is to be divided.
     * @return {@code this modulo divisor}.
     * @throws ArithmeticException if {@code divisor==0}
     */
    public ExtDecimal modulo(ExtDecimal divisor) {
        // Not yet customized
        ExtDecimal result = remainder(divisor);
        if (result.isNegative()) {
            result = result.add(divisor);
        }
        return result;
    }

    /**
     * Returns if the number is zero
     *
     * @return (@code this < 0)
     */
    public boolean isZero() {
        if (type == Type.NUMBER) {
            return compareTo(ZERO) == 0;
        } else if (type == Type.POSITIVEZERO || type == Type.NEGATIVEZERO) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the number is positive
     *
     * @return (@code this < 0)
     */
    public boolean isPositive() {
        if (type == Type.NUMBER) {
            return compareTo(ZERO) == 1;
        } else if (type == Type.POSITIVEZERO || type == Type.INFINITY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the number is negative
     *
     * @return (@code this < 0)
     */
    public boolean isNegative() {
        if (type == Type.NUMBER) {
            return compareTo(ZERO) == -1;
        } else if (type == Type.NEGATIVEZERO || type == Type.NEGATIVEINFINITY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if the number is even
     *
     * @return {@code this % 2 == 0}
     */
    public boolean isEven() {
        // Not yet customized
        return this.remainder(TWO).compareTo(ZERO) == 0;
    }

    /**
     * Returns if the number is odd.
     *
     * @return {@code this % 2 == 1}
     */
    public boolean isOdd() {
        // Not yet customized
        return this.remainder(TWO).compareTo(ONE) == 0;
    }

    /**
     * Returns the <i>scale</i> of this {@code ExtDecimal}. If zero or positive,
     * the scale is the number of digits to the right of the decimal point. If
     * negative, the unscaled value of the number is multiplied by ten to the
     * power of the negation of the scale. For example, a scale of {@code -3}
     * means the unscaled value is multiplied by 1000.
     *
     * @return the scale of this {@code ExtDecimal}.
     */
    public int scale() {
        if (type == Type.NUMBER) {
            return content.scale();
        } else {
            return 0;
        }
    }

    /**
     * Multiplies the value of this with 2^n
     *
     * 2013-06-21: OK
     *
     * @param n
     * @return {@code this * 2^n}
     */
    public ExtDecimal shiftBinaryExponent(int n) {
        // Not yet customized
        if (n > 0) {
            return this.multiply(TWO.pow(n));
        } else if (n == 0) {
            return this.plus();
        } else {
            return this.divide(TWO.pow(-n));
        }
    }

    /**
     * Multiplies the value of this with 10^n
     *
     * @param n
     * @return {@code this * 10^n}
     * @deprecated Use {@code movePointLeft()}, {@code movePointRight()} or {@code movePoint()()}
     * instead instead
     */
    public ExtDecimal shiftDecimalExponent(int n) {
        if (n > 0) {
            return this.multiply(TEN.pow(n));
        } else if (n == 0) {
            return this.plus();
        } else {
            return this.divide(TEN.pow(-n));
        }
    }

    /**
     * Returns a {@code ExtDecimal} which is equivalent to this one with the
     * decimal point moved {@code n} places to the left. If
     * {@code n} is non-negative, the call merely adds {@code n} to the scale.
     * If {@code n} is negative, the call is equivalent to {@code movePointRight(-n)}.
     * The {@code ExtDecimal} returned by this call has value <tt>(this &times;
     * 10<sup>-n</sup>)</tt> and scale {@code max(this.scale()+n,
     * 0)}.
     *
     * @param n number of places to move the decimal point to the left.
     * @return a {@code ExtDecimal} which is equivalent to this one with the
     * decimal point moved {@code n} places to the left.
     * @throws ArithmeticException if scale overflows.
     */
    public ExtDecimal movePointLeft(int n) {
        // Not yet customized
        return new ExtDecimal(content.movePointLeft(n));
    }

    /**
     * Returns a {@code ExtDecimal} which is equivalent to this one with the
     * decimal point moved {@code n} places to the right. If {@code n} is
     * non-negative, the call merely subtracts
     * {@code n} from the scale. If {@code n} is negative, the call is
     * equivalent to {@code movePointLeft(-n)}. The
     * {@code ExtDecimal} returned by this call has value <tt>(this &times;
     * 10<sup>n</sup>)</tt> and scale {@code max(this.scale()-n,
     * 0)}.
     *
     * @param n number of places to move the decimal point to the right.
     * @return a {@code ExtDecimal} which is equivalent to this one with the
     * decimal point moved {@code n} places to the right.
     * @throws ArithmeticException if scale overflows.
     */
    public ExtDecimal movePointRight(int n) {
        // Not yet customized
        return new ExtDecimal(content.movePointRight(n));
    }

    /**
     * Moves the Point n places to the left. For any negative value n the comma
     * is moved to the right.
     *
     * @param n
     * @return {@code this * 10^n}
     */
    public ExtDecimal movePoint(int n) {
        // Not yet customized
        if (n > 0) {
            return movePointLeft(n);
        } else if (n == 0) {
            return plus();
        } else {
            return movePointRight(-n);
        }
    }

    /**
     * This is the floor function for this.
     *
     * @return {@code floor(this)}
     */
    public ExtDecimal floor() {
        // Not yet customized
        return new ExtDecimal(new BigDecimal(content.toBigInteger()));
    }

    // Means in the correct order
    /**
     * Returns the maximum of this {@code ExtDecimal} and {@code val}.
     *
     * @param val value with which the maximum is to be computed.
     * @return the {@code ExtDecimal} whose value is the greater of this
     *         {@code ExtDecimal} and {@code val}. If they are equal, as defined by the {@link #compareTo(ExtDecimal) compareTo}
     * method, {@code this} is returned.
     */
    public ExtDecimal max(ExtDecimal val) {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.max(val.toBigDecimal()));
        } else {
            if (compareTo(val) == -1) {
                return val;
            } else {
                return this;
            }
        }
    }

    /**
     * Calculates the quadratic mean of {@code this} and {@code val}
     *
     *
     * @param val
     * @return {@code sqrt(1/2(this² + val²)) }
     */
    public ExtDecimal quadraticMean(ExtDecimal val, int scale) {
        return pow(2).arithmeticMean(val.pow(2)).sqrt(scale);
    }

    /**
     * Calculates the arithmetic mean of {@code this} and {@code val}
     *
     * 2013-06-21: OK
     *
     * @param val
     * @return {@code (this + val)/2}
     */
    public ExtDecimal arithmeticMean(ExtDecimal val) {
        return add(val).multiply(new ExtDecimal(0.5));
    }

    /**
     * Calculates the geometric mean of {@code this} and {@code val}
     *
     * 2013-06-21: OK (Bug in sqrt() detected and fixed) Tested 2013-06-21: OK,
     * Reduction to only for numbers >= 0
     *
     * @param val
     * @return {@code sqrt(this * val)}
     */
    public ExtDecimal geometricMean(ExtDecimal val, int scale) {
        if (!isNegative() && !val.isNegative()) {
            return multiply(val).sqrt(scale);
        } else {
            throw new ArithmeticException("Geometric mean of negative numbers");
        }
    }

    /**
     * Calculates the harmonic mean of {@code this} and {@code val}
     *
     * 2013-06-21: Zero test added
     *
     * @param val
     * @return {@code 2/(1/this + 1/val)}
     */
    public ExtDecimal harmonicMean(ExtDecimal val, int scale) {
        if (isPositive() && val.isPositive()) {
            return ExtDecimal.TWO.divide(ExtDecimal.ONE.divide(this, scale).add(ExtDecimal.ONE.divide(val, scale)), scale);
        } else {
            throw new ArithmeticException("Harmonic Mean of negative numbers or zero");
        }
    }

    /**
     * Returns the minimum of this {@code ExtDecimal} and
     * {@code val}.
     *
     * 2013-06-21: OK
     *
     * @param val value with which the minimum is to be computed.
     * @return the {@code ExtDecimal} whose value is the lesser of this {@code ExtDecimal}
     * and {@code val}. If they are equal, as defined by the {@link #compareTo(ExtDecimal) compareTo}
     * method, {@code this} is returned.
     */
    public ExtDecimal min(ExtDecimal val) {
        if (type == Type.NUMBER) {
            return new ExtDecimal(content.min(val.toBigDecimal()));
        } else {
            if (compareTo(val) != -1) {
                return val;
            } else {
                return this;
            }
        }
    }

    // Analytic and inverse functions
    /**
     * Returns an {@code ExtDecimal} whose value is <tt>sqrt(this)</tt>, and
     * whose scale is {@code scale}. If {@code this} is negative it throws an
     * ArithmeticException.
     *
     * Tested 2013-06-21: Bug for sqrt(0) detected an fixed.
     *
     * !!! There are some Exceptions for too large numbers !!!
     *
     * @param scale scale of the {@code ExtDecimal} radix to be returned.
     * @throws ArithmeticException
     * @return {@code sqrt(this)}
     */
    public ExtDecimal sqrt(int scale) {
        if (type == Type.NUMBER) {
            if (this.compareTo(ExtDecimal.ZERO) >= 0) {
                double dblval = this.doubleValue();
                double dblguess = Math.sqrt(dblval);
                //ExtDecimal factor = ExtDecimal.valueOf(1 / (2 * dblguess));
                ExtDecimal half = ExtDecimal.valueOf(0.5);
                ExtDecimal guess = ExtDecimal.valueOf(dblguess);
                if (guess.compareTo(ExtDecimal.ZERO) == 0) {
                    guess = new ExtDecimal(0.00001);
                }
                ExtDecimal newguess = guess.add(ExtDecimal.ONE);
                while (guess.compareTo(newguess) != 0 && !guess.equals(newguess)) {
                    newguess = guess.plus();
                    // guess := guess - (guess^2 - this) * factor
                    // Newton's method
                    //guess = guess.subtract(guess.pow(2).setScale(scale + 2, RoundingMode.HALF_UP).subtract(this).multiply(factor).setScale(scale + 2, RoundingMode.HALF_UP));
                    // Heron's method
                    guess = guess.add(this.divide(guess, scale + 2, RoundingMode.DOWN)).multiply(half).setScale(scale + 2, RoundingMode.DOWN);
                    System.out.println("SQRTSTEP");
                }
                return guess.setScale(scale, RoundingMode.DOWN);
            } else {
                throw new ArithmeticException("sqrt of ".toString());
            }
        } else if (type == Type.POSITIVEZERO) {
            return POSITIVEZERO;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else {
            throw new UnsupportedOperationException("sqrt of ".toString());
        }
    }

//    public int decimalPlaces() {
//        int upperlimit = 1;
//        int lowerlimit = 0;
//        while (compareTo(ExtDecimal.TEN.pow(upperlimit - 1)) >= 0) {
//            upperlimit *= 2;
//        }
//        while (upperlimit != lowerlimit) {
//            if (compareTo(ExtDecimal.TEN.)) {
//            }
//        }
//    }
    /**
     * Returns an {@code ExtDecimal} whose value is <tt>log10(this)</tt>, and
     * whose scale is {@code scale}. If {@code this} is negative or 0 it throws
     * an ArithmeticException.
     *
     * @param scale scale of the {@code ExtDecimal} log10 to be returned.
     * @throws ArithmeticException
     * @return {@code log10(this)}
     */
    public ExtDecimal log10(int scale) {
        // Not yet customized
        if (compareTo(ZERO) < 0) {
            throw new ArithmeticException("Logarithm of a negative number in a real context");
        } else if (compareTo(ZERO) == 0) {
            throw new ArithmeticException("Logarithm of 0");
        } else if (compareTo(ONE) == 0) {
            return ZERO;
        } else if (compareTo(E) == 0) {
            return ONE;
        } else {
            // Verfahren noch unbekannt
            // Arithmetisch-geometrisches Mittel angestrebt.
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    /**
     * Returns an {@code ExtDecimal} whose value is <tt>ln(this)</tt>, and whose
     * scale is {@code scale}. If {@code this} is negative or 0 it throws an
     * ArithmeticException.
     *
     * @param scale scale of the {@code ExtDecimal} ln to be returned.
     * @throws ArithmeticException
     * @return {@code ln(this)}
     */
    public ExtDecimal ln(int scale, RoundingMode rm) {
        if (type == Type.NUMBER) {
            if (compareTo(ZERO) < 0) {
                throw new ArithmeticException("Logarithm of a negative number in a real context");
            } else if (compareTo(ZERO) == 0) {
                throw new ArithmeticException("Logarithm of 0");
            } else if (compareTo(ONE) == 0) {
                return ZERO;
            } else if (compareTo(E) == 0) {
                return ONE;
            } else {
                ExtDecimal sum = ExtDecimal.ZERO;
                int limit = scale;
                for (int i = 0; i <= limit; i++) {
                    ExtDecimal augend = this.dec().divide(this.inc(), scale + 2, RoundingMode.HALF_DOWN).pow(2 * i + 1).divide(ExtDecimal.valueOf(2 * i + 1), scale + 2, RoundingMode.HALF_UP);
                    //System.out.println(augend);
                    sum = sum.add(augend);
                }
                return sum.multiply(ExtDecimal.TWO).setScale(scale, RoundingMode.DOWN);
            }
        } else if (type == Type.POSITIVEZERO) {
            return NEGATIVEINFINITY;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else {
            throw new UnsupportedOperationException("exp of ".toString());
        }
    }

    /**
     * Returns an {@code ExtDecimal} whose value is <tt>exp(this)</tt>, and
     * whose scale is {@code scale}.
     *
     * @param scale scale of the {@code ExtDecimal} ln to be returned.
     * @throws ArithmeticException
     * @return {@code exp(this)}
     */
    public ExtDecimal exp(int scale, RoundingMode rm) {
        if (type == Type.NUMBER) {
            ExtDecimal sum = ExtDecimal.ZERO;
            int limit = 10;
            for (int i = 0; i < limit; i++) {
                ExtDecimal augend = this.pow(i).divide(ExtDecimal.valueOf(i).factorial(), scale, RoundingMode.HALF_DOWN);
                sum = sum.add(augend);
            }
            return sum;
        } else if (type == Type.POSITIVEZERO) {
            return ONE;
        } else if (type == Type.NEGATIVEZERO) {
            return ONE;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else if (type == Type.NEGATIVEINFINITY) {
            return POSITIVEZERO;
        } else {
            throw new UnsupportedOperationException("exp of ".toString());
        }
    }

    /**
     * Returns an {@code ExtDecimal} whose value is <tt>floor(this)!</tt>. If {@code this}
     * is negative it returns 1.
     *
     * @throws ArithmeticException
     * @return {@code this!}
     */
    private ExtDecimal factorial() {
        if (type == Type.NUMBER) {
            if (compareTo(ONE) != 1) {
                return ONE;
            } else {
                return multiply(dec().factorial());
            }
        } else if (type == Type.POSITIVEZERO) {
            return ONE;
        } else if (type == Type.NEGATIVEZERO) {
            return ONE;
        } else if (type == Type.INFINITY) {
            return INFINITY;
        } else {
            throw new UnsupportedOperationException("Factorial of ".toString());
        }
    }

    /**
     * Returns the continued fraction of an ExtDecimal-number.
     *
     * @param d
     * @param max_steps
     * @return [a_1, a_2, ...]
     */
    public static int[] continuedFraction(ExtDecimal d, int max_steps, int EXCEED_FOR_CONTINUED_FRACTION) {
        if (d.type == Type.NUMBER) {
            int[] solution = new int[max_steps];
            int counter = 0;
            while (counter < max_steps) {
                solution[counter] = d.intValue();
                if (solution[counter] == 0 || d.compareTo(ZERO) == 0 || Math.abs(solution[counter]) > EXCEED_FOR_CONTINUED_FRACTION) {
                    solution[counter] = 0;
                    break;
                }
                ExtDecimal difference = d.subtract(ExtDecimal.valueOf(solution[counter]));
                if (difference.compareTo(ZERO) == 0) {
                    break;
                }
                d = ExtDecimal.ONE.divide(difference, max_steps * 2, RoundingMode.HALF_UP);
                counter++;
            }
            return solution;
        } else if (d.type == Type.POSITIVEZERO) {
            int[] zarray = {0};
            return zarray;
        } else if (d.type == Type.NEGATIVEZERO) {
            int[] zarray = {0};
            return zarray;
        } else if (d.type == Type.INFINITY) {
            return new int[0];
        } else if (d.type == Type.NEGATIVEINFINITY) {
            return new int[0];
        } else {
            return new int[0];
        }
    }

    /**
     * The overridden toString-function
     *
     * @return value as string
     */
    @Override
    public String toString() {
        return content.toString();
    }
}
