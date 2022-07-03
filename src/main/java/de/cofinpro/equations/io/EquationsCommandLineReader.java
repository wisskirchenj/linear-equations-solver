package de.cofinpro.equations.io;

import java.util.Scanner;

/**
 * Command Line reader class using a stdin scanner. Just wraps the abstract EquationsReader, that does all the reading.
 */
public class EquationsCommandLineReader extends EquationsReader {

    public EquationsCommandLineReader(Scanner scanner) {
        super(scanner);
    }
}
