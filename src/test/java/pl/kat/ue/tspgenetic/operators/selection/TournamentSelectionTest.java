package pl.kat.ue.tspgenetic.operators.selection;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TournamentSelectionTest {

    @Test
    void shouldSelectWithTournament() {
        //GIVEN
        Individual i1 = new Individual(1, new int[3]);
        Individual i2 = new Individual(2, new int[3]);
        Individual i3 = new Individual(3, new int[3]);
        Population population = new Population(i1, i2, i3);
        int selectivePressure = 2;

        try (MockedStatic<Random> mocked = Mockito.mockStatic(Random.class)) {

            //WHEN
            mocked.when(() -> Random.nextInt(population.size())).thenReturn(0, 2, 0, 1, 1, 2);
            Population actual = new TournamentSelection(selectivePressure).select(population);

            //THEN
            assertEquals(population.size(), actual.size());
            assertEquals(i1.getAssessment(), actual.get(0).getAssessment());
            assertEquals(i1.getAssessment(), actual.get(1).getAssessment());
            assertEquals(i2.getAssessment(), actual.get(2).getAssessment());
        }
    }
}