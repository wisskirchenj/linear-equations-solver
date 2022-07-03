package de.cofinpro.equations;

import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LinearEquationsIT {

    private static final String inputPath = "src/test/resources/example2_Stage2.txt";

    @Spy
    ConsolePrinter printer;

    LinearEquationsController controller;

    @Test
    void example2_Stage2FromFile() throws IOException {
        controller = new LinearEquationsController(printer,  new EquationsFileReader(inputPath));
        controller.run();
        verify(printer).printInfo("-1.0 2.0");
    }
}