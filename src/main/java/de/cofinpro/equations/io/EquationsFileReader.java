package de.cofinpro.equations.io;

import de.cofinpro.equations.model.ExtendedCoefficientMatrix;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class EquationsFileReader extends EquationsReader {

    public EquationsFileReader(String inputPath) throws IOException {
        super(new Scanner(Path.of(inputPath)));
    }

    public ExtendedCoefficientMatrix readExtendedCoefficientMatrix() {
        int dimension = Integer.parseInt(scanner.nextLine());
        return readExtendedCoefficientMatrix(dimension);
    }
}
