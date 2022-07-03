package de.cofinpro.equations;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LinearEquationsIT {

    private static final String INPUT_PATH = "src/test/resources/example2_Stage2.txt";
    private static final String OUTPUT_PATH = "src/test/resources/out.txt";

    @Spy
    ConsolePrinter printer;

    LinearEquationsController controller;

    @Test
    void example2_Stage2FromFile() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader(INPUT_PATH));
        controller.run();
        assertEquals("-1.0\n2.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }
}