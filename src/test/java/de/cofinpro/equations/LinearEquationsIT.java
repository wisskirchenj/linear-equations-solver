package de.cofinpro.equations;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinearEquationsIT {

    private static final String INPUT_PATH = "src/test/resources/example2_Stage2.txt";
    private static final String INPUT_PATH3 = "src/test/resources/example_stage3.txt";
    private static final String INPUT_PATH4 = "src/test/resources/ex_stage4.txt";
    private static final String OUTPUT_PATH = "src/test/resources/out.txt";

    ConsolePrinter printer = new ConsolePrinter();

    LinearEquationsController controller;

    @Test
    void example2_Stage2FromFile() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader(INPUT_PATH));
        controller.run();
        assertEquals("-1.0\n2.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void example_Stage3FromFile() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader(INPUT_PATH3));
        controller.run();
        assertEquals("1.0\n2.0\n3.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void example_Stage4FromFile() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader(INPUT_PATH4));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.NO_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenUniqueSolutionMatrixWithRedundantEquations_thenSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_unique_more_equs.txt"));
        controller.run();
        assertEquals("-19.0\n7.0\n1.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenLessEquationsThenVariablesValid_thenInfiniteSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_infinite_valid.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.INFINITE_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenSquareSmallerRank_thenInfiniteSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_infinite_square.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.INFINITE_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenSmallerRankMoreEquations_thenInfiniteSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_infinite_more.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.INFINITE_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenSmallerRankInvalid_thenNoSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_square_no.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.NO_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenZeroMatrix_thenInfiniteSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_zero.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.INFINITE_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @Test
    void whenZeroInhomogen_thenNoSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_zero_inhomogen.txt"));
        controller.run();
        assertEquals(PropertyManager.getProperty(PropertyManager.NO_SOLUTIONS_LABEL), new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }


    @Test
    void whenUnitHomogen_thenZeroSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_unit_homogen.txt"));
        controller.run();
        assertEquals("0.0\n0.0\n0.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }
}