package pl.kat.ue.tspgenetic.operators.selection;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.utils.WeightedCollection;

public class RouletteWheelSelection extends Selection {

    private WeightedCollection<Individual> weightedIndividuals;
    private int selectionSize;

    @Override
    public Population select(Population population) {
        selectionSize = population.size();
        fillCollectionWithWeightedIndividuals(population);
        return randomlySelectIndividuals();
    }

    private void fillCollectionWithWeightedIndividuals(Population population) {
        weightedIndividuals = new WeightedCollection<>();
        int max = getAssessmentMax(population);
        for (Individual individual : population) {
            double weight = max + 1.0 - individual.getAssessment();
            weightedIndividuals.add(weight, individual);
        }
    }

    private int getAssessmentMax(Population population) {
        int max = population.get(0).getAssessment();
        for (Individual individual : population) {
            if (individual.getAssessment() > max) {
                max = individual.getAssessment();
            }
        }
        return max;
    }

    private Population randomlySelectIndividuals() {
        Population population = new Population();
        while (population.size() != selectionSize) {
            population.add(weightedIndividuals.next().copy());
        }
        return population;
    }
}