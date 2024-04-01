package test;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RationalTest {


    public static Stream<Arguments> provideRationalForAddition() {
        return Stream.of (
                Arguments.of ( new Rational ( 1, 2 ), new Rational ( 1, 2 ), new Rational ( 1, 1 ) ),
                Arguments.of ( new Rational ( 1, 3 ), new Rational ( 2, 3 ), new Rational ( 1, 1 ) ),
                Arguments.of ( new Rational ( 5, 6 ), new Rational ( 1, 6 ), new Rational ( 1, 1 ) ),
                Arguments.of ( new Rational ( 7, 8 ), new Rational ( 1, 8 ), new Rational ( 1, 1 ) ),
                Arguments.of ( new Rational ( 9, 10 ), new Rational ( 1, 10 ), new Rational ( 1, 1 ) )
        );
    }

    private static Stream<Arguments> provideRationalsForSubtraction() {
        return Stream.of(
                Arguments.of(new Rational(1, 2), new Rational(1, 4), new Rational(1, 4)),
                Arguments.of(new Rational(2, 3), new Rational(1, 3), new Rational(1, 3)),
                Arguments.of(new Rational(5, 6), new Rational(1, 6), new Rational(2, 3)),
                Arguments.of(new Rational(7, 8), new Rational(1, 8), new Rational(3, 4)),
                Arguments.of(new Rational(9, 10), new Rational(1, 10), new Rational(4, 5))
        );
    }

    private static Stream<Arguments> provideRationalsForDivision() {
        return Stream.of(
                Arguments.of(new Rational(1, 2), new Rational(1, 2), new Rational(1, 1)),
                Arguments.of(new Rational(2, 3), new Rational(1, 3), new Rational(2, 1)),
                Arguments.of(new Rational(5, 6), new Rational(1, 6), new Rational(5, 1)),
                Arguments.of(new Rational(7, 8), new Rational(1, 8), new Rational(7, 1)),
                Arguments.of(new Rational(9, 10), new Rational(1, 10), new Rational(9, 1))
        );
    }

    private static Stream<Arguments> provideRationalsForMultiplication() {
        return Stream.of(
                Arguments.of(new Rational(1, 2), new Rational(1, 2), new Rational(1, 4)),
                Arguments.of(new Rational(2, 3), new Rational(1, 3), new Rational(2, 9)),
                Arguments.of(new Rational(5, 6), new Rational(1, 6), new Rational(5, 36)),
                Arguments.of(new Rational(7, 8), new Rational(1, 8), new Rational(7, 64)),
                Arguments.of(new Rational(9, 10), new Rational(1, 10), new Rational(9, 100))
        );
    }

    private static Stream<Arguments> provideRationalsForMixedNumber() {
        return Stream.of(
                Arguments.of(new Rational(3, 2), "1+1/2"),
                Arguments.of(new Rational(5, 2), "2+1/2"),
                Arguments.of(new Rational(7, 3), "2+1/3"),
                Arguments.of(new Rational(8, 3), "2+2/3"),
                Arguments.of(new Rational(11, 4), "2+3/4")
        );
    }

    private static Stream<Arguments> provideRationalsForFloatingPoint() {
        return Stream.of(
                Arguments.of(new Rational(1, 2), 0.5),
                Arguments.of(new Rational(2, 3), 0.6666666666666666),
                Arguments.of(new Rational(5, 6), 0.8333333333333334),
                Arguments.of(new Rational(7, 8), 0.875),
                Arguments.of(new Rational(9, 10), 0.9)
        );
    }



    // This test checks the addition of two Rational numbers.
    @ParameterizedTest
    @MethodSource("provideRationalForAddition")
    @DisplayName("Adding result for (Rational r1, Rational r2, Rational expected)")
    public void testAdd(Rational r1, Rational r2, Rational expected) {
        Rational result = r1.add(r2);
        assertEquals(expected, result, () -> r1 + " + " + r2 + " should return : " + expected);
    }

    // This test checks the subtraction of two Rational numbers.
    @ParameterizedTest
    @MethodSource("provideRationalsForSubtraction")
    @DisplayName("Subtracting result for (Rational r1, Rational r2, Rational expected)")
    public void testSubtract(Rational r1, Rational r2, Rational expected) {
        Rational result = r1.sub(r2);
        assertEquals(expected, result, () -> r1 + " - " + r2 + " should return : " + expected);
    }

    // This test checks the multiplication of two Rational numbers.
    @ParameterizedTest
    @MethodSource("provideRationalsForMultiplication")
    @DisplayName("Multiplying result for (Rational r1, Rational r2, Rational expected)")
    public void testMul(Rational r1, Rational r2, Rational expected) {
        Rational result = r1.mul(r2);
        assertEquals(expected, result, () -> r1 + " * " + r2 + " should return : " + expected);
    }

    // This test checks the division of two Rational numbers.
    @ParameterizedTest
    @MethodSource("provideRationalsForDivision")
    @DisplayName("Dividing result for (Rational r1, Rational r2, Rational expected)")
    public void testDiv(Rational r1, Rational r2, Rational expected) {
        Rational result = r1.div(r2);
        assertEquals(expected, result, () -> r1 + " / " + r2 + " should return : " + expected);
    }

    // This test checks the conversion of a Rational number to a floating point number.
    @ParameterizedTest
    @MethodSource("provideRationalsForFloatingPoint")
    @DisplayName("Floating point result for (Rational r, double expected)")
    public void testToFloatingPoint(Rational r, double expected) {
        double result = r.toFloatingPoint(r);
        assertEquals(expected, result, 0.0001, () -> r + " to floating point should return : " + expected);
    }

    // This test checks the conversion of a Rational number to a mixed number string.
    @ParameterizedTest
    @MethodSource("provideRationalsForMixedNumber")
    @DisplayName("Mixed number result for (Rational r, String expected)")
    public void testMixedNumber(Rational r, String expected) {
        String result = r.mixedNumber(r);
        assertEquals(expected, result, () -> r + " to mixed number should return : " + expected);
    }
}




