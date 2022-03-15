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
        int max = getAssessmentMax(population);
        population.forEach(individual -> {
            double weight = max + 1.0 - individual.getAssessment();
            weightedIndividuals.add(weight, individual);
        });
    }

    private int getAssessmentMax(Population population) {
        return population.stream()
                .mapToInt(Individual::getAssessment)
                .max().orElse(0);
    }

    private Population randomlySelectIndividuals() {
        return Stream.generate(weightedIndividuals::next)
                .limit(selectionSize)
                .map(Individual::copy)
                .collect(Collectors.toCollection(Population::new));
    }
}