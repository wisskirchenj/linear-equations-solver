package de.cofinpro.equations;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static de.cofinpro.equations.config.PropertyManager.INPUT_FILE_OPTION;

@Slf4j
public class Main {

    public static void main(String[] args) throws IOException {
        PropertyManager.processProperties(args);
        log.trace(PropertyManager.getProperties().toString());
        new LinearEquationsController(new ConsolePrinter(),
                new EquationsFileReader(PropertyManager.getProperty(INPUT_FILE_OPTION))).run();
    }
}
