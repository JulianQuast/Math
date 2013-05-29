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
 * @author Julian C. Quast (c) 2013
 */
public class ExtDecimal {

    // Constants
    /**
     * The value 0, with a scale of 0.
     *
     */
    public static final ExtDecimal ZERO = new ExtDecimal(BigDecimal.ZERO);
    /**
     * The value 1, with a scale of 0.
     *
     */
    public static final ExtDecimal ONE = new ExtDecimal(BigDecimal.ONE);
    /**
     * The value 10, with a scale of 0.
     *
     */
    public static final ExtDecimal TEN = new ExtDecimal(BigDecimal.TEN);
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
     * Contains the number in form of a BigDecimal object.
     *
     */
    private BigDecimal content;

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
        content = val.plus();
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
        return content.plus();
    }

    /**
     * Returns a {@code ExtDecimal} whose value is the absolute value of this {@code ExtDecimal},
     * and whose scale is
     * {@code this.scale()}.
     *
     * @return {@code abs(this)}
     */
    public ExtDecimal abs() {
        return new ExtDecimal(content.abs());
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
        return new ExtDecimal(content.abs(mc));
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
        return new ExtDecimal(content.plus());
    }

    /**
     * Returns a {@code ExtDecimal} whose value is {@code (-this)}, and whose
     * scale is {@code this.scale()}.
     *
     * @return {@code -this}.
     */
    public ExtDecimal negate() {
        return new ExtDecimal(content.negate());
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
        return content.compareTo(val.toBigDecimal());
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
        return new ExtDecimal(content.add(augend.toBigDecimal()));
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
        return new ExtDecimal(content.add(augend.toBigDecimal(), mc));
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
        return new ExtDecimal(content.subtract(subtrahend.toBigDecimal()));
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
        return new ExtDecimal(content.multiply(multiplicand.toBigDecimal()));
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
     * Returns a {@code ExtDecimal} whose value is <tt>(this &times;
     * multiplicand)</tt>, and whose scale is {@code (this.scale() +
     * multiplicand.scale())}.
     *
     * @param multiplicand value to be multiplied by this {@code ExtDecimal}.
     * @return {@code sqrt(this)}
     */
    public ExtDecimal sqrt(int scale) {
        double dblval = this.doubleValue();
        double dblguess = Math.sqrt(dblval);
        ExtDecimal factor = ExtDecimal.valueOf(1 / (2 * dblguess));
        ExtDecimal guess = ExtDecimal.valueOf(dblguess);
        ExtDecimal newguess = guess.add(ExtDecimal.ONE);
        while (guess.compareTo(newguess) != 0 && !guess.equals(newguess)) {
            newguess = guess.plus();
            guess = guess.subtract(guess.pow(2).setScale(scale + 2, RoundingMode.HALF_UP).subtract(this).multiply(factor));
        }
        return guess.setScale(scale, RoundingMode.DOWN);
    }

    /**
     * Returns the continued fraction of an ExtDecimal-number.
     *
     * @param d
     * @param max_steps
     * @return [a_1, a_2, ...]
     */
    public static int[] continuedFraction(ExtDecimal d, int max_steps, int EXCEED_FOR_CONTINUED_FRACTION) {
        int[] solution = new int[max_steps];
        int counter = 0;
        while (counter < max_steps) {
            solution[counter] = d.intValue();
            if (solution[counter] == 0 || d.compareTo(ExtDecimal.ZERO) == 0 || Math.abs(solution[counter]) > EXCEED_FOR_CONTINUED_FRACTION) {
                solution[counter] = 0;
                break;
            }
            ExtDecimal difference = d.subtract(ExtDecimal.valueOf(solution[counter]));
            if (difference.compareTo(ExtDecimal.ZERO) == 0) {
                break;
            }
            d = ExtDecimal.ONE.divide(difference, max_steps * 2, RoundingMode.HALF_UP);
            counter++;
        }
        return solution;
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