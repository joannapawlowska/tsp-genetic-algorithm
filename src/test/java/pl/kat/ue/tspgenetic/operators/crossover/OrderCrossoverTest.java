package pl.kat.ue.tspgenetic.operators.crossover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Pair;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Arrays;

class OrderCrossoverTest {

    @Test
    void shouldCrossPair() {
        //GIVEN
        Individual i1 = new Individual(new int[]{0, 1, 2, 3, 4, 5, 6});
        Individual i2 = new Individual(new int[]{4, 5, 1, 0, 3, 2, 6});
        Pair<Individual> pair = new Pair<>(i1, i2);

        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {

            //WHEN
            mocked.when(() -> Random.nextDistinctInts(2, 6)).thenReturn(Arrays.asList(1, 3));
            Pair<Individual> crossed = new OrderCrossover(Random.nextDouble()).crossPair(pair);

            //THEN
            Assertions.assertArrayEquals(new int[]{6, 5, 2, 3, 4, 1, 0}, crossed.getFirst().getGenotype());
            Assertions.assertArrayEquals(new int[]{5, 6, 1, 0, 3, 2, 4}, crossed.getSecond().getGenotype());
        }
    }
}