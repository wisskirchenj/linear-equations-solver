package de.cofinpro.equations.io;


import de.cofinpro.equations.model.Complex;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class to print info to an output file, whose path is given as constructor parameter.
 */
@Slf4j
public class FilePrinter {

    private final String outputPath;

    public FilePrinter(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * print a vector's components line-wise to a newly created output file - as only content.
     * @param vector  the given vector.
     */
    public void printVector(Complex[] vector) {
        writeToFile(String.join("\n", Arrays.stream(vector)
                .map(Complex::toString)
                .toList()));
    }

    private void writeToFile(String textToWrite) {
        try (FileWriter writer = new FileWriter(outputPath)) {
            writer.write(textToWrite);
        } catch (IOException e) {
            log.error("output path " + outputPath + " given is not a valid output file path.");
        }
    }

    public void printMessage(String message) {
        writeToFile(message);
    }
}
