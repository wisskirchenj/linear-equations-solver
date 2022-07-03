package de.cofinpro.equations.controller;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import de.cofinpro.equations.io.FilePrinter;
import de.cofinpro.equations.model.ExtendedCoefficientMatrix;
import de.cofinpro.equations.model.GaussAlgorithm;

import static de.cofinpro.equations.config.PropertyManager.OUTPUT_FILE_OPTION;

/**
 * Controller class that reads the coefficients, solves the equation and prints the result.
 */
public class LinearEquationsController {

    private final ConsolePrinter printer;
    private final EquationsFileReader equationsFileReader;

    public LinearEquationsController(ConsolePrinter consolePrinter, EquationsFileReader equationsFileReader) {
        this.printer = consolePrinter;
        this.equationsFileReader = equationsFileReader;
    }

    public void run() {
        ExtendedCoefficientMatrix extendedMatrix = equationsFileReader.readExtendedCoefficientMatrix();
        new GaussAlgorithm(printer).apply(extendedMatrix);
        new FilePrinter(PropertyManager.getProperty(OUTPUT_FILE_OPTION)).printVector(extendedMatrix.getResultVector());
    }
}
