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
}
