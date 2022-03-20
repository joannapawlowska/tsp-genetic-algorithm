package pl.kat.ue.tspgenetic.utils;

import java.util.Random;

public class Arrays {

    private static final Random random = new Random();

    private Arrays() {
    }

    /**
     * Searches for given integer in an array between specified indices.
     *
     * @param from     the start index inclusive.
     * @param to       the end index inclusive.
     * @param array    the array which is searched.
     * @param searched the searched element.
     * @return index of the element found in the array; -1 otherwise.
     */
    public static int contains(int from, int to, int[] array, int searched) {
        int result = -1;
        for (int index = from; index <= to; index++) {
            if (array[index] == searched) {
                result = index;
                break;
            }
        }
        return result;
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