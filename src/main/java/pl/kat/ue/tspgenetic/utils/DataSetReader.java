package pl.kat.ue.tspgenetic.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataSetReader {

    public static int[][] loadMatrix(String path) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            int numberOfRows = Integer.parseInt(reader.readLine());
            return createSymmetricSquareMatrix(numberOfRows, reader);

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static int[][] createSymmetricSquareMatrix(int numberOfRows, BufferedReader reader) throws IOException {
        int[][] matrix = createEmptySquareMatrix(numberOfRows);
        fillOutMatrix(reader, matrix);
        return matrix;
    }

    private static int[][] createEmptySquareMatrix(int size) {
        int[][] matrix = new int[size][];
        for (int i = 0; i < size; i++) {
            matrix[i] = new int[size];
        }
        return matrix;
    }

    private static void fillOutMatrix(BufferedReader reader, int[][] matrix) throws IOException {
        for (int i = 0; i < matrix.length; i++) {
            String[] row = reader.readLine().trim().split(" ");
            fillOutRowAndSymmetricallyColumn(matrix, row);
        }
    }

    private static void fillOutRowAndSymmetricallyColumn(int[][] matrix, String[] row) {
        int rowNumber = row.length - 1;
        for (int index = 0; index < rowNumber; index++) {
            int value = Integer.parseInt(row[index]);
            matrix[index][rowNumber] = value;
            matrix[rowNumber][index] = value;
        }
    }
}