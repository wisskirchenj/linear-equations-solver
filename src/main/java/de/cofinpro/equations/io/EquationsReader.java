package de.cofinpro.equations.io;

import de.cofinpro.equations.model.ExtendedCoefficientMatrix;

import java.util.Arrays;
import java.util.Scanner;

public abstract class EquationsReader {

    protected final Scanner scanner;

    protected EquationsReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * read a coefficient matrix line-wise per matrix row.
     * @param dimension the number of rows
     * @return hte CoefficientMatrix
     */
    public ExtendedCoefficientMatrix readExtendedCoefficientMatrix(int dimension) {
        ExtendedCoefficientMatrix matrix = new ExtendedCoefficientMatrix(dimension);
        for (int i = 0; i < dimension; i++) {
            matrix.fillRowFrom(i, readLineIntoDoubles());
        }
        return matrix;
    }

    /**
     * read in an arbitrary length line of doubles.
     * @return the doubles as array.
     */
    private double[] readLineIntoDoubles() {
        return Arrays.stream(scanner.nextLine().split("\\s+")).mapToDouble(Double::parseDouble).toArray();
    }
}
