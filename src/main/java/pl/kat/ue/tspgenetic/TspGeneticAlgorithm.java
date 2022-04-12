package pl.kat.ue.tspgenetic;

import lombok.Getter;
import pl.kat.ue.tspgenetic.operators.crossover.Crossover;
import pl.kat.ue.tspgenetic.operators.mutation.Mutation;
import pl.kat.ue.tspgenetic.operators.selection.Selection;
import pl.kat.ue.tspgenetic.operators.succession.Succession;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;

public class TspGeneticAlgorithm {

    private final int populationSize;
    private final int[][] dataSet;
    private final int epochs;
    private final Population population;
    private final NavigableMap<Integer, Selection> selections;
    private final NavigableMap<Integer, Crossover> crossovers;
    private final NavigableMap<Integer, Mutation> mutations;
    private final Succession succession;
    @Getter
    private final Results results;

    public static TspGeneticAlgorithmBuilder builder() {
        return new TspGeneticAlgorithmBuilder();
    }

    TspGeneticAlgorithm(TspGeneticAlgorithmBuilder builder) {
        this.populationSize = builder.populationSize;
        this.dataSet = builder.dataSet;
        this.epochs = builder.epochs;
        this.mutations = builder.mutations;
        this.selections = builder.selections;
        this.crossovers = builder.crossovers;
        this.succession = builder.succession;
        this.population = generateRandomlyPopulation();
        this.results = new Results();
        this.results.best = getBest(population);
    }

    private Population generateRandomlyPopulation() {
        var population = new Population();
        for (int i = 0; i < populationSize; i++) {
            var individual = new Individual(dataSet.length);
            assess(individual);
            population.add(individual);
        }
        return population;
    }

    private Individual getBest(Population population) {
        return population.stream()
                .sorted(Comparator.naturalOrder())
                .findFirst()
                .get();
    }

    public void assess(Individual individual) {
        int assessment = 0;
        int[] genotype = individual.getGenotype();
        int genotypeLength = genotype.length;
        for (int gene = 0; gene < genotypeLength; gene++) {
            assessment += dataSet[genotype[gene]][genotype[(gene + 1) % genotypeLength]];
        }
        individual.setAssessment(assessment);
    }

    private void assesPopulation(Population population) {
        for (Individual individual : population) {
            if (individual.isNotAssessed()) {
                assess(individual);
                updateBest(individual);
            }
        }
    }

    private void updateBest(Individual individual) {
        Individual best = individual.compareTo(results.best) < 0 ? individual : results.best;
        results.bestInts.add(best.getAssessment());
        results.best = best;
    }

    public Results perform() {
        for (int epoch = 0; epoch < epochs; epoch++) {
            var evolutingPopulation = selection(epoch).select(population);
            crossover(epoch).cross(evolutingPopulation);
            mutation(epoch).mutate(evolutingPopulation);
            assesPopulation(evolutingPopulation);
            succession.determineNext(population, evolutingPopulation);
        }
        return results;
    }

    private Mutation mutation(int epoch) {
        return mutations.ceilingEntry(epoch).getValue();
    }


    private Selection selection(int epoch) {
        return selections.ceilingEntry(epoch).getValue();
    }


    private Crossover crossover(int epoch) {
        return crossovers.ceilingEntry(epoch).getValue();
    }

    @Getter
    public static class Results {

        private final List<Integer> bestInts = new ArrayList<>();
        private Individual best;

    }
}