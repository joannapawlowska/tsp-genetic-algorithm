package pl.kat.ue.tspgenetic.utils;

import java.util.Random;

public class Arrays {

    private static final Random random = new Random();

    private Arrays() {
    }

    public static void shuffle(int[] array) {
        for (int index = array.length - 1; index > 0; index--) {
            int randomIndex = pickRandomIndexFrom0To(index);
            swapRandomIndexWithElementAtPosition(randomIndex, index, array);
        }
    }

    private static int pickRandomIndexFrom0To(int i) {
        return random.nextInt(i + 1);
    }

    private static void swapRandomIndexWithElementAtPosition(int randomIndex, int index, int[] array) {
        int temp = array[index];
        array[index] = array[randomIndex];
        array[randomIndex] = temp;
    }
}