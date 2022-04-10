package pl.kat.ue.tspgenetic.operators.crossover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Pair;
import pl.kat.ue.tspgenetic.utils.Random;

class CycleCrossoverTest {

    @Test
    void shouldCrossPairWhenCycleLengthIsLessThenGenotypeLength() {
        //GIVEN
        Individual i1 = new Individual(new int[]{0, 1, 2, 3, 4, 5, 6});
        Individual i2 = new Individual(new int[]{4, 5, 1, 0, 3, 2, 6});
        Pair<Individual> pair = new Pair<>(i1, i2);

        //WHEN
        Pair<Individual> crossed = new CycleCrossover(Random.nextDouble()).crossPair(pair);

        //THEN
        Assertions.assertArrayEquals(new int[]{0, 5, 1, 3, 4, 2, 6}, crossed.getFirst().getGenotype());
        Assertions.assertArrayEquals(new int[]{4, 1, 2, 0, 3, 5, 6}, crossed.getSecond().getGenotype());
    }

    @Test
    void shouldNotCrossPairWhenCycleIsOfGenotypeLength() {
        //GIVEN
        Individual i1 = new Individual(new int[]{0, 1, 2, 3, 4, 5, 6});
        Individual i2 = new Individual(new int[]{4, 5, 6, 1, 3, 2, 0});
        Pair<Individual> pair = new Pair<>(i1, i2);

        //WHEN
        Pair<Individual> crossed = new CycleCrossover(Random.nextDouble()).crossPair(pair);

        //THEN
        Assertions.assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6}, crossed.getFirst().getGenotype());
        Assertions.assertArrayEquals(new int[]{4, 5, 6, 1, 3, 2, 0}, crossed.getSecond().getGenotype());
    }

    @Test
    void shouldCrossPairForUnsortedGenotypes() {
        //GIVEN
        Individual i1 = new Individual(new int[]{0, 3, 1, 2, 4});
        Individual i2 = new Individual(new int[]{1, 2, 0, 4, 3});
        Pair<Individual> pair = new Pair<>(i1, i2);

        //WHEN
        Pair<Individual> crossed = new CycleCrossover(Random.nextDouble()).crossPair(pair);

        //THEN
        Assertions.assertArrayEquals(new int[]{0, 2, 1, 4, 3}, crossed.getFirst().getGenotype());
        Assertions.assertArrayEquals(new int[]{1, 3, 0, 2, 4}, crossed.getSecond().getGenotype());
    }
}