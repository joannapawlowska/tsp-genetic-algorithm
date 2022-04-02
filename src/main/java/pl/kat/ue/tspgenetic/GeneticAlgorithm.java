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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneticAlgorithm {

    private final int populationSize;
    private final int[][] dataSet;
    private final int epochs;
    private final Population population;
    private final Crossover crossover;
    private final NavigableMap<Integer, Mutation> mutations;
    private final Succession succession;
    private final Selection selection;
    @Getter
    private final Results results;

    public static GeneticAlgorithmBuilder builder() {
        return new GeneticAlgorithmBuilder();
    }

    GeneticAlgorithm(GeneticAlgorithmBuilder builder) {
        this.populationSize = builder.populationSize;
        this.dataSet = builder.dataSet;
        this.epochs = builder.epochs;
        this.mutations = builder.mutations;
        this.selection = builder.selection;
        this.crossover = builder.crossover;
        this.succession = builder.succession;
        this.population = generateRandomlyPopulation();
        this.results = new Results();
        this.results.best = getBest(population);
    }

    private Population generateRandomlyPopulation() {
        return Stream.generate(() -> new Individual(dataSet.length))
                .limit(populationSize)
                .peek(this::assess)
                .collect(Collectors.toCollection(Population::new));
    }

    private Individual getBest(Population population) {
        return population.stream().sorted(Comparator.naturalOrder())
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
        population.stream()
                .filter(Individual::isNotAssessed)
                .peek(this::assess)
                .forEach(this::updateBest);
    }

    private void updateBest(Individual individual) {
        Individual best = individual.compareTo(results.best) < 0 ? individual : results.best;
        results.bestInts.add(best.getAssessment());
        results.best = best;
    }

    public void perform() {
        for (int epoch = 1; epoch <= epochs; epoch++) {

            var evolutingPopulation = selection.select(population);
            crossover.cross(evolutingPopulation, epoch);
            mutation(epoch).mutate(evolutingPopulation);
            assesPopulation(evolutingPopulation);
            succession.determineNext(population, evolutingPopulation);

            if (epoch % 1000 == 0) {
                System.out.println(results.best);
            }
        }
    }

    private Mutation mutation(int epoch) {
        return mutations.ceilingEntry(epoch).getValue();
    }

    @Getter
    public static class Results {

        private final List<Integer> bestInts = new ArrayList<>();
        private Individual best;

        private void addNext(int best) {
            bestInts.add(best);
        }

        private void setBest(Individual best) {
            this.best = best;
        }
    }
}