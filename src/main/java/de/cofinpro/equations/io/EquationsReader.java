package de.cofinpro.equations.io;

import de.cofinpro.equations.model.Complex;
import de.cofinpro.equations.model.ExtendedCoefficientMatrix;

import java.util.Arrays;
import java.util.Scanner;

/**
 * abstract class for reading in systems of linear equations. Derived classes are for file or command lin reading.
 */
public abstract class EquationsReader {

    protected final Scanner scanner;

    protected EquationsReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * read a coefficient matrix line-wise per matrix row.
     * @param variables the number of variables = left side of equations
     * @param equations the number of equations = rows
     * @return hte CoefficientMatrix
     */
    public ExtendedCoefficientMatrix readExtendedCoefficientMatrix(int variables, int equations) {
        ExtendedCoefficientMatrix matrix = new ExtendedCoefficientMatrix(variables, equations);
        for (int i = 0; i < equations; i++) {
            matrix.fillRowFrom(i, readLineIntoComplexNumbers());
        }
        return matrix;
    }

    /**
     * read in an arbitrary length line of complex numbers.
     * @return the complex numbers as array.
     */
    private Complex[] readLineIntoComplexNumbers() {
        return Arrays.stream(scanner.nextLine().split("\\s+")).map(Complex::parseOf).toArray(Complex[]::new);
    }
}
