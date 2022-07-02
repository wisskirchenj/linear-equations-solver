package de.cofinpro.equations.controller;

import de.cofinpro.equations.io.EquationsCommandLineReader;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.model.ExtendedCoefficientMatrix;

/**
 * Controller class that reads the coefficients, solves the equation and prints the result.
 */
public class LinearEquationsController {

    private final ConsolePrinter printer;
    private final EquationsCommandLineReader equationsCommandLineReader;
    public LinearEquationsController(ConsolePrinter consolePrinter, EquationsCommandLineReader equationsCommandLineReader) {
        this.printer = consolePrinter;
        this.equationsCommandLineReader = equationsCommandLineReader;
    }

    public void run() {
        ExtendedCoefficientMatrix extendedMatrix = equationsCommandLineReader.readExtendedCoefficientMatrix(2);
        double[] solution = extendedMatrix.solve();
        printer.printVector(solution);
    }
}
