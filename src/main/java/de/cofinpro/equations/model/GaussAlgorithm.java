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
        for (int col = matrix.getDimension() - 1; col > 0; col--) {
            for (int row = col - 1; row >= 0 ; row--) {
                if (matrix.get(row, col) != 0) {
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
        for (int col = 0; col < matrix.getDimension(); col++) {
            setPivotOne(col, matrix);
            for (int row = col + 1; row < matrix.getDimension(); row++) {
                printer.printInfo("%.6f * R%d + R%d -> R%d".formatted(-matrix.get(row, col), col, row, row));
                matrix.addScaledRowToRow(-matrix.get(row, col), col, row);
            }
        }
    }

    /**
     * scales the pivot of the diagonal element (col, col) to 1. If the row has a 0 at the element to scale,
     * the algortihm searches for a row below with a non zeor element in the position and swaps the rows.
     * @param col the row = column whose diagaonal element is toi be scaled to one.
     * @param matrix the extended matrix to operate on.
     * @throws UnsupportedOperationException if the square matrix of the extended matrix is not regular.
     */
    private void setPivotOne(int col, ExtendedCoefficientMatrix matrix) {
        int row = col;
        while (matrix.get(row, col) == 0 && row < matrix.getDimension()) {
            row++;
        }
        if (row == matrix.getDimension()) {
            throw new UnsupportedOperationException("singular coefficient matrix not supported yet.");
        }
        if (col != row) {
            printer.printInfo("swapping R%d <-> R%d".formatted(row, col));
            matrix.swapRows(row, col);
        }
        printer.printInfo("1/%.6f * R%d -> R%d".formatted(matrix.get(col, col), col, col));
        matrix.scaleRowInverse(col, matrix.get(col, col));
    }
}
