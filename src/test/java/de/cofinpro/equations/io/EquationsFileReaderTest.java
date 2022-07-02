package de.cofinpro.equations.io;

import de.cofinpro.equations.model.ExtendedCoefficientMatrix;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class EquationsFileReaderTest {

    private static final String inputPath = "src/test/resources/example_stage3.txt";
    private static final String wrongPath = "src/test/resources/mple_stage3.txt";

    private EquationsFileReader fileReader;

    @Test
    void whenWrongPath_constructorThrows() {
        assertThrows(IOException.class, () -> fileReader = new EquationsFileReader(wrongPath));
    }

    @Test
    void whenGoodPath_readMatrixWorks() throws IOException {
        fileReader = new EquationsFileReader(inputPath);
        ExtendedCoefficientMatrix extendedCoefficientMatrix = fileReader.readExtendedCoefficientMatrix();
        assertEquals(3, extendedCoefficientMatrix.getResultVector().length);
        assertArrayEquals(new double[] {9, 1, 0}, extendedCoefficientMatrix.getResultVector());
    }
}