package pl.kat.ue.tspgenetic.operators.mutation;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ReverseSequenceMutationTest {

    @Test
    void shouldMutateWhenBothCutPointsAreInsideGenotype() {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(2, 5));
            new ReverseSequenceMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertArrayEquals(new int[]{7, 4, 2, 0, 1, 3, 5, 6}, individual.getGenotype());

    }

    @Test
    void shouldMutateIndividualWhenLeftCutPointIsExtremeLeftAndRightCutPointIsInsideGenotype() {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(0, 4));
            new ReverseSequenceMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertArrayEquals(new int[]{0, 1, 3, 4, 7, 2, 5, 6}, individual.getGenotype());
    }

    @Test
    void shouldMutateWhenLeftCutPointIsInsideGenotypeAndRightCutPointIsExtremeRight() {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(2, 7));
            new ReverseSequenceMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertArrayEquals(new int[]{7, 4, 6, 5, 2, 0, 1, 3}, individual.getGenotype());
    }

    @Test
    void shouldMutateWhenCutPointsAreExtremeInGenotype() {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(0, 7));
            new ReverseSequenceMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertArrayEquals(new int[]{6, 5, 2, 0, 1, 3, 4, 7}, individual.getGenotype());
    }
}