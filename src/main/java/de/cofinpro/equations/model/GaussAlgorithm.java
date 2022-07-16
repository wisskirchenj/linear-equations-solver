package de.cofinpro.equations.model;

import de.cofinpro.equations.io.ConsolePrinter;

/**
 * class implementing the Gauss algortihm. It offers a method apply to apply it to an extended matrix of arbitrary dimension.
 */
public class GaussAlgorithm {

    private final ConsolePrinter printer;

    public GaussAlgorithm(ConsolePrinter printer) {
        this.printer = printer;
    }

    public void apply(ExtendedCoefficientMatrix extendedMatrix) {
        printer.printInfo("Starting the Gauss algorithm");
        setToReducedEchelon(extendedMatrix);
        backSolve(extendedMatrix);
        printer.printInfo("Finishing the Gauss algorithm");
    }


    /**
     * as 2nd part of Gauss Algorithm backsolve the solution variables.
     * @param matrix the extended matrix to work on
     */
    private void backSolve(ExtendedCoefficientMatrix matrix) {
        for (int col = matrix.getColumns() - 2; col > 0; col--) {
            for (int row = col - 1; row >= 0 ; row--) {
                if (col < matrix.getRows() && matrix.get(row, col) != 0 && matrix.get(col, col) != 0) {
                    printer.printInfo("%.6f * R%d + R%d -> R%d".formatted(-matrix.get(row, col), col, row, row));
                    matrix.addScaledRowToRow(-matrix.get(row, col), col, row);
                }
            }
        }
    }

    /**
     * as 1st part of Gauss Algorithm transform matrix to reduced echelon.
     * @param matrix the extended matrix to work on
     */
    private void setToReducedEchelon(ExtendedCoefficientMatrix matrix) {
        for (int col = 0; col < Math.min(matrix.getColumns() - 1, matrix.getRows()); col++) {
            setPivotOne(col, matrix);
            for (int row = col + 1; row < matrix.getRows(); row++) {
                if (matrix.notPrecisionCorrectedZero(matrix.get(row, col))) {
                    printer.printInfo("%.6f * R%d + R%d -> R%d".formatted(-matrix.get(row, col), col, row, row));
                    matrix.addScaledRowToRow(-matrix.get(row, col), col, row);
                }
            }
        }
    }

    /**
     * scales the pivot of the diagonal element (col, col) to 1. If the row has a 0 at the element to scale,
     * the algorithm searches for a row below with a non zero element in the position and swaps the rows.
     * @param col the row = column whose diagonal element is to be scaled to one.
     * @param matrix the extended matrix to operate on.
     * @return true if a pivot was found and scaled to 1 for this column, false if all were 0
     */
    private boolean setPivotOne(int col, ExtendedCoefficientMatrix matrix) {
        int row = col;
        while (row < matrix.getRows() && matrix.get(row, col) == 0) {
            row++;
        }
        if (row == matrix.getRows()) {
            return false;
        }
        if (col != row) {
            printer.printInfo("swapping R%d <-> R%d".formatted(row, col));
            matrix.swapRows(row, col);
        }
        if (matrix.notPrecisionCorrectedZero(1 - matrix.get(col, col))) {
            printer.printInfo("1/%.6f * R%d -> R%d".formatted(matrix.get(col, col), col, col));
            matrix.scaleRowInverse(col, matrix.get(col, col));
        }
        return true;
    }
}
