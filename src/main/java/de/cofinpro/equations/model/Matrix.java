package de.cofinpro.equations.model;

import java.util.InputMismatchException;
import java.util.stream.IntStream;

/**
 * Matrix base class providing some basic operations to fill, multiply, etc..
 */
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

    protected double[] multVector(double[] vector) {
        if (vector.length != columns) {
            throw new InputMismatchException("Attempt to multiply unsuitable matrix and vector.");
        }
        double[] result = new double[rows];
        for (int i = 0; i < rows; i++) {
            result[i] = rowTimesVector(elements[i], vector);
        }
        return result;
    }

    private double rowTimesVector(double[] row, double[] vector) {
        return IntStream.range(0, row.length).mapToDouble(i -> row[i] * vector[i]).sum();
    }

    public void scaleRowInverse(int row, double inverseFactor) {
        IntStream.range(0, columns).forEach(i -> elements[row][i] /= inverseFactor);
    }

    public void addScaledRowToRow(double factor, int rowToAdd, int targetRow) {
        IntStream.range(0, columns).forEach(i -> elements[targetRow][i] =
                Math.fma(factor, elements[rowToAdd][i], elements[targetRow][i]));
    }

    public void swapRows(int i, int j) {
        double[] temp = elements[i].clone();
        fillRowFrom(i, elements[j]);
        fillRowFrom(j, temp);
    }
}
