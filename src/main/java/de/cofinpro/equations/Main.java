package de.cofinpro.equations;

import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.EquationsCommandLineReader;
import de.cofinpro.equations.io.ConsolePrinter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new LinearEquationsController(new ConsolePrinter(),
                new EquationsCommandLineReader(new Scanner(System.in))).run();
    }
}
