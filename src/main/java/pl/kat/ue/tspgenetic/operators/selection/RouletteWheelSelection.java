package pl.kat.ue.tspgenetic.operators.selection;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.WeightedCollection;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouletteWheelSelection implements Selection {

    private WeightedCollection<Individual> weightedIndividuals;
    private int selectionSize;

    @Override
    public Population select(Population population) {
        selectionSize = population.size() / 2;
        fillCollectionWithWeightedIndividuals(population);
        return randomlySelectIndividuals();
    }

    private void fillCollectionWithWeightedIndividuals(Population population) {
        weightedIndividuals = new WeightedCollection<>();
        AssessmentMetadata metadata = getAssessmentMetadata(population);
        population.forEach(individual -> {
            double probability = (double) (metadata.max + 1 - individual.getAssessment()) / metadata.sum;
            weightedIndividuals.add(probability, individual);
        });
    }

    private AssessmentMetadata getAssessmentMetadata(Population population) {
        AssessmentMetadata metadata = new AssessmentMetadata();
        metadata.max = population.stream()
                .mapToInt(Individual::getAssessment)
                .peek(metadata::add)
                .max().orElse(0);
        return metadata;
    }

    private Population randomlySelectIndividuals() {
        return Stream.generate(weightedIndividuals::next)
                .limit(selectionSize)
                .map(Individual::copy)
                .collect(Collectors.toCollection(Population::new));
    }

    private class AssessmentMetadata {
        private int max;
        private int sum = 0;

        private void add(int assessment) {
            sum += assessment;
        }
    }
}