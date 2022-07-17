package de.cofinpro.equations.io;


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
    public void printVector(double[] vector) {
        writeToFile(String.join("\n", Arrays.stream(vector)
                .map(d -> Math.rint(d * 1e8) / 1e8)
                .map(d -> {
                    if (d == -0) return 0;
                    return d;
                }).mapToObj(String::valueOf)
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
