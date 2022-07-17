package de.cofinpro.equations.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ComplexTest {

    @Test
    void multiplicationTest_fromExercises() {
        Complex result = new Complex(15, -16).times(new Complex(-2, -8));
        assertEquals(-158, result.real());
        assertEquals(-88, result.imaginary());
        System.out.println(result);
    }


    @Test
    void squareTest_fromExercises() {
        Complex number = new Complex(7, -4);
        Complex square = number.times(number);
        System.out.println(square);
        assertEquals(33, square.real());
        assertEquals(-56, square.imaginary());
        number = new Complex(8, -13);
        square = number.times(number);
        System.out.println(square);
        assertEquals(-105, square.real());
        assertEquals(-208, square.imaginary());
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            real,    imaginary,   toString
            1.0,     2.30,        1.0+2.3i
            -0.0,    -0.0,        0.0
            -1,      0,           -1.0
            23,      0,           23.0
            0,       2.3,         2.3i
            0,       -1.0,        -i
            0,       1,           i
            1,       1,           1.0+i
            -1.0,    -1,          -1.0-i
            3,       1e-12,       3.0
            1e-11,   2,           2.0i
            1e-10,   -1e-10,      0.0
            -2.2,    -12.456700,  -2.2-12.4567i
            """)
    void toStringTest(double real, double imaginary, String expected) {
        assertEquals(expected, new Complex(real, imaginary).toString());
    }


    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            real,    imaginary,   toString
            1.0,     2.30,        1.0+2.3i
            0.0,     0.0,         0.0
            -1,      0,           -1.0
            23,      0,           23.0
            0,       2.3,         2.3i
            0,       -1.0,        -i
            0,       1,           i
            1,       1,           1.0+i
            -1.0,    -1,          -1.0-i
            3,       0,           3.0
            0,       2,           2.0i
            0,       0,           0.0
            -2.2,    -12.456700,  -2.2-12.4567i
            """)
    void parseOf(double expectedReal, double expectedImaginary, String parseInput) {
        assertEquals(expectedReal, Complex.parseOf(parseInput).real());
        assertEquals(expectedImaginary, Complex.parseOf(parseInput).imaginary());
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
        real, imaginary
        1.0,  -1
        -1.0, +1
        0,    2
        0,    -2
        1,    0
        0,    0
        """)
    void conjugateTest(double real, double imaginary) {
        Complex c = new Complex(real, imaginary).conjugate();
        assertTrue(-imaginary == c.imaginary()); // do not simplify - test fails...
        assertEquals(real, c.real());
    }

    @Test
    void additionTest() {
        Complex result = new Complex(15, -16).plus(new Complex(-2, -8));
        assertEquals(13, result.real());
        assertEquals(-24, result.imaginary());
        System.out.println(result);
    }

    @Test
    void subtractionTest() {
        Complex result = new Complex(15, -16).minus(new Complex(-2, -8));
        assertEquals(17, result.real());
        assertEquals(-8, result.imaginary());
        System.out.println(result);
    }


    @Test
    void divisionTest() {
        Complex divisor = new Complex(-2, -1);
        Complex result = new Complex(15, -16).dividedBy(divisor);
        assertEquals(-2.8, result.real());
        assertEquals(9.4, result.imaginary());
        System.out.println(result);
        assertEquals(15, result.times(divisor).real());
        assertEquals(-16, result.times(divisor).imaginary());
    }
}