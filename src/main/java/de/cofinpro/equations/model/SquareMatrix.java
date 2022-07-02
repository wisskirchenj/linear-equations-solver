package de.cofinpro.equations.model;

/**
 * Square Matrix base class with special square functionality as determinant and inverses
 * (only partially implemented yet).
 */
public class SquareMatrix extends Matrix {

    private final int dimension;

    public SquareMatrix(int dimension) {
        super(dimension, dimension);
        this.dimension = dimension;
    }

    public double determinant() {
        if (dimension > 2) {
            throw new UnsupportedOperationException("determinant only implemented for dimension < 3 yet.");
        }
        return elements[0][0] * elements[1][1] - elements[0][1] * elements[1][0];
    }

    public SquareMatrix invert() {
        SquareMatrix inverse = new SquareMatrix(dimension);
        double det = determinant();
        if (det == 0) {
            throw new IllegalArgumentException("Attempt to invert a singular matrix!");
        }
        inverse.elements[0][0] = elements[1][1] / det;
        inverse.elements[1][1] = elements[0][0] / det;
        inverse.elements[1][0] = -elements[1][0] / det;
        inverse.elements[0][1] = -elements[0][1] / det;
        return inverse;
    }
}
