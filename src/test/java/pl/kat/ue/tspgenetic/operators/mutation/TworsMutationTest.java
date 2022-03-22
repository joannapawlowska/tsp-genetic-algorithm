package pl.kat.ue.tspgenetic.operators.mutation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.utils.Random;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TworsMutationTest {

    @ParameterizedTest
    @CsvSource({"3,6,5,1", "0,2,3,7", "5,7,6,2"})
    void shouldMutateIndividual(int index1, int index2, int expected1, int expected2) {
        //GIVEN
        Individual individual = new Individual(1, new int[]{7, 4, 3, 1, 0, 2, 5, 6});

        //WHEN
        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {
            mocked.when(() -> Random.nextDistinctInts(2, 8))
                    .thenReturn(Arrays.asList(index1, index2));
            new TworsMutation(0.05).mutateIndividual(individual);
        }

        //THEN
        assertEquals(expected1, individual.getGenotype()[index1]);
        assertEquals(expected2, individual.getGenotype()[index2]);
    }
}