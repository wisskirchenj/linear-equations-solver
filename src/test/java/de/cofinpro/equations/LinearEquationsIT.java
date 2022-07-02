package de.cofinpro.equations;

import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.EquationsCommandLineReader;
import de.cofinpro.equations.io.ConsolePrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinearEquationsIT {

    @Mock
    Scanner scanner;

    @Spy
    ConsolePrinter printer;

    LinearEquationsController controller;

    @BeforeEach
    void setUp() {
        controller = new LinearEquationsController(printer, new EquationsCommandLineReader(scanner));
    }

    @Test
    void example1_Stage2() {
        when(scanner.nextLine()).thenReturn("4 5 7", "3 9 9");
        controller.run();
        verify(printer).printInfo("0.85714 0.71429");
    }


    @Test
    void example2_Stage2() {
        when(scanner.nextLine()).thenReturn("1 2 3", "4 5 6");
        controller.run();
        verify(printer).printInfo("-1.0 2.0");
    }
}