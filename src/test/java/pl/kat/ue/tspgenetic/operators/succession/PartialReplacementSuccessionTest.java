package pl.kat.ue.tspgenetic.operators.succession;

import org.junit.jupiter.api.Test;
import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PartialReplacementSuccessionTest {

    @Test
    void shouldDetermineNextPopulationWithPartialReplacement() {
        //GIVEN
        double replacementRatio = 0.4;
        Individual i1 = new Individual(1, new int[3]);
        Individual i2 = new Individual(2, new int[3]);
        Individual i3 = new Individual(3, new int[3]);
        Individual i4 = new Individual(7, new int[3]);
        Individual i5 = new Individual(1, new int[3]);
        Individual i6 = new Individual(5, new int[3]);
        Population population = new Population(i1, i2, i3);
        Population evolvingPopulation = new Population(i4, i5, i6);

        //WHEN
        new PartialReplacementSuccession(replacementRatio).determineNext(population, evolvingPopulation);

        //THEN
        assertEquals(3, population.size());
        assertTrue(population.contains(i1));
        assertTrue(population.contains(i2));
        assertTrue(population.contains(i5));
    }
}