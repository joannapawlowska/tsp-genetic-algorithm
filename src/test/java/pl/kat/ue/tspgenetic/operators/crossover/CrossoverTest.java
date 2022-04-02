package pl.kat.ue.tspgenetic.operators.crossover;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Pair;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Arrays;

class CrossoverTest {

    @Test
    void shouldCrossOneTimeGivenPopulation() {
        //GIVEN
        Population population = new Population(
                new Individual(new int[]{1, 2, 0}),
                new Individual(new int[]{0, 1, 2}),
                new Individual(new int[]{0, 2, 1})
        );
        Crossover crossover = new Crossover(0.5) {
            @Override
            public Pair<Individual> crossPair(Pair<Individual> pair) {
                pair.getFirst().setGenotype(new int[]{2, 1, 0});
                pair.getSecond().setGenotype(new int[]{2, 0, 1});
                return pair;
            }
        };

        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {

            //WHEN
            mocked.when(() -> Random.nextDistinctInts(2, 3)).thenReturn(Arrays.asList(1, 2));
            mocked.when(Random::nextDouble).thenReturn(0.3);
            crossover.cross(population, 1);

            //THEN
            Assertions.assertArrayEquals(new int[]{1, 2, 0}, population.get(0).getGenotype());
            Assertions.assertArrayEquals(new int[]{2, 1, 0}, population.get(1).getGenotype());
            Assertions.assertArrayEquals(new int[]{2, 0, 1}, population.get(2).getGenotype());
        }
    }
}