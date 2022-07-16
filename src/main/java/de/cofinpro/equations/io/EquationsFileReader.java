package de.cofinpro.equations.io;

import de.cofinpro.equations.model.ExtendedCoefficientMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * File reader class for reading in a system of linear equations, using a scanner to the file given
 * as path as constructor parameter.
 */
public class EquationsFileReader extends EquationsReader {

    /**
     * constructor for the file reader
     * @param inputPath path string to the input data file.
     * @throws IOException if the file can't be read (e.g. wrong path).
     */
    public EquationsFileReader(String inputPath) throws IOException {
        super(new Scanner(Path.of(inputPath)));
    }

    /**
     * The method assumes, that the first line in the file contains exactly the dimension number
     * as is specified for stage 3.
     * @return the ExtendedCoefficientMatrix representing the system of linear equations stored in the file.
     */
    public ExtendedCoefficientMatrix readExtendedCoefficientMatrix() {
        int variables = scanner.nextInt();
        int equations = scanner.nextInt();
        scanner.nextLine();
        return readExtendedCoefficientMatrix(variables, equations);
    }
}
