package de.cofinpro.equations.model;

import java.util.stream.IntStream;

/**
 * coefficient matrix of a system of linear equations, with one extra column for the right side of the equation
 * (the result vector).
 */
public class ExtendedCoefficientMatrix extends Matrix {

    public ExtendedCoefficientMatrix(int variables, int equations) {
        super(equations, variables + 1);
    }

    public Complex[] getResultVector() {
        return IntStream.range(0, columns - 1).mapToObj(i -> elements[i][columns - 1]).toArray(Complex[]::new);
    }

    /**
     * Checks, if a matrix has a row of the form "0 0 ... 0 x" for an x != 0, which cannot have a solution.
     * Note, that the method gives a concise check on a system of lin. equations ony for a matrix in row echelon form.
     * @return the check result
     */
    public boolean hasUnsolvableEquation() {
        for (int i = 0; i < rows; i++) {
            boolean allCoefficientsZero = true;
            for (int j = 0; j < columns - 1; j++) {
                if (!get(i, j).countsAsZero()) {
                    allCoefficientsZero = false;
                    break;
                }
            }
            if (allCoefficientsZero && !get(i, columns - 1).countsAsZero()) {
                return true;
            }
        }
        return false;
    }
}
