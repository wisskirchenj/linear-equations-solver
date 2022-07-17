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
    protected final Complex[][] elements;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.elements = new Complex[rows][columns];
    }

    public Complex get(int row, int col) {
        return elements[row][col];
    }

    /**
     * fill a matrix row form a given vector
     * @param rowIndex index of the row to fill
     * @param rowData vector to fill into row
     */
    public void fillRowFrom(int rowIndex, Complex[] rowData) {
        System.arraycopy(rowData, 0, elements[rowIndex], 0, columns);
    }

    /**
     * scales a row with the inverse of the factor given
     * @param row the row index
     * @param inverseFactor the factor, which is inverted and scaled with
     */
    public void scaleRowInverse(int row, Complex inverseFactor) {
        IntStream.range(0, columns).forEach(i -> elements[row][i] = elements[row][i].dividedBy(inverseFactor));
    }

    /**
     * elementary row operation of adding a multiple of a line to another
     * @param factor scale factor for row to add
     * @param rowToAdd row index of row to add
     * @param targetRow row index of row which is replaced by result
     */
    public void addScaledRowToRow(Complex factor, int rowToAdd, int targetRow) {
        IntStream.range(0, columns).forEach(i -> elements[targetRow][i] =
                factor.times(elements[rowToAdd][i]).plus(elements[targetRow][i]));
    }

    public void swapRows(int i, int j) {
        Complex[] temp = elements[i].clone();
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
     * checks, if a row is not constantly zero in a precision range
     * @param row row index
     * @return true if the row is NOT completely zero in a precision range
     */
    private boolean isNotZeroRow(int row) {
        for (Complex el: elements[row]) {
            if (!el.countsAsZero()) {
                return true;
            }
        }
        return false;
    }
}
