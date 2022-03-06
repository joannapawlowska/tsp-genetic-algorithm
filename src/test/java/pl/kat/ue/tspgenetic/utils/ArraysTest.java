package pl.kat.ue.tspgenetic.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ArraysTest {

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