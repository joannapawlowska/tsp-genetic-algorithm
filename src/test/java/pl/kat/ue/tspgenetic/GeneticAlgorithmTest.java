package pl.kat.ue.tspgenetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GeneticAlgorithmTest {

    @Test
    void shouldCalculateAssessment() {
        //GIVEN
        GeneticAlgorithm algorithm = new GeneticAlgorithm(new int[][]{{0, 2, 3}, {2, 0, 1}, {3, 1, 0}}, 2);
        Individual individual = new Individual();
        individual.setGenotype(new int[]{1, 0, 2});

        //WHEN
        int actualAssessment = algorithm.calculateAssessment(individual);

        //THEN
        Assertions.assertEquals(2 + 3 + 1, actualAssessment);
    }
}