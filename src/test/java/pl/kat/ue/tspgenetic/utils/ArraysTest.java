package pl.kat.ue.tspgenetic.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ArraysTest {

    @ParameterizedTest
    @CsvSource({"0,1,3,0", "0,4,2,1", "1,3,4,3"})
    void shouldCheckThatArrayContainElementBetweenGivenIndices(int from, int to, int searched, int expected) {
        //GIVEN
        int[] array = {3, 2, 1, 4, 0};

        //WHEN
        int actual = pl.kat.ue.tspgenetic.utils.Arrays.contains(from, to, array, searched);

        //THEN
        assertEquals(expected, actual);
    }


    @ParameterizedTest
    @CsvSource({"0,1,4", "1,4,3", "1,3,0"})
    void shouldCheckThatArrayNotContainElementBetweenGivenIndices(int from, int to, int searched) {
        //GIVEN
        int[] array = {3, 2, 1, 4, 0};

        //WHEN
        int actual = pl.kat.ue.tspgenetic.utils.Arrays.contains(from, to, array, searched);

        //THEN
        assertEquals(-1, actual);
    }


    @Test
    void shouldShuffleArray() {
        //GIVEN
        int[] array = {1, 2, 3, 4, 5};

        //WHEN
        pl.kat.ue.tspgenetic.utils.Arrays.shuffle(array);

        //THEN
        assertFalse(java.util.Arrays.equals(new int[]{1, 2, 3, 4, 5}, array));
    }
}