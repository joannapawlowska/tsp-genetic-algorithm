package pl.kat.ue.tspgenetic.operators.succession;

import pl.kat.ue.tspgenetic.Population;

public class ElitismSuccession extends Succession {

    @Override
    public void determineNext(Population population, Population evolutingPopulation) {
        int size = population.size();
        evolutingPopulation.addAll(population);
        population.clear();
        population.addAll(getBestIndividuals(evolutingPopulation, size));
    }
}