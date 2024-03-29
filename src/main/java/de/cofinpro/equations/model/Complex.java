package de.cofinpro.equations.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * immutable class representing a complex number
 * @param real the real part
 * @param imaginary the imaginary part
 */
public record Complex(double real, double imaginary) {

    private static final Pattern REAL_AND_IMAGINARY_PATTERN = Pattern.compile("(.*\\d)([+-].*)i");
    private static final Pattern IMAGINARY_PART_PATTERN = Pattern.compile("(.*)i");
    public static final Complex ONE = new Complex(1);

    public Complex(double real) {
        this(real, 0);
    }

    /**
     * tries to parse a given string into a complex number (e.g.: "-2.513+i" or "-2i" or "1").
     * @param input the string input to parse
     * @return the complex number constructed from the parsing result
     * @throws NumberFormatException if the input is not a vlaid complex string representation.
     */
    public static Complex parseOf(String input) {
        Matcher matcher = REAL_AND_IMAGINARY_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Complex(Double.parseDouble(matcher.group(1)), parseImaginary(matcher.group(2)));
        }
        matcher = IMAGINARY_PART_PATTERN.matcher(input);
        if (matcher.matches()) {
            return new Complex(0, parseImaginary(matcher.group(1)));
        }
        return new Complex(Double.parseDouble(input));
    }

    /**
     * parse only thew imaginary part string (called without the "i").
     * @param input string to parse - without "i" at the end
     * @return the imaginary part (double)
     */
    private static double parseImaginary(String input) {
        if (input.equals("") || input.equals("+")) {
            return 1;
        }
        if (input.equals("-")) {
            return -1;
        }
        return Double.parseDouble(input);
    }

    /**
     * rounding errors can produce near 0 numbers, which are checked for here
     * @return true, if the norm square is smaller than 1e-16
     */
    public boolean countsAsZero() {
        return normSquare() < 1e-16;
    }

    public Complex plus(Complex other) {
        return new Complex(real + other.real, imaginary + other.imaginary);
    }

    public Complex minus(Complex other) {
        return new Complex(real - other.real, imaginary - other.imaginary);
    }

    public Complex times(Complex other) {
        return new Complex(real * other.real - imaginary * other.imaginary,
                real * other.imaginary + imaginary * other.real);
    }

    public Complex negate() {
        return new Complex(-real, -imaginary);
    }

    public double normSquare() {
        return real * real + imaginary * imaginary;
    }

    public Complex conjugate() {
        if (imaginary == 0) {
            return this;
        }
        return new Complex(real, -imaginary);
    }

    public Complex dividedBy(Complex other) {
        return new Complex((real * other.real + imaginary * other.imaginary) / other.normSquare(),
                (imaginary * other.real - real * other.imaginary) / other.normSquare());
    }

    @Override
    public String toString() {
        return realToString() + imaginaryToString();
    }

    private String realToString() {
        if (outputsToZero(real) && !outputsToZero(imaginary)) {
            return "";
        }
        double rounded = Math.rint(real * 1e8) / 1e8;
        if (rounded == -0) {
            rounded = 0;
        }
        return String.valueOf(rounded);
    }

    private String imaginaryToString() {
        if (outputsToZero(imaginary)) {
            return "";
        }
        double rounded = Math.rint(imaginary * 1e8) / 1e8;
        if (outputsToZero(imaginary - 1)) {
            return outputsToZero(real) ? "i" : "+i";
        }
        if (outputsToZero(imaginary + 1)) {
            return "-i";
        }
        if (rounded > 0) {
            return outputsToZero(real) ? "%si".formatted(String.valueOf(rounded)) : "+%si".formatted(String.valueOf(rounded));
        }
        return "%si".formatted(String.valueOf(rounded));
    }

    private boolean outputsToZero(double part) {
        return Math.abs(part) < 1e-08;
    }
}
