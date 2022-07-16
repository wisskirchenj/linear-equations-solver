package de.cofinpro.equations.model;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.io.FilePrinter;

import static de.cofinpro.equations.config.PropertyManager.INFINITE_SOLUTIONS_LABEL;
import static de.cofinpro.equations.config.PropertyManager.NO_SOLUTIONS_LABEL;

/**
 * class that is able to analyze a matrix given in echelon form. Each instance serves for analyzing one matrix
 * given via constructor argument (could be optimized using factory - but it's a small class and not many instances used...)
 * the analyze result - i.e. the solution(s) to the represented system of linear equations is saved to the specified output file.
 */
public class EchelonFormAnalyzer {

    private final ExtendedCoefficientMatrix matrix;
    public EchelonFormAnalyzer(ExtendedCoefficientMatrix matrixInEchelonForm) {
        this.matrix = matrixInEchelonForm;
    }

    /**
     * saves the result vector, resp. a message on no or infinite solutions for the matrix in extended echelon form
     * given as constructor parameter.
     * @param outputPath the output file path
     */
    public void saveResult(String outputPath) {
        FilePrinter filePrinter = new FilePrinter(outputPath);
        if (matrix.hasUnsolvableEquation()) {
            filePrinter.printMessage(PropertyManager.getProperty(NO_SOLUTIONS_LABEL));
        } else if (matrix.rankForEchelonForm() < matrix.getColumns() - 1) {
            filePrinter.printMessage(PropertyManager.getProperty(INFINITE_SOLUTIONS_LABEL));
        } else {
            filePrinter.printVector(matrix.getResultVector());
        }
    }
}
