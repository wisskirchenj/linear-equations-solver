package de.cofinpro.equations.io;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Slf4j wrapper class to print results or errors to console.
 */
@Slf4j
public class ConsolePrinter {

    public void printError(String errorMessage) {
        log.error(errorMessage);
    }

    public void printInfo(String message) {
        log.info(message);
    }

    /**
     * printa vector of doubles as one line, rounding to max. 5 digits after the decimal point.
     * @param vector the vector input
     */
    public void printVector(double[] vector) {
        StringBuilder builder = new StringBuilder();
        Arrays.stream(vector)
                .map(d -> Math.rint(d * 1e6) / 1e6)
                .mapToObj(String::valueOf)
                .forEach(string -> builder.append(string).append(" "));
        builder.deleteCharAt(builder.length() - 1);
        printInfo(builder.toString());
    }
}
