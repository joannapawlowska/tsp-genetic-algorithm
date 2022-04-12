package pl.kat.ue.tspgenetic.operators.succession;//package pl.kat.ue.tspgenetic.operators.succession;

import pl.kat.ue.tspgenetic.Individual;
import pl.kat.ue.tspgenetic.Population;
import pl.kat.ue.tspgenetic.operators.GeneticOperator;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Succession implements GeneticOperator {

    public abstract void determineNext(Population population, Population evolutingPopulation);

    protected List<Individual> getBestIndividuals(Population population, int size) {
        return population.stream()
                .sorted(Individual::compareTo)
                .limit(size)
                .collect(Collectors.toList());
    }
}