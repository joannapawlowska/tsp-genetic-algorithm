package pl.kat.ue.tspgenetic.utils;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static pl.kat.ue.tspgenetic.utils.DataSetReader.loadMatrix;

class DataSetReaderTest {

    private final static String path = "src/main/resources/test-data-reader.txt";

    @BeforeAll
    static void initFile() throws IOException {
        String content = "4\n" +
                "0\n" +
                "1 0\n" +
                "4 2 0\n" +
                "7 2 5 0\n";
        Path path = Paths.get(DataSetReaderTest.path);
        Files.writeString(path, content);
    }

    @AfterAll
    static void deleteFile() throws IOException {
        Path path = Paths.get(DataSetReaderTest.path);
        Files.delete(path);
    }

    @Test
    void shouldLoadMatrixWhenFormatFileIsCorrect() {
        //GIVEN
        int[][] expectedDataset = new int[][]{{0, 1, 4, 7}, {1, 0, 2, 2}, {4, 2, 0, 5}, {7, 2, 5, 0}};

        //WHEN
        int[][] actualDataset = loadMatrix(path);

        //THEN
        Assertions.assertArrayEquals(expectedDataset, actualDataset);
    }
}