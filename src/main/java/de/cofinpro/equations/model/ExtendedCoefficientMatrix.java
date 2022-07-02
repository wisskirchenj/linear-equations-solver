package de.cofinpro.equations.model;

import java.util.stream.IntStream;

/**
 * coefficient matrix of a full system of linear equations, with one extra column for the right side of the equation
 * (the result vector).
 */
public class ExtendedCoefficientMatrix extends Matrix {

    private final int dimension;
    public ExtendedCoefficientMatrix(int dimension) {
        super(dimension, dimension + 1);
        this.dimension = dimension;
    }

    /**
     * get the left-side coefficients a square matrix
     */
    public SquareMatrix getSquareMatrix() {
        SquareMatrix result = new SquareMatrix(dimension);
        for (int i = 0; i < dimension; i++) {
            result.fillRowFrom(i, elements[i]);
        }
        return result;
    }

    public double[] getResultVector() {
        return IntStream.range(0, dimension).mapToDouble(i -> elements[i][dimension]).toArray();
    }

    /**
     * solve the linear eqaution represented by the coefficient matrix - by inverting the square and multiplying
     * if the determinant is zero, an exception is thrown in determinant().
     * @return the solution vector.
     */
    public double[] solve() {
        return getSquareMatrix().invert().multVector(getResultVector());
    }
}
