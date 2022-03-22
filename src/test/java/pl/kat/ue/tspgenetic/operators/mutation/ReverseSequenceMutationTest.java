package pl.kat.ue.tspgenetic.operators.mutation;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseSequenceMutationTest {

    @Test
    void shouldMutateIndividual() {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(2, 5));
            new ReverseSequenceMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertEquals(2, individual.getGenotype()[2]);
        assertEquals(0, individual.getGenotype()[3]);
        assertEquals(1, individual.getGenotype()[4]);
        assertEquals(3, individual.getGenotype()[5]);
    }
}