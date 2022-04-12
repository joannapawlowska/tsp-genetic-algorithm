package pl.kat.ue.tspgenetic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.kat.ue.tspgenetic.operators.crossover.PartiallyMappedCrossover;
import pl.kat.ue.tspgenetic.operators.mutation.TworsMutation;
import pl.kat.ue.tspgenetic.operators.selection.TournamentSelection;
import pl.kat.ue.tspgenetic.operators.succession.PartialReplacementSuccession;
import pl.kat.ue.tspgenetic.utils.DataSetReader;

class TspGeneticAlgorithmTest {

    @Test
    void shouldCalculateAssessment() {
        //GIVEN
        TspGeneticAlgorithm algorithm = TspGeneticAlgorithm.builder()
                .dataSet(new int[][]{
                        {0, 2, 3, 7},
                        {2, 0, 1, 1},
                        {3, 1, 0, 4},
                        {7, 1, 4, 6}
                })
                .populationSize(2)
                .build();
        Individual individual = new Individual();
        individual.setGenotype(new int[]{3, 1, 0, 2});

        //WHEN
        algorithm.assess(individual);

        //THEN
        Assertions.assertEquals(1 + 2 + 3 + 4, individual.getAssessment());
    }

    @Test
    void shouldPerformAlgorithm() {
        //GIVEN
        TspGeneticAlgorithm algorithm = TspGeneticAlgorithm.builder()
                .dataSet(DataSetReader.loadMatrix("./src/test/resources/dataset.txt"))
                .populationSize(5)
                .epochs(100)
                .selection(new TournamentSelection(2)).performUpToEpoch(100)
                .crossover(new PartiallyMappedCrossover(0.75)).performUpToEpoch(100)
                .mutation(new TworsMutation(0.05)).performUpToEpoch(100)
                .succession(new PartialReplacementSuccession(0.7))
                .build();

        //WHEN
        Individual best = algorithm.perform().getBest();

        //THEN
        Assertions.assertNotNull(best);
    }
}