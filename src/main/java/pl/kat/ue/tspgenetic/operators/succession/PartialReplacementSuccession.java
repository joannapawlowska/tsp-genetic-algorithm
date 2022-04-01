package pl.kat.ue.tspgenetic.operators.succession;

import pl.kat.ue.tspgenetic.Population;

public class PartialReplacementSuccession extends Succession {

    private final double replacementRatio;

    public PartialReplacementSuccession(double replacementRatio) {
        this.replacementRatio = replacementRatio;
    }

    @Override
    public void determineNext(Population population, Population evolvingPopulation) {
        int size = population.size();
        int amountFromEvolving = (int) (size * replacementRatio);
        int amountFromOld = size - amountFromEvolving;

        var oldIndividuals = getBestIndividuals(population, amountFromOld);
        var newIndividuals = getBestIndividuals(evolvingPopulation, amountFromEvolving);

        population.clear();
        population.addAll(oldIndividuals);
        population.addAll(newIndividuals);
    }
}