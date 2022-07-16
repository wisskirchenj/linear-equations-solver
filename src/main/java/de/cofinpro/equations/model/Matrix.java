package de.cofinpro.equations.model;

import lombok.Getter;

import java.util.stream.IntStream;

/**
 * Matrix base class providing some basic operations to fill, multiply, etc..
 */
@Getter
public class Matrix {

    protected final int rows;
    protected final int columns;
    protected final double[][] elements;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new double[rows][columns];
    }

    public double get(int row, int col) {
        return elements[row][col];
    }

    /**
     * fill a matrix row form a given vector
     * @param rowIndex index of the row to fill
     * @param rowData vector to fill into row
     */
    public void fillRowFrom(int rowIndex, double[] rowData) {
        System.arraycopy(rowData, 0, elements[rowIndex], 0, columns);
    }

    /**
     * scales a row with the inverse of the factor given
     * @param row the row index
     * @param inverseFactor the factor, which is inverted and scaled with
     */
    public void scaleRowInverse(int row, double inverseFactor) {
        IntStream.range(0, columns).forEach(i -> elements[row][i] /= inverseFactor);
    }

    /**
     * elementary row operation of adding a multiple of a line to another
     * @param factor scale factor for row to add
     * @param rowToAdd row index of row to add
     * @param targetRow row index of row which is replaced by result
     */
    public void addScaledRowToRow(double factor, int rowToAdd, int targetRow) {
        IntStream.range(0, columns).forEach(i -> elements[targetRow][i] =
                Math.fma(factor, elements[rowToAdd][i], elements[targetRow][i]));
    }

    public void swapRows(int i, int j) {
        double[] temp = elements[i].clone();
        fillRowFrom(i, elements[j]);
        fillRowFrom(j, temp);
    }

    /**
     * determines the rank - works only for a matrix in echelon form.
     * @return the rank for the matrix iff it is in echelon form
     */
    protected int rankForEchelonForm() {
        int rank = 0;
        for (int i = 0; i < rows; i++) {
            if (isNotZeroRow(i)) {
                rank++;
            }
        }
        return rank;
    }

    /**
     * checks, if a row is not constantly zero in a double precision range
     * @param row row index
     * @return true if the row is NOT completely zero in a double precision range
     */
    private boolean isNotZeroRow(int row) {
        for (double el: elements[row]) {
            if (notPrecisionCorrectedZero(el)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks, if a double is NOT smaller than a precision error - thus to be regarded as non-zero
     * @param decimal given double
     * @return true if the element is NOT zero in the precision
     */
    protected boolean notPrecisionCorrectedZero(double decimal) {
        return Math.abs(decimal) > 1e-08;
    }
}
