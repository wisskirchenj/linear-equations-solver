package de.cofinpro.equations;

import de.cofinpro.equations.config.PropertyManager;
import de.cofinpro.equations.controller.LinearEquationsController;
import de.cofinpro.equations.io.ConsolePrinter;
import de.cofinpro.equations.io.EquationsFileReader;
import de.cofinpro.equations.model.Complex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void example_Stage5() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_stage5.txt"));
        controller.run();
        Complex[] results = Arrays.stream((new String(Files.readAllBytes(Path.of(OUTPUT_PATH)))).split("\\n"))
                .map(Complex::parseOf).toArray(Complex[]::new);
        Complex[] expected = new Complex[] {new Complex(6.73, -22.9),
                new Complex(-1.79, 2),
                new Complex(15.6994, 7.396)};
        for (int i = 0; i < results.length; i++) {
            assertTrue(Math.abs(results[i].real() / expected[i].real() - 1) < 0.05);
            assertTrue(Math.abs(results[i].imaginary() / expected[i].imaginary() - 1) < 0.05);
        }
    }


    @Test
    void whenUniqueSolutionMatrixWithRedundantEquations_thenSolutionFound() throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader("src/test/resources/ex_unique_more_equs.txt"));
        controller.run();
        assertEquals("-19.0\n7.0\n1.0", new String(Files.readAllBytes(Path.of(OUTPUT_PATH))));
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/resources/ex_infinite_valid.txt",
            "src/test/resources/ex_infinite_square.txt",
            "src/test/resources/ex_infinite_more.txt",
            "src/test/resources/ex_zero.txt"
            })
    void whenLessSignificantEquationsThanVariablesValid_thenInfiniteSolutionFound(String inputPath) throws IOException {
        PropertyManager.getProperties().setProperty(PropertyManager.OUTPUT_FILE_OPTION, OUTPUT_PATH);
        controller = new LinearEquationsController(printer,  new EquationsFileReader(inputPath));
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